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

  // Créer un nouveau dresseur
  @PostMapping // [cite: 20]
  public ResponseEntity<TrainerOutputDto> createTrainer(@RequestBody TrainerInputDto dto) {
    TrainerOutputDto createdTrainer = trainerService.createTrainer(dto);
    return new ResponseEntity<>(createdTrainer, HttpStatus.CREATED); // Code 201
  }

  // Récupérer la liste de tous les dresseurs
  @GetMapping // [cite: 21]
  public ResponseEntity<List<TrainerOutputDto>> getAllTrainers() {
    return ResponseEntity.ok(trainerService.getAllTrainers()); // Code 200
  }

  // Récupérer un dresseur par son ID
  @GetMapping("/{id}") // [cite: 22]
  public ResponseEntity<TrainerOutputDto> getTrainerById(@PathVariable Long id) {
    return ResponseEntity.ok(trainerService.getTrainerById(id)); // Code 200
  }

  // Supprimer un dresseur
  @DeleteMapping("/{id}") // [cite: 23]
  public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
    trainerService.deleteTrainer(id);
    return ResponseEntity.noContent().build(); // Code 204
  }

  // Capture de Pokémon [cite: 30, 31]
  @PostMapping("/{trainerId}/capture/{pokemonId}") //
  public ResponseEntity<String> capturePokemon(@PathVariable Long trainerId, @PathVariable Long pokemonId) {
    trainerService.capturePokemon(trainerId, pokemonId);
    return ResponseEntity.ok("Pokémon capturé avec succès !");
  }

  // Calcul du Niveau Moyen
  @GetMapping("/{id}/average-level") //
  public ResponseEntity<Double> getAverageLevel(@PathVariable Long id) {
    return ResponseEntity.ok(trainerService.getAverageLevel(id));
  }

  // Génération du Profil Complet
  @GetMapping("/{id}/profile") //
  public ResponseEntity<com.example.b3pokemon.dto.output.TrainerProfileOutputDto> getTrainerProfile(@PathVariable Long id) {
    return ResponseEntity.ok(trainerService.getTrainerProfile(id));
  }
}