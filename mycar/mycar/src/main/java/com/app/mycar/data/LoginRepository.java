package com.app.mycar.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {

    LoginEntity findByUsuarioAndSenhaAndTipo(String usuario, String senha, String tipo);

}