package br.com.zup.GerenciadorDeContas.Conta;

import br.com.zup.GerenciadorDeContas.enuns.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta cadastrarConta(Conta conta) {
        atualizarStatusConta(conta);
        conta.setDataDePagamento(null);
        return contaRepository.save(conta);

    }
    public Status atualizarStatusConta(Conta conta) {
        if (conta.getDataDeVencimento().isBefore(LocalDate.now())) {
            conta.setStatus(Status.VENCIDA);
        } else {
            conta.setStatus(Status.AGUARDANDO);
        }
        return conta.getStatus();

    }
    public List<Conta> buscarContasCadastradas() {
        Iterable<Conta> listaContas = contaRepository.findAll();
        return (List<Conta>) listaContas;
    }


}
