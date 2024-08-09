package com.wifi.publicwifiproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookMarkGroupDTO {
    private int id;
    private String groupName;
    private int orderingNumber;
    private String createdAt;
    private String updatedAt;
}
