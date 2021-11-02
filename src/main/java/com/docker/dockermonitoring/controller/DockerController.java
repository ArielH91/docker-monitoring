package com.docker.dockermonitoring.controller;

import com.docker.dockermonitoring.model.DockerContainerData;
import com.docker.dockermonitoring.model.DockerContainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController

public class DockerController {
    public static final Logger logger = LoggerFactory.getLogger(DockerController.class);
    private final DockerContainerRepository repository;

    public DockerController(DockerContainerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/dockerContainerDatas")
    ResponseEntity<DockerContainerData> createContainer(@RequestBody DockerContainerData containerData) {
        DockerContainerData result = repository.save(containerData);
        return ResponseEntity.created(URI.create("/" + result.getContainerId())).body(result);
    }

    @GetMapping(value = "/dockerContainerDatas", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<DockerContainerData>> readAllContainers() {
        logger.warn("Exposing all containers");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/dockerContainerDatas")
    ResponseEntity<List<DockerContainerData>> readAllContainers(Pageable page) {
        logger.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping("dockerContainerDatas/{containerId}")
    ResponseEntity<DockerContainerData> readContainer(@PathVariable String containerId) {
        return repository.findById(containerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("dockerContainerDatas/{containerId}")
    ResponseEntity<?> updateContainer(@PathVariable String containerId, @RequestBody DockerContainerData toUpdate) {
        if (!repository.existsById(containerId)) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setContainerId(containerId);
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }

}