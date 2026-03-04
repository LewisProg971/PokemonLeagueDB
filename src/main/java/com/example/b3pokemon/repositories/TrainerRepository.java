package com.example.b3pokemon.repositories;

import com.example.b3pokemon.entities.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
}