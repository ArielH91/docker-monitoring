import lombok.Data;
import lombok.Setter;

@Data
public class DockerContainerDetails {
    @Setter
    private String containerNetworkID;
    private String containerVolumes;
    private String containerPlatform;
    private String containerDriver;


    public DockerContainerDetails(String containerNetworkID, String containerVolumes, String containerPlatform, String containerDriver) {
        this.containerNetworkID = containerNetworkID;
        this.containerVolumes = containerVolumes;
        this.containerPlatform = containerPlatform;
        this.containerDriver = containerDriver;
    }
}
