public class DockerContainerData {

        private String containerId;
        private String containerName;
        private String containerStatus;

        private String containerPorts;
        private String containerImage;

        protected DockerContainerData(){}

        public DockerContainerData (String containerId, String containerName, String containerStatus, String containerPorts, String containerImage){
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

        public void setContainerId(String newContainerId) {
            this.containerId = newContainerId;
        }

        public String getContainerName(){
            return containerName;
        }

        public void setContainerName(String newContainerName) {
            this.containerName = newContainerName;
        }

        public String getContainerStatus(){
            return containerStatus;
        }

        public void setContainerStatus(String newContainerStatus){
            this.containerStatus = newContainerStatus;
        }

        public String getContainerPorts(){
                return containerPorts;

        }

        public void setContainerPorts(String newContainerPorts){
            this.containerPorts = newContainerPorts;

        }

        public String getContainerImage(){
            return containerImage;
        }

        public void setContainerImage(String newContainerImage){
            this.containerImage = newContainerImage;
        }
}
