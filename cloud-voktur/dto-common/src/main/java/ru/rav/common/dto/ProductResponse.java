package ru.rav.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ProductResponse {

    private Long id;

    private String title;

    private long price;

}
