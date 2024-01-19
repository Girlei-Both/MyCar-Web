package com.app.mycar.controller;

import com.app.mycar.data.ServicoEntity;
import com.app.mycar.service.ServicoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mycarweb/servico")
public class ServicoController {

    @Autowired
    ServicoService servicoService;

    //1º - LISTAR todos os registros da base de dados
    @GetMapping("/listar")
    public ResponseEntity<List> getAllServico() {
        List<ServicoEntity> servicos = servicoService.getAllServico();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    //2º - ADICIONAR novo registro
    @PostMapping("/adicionar")
    public ResponseEntity<ServicoEntity> newServico(@Valid @RequestBody ServicoEntity servico) {
        var novoServico = servicoService.newServico(servico);
        return new ResponseEntity<>(novoServico, HttpStatus.CREATED);
    }

    //3º - Pesquisar por ID -> Usado para Deletar e Atualizar
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<ServicoEntity> getIdServico(@PathVariable Integer id) {
        ServicoEntity servico = servicoService.getIdServico(id);
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    //4º - DELETAR registro
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletServico(@PathVariable Integer id) {
        servicoService.deletServico(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //5º - ATUALIZAR registro
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ServicoEntity> updateServico(@PathVariable Integer id, @RequestBody ServicoEntity servico) {
        var atualizaServico = servicoService.updateServico(id, servico);
        return new ResponseEntity<>(atualizaServico, HttpStatus.OK);
    }

}
