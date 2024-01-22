package com.app.mycar.controller;

import com.app.mycar.data.LoginEntity;
import com.app.mycar.service.LoginService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginWebControl {

    @Autowired
    LoginService loginService;

    @GetMapping("/viewLoginPage")
    public String viewLoginPage(Model model) {
        model.addAttribute("loginEntity", new LoginEntity());
        return "login";
    }

    @PostMapping("/autenticar")
    public String autenticar(LoginEntity loginEntity, HttpSession session, Model model) {
        LoginEntity authenticatedUser = loginService.autenticar(loginEntity.getUsuario(), loginEntity.getSenha(), loginEntity.getTipo());

        if (authenticatedUser != null) {
            session.setAttribute("tipoUsuario", authenticatedUser.getTipo());
            if (null == loginEntity.getTipo()) {
                return "redirect:/erroPage";
            } else return switch (loginEntity.getTipo()) {
                case "1" -> "redirect:/clienteHomePage";
                case "2" -> "redirect:/empreendedorHomePage";
                default -> "redirect:/erroPage";
            };
        } else {
            model.addAttribute("mensagemErro", "Falha na autenticação. Verifique o usuário e senha.");
            return "redirect:/erroPage";
        }
    }

    //2º - Abre a página onde ADICIONA um novo registro
    @GetMapping("/formInsereLogin")
    public String formInsereLogin(Model model) {
        LoginEntity login = new LoginEntity();
        model.addAttribute("login", login);
        return "login-inserir";
    }

    //4º - Ação que ADICIONA novo registro
    @PostMapping("/salvarLogin")
    public String salvarLogin(@Valid @ModelAttribute("login") LoginEntity login, BindingResult result) {
        if (result.hasErrors()) {
            return "login-inserir";
        }
        if (login.getId() == null) {
            loginService.newLogin(login);
        } else {
            loginService.updateLogin(login.getId(), login);
        }
        return "redirect:/";
    }

}
