package ru.home.services;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface BuyService {
    double saveBuy(int id, List<HashMap<String, Integer>> buyList) throws ParseException;
}
