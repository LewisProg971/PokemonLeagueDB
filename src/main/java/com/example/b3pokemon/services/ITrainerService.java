package com.example.b3pokemon.services;

import com.example.b3pokemon.dto.input.TrainerInputDto;
import com.example.b3pokemon.dto.output.TrainerOutputDto;
import com.example.b3pokemon.dto.output.TrainerProfileOutputDto;

import java.util.List;

public interface ITrainerService {
  TrainerOutputDto createTrainer(TrainerInputDto dto);

  List<TrainerOutputDto> getAllTrainers();

  TrainerOutputDto getTrainerById(Long id);

  void deleteTrainer(Long id);

  void capturePokemon(Long trainerId, Long pokemonId);

  Double getAverageLevel(Long trainerId);

  TrainerProfileOutputDto getTrainerProfile(Long trainerId);
}