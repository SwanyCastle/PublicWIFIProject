package com.wifi.publicwifiproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationHistoryDTO {
    private int id;
    private double lat;
    private double lnt;
    private String searchDate;
}
