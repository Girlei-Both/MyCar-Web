package com.app.mycar.service;

import com.app.mycar.data.LoginEntity;
import com.app.mycar.data.LoginRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    //1º - LISTAR todos os registros da base de dados
    public List<LoginEntity> getAllLogin() {
        return loginRepository.findAll();
    }

    //2º - ADICIONAR novo registro
    public LoginEntity newLogin(LoginEntity login) {
        login.setId(null);
        loginRepository.save(login);
        return login;
    }

    //3º - Pesquisar por ID -> Usado para Deletar e Atualizar
    public LoginEntity getIdLogin(Integer id) {
        return loginRepository.findById(id).orElse(null);
    }

    //4º - DELETAR registro
    public void deletLogin(Integer id) {
        LoginEntity login = getIdLogin(id);
        loginRepository.deleteById(login.getId());
    }

    //5º - ATUALIZAR registro
    public LoginEntity updateLogin(Integer id, LoginEntity loginPush) {
        LoginEntity login = getIdLogin(id);
        login.setUsuario(loginPush.getUsuario());
        login.setSenha(loginPush.getSenha());
        login.setTipo(loginPush.getTipo());
        loginRepository.save(login);
        return login;
    }

    //6º - AUTENTICAR login
    public LoginEntity autenticar(String usuario, String senha, String tipo) {
        return loginRepository.findByUsuarioAndSenhaAndTipo(usuario, senha, tipo);
    }

}