package br.com.etechas.habito_produtividade.repository;

import br.com.etechas.habito_produtividade.entity.Habito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitoRepository extends JpaRepository<Habito, Long> {
}
