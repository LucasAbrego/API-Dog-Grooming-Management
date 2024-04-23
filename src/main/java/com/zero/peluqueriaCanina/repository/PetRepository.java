package com.zero.peluqueriaCanina.repository;

import com.zero.peluqueriaCanina.model.Client;
import com.zero.peluqueriaCanina.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByActiveTrue();

    List<Pet> findByOwner(Client owner);

    boolean existsById(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Pet p WHERE p.owner = :client")
    void deleteByOwner(@Param("client") Client client);
}
