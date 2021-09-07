import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class DockerMonitoringInterface {

    final String wrongValueMessage = "Wrong value, try again";
    final String wrongNameMessage = "Wrong name or container not exists, try again";
    final String[] statuses = {"EXITED", "UP"};
    private final String[] options = {"0", "1", "2", "3"};
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
            } while (!Objects.equals(choose, options[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findContainerById() {
        System.out.println("Enter Container Id");
        String id = scanner.nextLine();
        if (DockerContainersMonitoringService.containerDataHashMapId.get(id) != null) {
            System.out.println(DockerContainersMonitoringService.containerDataHashMapId.get(id));
            findContainerDetails(id);
        } else {
            System.out.println(wrongValueMessage);
        }
    }

    public void findContainerByStatus() {
        System.out.println("Enter Status");
        String status = scanner.nextLine().toUpperCase(Locale.ROOT);
        if (Arrays.asList(statuses).contains(status)) {
            List<DockerContainerData> result = DockerContainersMonitoringService.containerDataHashMapId.values().stream()
                    .filter(data -> data.getContainerStatus().toUpperCase(Locale.ROOT).contains(status))
                    .collect(Collectors.toList());
            System.out.println(result);
            System.out.println();
        } else {
            System.out.println(wrongValueMessage);
        }
    }

    public void findContainerByName() {
        System.out.println("Enter Container Name");
        String name = scanner.nextLine();
        List<DockerContainerData> result2 = DockerContainersMonitoringService.containerDataHashMapId.values().stream()
                .filter(data -> data.getContainerName().equals(name))
                .collect(Collectors.toList());
        if (!result2.isEmpty()) {
            System.out.println(result2);
            findContainerDetails(name);

        } else {
            System.out.println(wrongNameMessage);
        }
    }

    private void findContainerDetails(String container) {


        try {
            Runtime runtime1 = Runtime.getRuntime();
            String terminal1 = "docker container inspect --format='{{.Platform}},{{.NetworkSettings.Networks.bridge.NetworkID}},{{.Config.Volumes}},{{.Config.Hostname}},{{.Path}}'";

            Process process1 = runtime1.exec(terminal1 + " " + container);

            BufferedReader processOutput1 = new BufferedReader(new InputStreamReader(process1.getInputStream()));

            String line;

            line = processOutput1.readLine();

            String[] containerDetails = line.substring(1, line.length() -1).split(",");
            DockerContainerDetails details;

            details = new DockerContainerDetails(containerDetails[1], containerDetails[2], containerDetails[0], containerDetails[3], containerDetails[4]);

            System.out.println(details);

        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}