package com.docker.dockermonitoring.adapter;

import com.docker.dockermonitoring.model.DockerContainerData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public
interface DockerContainerRepository extends JpaRepository<DockerContainerData, String> {

    List<DockerContainerData> findAll();

    Page<DockerContainerData> findAll(Pageable page);

    Optional<DockerContainerData> findByContainerId(String containerId);

    List<DockerContainerData> findByContainerStatusContainingIgnoreCase(String containerStatus);

    Optional<DockerContainerData> findByContainerName(String containerName);

    List<DockerContainerData> findByContainerPortsContainingIgnoreCase(String containerPorts);

    List<DockerContainerData> findByContainerImageContainingIgnoreCase(String containerImage);

    DockerContainerData save(DockerContainerData data);
}
