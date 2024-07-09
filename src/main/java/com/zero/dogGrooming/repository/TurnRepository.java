package com.zero.dogGrooming.repository;

import com.zero.dogGrooming.model.Client;
import com.zero.dogGrooming.model.Pet;
import com.zero.dogGrooming.model.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {
    @Query("SELECT t FROM Turn t WHERE t.pet.id = :petId order by t.dateAndTime DESC")
    List<Turn> findTurnsByPetId(@Param("petId") Long petId);

    @Query("SELECT t FROM Turn t JOIN t.pet pet JOIN pet.owner client WHERE client.dni = :dni")
    List<Turn> findTurnsByClientDni(@Param("dni") String dni);

    @Transactional
    @Modifying
    @Query("DELETE FROM Turn t WHERE t.pet.owner = :client")
    void deleteByClient(@Param("client") Client client);

    @Transactional
    @Modifying
    @Query("DELETE FROM Turn t WHERE t.pet = :pet")
    void deleteByPet(@Param("pet") Pet pet);

    @Query("SELECT t FROM Turn t JOIN t.pet p JOIN p.owner c WHERE c.dni = :dni AND t.paid = false AND t.status = 'completed'")
    List<Turn> findUnpaidTurnsByOwnerDni(@Param("dni") String dni);

    @Query("SELECT t FROM Turn t WHERE t.dateAndTime BETWEEN :startDate AND :endDate")
    List<Turn> findByDateAndTimeBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
