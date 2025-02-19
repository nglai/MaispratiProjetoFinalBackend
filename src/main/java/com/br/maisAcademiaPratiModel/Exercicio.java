package com.br.maisAcademiaPratiModel;

import jakarta.persistence.*;

@Entity
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    // Relacionamento com Ficha (um exercício pertence a uma ficha)
    @ManyToOne
    @JoinColumn(name = "ficha_id")
    private Ficha ficha;

    public Exercicio() {}

    public Exercicio(String nome, String descricao, Ficha ficha) {
        this.nome = nome;
        this.descricao = descricao;
        this.ficha = ficha;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Ficha getFicha() {
        return ficha;
    }
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
}

