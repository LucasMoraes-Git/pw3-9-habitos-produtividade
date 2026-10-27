package br.com.etechas.habito_produtividade.service;

import br.com.etechas.habito_produtividade.dtos.HabitoRequestDTO;
import br.com.etechas.habito_produtividade.dtos.HabitoResponseDTO;
import br.com.etechas.habito_produtividade.entity.Habito;
import br.com.etechas.habito_produtividade.mapper.HabitoMapper;
import br.com.etechas.habito_produtividade.repository.HabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitoService {

    @Autowired
    private HabitoRepository repository;

    @Autowired
    private HabitoMapper mapper;

    public List<HabitoResponseDTO> buscarHabitos()
    {
        return repository.findAll().stream().map(mapper::paraResponse).toList();
    }

    public Optional<HabitoResponseDTO> buscarHabitoPorId(Long id)
    {
        var habito = repository.findById(id);

        return habito.map(mapper::paraResponse);
    }

    public HabitoResponseDTO cadastrarHabito(HabitoRequestDTO request)
    {
        Habito habitoCadastrado = repository.save(mapper.paraEntidade(request));

        habitoCadastrado.setDiasConsecutivos(0);
        habitoCadastrado.setConquistaAlcancada(false);

        return mapper.paraResponse(habitoCadastrado);
    }

    public Optional<HabitoResponseDTO> editarHabito(Long id, HabitoRequestDTO request)
    {
         return repository.findById(id).map(meuHabito -> {
            meuHabito.setDescricao(request.descricao());
            meuHabito.setMetaDias(request.metaDias());
            meuHabito.setConquistaAlcancada(meuHabito.getDiasConsecutivos() >= meuHabito.getMetaDias());
            return mapper.paraResponse(repository.save(meuHabito));
        });
    }

    public Optional<HabitoResponseDTO> deletarHabito(Long id)
    {
        var habito = repository.findById(id);

        if (habito.isPresent())
        {
            repository.delete(habito.get());
        }
        return habito.map(mapper::paraResponse);
    }

    public Optional<HabitoResponseDTO> realizarCheckIn(Long id)
    {
        return repository.findById(id).map(meuHabito -> {
            meuHabito.setDiasConsecutivos(meuHabito.getDiasConsecutivos() + 1);
            meuHabito.setConquistaAlcancada(meuHabito.getDiasConsecutivos() >= meuHabito.getMetaDias());
            return mapper.paraResponse(repository.save(meuHabito));
        });
    }


}
