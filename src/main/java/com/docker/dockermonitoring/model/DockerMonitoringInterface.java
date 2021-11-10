package com.docker.dockermonitoring.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DockerMonitoringInterface {

    @Autowired
    private DockerContainerRepository repository;
    final String wrongValueMessage = "Wrong value, try again";
    final String wrongNameMessage = "Wrong name or container not exists, try again";
    final String wrongImageMessage = "Wrong image name or containers on this image not exists, try again";
    final String[] statuses = {"EXITED", "UP"};
    private final String[] options = {"0", "1", "2", "3", "4", "5"};
    public Scanner scanner = new Scanner(System.in);

    public void run() {

        String choose;
        try {
            do {
                System.out.println("Hi,What filter you'd like to apply? 1.ContainerId 2.ContainerStatus 3.ContainerName 0.Exit");
                choose = scanner.nextLine();
                if (Arrays.stream(options).noneMatch(choose::equals)) {
                    System.out.println("Option " + "'" + choose + "'" + " does not exist");
                }
                if (choose.equals(options[1])) {
                    findContainerById();
                }
                if (choose.equals(options[2])) {
                    findContainerByStatus();
                }
                if (choose.equals(options[3])) {
                    findContainerByName();
                }
                if (choose.equals(options[4])) {
                    findContainerByPorts();
                }
                if (choose.equals(options[5])) {
                    findContainersByImage();
                }
            } while (!Objects.equals(choose, options[0]));
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    public void findContainerById() {
        System.out.println("Enter Container Id");
        String id = scanner.nextLine();
        Optional<DockerContainerData> containerData = repository.findByContainerId(id);
        if (containerData.isPresent()) {
            System.out.println(containerData.get());
        } else {
            System.out.println(wrongValueMessage);
        }
    }

    public void findContainerByStatus() {
        System.out.println("Enter Status");
        String status = scanner.nextLine().toUpperCase(Locale.ROOT);
        if (Arrays.asList(statuses).contains(status)) {
            List<DockerContainerData> containersStatus = repository.findByContainerStatusContainingIgnoreCase(status);
            System.out.println(containersStatus);
        } else {
            System.out.println(wrongValueMessage);
        }
    }

    public void findContainerByName() {
        System.out.println("Enter Container Name");
        String name = scanner.nextLine();
        Optional<DockerContainerData> containerName = repository.findByContainerName(name);
        if (containerName.isPresent()) {
            System.out.println(containerName);

        } else {
            System.out.println(wrongNameMessage);
        }
    }

    public void findContainerByPorts() {
        System.out.println("Enter Ports");
        String ports = scanner.nextLine().toUpperCase(Locale.ROOT);
        List<DockerContainerData> containerData = repository.findByContainerPortsContainingIgnoreCase(ports);
        if (!containerData.isEmpty()) {
            System.out.println(containerData);
        } else {
            System.out.println(wrongValueMessage);
        }
    }

        public void findContainersByImage () {
            System.out.println("Enter Containers Image");
            String image = scanner.nextLine();
            List<DockerContainerData> containersImage = repository.findByContainerImageContainingIgnoreCase(image);
            if (!containersImage.isEmpty()) {
                System.out.println(containersImage);
            } else {
                System.out.println(wrongImageMessage);
            }
        }
    }
