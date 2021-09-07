import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DockerContainerDetails {

    private final String containerNetwork;
    private final String containerVolumes;
    private final String containerPlatform;
    private final String containerHostname;
    private final String containerPath;


/*    public DockerContainerDetails(String containerNetwork, String containerVolumes, String containerPlatform, String containerHostname, String containerPath) {
        this.containerNetwork = containerNetwork;
        this.containerVolumes = containerVolumes;
        this.containerPlatform = containerPlatform;
        this.containerHostname = containerHostname;
        this.containerPath = containerPath;
    }*/
}
