package ru.home.services;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface BuyService {
    /**
     * Покупка товаров, id передается из url (id покупателя), список товаров в json в виде:
     *      [{"toy_1":x}, {"toy_2":x}, {"toy_3":x}, ..., {"toy_n":x}] ,
     *      где "toy_n" - название товара, x - количество товаров на покупку.
     * При выполнении создается новый ордер, создаются записи в shop_list по каждому товару,
     *      вычитается количество товаров со склада.
     *
     * @param id Покупатель
     * @param buyList Лист товаров с количеством
     * @return sumAmount - Общая стоимость покупки
     */
    double saveBuy(int id, List<HashMap<String, Integer>> buyList) throws ParseException;
}
