package ru.home.services;

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
}
