public class DockerContainerData {

        private String containerId;
        private String containerName;
        private String containerStatus;
        private String containerPorts;
        private String containerImage;


        public DockerContainerData(String containerId, String containerName, String containerStatus, String containerPorts, String containerImage){
            this.containerId = containerId;
            this.containerName = containerName;
            this.containerStatus = containerStatus;
            this.containerPorts = containerPorts;
            this.containerImage = containerImage;
        }

    public String toString() {

            return String.format(
                    "ContainerData[ id='%s', name='%s', status='%s', ports='%s', image='%s']",
                    containerId, containerName, containerStatus, containerPorts, containerImage);
        }


        public String getContainerId() {
            return containerId;
        }


        public String getContainerName(){
            return containerName;
        }


        public String getContainerStatus(){
            return containerStatus;
        }

}
