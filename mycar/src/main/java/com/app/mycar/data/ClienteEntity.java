package com.app.mycar.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cpf;
    private String data_nascimento;
    private String genereo;
    private String telefone;
    private String celular;
    private String email;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String data_cadastro;

}