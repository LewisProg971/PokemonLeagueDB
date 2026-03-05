package com.example.b3pokemon.services;

import com.example.b3pokemon.dto.input.TrainerInputDto;
import com.example.b3pokemon.dto.output.TrainerOutputDto;
import com.example.b3pokemon.entities.PokemonEntity;
import com.example.b3pokemon.entities.TrainerEntity;
import com.example.b3pokemon.repositories.PokemonRepository;
import com.example.b3pokemon.repositories.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService implements ITrainerService {

  private final TrainerRepository trainerRepository;
  private final PokemonRepository pokemonRepository;

  public TrainerService(TrainerRepository trainerRepository, PokemonRepository pokemonRepository) {
    this.trainerRepository = trainerRepository;
    this.pokemonRepository = pokemonRepository;
  }


  @Override
  public TrainerOutputDto createTrainer(TrainerInputDto dto) {
    TrainerEntity entity = new TrainerEntity();
    entity.setName(dto.getName());
    entity.setCity(dto.getCity());
    entity.setExperiencePoints(dto.getExperiencePoints() != null ? dto.getExperiencePoints() : 0);

    TrainerEntity savedEntity = trainerRepository.save(entity);
    return mapToOutputDto(savedEntity);
  }


  @Override
  public List<TrainerOutputDto> getAllTrainers() {
    return trainerRepository.findAll().stream()
      .map(this::mapToOutputDto)
      .collect(Collectors.toList());
  }


  @Override
  public TrainerOutputDto getTrainerById(Long id) {
    TrainerEntity entity = trainerRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Dresseur introuvable avec l'ID : " + id));
    return mapToOutputDto(entity);
  }


  @Override
  public void deleteTrainer(Long id) {
    if (!trainerRepository.existsById(id)) {
      throw new RuntimeException("Impossible de supprimer : Dresseur introuvable avec l'ID : " + id);
    }
    trainerRepository.deleteById(id);
  }

  private TrainerOutputDto mapToOutputDto(TrainerEntity entity) {
    TrainerOutputDto dto = new TrainerOutputDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setCity(entity.getCity());
    dto.setExperiencePoints(entity.getExperiencePoints());
    return dto;
  }


  @Override
  @Transactional
  public void capturePokemon(Long trainerId, Long pokemonId) {
    TrainerEntity trainer = trainerRepository.findById(trainerId)
      .orElseThrow(() -> new RuntimeException("Dresseur introuvable"));

    if (trainer.getPokemons().size() >= 6) {
      throw new RuntimeException("Limite atteinte : Un dresseur ne peut pas posséder plus de 6 Pokémon.");
    }

    PokemonEntity pokemon = pokemonRepository.findById(pokemonId)
      .orElseThrow(() -> new RuntimeException("Pokémon introuvable"));

    pokemon.setTrainer(trainer);
    pokemonRepository.save(pokemon);
  }


  @Override
  @Transactional(readOnly = true)
  public Double getAverageLevel(Long trainerId) {
    TrainerEntity trainer = trainerRepository.findById(trainerId)
      .orElseThrow(() -> new RuntimeException("Dresseur introuvable"));

    if (trainer.getPokemons() == null || trainer.getPokemons().isEmpty()) {
      return 0.0;
    }

    return trainer.getPokemons().stream()
      .mapToInt(PokemonEntity::getLevel)
      .average()
      .orElse(0.0);
  }


  @Override
  @Transactional(readOnly = true)
  public com.example.b3pokemon.dto.output.TrainerProfileOutputDto getTrainerProfile(Long trainerId) {
    TrainerEntity trainer = trainerRepository.findById(trainerId)
      .orElseThrow(() -> new RuntimeException("Dresseur introuvable"));

    com.example.b3pokemon.dto.output.TrainerProfileOutputDto profile = new com.example.b3pokemon.dto.output.TrainerProfileOutputDto();
    profile.setName(trainer.getName());
    profile.setCity(trainer.getCity());
    profile.setExperiencePoints(trainer.getExperiencePoints());

    List<PokemonEntity> team = trainer.getPokemons();

    profile.setTeam(team.stream().map(this::mapPokemonToDto).collect(Collectors.toList()));

    if (team.isEmpty()) {
      profile.setPowerScore(0.0);
      profile.setLegendStatus(false);
      return profile;
    }

    com.example.b3pokemon.enumeration.PokemonType favoriteType = team.stream()
      .collect(Collectors.groupingBy(PokemonEntity::getType, Collectors.counting()))
      .entrySet().stream()
      .max(java.util.Map.Entry.comparingByValue())
      .map(java.util.Map.Entry::getKey)
      .orElse(null);
    profile.setFavoriteType(favoriteType);

    int sumLevels = team.stream().mapToInt(PokemonEntity::getLevel).sum();
    long distinctTypes = team.stream().map(PokemonEntity::getType).distinct().count();
    double powerScore = sumLevels;
    if (distinctTypes >= 3) {
      powerScore *= 1.10;
    }
    profile.setPowerScore(powerScore);

    double avgLevel = getAverageLevel(trainerId);
    boolean isLegend = (team.size() == 6 && avgLevel > 75);
    profile.setLegendStatus(isLegend);

    return profile;
  }


  private com.example.b3pokemon.dto.output.PokemonOutputDto mapPokemonToDto(PokemonEntity entity) {
    com.example.b3pokemon.dto.output.PokemonOutputDto dto = new com.example.b3pokemon.dto.output.PokemonOutputDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setNickname(entity.getNickname());
    dto.setLevel(entity.getLevel());
    dto.setType(entity.getType());
    dto.setTrainerId(entity.getTrainer() != null ? entity.getTrainer().getId() : null);
    return dto;
  }

}