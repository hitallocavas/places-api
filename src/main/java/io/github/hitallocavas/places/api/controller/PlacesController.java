package io.github.hitallocavas.places.api.controller;

import io.github.hitallocavas.places.api.dto.PlaceDTO;
import io.github.hitallocavas.places.api.service.PlacesService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.hitallocavas.places.api.constants.RestConstants.PLACES;

@RestController
@RequestMapping(value = PLACES)
@RequiredArgsConstructor
public class PlacesController {

    private final PlacesService service;

    @PostMapping
    public ResponseEntity<PlaceDTO> createPlace(@RequestBody PlaceDTO dto){
        PlaceDTO response = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PlaceDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(value = "/filter/{name}")
    public ResponseEntity<List<PlaceDTO>> filterByName(@PathVariable String name) {
        return ResponseEntity.ok(service.filterByName(name));
    }

    @PutMapping
    public ResponseEntity<PlaceDTO> updatePlace(@RequestBody PlaceDTO dto){
        PlaceDTO response = service.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
