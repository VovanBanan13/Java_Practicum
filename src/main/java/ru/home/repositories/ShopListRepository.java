package ru.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.models.ShopList;

@Repository
public interface ShopListRepository extends JpaRepository<ShopList, Integer> {
}
