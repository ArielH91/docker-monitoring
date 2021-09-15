package com.docker.dockermonitoring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface DockerContainerRepository extends JpaRepository<DockerContainerData, String> {
}
