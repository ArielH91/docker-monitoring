package com.docker.dockermonitoring;

import com.docker.dockermonitoring.model.DockerContainersMonitoringService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableAsync
@SpringBootApplication
public class DockerMonitoringApplication {

    DockerContainersMonitoringService updateStatus;

    public DockerMonitoringApplication(ConfigurableApplicationContext context) {
        updateStatus = context.getBean(DockerContainersMonitoringService.class);
    }

    public void run() {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            while (true) {
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
