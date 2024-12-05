package com.cybersoft.osahaneat.mockData;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.stereotype.Component;

public class Wiremock {

    public static void main(String[] args) {
        // Tạo một WireMockServer với cấu hình mặc định
        StartWireMockServer wireMockServer = new StartWireMockServer();
        WireMockServer localhost8080 = wireMockServer.startWireMockPort8080();
        WireMockServer localhost8081 = wireMockServer.startWireMockPort8081();
        localhost8081.start();
        localhost8080.start();

        // Tạo một stub đơn giản để mock API
        localhost8081.stubFor(WireMock.get(WireMock.urlEqualTo("/api/hello"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("{\"message\": \"Hello from WireMock!\"}")
                ));

        localhost8080.stubFor(WireMock.get(WireMock.urlEqualTo("/api/hello"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("{\"message\": \"Hello from WireMock!\"}")
                ));

        System.out.println("WireMock server is running on port ...");
    }
}
