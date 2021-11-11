package br.com.zup.GerenciadorDeContas.dtos;

import br.com.zup.GerenciadorDeContas.enuns.Status;

public class StatusContaDTO {
    private Status status;

    public StatusContaDTO() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
