import lombok.Data;
import lombok.Setter;

@Data
public class DockerContainerDetails {
    @Setter
    private String containerNetworkID;
    private String containerVolumes;
    private String containerPlatform;
    private String containerHostname;
    private String containerPath;


    public DockerContainerDetails(String containerNetworkID, String containerVolumes, String containerPlatform, String containerHostname, String containerPath) {
        this.containerNetworkID = containerNetworkID;
        this.containerVolumes = containerVolumes;
        this.containerPlatform = containerPlatform;
        this.containerHostname = containerHostname;
        this.containerPath = containerPath;
    }
}
