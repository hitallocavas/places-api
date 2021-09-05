package io.github.hitallocavas.places.api.mapper;

import io.github.hitallocavas.places.api.dto.PlaceDTO;
import io.github.hitallocavas.places.api.entity.PlaceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlacesMapper {
    PlacesMapper INSTANCE = Mappers.getMapper(PlacesMapper.class);

    PlaceEntity fromDTO(PlaceDTO placeDTO);
    PlaceDTO fromEntity(PlaceEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(PlaceDTO dto, @MappingTarget PlaceEntity entity);
}
