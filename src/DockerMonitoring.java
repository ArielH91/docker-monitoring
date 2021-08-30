import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DockerMonitoring {


    public static void main(String[] args) {


        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {

            DockerContainers containers = new DockerContainers();
            containers.run();
        });
        executor.execute(() -> {

            DockerMonitoringInterface monitoringInterface = new DockerMonitoringInterface();
            monitoringInterface.run();
        });

        executor.shutdown();
    }
}
