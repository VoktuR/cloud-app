package ru.rav.common.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private Long id;

    private String title;

    private long price;

}
