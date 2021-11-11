package com.docker.dockermonitoring.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DockerRequestRepository extends JpaRepository<DockerRequest, Integer> {

    List<DockerRequest> findByRequestStatus(String requestStatus);
    DockerRequest save(DockerRequest request);
}
