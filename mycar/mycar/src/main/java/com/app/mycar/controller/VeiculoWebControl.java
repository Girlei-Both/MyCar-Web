package com.app.mycar.controller;

import com.app.mycar.data.VeiculoEntity;
import com.app.mycar.service.VeiculoService;
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
public class VeiculoWebControl {

    @Autowired
    VeiculoService veiculoService;

    //1º - Abre a página onde LISTA todos os registros da base de dados
    @GetMapping("/formListaVeiculo")
    public String formListaVeiculo(Model model) {
        model.addAttribute("listarVeiculos", veiculoService.getAllVeiculo());
        return "veiculo-listar";
    }

    //2º - Abre a página onde ADICIONA um novo registro
    @GetMapping("/formInsereVeiculo")
    public String formInsereVeiculo(Model model) {
        VeiculoEntity veiculo = new VeiculoEntity();
        model.addAttribute("veiculo", veiculo);
        return "veiculo-inserir";
    }

    //3º - Abre a página onde ATUALIZA um registro
    @GetMapping("/formAtualizaVeiculo/{id}")
    public String formAtualizaVeiculo(@PathVariable(value = "id") Integer id, Model model) {
        VeiculoEntity veiculo = veiculoService.getIdVeiculo(id);
        model.addAttribute("veiculo", veiculo);
        return "veiculo-atualizar";
    }

    //4º - Ação que ADICIONA novo registro
    @PostMapping("/salvarVeiculo")
    public String salvarVeiculo(@Valid @ModelAttribute("veiculo") VeiculoEntity veiculo, BindingResult result) {
        if (result.hasErrors()) {
            return "veiculo-inserir";
        }
        if (veiculo.getId() == null) {
            veiculoService.newVeiculo(veiculo);
        } else {
            veiculoService.updateVeiculo(veiculo.getId(), veiculo);
        }
        return "redirect:/formListaVeiculo";
    }

    //5º - Ação que DELETA um registro
    @GetMapping("/deletarVeiculo/{id}")
    public String deletarVeiculo(@PathVariable(value = "id") Integer id) {
        veiculoService.deletVeiculo(id);
        return "redirect:/formListaVeiculo";
    }
    
}