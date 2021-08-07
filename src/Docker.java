import java.io.*;

public class Docker {
    public static void main(String[] args){
        String line;

        try {
            Runtime runtime = Runtime.getRuntime();
            String terminal = "docker ps -a";
            Process process = runtime.exec(terminal);


            BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter processInput = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

            int lineCounter = 0;

            while ((line = processOutput.readLine()) != null) {
                processOutput.toString();
                lineCounter++;
                if (lineCounter == 1) continue;

                /*line.split("\n");*/
                String[] containerData = new String[8];
                containerData = line.split("  |\\n");

                DockerContainerData data = new DockerContainerData();
                data.setContainerId(containerData[0]);
                data.setContainerName(containerData[7]);
                data.setContainerStatus(containerData[4]);
                data.setContainerPorts(containerData[5]);
                data.setContainerImage(containerData[1]);


                System.out.print(data.getContainerId() + " ");
                System.out.print(data.getContainerName() + " ");
                System.out.print(data.getContainerStatus() + " ");
                System.out.print(data.getContainerPorts() + " ");
                System.out.println(data.getContainerImage());
            }

            processInput.close();
            processOutput.close();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
