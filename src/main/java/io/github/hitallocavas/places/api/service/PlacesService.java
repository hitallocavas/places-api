package io.github.hitallocavas.places.api.service;

import io.github.hitallocavas.places.api.dto.PlaceDTO;
import io.github.hitallocavas.places.api.entity.PlaceEntity;
import io.github.hitallocavas.places.api.mapper.PlacesMapper;
import io.github.hitallocavas.places.api.repository.PlacesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlacesService {
    private final PlacesRepository repository;

    public PlaceDTO create(PlaceDTO dto) {
        log.info("Creating place with slug {}", dto.getSlug());
        var entity = PlacesMapper.INSTANCE.fromDTO(dto);
        return save(entity);
    }

    public PlaceDTO update(PlaceDTO dto) {
        log.info("Updating place with slug {}", dto.getSlug());
        var entity = getById(dto.getId());
        PlacesMapper.INSTANCE.updateEntityFromDTO(dto, entity);
        return save(entity);
    }

    public PlaceDTO findById(Long id) {
        log.info("Finding Place with id {}", id);
        var entity = getById(id);
        return PlacesMapper.INSTANCE.fromEntity(entity);
    }

    public List<PlaceDTO> filterByName(String name) {
        log.info("Filtering Places with name like {}", name);
        var places = repository.findAllByNameContaining(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return places.stream()
                .map(PlacesMapper.INSTANCE::fromEntity)
                .collect(Collectors.toList());
    }

    private PlaceEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private PlaceDTO save(PlaceEntity entity) {
        entity = repository.save(entity);
        log.info("A place with id {} was stored", entity.getId());
        return PlacesMapper.INSTANCE.fromEntity(entity);
    }
}
