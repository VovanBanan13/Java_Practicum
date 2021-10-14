package ru.home.services;

import java.util.Map;

public interface ReportService {
    /**
     * Вывод дохода от продаж всех товаров.
     *
     * @return Сумма всех доходов
     */
    double getProfit();

    /**
     * Вывод среднего чека по всем покупкам.
     *
     * @return Средний чек по всем покупкам
     */
    double getAverageCheck();

    /**
     * Вывод всех проданных товаров.
     *
     * @return Проданные товары и их количество
     */
    Map<String, Integer> getToysSold();
}
