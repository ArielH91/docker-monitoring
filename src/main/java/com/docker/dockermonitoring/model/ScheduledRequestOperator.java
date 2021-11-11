package com.docker.dockermonitoring.model;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Component
public class ScheduledRequestOperator {
    enum RequestAction {
        CREATE("CREATE", ScheduledRequestOperator::create),
        START("START", ScheduledRequestOperator::start),
        STOP("STOP", ScheduledRequestOperator::stop),
        REMOVE("REMOVE", ScheduledRequestOperator::remove);

        private String action;
        private Function<DockerRequest, String> function;

        RequestAction (String action, Function<DockerRequest, String> function) {
            this.action = action;
            this.function = function;
        }
    }
    private final DockerRequestRepository requestRepository;

    @Autowired
    public ScheduledRequestOperator(DockerRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }


    public void createDocker() {
        List<DockerRequest> requests = requestRepository.findByRequestStatus("p");

        for(DockerRequest request: requests){
            request.setRequestStatus("i");
            var request2 = requestRepository.save(request);

            try {
                var runtime = Runtime.getRuntime();
                String create = "docker run";
                Process process = runtime.exec(create + " --name " + request.getContainerName() + " -p " + request.getContainerPort() + " -d " + request.getContainerImage());

                /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));*/
                process.waitFor();
                int exitValue = process.exitValue();

                if(exitValue == 0) {
                    request2.setRequestStatus("s");
                    requestRepository.save(request2);
                }else {
                    request2.setRequestStatus("f");
                    requestRepository.save(request2);
                }


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    static String create(DockerRequest request) {
        return "s";
    }

    static String start(DockerRequest request) {
        return  "s";
    }

    static String stop(DockerRequest request) {
        return "s";
    }

    static String remove(DockerRequest request) {
        return "s";
    }
}
