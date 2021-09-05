package io.github.hitallocavas.places.api.repository;

import io.github.hitallocavas.places.api.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlacesRepository extends JpaRepository<PlaceEntity, Long> {
    Optional<List<PlaceEntity>> findAllByNameContaining(String name);
}
