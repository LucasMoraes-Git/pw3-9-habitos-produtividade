package br.com.etechas.habito_produtividade.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "TBL_HABITO")
@Getter
@Setter
@Entity

public class Habito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HABITO")
    private Long id;

    @Column(name = "TX_DESCRICAO")
    private String descricao;

    @Column(name = "NR_DIAS_CONSECUTIVOS")
    private Integer diasConsecutivos = 0;

    @Column(name = "NR_META_DIAS")
    private Integer metaDias;

    @Column(name = "FL_CONQUISTA_ALCANCADA")
    private Boolean conquistaAlcancada = false;
}
