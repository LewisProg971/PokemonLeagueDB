package com.example.b3pokemon.entities;

import com.example.b3pokemon.enumeration.PokemonType;
import jakarta.persistence.*;

@Entity
@Table(name = "pokemon")
public class PokemonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(length = 100)
  private String nickname;

  @Column(nullable = false)
  private Integer level = 1;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private PokemonType type;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "trainer_id")
  private TrainerEntity trainer;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public TrainerEntity getTrainer() {
    return trainer;
  }

  public void setTrainer(TrainerEntity trainer) {
    this.trainer = trainer;
  }
}