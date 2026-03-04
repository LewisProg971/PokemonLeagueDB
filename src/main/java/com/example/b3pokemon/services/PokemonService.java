package com.example.b3pokemon.services;

import com.example.b3pokemon.dto.input.PokemonInputDto;
import com.example.b3pokemon.dto.output.PokemonOutputDto;
import com.example.b3pokemon.entities.PokemonEntity;
import com.example.b3pokemon.entities.TrainerEntity;
import com.example.b3pokemon.repositories.PokemonRepository;
import com.example.b3pokemon.repositories.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService implements IPokemonService {

  private final PokemonRepository pokemonRepository;
  private final TrainerRepository trainerRepository;

  public PokemonService(PokemonRepository pokemonRepository, TrainerRepository trainerRepository) {
    this.pokemonRepository = pokemonRepository;
    this.trainerRepository = trainerRepository;
  }

  @Override
  public PokemonOutputDto createPokemon(PokemonInputDto dto) {
    PokemonEntity entity = new PokemonEntity();
    entity.setName(dto.getName());
    entity.setNickname(dto.getNickname());
    entity.setLevel(dto.getLevel() != null ? dto.getLevel() : 1);
    entity.setType(dto.getType());

    if (dto.getTrainerId() != null) {
      TrainerEntity trainer = trainerRepository.findById(dto.getTrainerId())
        .orElseThrow(() -> new RuntimeException("Dresseur introuvable pour l'ID : " + dto.getTrainerId()));
      entity.setTrainer(trainer);
    }

    PokemonEntity savedEntity = pokemonRepository.save(entity);
    return mapToOutputDto(savedEntity);
  }

  @Override
  public List<PokemonOutputDto> getAllPokemons() {
    return pokemonRepository.findAll().stream()
      .map(this::mapToOutputDto)
      .collect(Collectors.toList());
  }

  @Override
  public void deletePokemon(Long id) {
    if (!pokemonRepository.existsById(id)) {
      throw new RuntimeException("Impossible de supprimer : Pokémon introuvable avec l'ID : " + id);
    }
    pokemonRepository.deleteById(id);
  }

  // Méthode utilitaire de mapping
  private PokemonOutputDto mapToOutputDto(PokemonEntity entity) {
    PokemonOutputDto dto = new PokemonOutputDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setNickname(entity.getNickname());
    dto.setLevel(entity.getLevel());
    dto.setType(entity.getType());
    if (entity.getTrainer() != null) {
      dto.setTrainerId(entity.getTrainer().getId());
    }
    return dto;
  }
  @Override
  public List<PokemonOutputDto> getPokemonsByType(com.example.b3pokemon.enumeration.PokemonType type) {
    return pokemonRepository.findByType(type).stream()
      .map(this::mapToOutputDto)
      .collect(Collectors.toList());
  }
}