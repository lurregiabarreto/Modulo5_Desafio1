package br.com.zup.GerenciadorDeContas.Conta;


import br.com.zup.GerenciadorDeContas.dtos.ContaDTO;
import br.com.zup.GerenciadorDeContas.dtos.ResumoContaDTO;
import br.com.zup.GerenciadorDeContas.dtos.SaidaContaDTO;
import br.com.zup.GerenciadorDeContas.dtos.StatusContaDTO;
import br.com.zup.GerenciadorDeContas.enuns.Status;
import br.com.zup.GerenciadorDeContas.enuns.Tipo;
import br.com.zup.GerenciadorDeContas.exception.StatusIncorretoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaidaContaDTO cadastrarConta(@RequestBody  @Valid ContaDTO contaDTO) {
        Conta conta = modelMapper.map(contaDTO, Conta.class);
        SaidaContaDTO respostaCadastroDTO = modelMapper.map(contaService.cadastrarConta(conta), SaidaContaDTO.class);

        return respostaCadastroDTO;

    }

    @GetMapping
    public List<ResumoContaDTO> buscarContasCadastradas(@RequestParam(required = false) Status status,
                                                        @RequestParam(required = false) Tipo tipo,
                                                        @RequestParam(required = false) Double valor) {
        List<ResumoContaDTO> listaResumo = new ArrayList<>();

        for (Conta conta : contaService.buscarContasCadastradas()) {
            ResumoContaDTO resumo = modelMapper.map(conta, ResumoContaDTO.class);
            listaResumo.add(resumo);
        }
        return listaResumo;
    }
    @PutMapping("/{id}")
    public SaidaContaDTO pagarConta(@RequestBody StatusContaDTO statusContaDTO, @PathVariable int id) {
        if (statusContaDTO.getStatus() != Status.PAGO) {
            throw new StatusIncorretoException("Deve ser informado status: PAGO");
        }
        SaidaContaDTO respostaCadastroDTO = modelMapper.map(contaService.pagarConta(id), SaidaContaDTO.class);
        return respostaCadastroDTO;

    }

}