package ru.home.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.models.Storage;
import ru.home.models.Toy;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {
    Optional<Storage> findByToy(Optional<Toy> toy);
}
