package com.example.b3pokemon.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainer")
public class TrainerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 100)
  private String city;

  @Column(name = "experience_points", nullable = false)
  private Integer experiencePoints = 0;

  // Relation inverse : Un dresseur possède plusieurs Pokémon
  @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
  private List<PokemonEntity> pokemons;

  // Getters et Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }

  public Integer getExperiencePoints() { return experiencePoints; }
  public void setExperiencePoints(Integer experiencePoints) { this.experiencePoints = experiencePoints; }

  public List<PokemonEntity> getPokemons() { return pokemons; }
  public void setPokemons(List<PokemonEntity> pokemons) { this.pokemons = pokemons; }
}