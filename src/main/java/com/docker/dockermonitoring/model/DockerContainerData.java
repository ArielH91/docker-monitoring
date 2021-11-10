package com.docker.dockermonitoring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "CONTAINERS")
public class DockerContainerData {

    @Id
    private String containerId;
    private String containerName;
    private String containerStatus;
    private String containerPorts;
    private String containerImage;

    public String toString() {
        return String.format(
                "ContainerData[ id='%s', name='%s', status='%s', ports='%s', image='%s']",
                containerId, containerName, containerStatus, containerPorts, containerImage);
    }
}
