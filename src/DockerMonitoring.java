import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DockerMonitoring {


    public static void main(String[] args) {


        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {

            DockerContainersMonitoringService containers = new DockerContainersMonitoringService();
            while(true) {
                containers.run();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.execute(() -> {

            DockerMonitoringInterface monitoringInterface = new DockerMonitoringInterface();
            while (true) {
                monitoringInterface.run();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.shutdown();
    }
}
