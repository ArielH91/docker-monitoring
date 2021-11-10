package com.docker.dockermonitoring;
import com.docker.dockermonitoring.model.DockerContainersMonitoringService;
import com.docker.dockermonitoring.model.DockerMonitoringInterface;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@EnableAsync
@SpringBootApplication
public class DockerMonitoringApplication {

    @Autowired
    DockerContainersMonitoringService updateStatus;
    @Autowired
    DockerMonitoringInterface monitoringInterface;

    public DockerMonitoringApplication(ConfigurableApplicationContext context) {
        monitoringInterface = context.getBean(DockerMonitoringInterface.class);
        updateStatus = context.getBean(DockerContainersMonitoringService.class);
    }

    public void run(){

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<?> future = executor.submit(() -> {
            monitoringInterface.run();
        });
        executor.submit(() -> {
            while (!future.isDone()) {
                updateStatus.runService();
            }
        });



        executor.shutdown();
    }

    public static void main(String[] args) {


        ConfigurableApplicationContext context = SpringApplication.run(DockerMonitoringApplication.class, args);

        DockerMonitoringApplication dockerMonitoringApplication = new DockerMonitoringApplication(context);

        dockerMonitoringApplication.run();

    }
}
