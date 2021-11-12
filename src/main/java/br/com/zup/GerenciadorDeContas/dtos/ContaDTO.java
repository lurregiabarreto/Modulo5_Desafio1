package br.com.zup.GerenciadorDeContas.dtos;

import br.com.zup.GerenciadorDeContas.enuns.Tipo;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class ContaDTO {
    @Size(min=3, message = "Nome deve ter acima de 3 letras ")
    @NotBlank
    private String nome;
    @Min(value = 1, message = "O valor é obrigatório e deve ser maior ou igual a R$ 1.00")
    private double valor;
    @NotNull(message = "Tipo é obrigatório")
    private Tipo tipo;
    @NotNull
    private LocalDate dataDeVencimento;

    public ContaDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }
}

