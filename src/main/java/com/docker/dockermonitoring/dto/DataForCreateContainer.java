package com.docker.dockermonitoring.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataForCreateContainer {
    private String containerName;
    private String containerPort;
    private String containerImage;

}
