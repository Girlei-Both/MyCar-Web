package com.app.mycar.controller;

import com.app.mycar.data.EmpreendedorEntity;
import com.app.mycar.service.EmpreendedorService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ClienteWebControl.class);

    @Autowired
    EmpreendedorService empreendedorService;

    //1º - Abre a página onde LISTA todos os registros da base de dados
    @GetMapping("/formListaEmpreendedor")
    public String formListaEmpreendedor(Model model, HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        logger.info("Tipo de usuário: {}", tipoUsuario);
        if ("2".equals(tipoUsuario)) {
            model.addAttribute("listarEmpreendedores", empreendedorService.getAllEmpreendedor());
            return "empreendedor-listar";
        } else {
            return "redirect:/erroPage";
        }
    }

    //2º - Abre a página onde ADICIONA um novo registro
    @GetMapping("/formInsereEmpreendedor")
    public String formInsereEmpreendedor(Model model, HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        logger.info("Tipo de usuário: {}", tipoUsuario);
        if ("2".equals(tipoUsuario)) {
            EmpreendedorEntity empreendedor = new EmpreendedorEntity();
            model.addAttribute("empreendedor", empreendedor);
            return "empreendedor-inserir";
        } else {
            return "redirect:/erroPage";
        }
    }

    //3º - Abre a página onde ATUALIZA um registro
    @GetMapping("/formAtualizaEmpreendedor/{id}")
    public String formAtualizaEmpreendedor(@PathVariable(value = "id") Integer id, Model model, HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        logger.info("Tipo de usuário: {}", tipoUsuario);
        if ("2".equals(tipoUsuario)) {
            EmpreendedorEntity empreendedor = empreendedorService.getIdEmpreendedor(id);
            model.addAttribute("empreendedor", empreendedor);
            return "empreendedor-atualizar";
        } else {
            return "redirect:/erroPage";
        }
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
