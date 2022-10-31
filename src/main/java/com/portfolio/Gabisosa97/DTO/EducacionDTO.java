package com.portfolio.Gabisosa97.DTO;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EducacionDTO {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;
}
