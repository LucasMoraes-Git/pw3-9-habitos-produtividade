package br.com.etechas.habito_produtividade.dtos;

import jakarta.persistence.Column;

public record HabitoResponseDTO(
         String descricao,
         Integer diasConsecutivos,
         Integer metaDias,
         Boolean conquistaAlcancada
) {
}
