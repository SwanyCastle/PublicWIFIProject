package com.wifi.publicwifiproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookMarkDTO {
    private int id;
    private int wifiId;
    private String wifiName;
    private int bookmarkId;
    private String bookmarkName;
    private String created_at;
}
