package ru.home.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.exceptions.ObjectNotFoundAdvice;
import ru.home.models.Toy;
import ru.home.repositories.ToyRepository;
import ru.home.services.ToyService;

@Service
@Transactional
public class ToyServiceImpl implements ToyService {

    private final ToyRepository toyRepository;

    @Autowired
    public ToyServiceImpl(ToyRepository toyRepository) {
        this.toyRepository = toyRepository;
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
