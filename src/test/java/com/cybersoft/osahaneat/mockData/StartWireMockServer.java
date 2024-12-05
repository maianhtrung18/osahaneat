package com.cybersoft.osahaneat.mockData;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class StartWireMockServer {

    @Bean()
    public WireMockServer startWireMockPort8080(){
        return startWireMockServerPort(8080);
    }

    @Bean()
    public WireMockServer startWireMockPort8081(){
        return startWireMockServerPort(8081);
    }

    public WireMockServer startWireMockServerPort(int port){
        return new WireMockServer(
                WireMockConfiguration.options()
                        .port(port)
                        .bindAddress("localhost")
                        .usingFilesUnderClasspath("mockData")
        );
    }
}
