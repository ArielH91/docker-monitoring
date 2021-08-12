import java.io.*;

public class Docker {
    public static void main(String[] args) {


        try {
            Runtime runtime = Runtime.getRuntime();
            String terminal = "docker ps -a";
            Process process = runtime.exec(terminal);


            BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter processInput = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));


            int lineCounter = 0;
            String line;


            while ((line = processOutput.readLine()) != null) {
                lineCounter++;
                if (lineCounter == 1) continue;
                while (line.contains("   ")) {
                    line = line.replace("   ", "  ");
                }

                String[] containerData = line.split("  ");
                DockerContainerData data = new DockerContainerData();

                data.setContainerId(containerData[0]);
                data.setContainerImage(containerData[1]);
                data.setContainerStatus(containerData[4]);
                if(containerData.length > 6) {
                    data.setContainerPorts(containerData[5]);
                    data.setContainerName(containerData[6]);
                } else {
                    data.setContainerPorts("None");
                    data.setContainerName(containerData[5]);
                }


                System.out.println(data.toString());
            }

        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
