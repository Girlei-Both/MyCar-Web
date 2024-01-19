package com.app.mycar.controller;

import com.app.mycar.data.ServicoEntity;
import com.app.mycar.service.ServicoService;
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
public class ServicoWebControl {

    @Autowired
    ServicoService servicoService;

    //1º - Abre a página onde LISTA todos os registros da base de dados
    @GetMapping("/formListaServico")
    public String formListaServico(Model model) {
        model.addAttribute("listarServicos", servicoService.getAllServico());
        return "servico-listar";
    }

    //2º - Abre a página onde ADICIONA um novo registro
    @GetMapping("/formInsereServico")
    public String formInsereServico(Model model) {
        ServicoEntity servico = new ServicoEntity();
        model.addAttribute("servico", servico);
        return "servico-inserir";
    }

    //3º - Abre a página onde ATUALIZA um registro
    @GetMapping("/formAtualizaServico/{id}")
    public String formAtualizaServico(@PathVariable(value = "id") Integer id, Model model) {
        ServicoEntity servico = servicoService.getIdServico(id);
        model.addAttribute("servico", servico);
        return "servico-atualizar";
    }

    //4º - Ação que ADICIONA novo registro
    @PostMapping("/salvarServico")
    public String salvarServico(@Valid @ModelAttribute("servico") ServicoEntity servico, BindingResult result) {
        if (result.hasErrors()) {
            return "servico-inserir";
        }
        if (servico.getId() == null) {
            servicoService.newServico(servico);
        } else {
            servicoService.updateServico(servico.getId(), servico);
        }
        return "redirect:/formListaServico";
    }

    //5º - Ação que DELETA um registro
    @GetMapping("/deletarServico/{id}")
    public String deletarServico(@PathVariable(value = "id") Integer id) {
        servicoService.deletServico(id);
        return "redirect:/formListaServico";
    }

}
