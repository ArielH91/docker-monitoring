import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DockerMonitoring {


    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<?> future = executor.submit(() -> {

            DockerMonitoringInterface monitoringInterface = new DockerMonitoringInterface();
            monitoringInterface.run();
        });

        executor.execute(() -> {

            DockerContainersMonitoringService containersMonitoringService = new DockerContainersMonitoringService();

                while (!future.isDone()) {
                    containersMonitoringService.run();
                }

        });

        executor.shutdown();

    }
}
