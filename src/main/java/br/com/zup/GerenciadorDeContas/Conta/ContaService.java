package br.com.zup.GerenciadorDeContas.Conta;

import br.com.zup.GerenciadorDeContas.enuns.Status;
import br.com.zup.GerenciadorDeContas.exception.ContaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
    public Conta localizarContaPorId(int id) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isPresent()) {
            return contaOptional.get();
        }

        throw new ContaNaoEncontradaException("Conta não encontrada!");
    }
    public Conta pagarConta(int id) {
        Conta conta = localizarContaPorId(id);
        conta.setStatus(Status.PAGO);
        conta.setDataDePagamento(formatarDataEHora());
        contaRepository.save(conta);

        return conta;
    }
    public LocalDateTime formatarDataEHora() {
        LocalDateTime dataAgora = LocalDateTime.now();
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataDePagamento = dataAgora.format(formatar);

        return LocalDateTime.parse(dataDePagamento, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


}
