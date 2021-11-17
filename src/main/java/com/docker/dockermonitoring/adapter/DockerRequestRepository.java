package com.docker.dockermonitoring.adapter;

import com.docker.dockermonitoring.model.DockerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DockerRequestRepository extends JpaRepository<DockerRequest, Integer> {

    List<DockerRequest> findByRequestStatus(String requestStatus);
    DockerRequest save(DockerRequest request);
}
