package com.zero.dogGrooming.model;

import com.zero.dogGrooming.dto.PetSaveDto;
import com.zero.dogGrooming.dto.PetUpdateDto;
import com.zero.dogGrooming.enums.PetFurTypeEnum;
import com.zero.dogGrooming.enums.PetSizeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long age;
    private String breed;
    @Enumerated(EnumType.STRING)
    private PetSizeEnum size;
    @Enumerated(EnumType.STRING)
    private PetFurTypeEnum furType;
    private String considerations;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "owner")
    private Client owner;

    public Pet(PetSaveDto petDto) {
        this.name = petDto.getName();
        this.age = petDto.getAge();
        this.breed = petDto.getBreed();
        this.size = petDto.getSize();
        this.furType = petDto.getFurType();
        this.considerations = (petDto.getConsiderations() != null) ? petDto.getConsiderations() : "";
        this.active = true;
    }

    public void fromPetUpdateDto(PetUpdateDto petDto) {
        if (petDto.getName() != null) {
            this.setName(petDto.getName());
        }
        if (petDto.getAge() != null) {
            this.setAge(petDto.getAge());
        }
        if (petDto.getBreed() != null) {
            this.setBreed(petDto.getBreed());
        }
        if (petDto.getSize() != null) {
            this.setSize(petDto.getSize());
        }
        if (petDto.getFurType() != null) {
            this.setFurType(petDto.getFurType());
        }
        if (petDto.getConsiderations() != null) {
            this.setConsiderations(petDto.getConsiderations());
        }
    }

}
