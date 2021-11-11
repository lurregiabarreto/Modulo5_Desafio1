package br.com.zup.GerenciadorDeContas.Conta;


import br.com.zup.GerenciadorDeContas.dtos.SaidaContaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaidaContaDTO cadastrarConta(@RequestBody SaidaContaDTO saidaContaDTO) {
        Conta conta = modelMapper.map(saidaContaDTO, Conta.class);
        SaidaContaDTO respostaCadastroDTO = modelMapper.map(contaService.cadastrarConta(conta), SaidaContaDTO.class);

        return respostaCadastroDTO;
    }
}