package com.br.maisAcademiaPratiModel;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private LocalDate dataCriacao;

    // Relacionamento com Aluno (uma ficha pertence a um aluno)
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Ficha() {}

    public Ficha(String descricao, LocalDate dataCriacao, Aluno aluno) {
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.aluno = aluno;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
