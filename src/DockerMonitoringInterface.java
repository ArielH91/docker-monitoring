import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DockerMonitoringInterface {
    public void run(){
        try {
            Scanner scan = new Scanner(System.in);
            String choose;
            String id;
            String status;
            String name;

            do {
                System.out.println("Hi,What filter you'd like to apply? 1.ContainerId 2.Image 3.ContainerName 0.Exit");
                choose = scan.nextLine();
                if (choose.equals("1")) {
                    System.out.println("Enter Container Id");
                    id = scan.nextLine();
                    if(DockerContainers.containerDataHashMapId.get(id) != null) {
                        System.out.println(DockerContainers.containerDataHashMapId.get(id));
                    }else{
                        System.out.println("Wrong value, try again");
                    }
                }
                if (choose.equals("2")) {
                    System.out.println("Enter Status");
                    status = scan.nextLine().toUpperCase(Locale.ROOT);
                    if (status.equals("EXITED")) {
                        List result = DockerContainers.containerDataHashMapId.values().stream()
                                .filter(data -> data.getContainerStatus().contains("EXITED"))
                                .collect(Collectors.toList());
                        System.out.println(result);
                    } else if (status.equals("UP")) {
                        List result1 = DockerContainers.containerDataHashMapId.values().stream()
                                .filter(data -> data.getContainerStatus().contains("UP"))
                                .collect(Collectors.toList());
                        System.out.println(result1);
                    } else {
                        System.out.println("Wrong value, try again");
                    }
                }
                if (choose.equals("3")) {
                    System.out.println("Enter Container Name");
                    name = scan.nextLine().toLowerCase(Locale.ROOT);
                    if(DockerContainers.containerDataHashMapName.get(name) !=null){
                    System.out.println(DockerContainers.containerDataHashMapName.get(name));
                }else
                    {
                        System.out.println("Wrong value, try again");
                    }
                }
            } while (!Objects.equals(choose, "0"));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}