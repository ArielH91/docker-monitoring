package com.docker.dockermonitoring.controller;

import com.docker.dockermonitoring.dto.DataForCreateContainer;
import com.docker.dockermonitoring.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DockerController {
    public static final Logger logger = LoggerFactory.getLogger(DockerController.class);
    private final DockerContainerRepository repository;
    private final DockerRequestRepository requestRepository;

    public DockerController(DockerContainerRepository repository, DockerRequestRepository requestRepository) {
        this.repository = repository;
        this.requestRepository = requestRepository;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<DockerRequest> createDocker (@RequestBody @Valid DataForCreateContainer dockerContainer) {
        DockerRequest createContainer = requestRepository.save(DockerRequest.getInstance(dockerContainer, ScheduledRequestOperator.RequestAction.CREATE.getAction()));
        return ResponseEntity.ok(createContainer);
    }

    @PatchMapping(value = "/start")
    public ResponseEntity<DockerRequest> startDocker (@RequestBody @Valid DataForCreateContainer dockerContainer) {
        DockerRequest createContainer = requestRepository.save(DockerRequest.getInstance(dockerContainer, ScheduledRequestOperator.RequestAction.START.getAction()));
        return ResponseEntity.ok(createContainer);
    }

    @PatchMapping(value = "/stop")
    public ResponseEntity<DockerRequest> stopDocker (@RequestBody @Valid DataForCreateContainer dockerContainer) {
        DockerRequest createContainer = requestRepository.save(DockerRequest.getInstance(dockerContainer, ScheduledRequestOperator.RequestAction.STOP.getAction()));
        return ResponseEntity.ok(createContainer);
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<DockerRequest> deleteDocker (@RequestBody @Valid DataForCreateContainer dockerContainer) {
        DockerRequest createContainer = requestRepository.save(DockerRequest.getInstance(dockerContainer, ScheduledRequestOperator.RequestAction.REMOVE.getAction()));
        return ResponseEntity.ok(createContainer);
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