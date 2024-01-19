package com.app.mycar.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Veiculo")
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String marca;
    private String modelo;
    private String versao;
    private String ano_fabricacao;
    private String ano_modelo;
    private String placa;
    private String renavam;
    private String chassis;
    private String cod_motor;
    private String valvulas;
    private String cilindros;
    private String cilindradas;
    private String tracao;
    private String categoria;
    private String especie;
    private String tipo;
    private String cor;
    private String combustivel;
    private String opcionais;
    private String ativo;

}
