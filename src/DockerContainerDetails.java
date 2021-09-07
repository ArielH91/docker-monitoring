import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DockerContainerDetails {

    private final String containerNetwork;
    private final String containerVolumes;
    private final String containerPlatform;
    private final String containerHostname;
    private final String containerPath;

    @Override
    public String toString() {
        return "DockerContainerDetails{" +
                "containerNetwork='" + containerNetwork + '\'' +
                ", containerVolumes='" + containerVolumes + '\'' +
                ", containerPlatform='" + containerPlatform + '\'' +
                ", containerHostname='" + containerHostname + '\'' +
                ", containerPath='" + containerPath + '\'' +
                '}';
    }
}
