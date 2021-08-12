import java.io.*;

public class Docker {
    public static void main(String[] args){


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

               String line1 = line.trim().replaceAll("     ","  ");
               String line2 = line1.trim().replaceAll("    ", "  ");
                String[] containerData = line2.trim().replaceAll("   ","  ").split("  |\\n");
                DockerContainerData data = new DockerContainerData();

                data.setContainerId(containerData[0]);
                data.setContainerName(containerData[6]);
                data.setContainerStatus(containerData[4]);
                data.setContainerImage(containerData[1]);
                data.setContainerPorts(containerData[5]);

                System.out.println(data.toString());
            }

        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
