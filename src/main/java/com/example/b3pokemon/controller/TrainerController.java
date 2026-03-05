package com.example.b3pokemon.controller;

import com.example.b3pokemon.dto.input.TrainerInputDto;
import com.example.b3pokemon.dto.output.TrainerOutputDto;
import com.example.b3pokemon.services.ITrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

  private final ITrainerService trainerService;

  public TrainerController(ITrainerService trainerService) {
    this.trainerService = trainerService;
  }


  @PostMapping
  public ResponseEntity<TrainerOutputDto> createTrainer(@RequestBody TrainerInputDto dto) {
    TrainerOutputDto createdTrainer = trainerService.createTrainer(dto);
    return new ResponseEntity<>(createdTrainer, HttpStatus.CREATED);
  }


  @GetMapping
  public ResponseEntity<List<TrainerOutputDto>> getAllTrainers() {
    return ResponseEntity.ok(trainerService.getAllTrainers());
  }


  @GetMapping("/{id}")
  public ResponseEntity<TrainerOutputDto> getTrainerById(@PathVariable Long id) {
    return ResponseEntity.ok(trainerService.getTrainerById(id));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
    trainerService.deleteTrainer(id);
    return ResponseEntity.noContent().build();
  }


  @PostMapping("/{trainerId}/capture/{pokemonId}")
  public ResponseEntity<String> capturePokemon(@PathVariable Long trainerId, @PathVariable Long pokemonId) {
    trainerService.capturePokemon(trainerId, pokemonId);
    return ResponseEntity.ok("Pokémon capturé avec succès !");
  }


  @GetMapping("/{id}/average-level")
  public ResponseEntity<Double> getAverageLevel(@PathVariable Long id) {
    return ResponseEntity.ok(trainerService.getAverageLevel(id));
  }


  @GetMapping("/{id}/profile")
  public ResponseEntity<com.example.b3pokemon.dto.output.TrainerProfileOutputDto> getTrainerProfile(@PathVariable Long id) {
    return ResponseEntity.ok(trainerService.getTrainerProfile(id));
  }
}