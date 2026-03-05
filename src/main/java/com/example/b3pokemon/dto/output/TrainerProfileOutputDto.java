package com.example.b3pokemon.dto.output;

import com.example.b3pokemon.enumeration.PokemonType;

import java.util.List;

public class TrainerProfileOutputDto {
  private String name;
  private String city;
  private Integer experiencePoints;
  private List<PokemonOutputDto> team;
  private PokemonType favoriteType;
  private Double powerScore;
  private Boolean legendStatus;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Integer getExperiencePoints() {
    return experiencePoints;
  }

  public void setExperiencePoints(Integer experiencePoints) {
    this.experiencePoints = experiencePoints;
  }

  public List<PokemonOutputDto> getTeam() {
    return team;
  }

  public void setTeam(List<PokemonOutputDto> team) {
    this.team = team;
  }

  public PokemonType getFavoriteType() {
    return favoriteType;
  }

  public void setFavoriteType(PokemonType favoriteType) {
    this.favoriteType = favoriteType;
  }

  public Double getPowerScore() {
    return powerScore;
  }

  public void setPowerScore(Double powerScore) {
    this.powerScore = powerScore;
  }

  public Boolean getLegendStatus() {
    return legendStatus;
  }

  public void setLegendStatus(Boolean legendStatus) {
    this.legendStatus = legendStatus;
  }
}