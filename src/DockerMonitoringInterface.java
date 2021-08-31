import java.util.*;
import java.util.stream.Collectors;

public class DockerMonitoringInterface {
    final String wrongValueMessage = "Wrong value, try again";
    final String[] statuses = {"EXITED", "UP"};

    public void run() {
        try {
            Scanner scan = new Scanner(System.in);
            String choose;

            do {
                System.out.println("Hi,What filter you'd like to apply? 1.ContainerId 2.Image 3.ContainerName 0.Exit");
                choose = scan.nextLine();
                if (choose.equals("1")) {
                    System.out.println("Enter Container Id");
                   String id = scan.nextLine();
                    if (DockerContainersMonitoringService.containerDataHashMapId.get(id) != null) {
                        System.out.println(DockerContainersMonitoringService.containerDataHashMapId.get(id));
                    } else {
                        System.out.println(wrongValueMessage);
                    }
                }
                if (choose.equals("2")) {
                    System.out.println("Enter Status");
                    String status = scan.nextLine().toUpperCase(Locale.ROOT);
                    if (Arrays.asList(statuses).contains(status)) {
                        List<DockerContainerData> result = DockerContainersMonitoringService.containerDataHashMapId.values().stream()
                                .filter(data -> data.getContainerStatus().toUpperCase(Locale.ROOT).contains(status))
                                .collect(Collectors.toList());
                        System.out.println(result);
                    } else {
                        System.out.println(wrongValueMessage);
                    }
                }
                if (choose.equals("3")) {
                    System.out.println("Enter Container Name");
                    String name = scan.nextLine();
                        List<DockerContainerData> result2 = DockerContainersMonitoringService.containerDataHashMapId.values().stream()
                                .filter(data -> data.getContainerName().equals(name))
                                .collect(Collectors.toList());
                    if(!result2.toString().contains("[]")){
                        System.out.println(result2);
                    } else {
                        System.out.println(wrongValueMessage);
                    }
                }
            } while (!Objects.equals(choose, "0"));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}