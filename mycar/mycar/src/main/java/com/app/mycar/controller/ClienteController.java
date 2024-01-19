package com.app.mycar.controller;

import com.app.mycar.data.ClienteEntity;
import com.app.mycar.service.ClienteService;
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
@RequestMapping("mycarweb/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //1º - LISTAR todos os registros da base de dados
    @GetMapping("/listar")
    public ResponseEntity<List> getAllCliente() {
        List<ClienteEntity> clientes = clienteService.getAllCliente();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    //2º - ADICIONAR novo registro
    @PostMapping("/adicionar")
    public ResponseEntity<ClienteEntity> newCliente(@Valid @RequestBody ClienteEntity cliente) {
        var novoCliente = clienteService.newCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    //3º - Pesquisar por ID -> Usado para Deletar e Atualizar
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<ClienteEntity> getIdCliente(@PathVariable Integer id) {
        ClienteEntity cliente = clienteService.getIdCliente(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    //4º - DELETAR registro
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletCliente(@PathVariable Integer id) {
        clienteService.deletCliente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //5º - ATUALIZAR registro
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteEntity> updateCliente(@PathVariable Integer id, @RequestBody ClienteEntity cliente) {
        var atualizaCliente = clienteService.updateCliente(id, cliente);
        return new ResponseEntity<>(atualizaCliente, HttpStatus.OK);
    }

}
