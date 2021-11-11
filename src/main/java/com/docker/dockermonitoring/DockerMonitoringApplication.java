package com.docker.dockermonitoring;

import com.docker.dockermonitoring.model.DockerContainersMonitoringService;
import com.docker.dockermonitoring.model.ScheduledRequestOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@EnableAsync
@SpringBootApplication
public class DockerMonitoringApplication {

    DockerContainersMonitoringService updateStatus;
    ScheduledRequestOperator scheduledRequestOperator;

    public DockerMonitoringApplication(ConfigurableApplicationContext context) {
        updateStatus = context.getBean(DockerContainersMonitoringService.class);
        scheduledRequestOperator = context.getBean(ScheduledRequestOperator.class);
    }

    public void run() {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            while (true) {
                Thread.sleep(5000);
                updateStatus.runService();

            }
        });
        executor.submit(() -> {
            while (true) {
                Thread.sleep(5000);
                scheduledRequestOperator.handleRequest();
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
