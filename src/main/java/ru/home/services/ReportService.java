package ru.home.services;

import java.util.Date;
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

    /**
     * Вывод проданных товаров в промежутке времени.
     *
     * @param startDate Дата начала
     * @param endDate Дата конца
     * @return Проданные товары и их количество
     */
    Map<String, Integer> getToysSoldByTime(Date startDate, Date endDate);
}
