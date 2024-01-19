package com.app.mycar.service;

import com.app.mycar.data.EmpreendedorEntity;
import com.app.mycar.data.EmpreendedorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpreendedorService {

    @Autowired
    EmpreendedorRepository empreendedorRepository;

    //1º - LISTAR todos os registros da base de dados
    public List<EmpreendedorEntity> getAllEmpreendedor() {
        return empreendedorRepository.findAll();
    }

    //2º - ADICIONAR novo registro
    public EmpreendedorEntity newEmpreendedor(EmpreendedorEntity empreendedor) {
        empreendedor.setId(null);
        empreendedorRepository.save(empreendedor);
        return empreendedor;
    }

    //3º - Pesquisar por ID -> Usado para Deletar e Atualizar
    public EmpreendedorEntity getIdEmpreendedor(Integer id) {
        return empreendedorRepository.findById(id).orElse(null);
    }

    //4º - DELETAR registro
    public void deletEmpreendedor(Integer id) {
        EmpreendedorEntity empreendedor = getIdEmpreendedor(id);
        empreendedorRepository.deleteById(empreendedor.getId());
    }

    //5º - ATUALIZAR registro
    public EmpreendedorEntity updateEmpreendedor(Integer id, EmpreendedorEntity empreendedorPush) {
        EmpreendedorEntity empreendedor = getIdEmpreendedor(id);
        empreendedor.setTipo_emp(empreendedorPush.getTipo_emp());
        empreendedor.setNome(empreendedorPush.getNome());
        empreendedor.setCpf(empreendedorPush.getCpf());
        empreendedor.setData_cadastro(empreendedorPush.getData_cadastro());
        empreendedorRepository.save(empreendedor);
        return empreendedor;
    }

}
