import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class DockerContainersMonitoringService {

    public static HashMap<String, DockerContainerData> containerDataHashMapId = new HashMap<>();

    public void run() {

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
                String[] containerData = line.split(" {2}");
                DockerContainerData data;

                if (containerData.length > 6) {
                    data = new DockerContainerData(containerData[0], containerData[6], containerData[4], containerData[5], containerData[1]);
                } else {
                    data = new DockerContainerData(containerData[0], containerData[5], containerData[4], "None", containerData[1]);
                }
                containerDataHashMapId.put(data.getContainerId(), data);
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
