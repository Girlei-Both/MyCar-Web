package com.app.mycar.controller;

import com.app.mycar.data.EmpreendedorEntity;
import com.app.mycar.service.EmpreendedorService;
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
@RequestMapping("mycarweb/empreendedor")
public class EmpreendedorController {
    
    @Autowired
    EmpreendedorService empreendedorService;
    
    //1º - LISTAR todos os registros da base de dados
    @GetMapping("/listar")
    public ResponseEntity<List> getAllEmpreendedor() {
        List<EmpreendedorEntity> empreendedores = empreendedorService.getAllEmpreendedor();
        return new ResponseEntity<>(empreendedores, HttpStatus.OK);
    }
    
    //2º - ADICIONAR novo registro
    @PostMapping("/adicionar")
    public ResponseEntity<EmpreendedorEntity> newEmpreendedor(@Valid @RequestBody EmpreendedorEntity empreendedor) {
        var novoEmpreendedor = empreendedorService.newEmpreendedor(empreendedor);
        return new ResponseEntity<>(novoEmpreendedor, HttpStatus.CREATED);
    }
    
    //3º - Pesquisar por ID -> Usado para Deletar e Atualizar
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<EmpreendedorEntity> getIdEmpreendedor(@PathVariable Integer id) {
        EmpreendedorEntity empreendedor = empreendedorService.getIdEmpreendedor(id);
        return new ResponseEntity<>(empreendedor, HttpStatus.OK);
    }
    
    //4º - DELETAR registro
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletEmpreendedor(@PathVariable Integer id) {
        empreendedorService.deletEmpreendedor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //5º - ATUALIZAR registro
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EmpreendedorEntity> updateEmpreendedor(@PathVariable Integer id, @RequestBody EmpreendedorEntity empreendedor) {
        var atualizaEmpreendedor = empreendedorService.updateEmpreendedor(id, empreendedor);
        return new ResponseEntity<>(atualizaEmpreendedor, HttpStatus.OK);
    }
    
}