import web.service.FirstService;

import javax.xml.ws.Endpoint;

public class FirstServicePublisher {

    public static void main(String[] args) {
        final String url = "http://localhost:8888/fs";
        System.out.println("Publishing FirstService at endpoint " + url);

        Endpoint.publish(url, new FirstService());
    }

}
