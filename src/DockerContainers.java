import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class DockerContainers {

    public static HashMap<String, DockerContainerData> containerDataHashMapId = new HashMap<>();
    public static HashMap<String, DockerContainerData> containerDataHashMapName = new HashMap<>();
    public static DockerContainerData data;

    public void run(){


        try {
            Runtime runtime = Runtime.getRuntime();
            String terminal = "docker ps -a";
            Process process = runtime.exec(terminal);


            BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

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

                containerDataHashMapId.put(data.getContainerId(),data);
                containerDataHashMapName.put(data.getContainerName(),data);
            }

        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
