package com.app.mycar.controller;

import com.app.mycar.data.VeiculoEntity;
import com.app.mycar.service.VeiculoService;
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
@RequestMapping("mycarweb/veiculo")
public class VeiculoController {

    @Autowired
    VeiculoService veiculoService;
    
    //1º - LISTAR todos os registros da base de dados
    @GetMapping("/listar")
    public ResponseEntity<List> getAllVeiculo() {
        List<VeiculoEntity> veiculos = veiculoService.getAllVeiculo();
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }
    
    //2º - ADICIONAR novo registro
    @PostMapping("/adicionar")
    public ResponseEntity<VeiculoEntity> newVeiculo(@Valid @RequestBody VeiculoEntity veiculo) {
        var novoVeiculo = veiculoService.newVeiculo(veiculo);
        return new ResponseEntity<>(novoVeiculo, HttpStatus.CREATED);
    }
    
    //3º - Pesquisar por ID -> Usado para Deletar e Atualizar
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<VeiculoEntity> getIdVeiculo(@PathVariable Integer id) {
        VeiculoEntity veiculo = veiculoService.getIdVeiculo(id);
        return new ResponseEntity<>(veiculo, HttpStatus.OK);
    }
    
    //4º - DELETAR registro
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletVeiculo(@PathVariable Integer id) {
        veiculoService.deletVeiculo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //5º - ATUALIZAR registro
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<VeiculoEntity> updateVeiculo(@PathVariable Integer id, @RequestBody VeiculoEntity veiculo) {
        var atualizaVeiculo = veiculoService.updateVeiculo(id, veiculo);
        return new ResponseEntity<>(atualizaVeiculo, HttpStatus.OK);
    }
    
}