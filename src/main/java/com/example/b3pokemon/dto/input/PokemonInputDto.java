package com.example.b3pokemon.dto.input;

import com.example.b3pokemon.enumeration.PokemonType;

public class PokemonInputDto {
  private String name;
  private String nickname;
  private Integer level;
  private PokemonType type;
  private Long trainerId;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public PokemonType getType() {
    return type;
  }

  public void setType(PokemonType type) {
    this.type = type;
  }

  public Long getTrainerId() {
    return trainerId;
  }

  public void setTrainerId(Long trainerId) {
    this.trainerId = trainerId;
  }
}