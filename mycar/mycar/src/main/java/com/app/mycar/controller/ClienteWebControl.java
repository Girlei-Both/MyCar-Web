package com.app.mycar.controller;

import com.app.mycar.data.ClienteEntity;
import com.app.mycar.service.ClienteService;
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
public class ClienteWebControl {

    private static final Logger logger = LoggerFactory.getLogger(ClienteWebControl.class);

    @Autowired
    ClienteService clienteService;

    //1º - Abre a página onde LISTA todos os registros da base de dados
    @GetMapping("/formListaCliente")
    public String formListaCliente(Model model, HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        logger.info("Tipo de usuário: {}", tipoUsuario);
        if ("1".equals(tipoUsuario)) {
            model.addAttribute("listarClientes", clienteService.getAllCliente());
            return "cliente-listar";
        } else {
            return "redirect:/erroPage";
        }
    }

    //2º - Abre a página onde ADICIONA um novo registro
    @GetMapping("/formInsereCliente")
    public String formInsereCliente(Model model, HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        logger.info("Tipo de usuário: {}", tipoUsuario);
        if ("1".equals(tipoUsuario)) {
            ClienteEntity cliente = new ClienteEntity();
            model.addAttribute("cliente", cliente);
            return "cliente-inserir";
        } else {
            return "redirect:/erroPage";
        }
    }

    //3º - Abre a página onde ATUALIZA um registro
    @GetMapping("/formAtualizaCliente/{id}")
    public String formAtualizaCliente(@PathVariable(value = "id") Integer id, Model model, HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        logger.info("Tipo de usuário: {}", tipoUsuario);
        if ("1".equals(tipoUsuario)) {
            ClienteEntity cliente = clienteService.getIdCliente(id);
            model.addAttribute("cliente", cliente);
            return "cliente-atualizar";
        } else {
            return "redirect:/erroPage";
        }
    }

    //4º - Ação que ADICIONA novo registro
    @PostMapping("/salvarCliente")
    public String salvarCliente(@Valid @ModelAttribute("cliente") ClienteEntity cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "cliente-inserir";
        }
        if (cliente.getId() == null) {
            clienteService.newCliente(cliente);
        } else {
            clienteService.updateCliente(cliente.getId(), cliente);
        }
        return "redirect:/formListaCliente";
    }

    //5º - Ação que DELETA um registro
    @GetMapping("/deletarCliente/{id}")
    public String deletarCliente(@PathVariable(value = "id") Integer id) {
        clienteService.deletCliente(id);
        return "redirect:/formListaCliente";
    }

}
