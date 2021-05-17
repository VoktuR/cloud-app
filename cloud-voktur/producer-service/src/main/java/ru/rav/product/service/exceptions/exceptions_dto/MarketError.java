package ru.rav.product.service.exceptions.exceptions_dto;

import lombok.Data;

import java.util.Date;

@Data
public class MarketError {

    private Integer status;

    private String message;

    private Date timestamp;

    public MarketError(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }

}
