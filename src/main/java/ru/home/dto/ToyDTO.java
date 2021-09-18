package ru.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToyDTO {
    private int id;
    private String name;
    private double price;
    private int category_id;
}
