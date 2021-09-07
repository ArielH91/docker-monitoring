import lombok.Data;
import lombok.Setter;

@Data
public class DockerContainerDetails {
    @Setter
    private String containerNetwork;
    private String containerVolumes;
    private String containerPlatform;
    private String containerHostname;
    private String containerPath;


    public DockerContainerDetails(String containerNetwork, String containerVolumes, String containerPlatform, String containerHostname, String containerPath) {
        this.containerNetwork = containerNetwork;
        this.containerVolumes = containerVolumes;
        this.containerPlatform = containerPlatform;
        this.containerHostname = containerHostname;
        this.containerPath = containerPath;
    }
}
