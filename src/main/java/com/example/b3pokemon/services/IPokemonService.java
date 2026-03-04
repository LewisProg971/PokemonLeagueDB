package com.example.b3pokemon.services;

import com.example.b3pokemon.dto.input.PokemonInputDto;
import com.example.b3pokemon.dto.output.PokemonOutputDto;
import java.util.List;

public interface IPokemonService {
  PokemonOutputDto createPokemon(PokemonInputDto dto);
  List<PokemonOutputDto> getAllPokemons();
  void deletePokemon(Long id);
  List<PokemonOutputDto> getPokemonsByType(com.example.b3pokemon.enumeration.PokemonType type);
}