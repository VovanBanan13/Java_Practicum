package ru.home.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.exceptions.ObjectNotFoundAdvice;
import ru.home.models.Category;
import ru.home.models.Toy;
import ru.home.repositories.CategoryRepository;
import ru.home.repositories.ToyRepository;
import ru.home.services.ToyService;

@Service
@Transactional
public class ToyServiceImpl implements ToyService {

    private final ToyRepository toyRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ToyServiceImpl(ToyRepository toyRepository, CategoryRepository categoryRepository) {
        this.toyRepository = toyRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Toy> findAllToys() {
        List<Toy> toys = toyRepository.findAll();
        if(toys.isEmpty()) {
            throw new ObjectNotFoundAdvice();
        } else
            return toys;
    }

    @Override
    public Toy getById(int id) {
        return toyRepository.findById(id).orElseThrow(() -> new ObjectNotFoundAdvice());
    }

    @Override
    public Toy getByName(String name) {
        return toyRepository.findByName(name).orElseThrow(() -> new ObjectNotFoundAdvice());
    }

    @Override
    public List<Toy> getAllToyByCategory(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        List<Toy> toys = toyRepository.findAllByCategory(category);
        if(toys.isEmpty()) {
            throw new ObjectNotFoundAdvice();
        } else
            return toys;
    }

    @Override
    public void save(Toy toy) {
        if(toy.getName()==null || toy.getCategory()==null)
            throw new ObjectNotFoundAdvice();
        this.toyRepository.save(toy);
    }

    @Override
    public void delete(int id) {
        toyRepository.deleteById(id);
    }

    @Override
    public Toy update(int id, Toy toy) {
        Toy changedToy = toyRepository.findById(id).orElseThrow(() -> new ObjectNotFoundAdvice());
        changedToy.setName(toy.getName());
        changedToy.setPrice(toy.getPrice());
        changedToy.setCategory(toy.getCategory());
        this.toyRepository.save(changedToy);
        return changedToy;
    }
}
