package br.com.zup.GerenciadorDeContas.config;

public class MensagemErroValidacao {
    private String mensagem;
    private String campo;

    public MensagemErroValidacao(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}

