package ru.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.models.Toy;

@Repository
public interface ToyRepository extends JpaRepository<Toy, Integer> {
}
