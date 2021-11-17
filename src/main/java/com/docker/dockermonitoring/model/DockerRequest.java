package com.docker.dockermonitoring.model;

import com.docker.dockermonitoring.dto.DataForCreateContainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "DockerRequest")
public class DockerRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String containerName;
    private String containerPort;
    private String containerImage;
    private String requestStatus;
    private LocalDateTime createDate;

    private String action;


    public static DockerRequest getInstance(DataForCreateContainer dockerContainer, String action) {
        return new DockerRequest( 0,
                dockerContainer.getContainerName(), dockerContainer.getContainerPort(),dockerContainer.getContainerImage(), "p", LocalDateTime.now(), action);
    }
}
