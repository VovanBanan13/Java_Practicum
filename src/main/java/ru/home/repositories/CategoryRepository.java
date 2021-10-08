package ru.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
