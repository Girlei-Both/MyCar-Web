package com.app.mycar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        return "index";
    }

    @RequestMapping("/clienteHomePage")
    public String clienteHomePage(Model model) {
        return "cliente-home";
    }

    @RequestMapping("/empreendedorHomePage")
    public String empreendedorHomePage(Model model) {
        return "empreendedor-home";
    }

    @RequestMapping("/erroPage")
    public String erroPage(Model model) {
        return "erro";
    }

}
