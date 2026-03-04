package com.example.b3pokemon.controller;

import com.example.b3pokemon.dto.input.PokemonInputDto;
import com.example.b3pokemon.dto.output.PokemonOutputDto;
import com.example.b3pokemon.services.IPokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

  private final IPokemonService pokemonService;

  public PokemonController(IPokemonService pokemonService) {
    this.pokemonService = pokemonService;
  }

  // Enregistrer un nouveau Pokémon
  @PostMapping // [cite: 25]
  public ResponseEntity<PokemonOutputDto> createPokemon(@RequestBody PokemonInputDto dto) {
    PokemonOutputDto createdPokemon = pokemonService.createPokemon(dto);
    return new ResponseEntity<>(createdPokemon, HttpStatus.CREATED); // Code 201
  }

  // Liste de tous les Pokémon
  @GetMapping // [cite: 26]
  public ResponseEntity<List<PokemonOutputDto>> getAllPokemons() {
    return ResponseEntity.ok(pokemonService.getAllPokemons()); // Code 200
  }

  // Supprimer un Pokémon
  @DeleteMapping("/{id}") // [cite: 27]
  public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
    pokemonService.deletePokemon(id);
    return ResponseEntity.noContent().build(); // Code 204
  }
  // Recherche par Type
  @GetMapping("/type/{type}") //
  public ResponseEntity<List<PokemonOutputDto>> getPokemonsByType(@PathVariable com.example.b3pokemon.enumeration.PokemonType type) {
    return ResponseEntity.ok(pokemonService.getPokemonsByType(type));
  }
}