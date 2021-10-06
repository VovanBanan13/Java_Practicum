package ru.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopListDto {
    private int id;
    private int order_id;
    private int toy_id;
    private int count;
}
