package com.cybersoft.osahaneat;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@SpringBootTest
class OsahaneatApplicationWireMockTests {
    @Autowired
    WireMockServer startWireMockPort8080;

    @Autowired
    WireMockServer startWireMockPort8081;

    @BeforeEach
    void  Befortest(){
        if(!startWireMockPort8080.isRunning()){
            startWireMockPort8080.start();
        }
        if(!startWireMockPort8081.isRunning()){
            startWireMockPort8081.start();
        }
    }

    @AfterEach
    void AfterTest(){
        if(startWireMockPort8080.isRunning()){
            startWireMockPort8080.stop();
        }
        if(startWireMockPort8081.isRunning()){
            startWireMockPort8081.stop();
        }
    }

    @Test
    void Test123123() {

        startWireMockPort8080.stubFor(get(urlEqualTo("/api/test"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Hello World1223tr1")));

        startWireMockPort8081.stubFor(get(urlEqualTo("/api/test"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Hello World1223tr")));

        System.out.println("123");
//        try {
//            Thread.sleep(250000); // Mô phỏng một bài kiểm thử kéo dài lâu (lớn hơn 200 giây)
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
