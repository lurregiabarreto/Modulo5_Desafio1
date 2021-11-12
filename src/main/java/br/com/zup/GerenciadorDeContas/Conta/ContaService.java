package br.com.zup.GerenciadorDeContas.Conta;

import br.com.zup.GerenciadorDeContas.enuns.Status;
import br.com.zup.GerenciadorDeContas.enuns.Tipo;
import br.com.zup.GerenciadorDeContas.exception.ContaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public List<Conta> buscarContasCadastradas(Status status, Tipo tipo, Double valor) {
        if (status != null) {
            return contaRepository.findAllByStatus(status);
        }
        if (tipo != null) {
            return contaRepository.findAllByTipo(tipo);
        }
        if (valor != null) {
            return contaRepository.findAllByValorAproximado(valor);
        }
        Iterable<Conta> listaContas = contaRepository.findAll();
        return (List<Conta>) listaContas;
    }

    public Conta localizarContaPorId(int id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada!"));
    }

    public Conta pagarConta(int id) {
        Conta conta = localizarContaPorId(id);
        conta.setStatus(Status.PAGO);
        conta.setDataDePagamento(LocalDateTime.now());
        contaRepository.save(conta);

        return conta;
    }

    public void deletarConta(int id) {
        if (contaRepository.existsById(id)) {
            contaRepository.deleteById(id);
        } else {
            throw new ContaNaoEncontradaException("Conta não localizada!");
        }


    }
}