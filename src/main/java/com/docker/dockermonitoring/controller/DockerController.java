package com.docker.dockermonitoring.controller;

import com.docker.dockermonitoring.model.DockerContainerData;
import com.docker.dockermonitoring.model.DockerContainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class DockerController {
    public static final Logger logger = LoggerFactory.getLogger(DockerController.class);
    private final DockerContainerRepository repository;

    public DockerController(DockerContainerRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/containers", params = {"!sort", "!page", "!size"})
    public ResponseEntity<List<DockerContainerData>> readAllContainers() {
        logger.warn("Exposing all containers");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/containers")
    public ResponseEntity<List<DockerContainerData>> readAllContainers(Pageable page) {
        logger.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping("container/searchById/{containerId}")
    public ResponseEntity<DockerContainerData> searchById(@PathVariable String containerId) {
        return repository.findByContainerId(containerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("container/searchByName/{containerName}")
    public ResponseEntity<DockerContainerData> searchByName(@PathVariable String containerName) {
        return repository.findByContainerName(containerName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("container/searchByStatus/{containerStatus}")
    public List<DockerContainerData> searchByStatus(@PathVariable String containerStatus) {
        return repository.findByContainerStatusContainingIgnoreCase(containerStatus);
    }

    @GetMapping("container/searchByPorts/{containerPorts}")
    public List<DockerContainerData> searchByPorts(@PathVariable String containerPorts) {
        return repository.findByContainerPortsContainingIgnoreCase(containerPorts);
    }

    @GetMapping("container/searchByPorts/{containerImage}")
    public List<DockerContainerData> searchByImage(@PathVariable String containerImage) {
        return repository.findByContainerImageContainingIgnoreCase(containerImage);
    }
}