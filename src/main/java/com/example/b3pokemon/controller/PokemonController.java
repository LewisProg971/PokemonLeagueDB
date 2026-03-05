package com.example.b3pokemon.controller;

import com.example.b3pokemon.dto.input.PokemonInputDto;
import com.example.b3pokemon.dto.output.PokemonOutputDto;
import com.example.b3pokemon.enumeration.PokemonType;
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

  @PostMapping
  public ResponseEntity<PokemonOutputDto> createPokemon(@RequestBody PokemonInputDto dto) {
    PokemonOutputDto createdPokemon = pokemonService.createPokemon(dto);
    return new ResponseEntity<>(createdPokemon, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<PokemonOutputDto>> getAllPokemons() {
    return ResponseEntity.ok(pokemonService.getAllPokemons());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
    pokemonService.deletePokemon(id);
    return ResponseEntity.noContent().build();
  }


  @GetMapping("/type/{type}")
  public ResponseEntity<List<PokemonOutputDto>> getPokemonsByType(@PathVariable PokemonType type) {
    return ResponseEntity.ok(pokemonService.getPokemonsByType(type));
  }
}