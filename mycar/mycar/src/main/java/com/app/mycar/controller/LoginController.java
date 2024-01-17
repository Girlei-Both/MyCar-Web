package com.app.mycar.controller;

import com.app.mycar.data.LoginEntity;
import com.app.mycar.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mycarweb/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    //1º AUTENTICAR login
    @PostMapping("/autenticar")
    public ResponseEntity<String> autenticar(@RequestParam String usuario, @RequestParam String senha, @RequestParam String tipo, HttpServletRequest request) {
        LoginEntity loginEntity = loginService.autenticar(usuario, senha, tipo);

        if (loginEntity != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", loginEntity.getUsuario());
            return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Falha na autenticação", HttpStatus.UNAUTHORIZED);
        }
    }
    
    //2º - ADICIONAR novo registro
    @PostMapping("/adicionar")
    public ResponseEntity<LoginEntity> newLogin(@Valid @RequestBody LoginEntity login) {
        var novoLogin = loginService.newLogin(login);
        return new ResponseEntity<>(novoLogin, HttpStatus.CREATED);
    }
    
}