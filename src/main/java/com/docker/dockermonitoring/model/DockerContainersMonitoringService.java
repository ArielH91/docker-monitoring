package com.docker.dockermonitoring.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Component
public class DockerContainersMonitoringService {

    private final DockerContainerRepository repository;

    @Autowired
    public DockerContainersMonitoringService(DockerContainerRepository repository) {
        this.repository = repository;
    }

    public void runService() {

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

                repository.save(data);
            }
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }
}
