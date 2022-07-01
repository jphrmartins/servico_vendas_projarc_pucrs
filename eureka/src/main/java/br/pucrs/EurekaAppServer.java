package br.pucrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaAppServer {
    public static void main(String[] args) {
        SpringApplication.run(EurekaAppServer.class, args);
    }
}
