package io.github.hitallocavas.places.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String slug;
    private String city;
    private String state;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    private void addCreationDate(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void updateDate(){
        this.updatedAt = LocalDateTime.now();
    }
}
