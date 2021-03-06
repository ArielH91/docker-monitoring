package com.docker.dockermonitoring.logic;

import com.docker.dockermonitoring.model.DockerRequest;
import com.docker.dockermonitoring.adapter.DockerRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ScheduledRequestOperator {

    public enum RequestAction {
        CREATE("CREATE"),
        START("START"),
        STOP("STOP"),
        REMOVE("REMOVE");

        private String action;
        private static Map<String, RequestAction> actionHashMap= new HashMap<>();
        static {
            actionHashMap.put(CREATE.getAction(), CREATE);
            actionHashMap.put(START.getAction(), START);
            actionHashMap.put(STOP.getAction(), STOP);
            actionHashMap.put(REMOVE.getAction(), REMOVE);
        }

        RequestAction (String action) {
            this.action = action;
        }

        public String getAction() {
            return action;
        }

        public static RequestAction forAction(String action) {
            return actionHashMap.get(action);
        }

    }
    private final DockerRequestRepository requestRepository;

    @Autowired
    public ScheduledRequestOperator(DockerRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }


    public void handleRequest() throws IOException, InterruptedException {
        List<DockerRequest> requests = requestRepository.findByRequestStatus("p");

        for(DockerRequest request: requests){
            request.setRequestStatus("i");
            var request2 = requestRepository.save(request);

            String status;

            switch (RequestAction.forAction(request2.getAction())) {
                case CREATE: status = create(request2);break;
                case START: status = start(request2);break;
                case STOP: status = stop(request2);break;
                case REMOVE: status = remove(request2);break;
                default: status = "f";
            }

            request2.setRequestStatus(status);
            requestRepository.save(request2);
        }


    }

    String create(DockerRequest request) throws IOException, InterruptedException {

        var runtime = Runtime.getRuntime();
        String create = "docker run";
        Process process = runtime.exec(create + " --name " + request.getContainerName() + " -p " + request.getContainerPort() + " " + request.getContainerImage());

        process.waitFor();
        int exitValue = process.exitValue();

        if(exitValue == 0) {
            return "s";
        }

        return "f";
    }

    String start(DockerRequest request) throws IOException, InterruptedException {
        var runtime = Runtime.getRuntime();
        String start = "docker start";
        Process process = runtime.exec(start + " " + request.getContainerName());

        process.waitFor();
        int exitValue = process.exitValue();

        if(exitValue == 0) {
            return "s";
        }

        return "f";
    }

    String stop(DockerRequest request) throws IOException, InterruptedException {
        var runtime = Runtime.getRuntime();
        String stop = "docker stop";
        Process process = runtime.exec(stop + " " + request.getContainerName());

        process.waitFor();
        int exitValue = process.exitValue();

        if(exitValue == 0) {
            return "s";
        }

        return "f";
    }

    String remove(DockerRequest request) throws IOException, InterruptedException {
        var runtime = Runtime.getRuntime();
        String remove = "docker rm";
        Process process = runtime.exec(remove + " " + request.getContainerName());

        process.waitFor();
        int exitValue = process.exitValue();

        if(exitValue == 0) {
            return "s";
        }

        return "f";
    }
}
