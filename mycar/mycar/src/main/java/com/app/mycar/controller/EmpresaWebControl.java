package com.app.mycar.controller;

import com.app.mycar.data.EmpresaEntity;
import com.app.mycar.service.EmpresaService;
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
public class EmpresaWebControl {

    private static final Logger logger = LoggerFactory.getLogger(ClienteWebControl.class);

    @Autowired
    EmpresaService empresaService;

    //1º - Abre a página onde LISTA todos os registros da base de dados
    @GetMapping("/formListaEmpresa")
    public String formListaEmpresa(Model model, HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        logger.info("Tipo de usuário: {}", tipoUsuario);
        if ("2".equals(tipoUsuario)) {
            model.addAttribute("listarEmpresas", empresaService.getAllEmpresa());
            return "empresa-listar";
        } else {
            return "redirect:/erroPage";
        }
    }

    //2º - Abre a página onde ADICIONA um novo registro
    @GetMapping("/formInsereEmpresa")
    public String formInsereEmpresa(Model model, HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        logger.info("Tipo de usuário: {}", tipoUsuario);
        if ("2".equals(tipoUsuario)) {
            EmpresaEntity empresa = new EmpresaEntity();
            model.addAttribute("empresa", empresa);
            return "empresa-inserir";
        } else {
            return "redirect:/erroPage";
        }
    }

    //3º - Abre a página onde ATUALIZA um registro
    @GetMapping("/formAtualizaEmpresa/{id}")
    public String formAtualizaEmpresa(@PathVariable(value = "id") Integer id, Model model, HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        logger.info("Tipo de usuário: {}", tipoUsuario);
        if ("2".equals(tipoUsuario)) {
            EmpresaEntity empresa = empresaService.getIdEmpresa(id);
            model.addAttribute("empresa", empresa);
            return "empresa-atualizar";
        } else {
            return "redirect:/erroPage";
        }
    }

    //4º - Ação que ADICIONA novo registro
    @PostMapping("/salvarEmpresa")
    public String salvarEmpresa(@Valid @ModelAttribute("empresa") EmpresaEntity empresa, BindingResult result) {
        if (result.hasErrors()) {
            return "empresa-inserir";
        }
        if (empresa.getId() == null) {
            empresaService.newEmpresa(empresa);
        } else {
            empresaService.updateEmpresa(empresa.getId(), empresa);
        }
        return "redirect:/formListaEmpresa";
    }

    //5º - Ação que DELETA um registro
    @GetMapping("/deletarEmpresa/{id}")
    public String deletarEmpresa(@PathVariable(value = "id") Integer id) {
        empresaService.deletEmpresa(id);
        return "redirect:/formListaEmpresa";
    }

}
