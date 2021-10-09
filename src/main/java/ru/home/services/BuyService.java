package ru.home.services;

import java.util.HashMap;
import java.util.List;

public interface BuyService {
    void saveBuy(int id, List<HashMap<String, Integer>> buyList);
}
