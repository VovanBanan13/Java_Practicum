package ru.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
