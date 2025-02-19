
package com.br.maisAcademiaPratiModel;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataAvaliacao;
    private Double nota;
    private String comentario;

    // Relacionamento com Aluno (uma avaliação pertence a um aluno)
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Avaliacao() {}

    public Avaliacao(LocalDate dataAvaliacao, Double nota, String comentario, Aluno aluno) {
        this.dataAvaliacao = dataAvaliacao;
        this.nota = nota;
        this.comentario = comentario;
        this.aluno = aluno;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }
    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
    public Double getNota() {
        return nota;
    }
    public void setNota(Double nota) {
        this.nota = nota;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
