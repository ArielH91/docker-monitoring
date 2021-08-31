import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DockerMonitoring {


    public static void main(String[] args) {


        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {

            DockerContainersMonitoringService containersMonitoringService = new DockerContainersMonitoringService();
            while (true) {
                containersMonitoringService.run();

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.execute(() -> {

            DockerMonitoringInterface monitoringInterface = new DockerMonitoringInterface();
            monitoringInterface.run();
        });

        executor.shutdown();
    }
}
