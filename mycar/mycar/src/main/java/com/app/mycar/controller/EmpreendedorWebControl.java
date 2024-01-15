package com.app.mycar.controller;

import com.app.mycar.data.EmpreendedorEntity;
import com.app.mycar.service.EmpreendedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmpreendedorWebControl {
    
    @Autowired
    EmpreendedorService empreendedorService;
    
    //1º - Abre a página onde LISTA todos os registros da base de dados
    @GetMapping("/formListaEmpreendedor")
    public String formListaEmpreendedor(Model model) {
        model.addAttribute("listarEmpreendedores", empreendedorService.getAllEmpreendedor());
        return "empreendedor-listar";
    }
    
    //2º - Abre a página onde ADICIONA um novo registro
    @GetMapping("/formInsereEmpreendedor")
    public String formInsereEmpreendedor(Model model) {
        EmpreendedorEntity empreendedor = new EmpreendedorEntity();
        model.addAttribute("empreendedor", empreendedor);
        return "empreendedor-inserir";
    }
    
    //3º - Abre a página onde ATUALIZA um registro
    @GetMapping("/formAtualizaEmpreendedor/{id}")
    public String formAtualizaEmpreendedor(@PathVariable(value = "id") Integer id, Model model) {
        EmpreendedorEntity empreendedor = empreendedorService.getIdEmpreendedor(id);
        model.addAttribute("empreendedor", empreendedor);
        return "empreendedor-atualizar";
    }
    
    //4º - Ação que ADICIONA novo registro
    @PostMapping("/salvarEmpreendedor")
    public String salvarEmpreendedor(@Valid @ModelAttribute("empreendedor") EmpreendedorEntity empreendedor, BindingResult result) {
        if (result.hasErrors()) {
            return "empreendedor-inserir";
        }
        if (empreendedor.getId() == null) {
            empreendedorService.newEmpreendedor(empreendedor);
        } else {
            empreendedorService.updateEmpreendedor(empreendedor.getId(), empreendedor);
        }
        return "redirect:/formListaEmpreendedor";
    }
    
    //5º - Ação que DELETA um registro
    @GetMapping("/deletarEmpreendedor/{id}")
    public String deletarEmpreendedor(@PathVariable(value = "id") Integer id) {
        empreendedorService.deletEmpreendedor(id);
        return "redirect:/formListaEmpreendedor";
    }
    
}