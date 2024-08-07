package com.wifi.publicwifiproject;


import com.wifi.publicwifiproject.service.OpenAPIService;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        OpenAPIService openAPIService = new OpenAPIService();
        System.out.println("count = " + openAPIService.loadWifiInfo());
    }
}
