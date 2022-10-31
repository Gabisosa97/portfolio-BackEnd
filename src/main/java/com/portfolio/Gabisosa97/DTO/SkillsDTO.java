package com.portfolio.Gabisosa97.DTO;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class SkillsDTO {

    @NotBlank
    private String nombre;
    @NotBlank
    private int porcentaje;
}
