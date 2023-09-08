package com.br.myteam.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PlayerRecordDto(@NotBlank String name,
                              @NotBlank String surname,
                              @NotBlank String position,
                              @NotBlank String team,
                              @NotNull int number,
                              @NotNull int overall,
                              @NotNull BigDecimal wage) {

                              }
