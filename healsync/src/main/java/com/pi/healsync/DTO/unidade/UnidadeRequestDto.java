package com.pi.healsync.DTO.unidade;

import com.pi.healsync.models.Endereco;
import com.pi.healsync.DTO.EnderecoRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UnidadeRequestDto {
    private String name;
    private EnderecoRequestDTO endereco;
}
