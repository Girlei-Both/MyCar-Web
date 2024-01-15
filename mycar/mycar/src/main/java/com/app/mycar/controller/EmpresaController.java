package com.app.mycar.controller;

import com.app.mycar.data.EmpresaEntity;
import com.app.mycar.service.EmpresaService;
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
@RequestMapping("mycarweb/empresa")
public class EmpresaController {
    
    @Autowired
    EmpresaService empresaService;
    
    //1º - LISTAR todos os registros da base de dados
    @GetMapping("/listar")
    public ResponseEntity<List> getAllEmpresa() {
        List<EmpresaEntity> empresas = empresaService.getAllEmpresa();
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }
    
    //2º - ADICIONAR novo registro
    @PostMapping("/adicionar")
    public ResponseEntity<EmpresaEntity> newEmpresa(@Valid @RequestBody EmpresaEntity empresa) {
        var novaEmpresa = empresaService.newEmpresa(empresa);
        return new ResponseEntity<>(novaEmpresa, HttpStatus.CREATED);
    }
    
    //3º - Pesquisar por ID -> Usado para Deletar e Atualizar
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<EmpresaEntity> getIdEmpresa(@PathVariable Integer id) {
        EmpresaEntity empresa = empresaService.getIdEmpresa(id);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
    
    //4º - DELETAR registro
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletEmpresa(@PathVariable Integer id) {
        empresaService.deletEmpresa(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //5º - ATUALIZAR registro
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EmpresaEntity> updateEmpresa(@PathVariable Integer id, @RequestBody EmpresaEntity empresa) {
        var atualizaEmpresa = empresaService.updateEmpresa(id, empresa);
        return new ResponseEntity<>(atualizaEmpresa, HttpStatus.OK);
    }
    
}