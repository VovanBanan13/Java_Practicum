package ru.home.services;

import java.util.List;
import ru.home.models.Toy;

public interface ToyService {
    List<Toy> findAllToys();
    Toy getById(int id);
    void save(Toy toy);
    void delete(int id);
}
