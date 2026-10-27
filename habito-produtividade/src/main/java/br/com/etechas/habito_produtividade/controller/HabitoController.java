package br.com.etechas.habito_produtividade.controller;

import br.com.etechas.habito_produtividade.dtos.HabitoRequestDTO;
import br.com.etechas.habito_produtividade.dtos.HabitoResponseDTO;
import br.com.etechas.habito_produtividade.service.HabitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/habitos")
@RestController
public class HabitoController {

    @Autowired
    private HabitoService service;

    @GetMapping
    public ResponseEntity<List<HabitoResponseDTO>> buscarHabitos()
    {
        return ResponseEntity.ok(service.buscarHabitos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitoResponseDTO> buscarHabitoPorId(@PathVariable Long id)
    {
        var habito = service.buscarHabitoPorId(id);
        return (habito.isPresent()) ? ResponseEntity.ok(habito.get()): ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<HabitoResponseDTO> cadastrarHabito(@RequestBody HabitoRequestDTO request)
    {
        HabitoResponseDTO habitoCriado = service.cadastrarHabito(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(habitoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitoResponseDTO> editarHabito(@RequestBody HabitoRequestDTO request, @PathVariable Long id)
    {
        var habitoEditado = service.editarHabito(id, request);
        return (habitoEditado.isPresent()) ? ResponseEntity.ok(habitoEditado.get()): ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HabitoResponseDTO> deletarHabito(@PathVariable Long id)
    {
        var habitoDeletado = service.deletarHabito(id);
        return (habitoDeletado.isPresent()) ? ResponseEntity.ok(habitoDeletado.get()): ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/checkin")
    public ResponseEntity<HabitoResponseDTO> realizarCheckIn(@PathVariable Long id)
    {
        var habitoCheckIn = service.realizarCheckIn(id);
        return (habitoCheckIn.isPresent()) ? ResponseEntity.ok(habitoCheckIn.get()): ResponseEntity.notFound().build();
    }


}
