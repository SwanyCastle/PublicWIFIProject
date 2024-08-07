package com.wifi.publicwifiproject.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URL;

import static com.wifi.publicwifiproject.dao.WifiDAO.insertWifiInfo;


public class OpenAPIService {
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static String apiUrl = "http://openapi.seoul.go.kr:8088/674267766b6b736837334841544c65/json/TbPublicWifiInfo/";

    public static int totalWifiCount() throws IOException {
        int wifiCount = 0;

        URL url = new URL(apiUrl + "1/1");

        //URL 요청
        Request.Builder builder = new Request.Builder().url(url).get();

        //URL 응답
        Response response = okHttpClient.newCall(builder.build()).execute();

        try {
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();

                if (responseBody != null) {
                    JsonElement jsonElement = JsonParser.parseString(responseBody.string());

                    wifiCount = jsonElement.getAsJsonObject().get("TbPublicWifiInfo")
                            .getAsJsonObject().get("list_total_count")
                            .getAsInt();

                    System.out.println(jsonElement.toString());
                    System.out.println("찾은 와이파이 개수 = " + wifiCount);
                }

            } else {
                System.out.println("API 호출 실패: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wifiCount;
    }

    public int loadWifiInfo() throws IOException {
        int wifiCnt = totalWifiCount();
        int start = 1, end = 1;
        int count = 0;

        try {
            for (int i = 0; i <= wifiCnt / 1000; i++) {
                start = 1 + (i * 1000);
                end = (i + 1) * 1000;

                URL url = new URL(apiUrl + start + "/" + end);

                //URL 요청
                Request.Builder builder = new Request.Builder().url(url).get();

                //URL 응답
                Response response = okHttpClient.newCall(builder.build()).execute();

                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();

                    if (responseBody != null) {
                        JsonElement jsonElement = JsonParser.parseString(responseBody.string());

                        JsonArray jsonArray = jsonElement.getAsJsonObject().get("TbPublicWifiInfo")
                                .getAsJsonObject().get("row")
                                .getAsJsonArray();

                        count += insertWifiInfo(jsonArray);
                    }

                } else {
                    System.out.println("API 호출 실패: " + response.code());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
