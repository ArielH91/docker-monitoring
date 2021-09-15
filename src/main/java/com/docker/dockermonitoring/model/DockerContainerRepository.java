package com.docker.dockermonitoring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface DockerContainerRepository extends JpaRepository<DockerContainerData, String> {

}
