package br.com.etechas.habito_produtividade.mapper;

import br.com.etechas.habito_produtividade.dtos.HabitoRequestDTO;
import br.com.etechas.habito_produtividade.dtos.HabitoResponseDTO;
import br.com.etechas.habito_produtividade.entity.Habito;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HabitoMapper {

    HabitoResponseDTO paraResponse(Habito entidade);
    Habito paraEntidade(HabitoRequestDTO request);

}
