package ru.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.models.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {
}
