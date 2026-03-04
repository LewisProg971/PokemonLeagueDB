package com.example.b3pokemon.repositories;

import com.example.b3pokemon.entities.PokemonEntity;
import com.example.b3pokemon.enumeration.PokemonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {
  // Méthode utile pour la Partie III du cahier des charges
  List<PokemonEntity> findByType(PokemonType type);
}