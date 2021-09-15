package com.docker.dockermonitoring.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "containers")
public class DockerContainerData extends DockerContainersMonitoringService {
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

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public void setContainerStatus(String containerStatus) {
        this.containerStatus = containerStatus;
    }

    public String getContainerPorts() {
        return containerPorts;
    }

    public void setContainerPorts(String containerPorts) {
        this.containerPorts = containerPorts;
    }

    public String getContainerImage() {
        return containerImage;
    }

    public void setContainerImage(String containerImage) {
        this.containerImage = containerImage;
    }

    public String getContainerId() {
        return containerId;
    }

    public String getContainerName() {
        return containerName;
    }

    public String getContainerStatus() {
        return containerStatus;
    }
}
