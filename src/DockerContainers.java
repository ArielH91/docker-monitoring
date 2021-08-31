import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;

public class DockerContainers {

    public static HashMap<String, DockerContainerData> containerDataHashMapId = new HashMap<>();
    public static HashMap<String, DockerContainerData> containerDataHashMapName = new HashMap<>();
    public static DockerContainerData data;
    public String id;
    public String name;
    public String status;
    public String image;
    public String ports;

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
                DockerContainerData data = new DockerContainerData( id, name, status, ports, image);

                data.containerId = containerData[0];
                data.containerImage = containerData[1];
                data.containerStatus = containerData[4].toUpperCase(Locale.ROOT);
                if(containerData.length > 6) {
                    data.containerPorts = containerData[5];
                    data.containerName = containerData[6];
                } else {
                    data.containerPorts = "None";
                    data.containerName = containerData[5];
                }

                containerDataHashMapId.put(data.getContainerId(),data);
                containerDataHashMapName.put(data.getContainerName(),data);

            }
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
