package com.app.mycar.service;

import com.app.mycar.data.EmpresaEntity;
import com.app.mycar.data.EmpresaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    //1º - LISTAR todos os registros da base de dados
    public List<EmpresaEntity> getAllEmpresa() {
        return empresaRepository.findAll();
    }

    //2º - ADICIONAR novo registro
    public EmpresaEntity newEmpresa(EmpresaEntity empresa) {
        empresa.setId(null);
        empresaRepository.save(empresa);
        return empresa;
    }

    //3º - Pesquisar por ID -> Usado para Deletar e Atualizar
    public EmpresaEntity getIdEmpresa(Integer id) {
        return empresaRepository.findById(id).orElse(null);
    }

    //4º - DELETAR registro
    public void deletEmpresa(Integer id) {
        EmpresaEntity empresa = getIdEmpresa(id);
        empresaRepository.deleteById(empresa.getId());
    }

    //5º - ATUALIZAR registro
    public EmpresaEntity updateEmpresa(Integer id, EmpresaEntity empresaPush) {
        EmpresaEntity empresa = getIdEmpresa(id);
        empresa.setTipo(empresaPush.getTipo());
        empresa.setNome(empresaPush.getNome());
        empresa.setCpf_cnpj(empresaPush.getCpf_cnpj());
        empresa.setData_abertura(empresaPush.getData_abertura());
        empresa.setInscr_estadual(empresaPush.getInscr_estadual());
        empresa.setTelefone(empresaPush.getTelefone());
        empresa.setCelular(empresaPush.getCelular());
        empresa.setEmail(empresaPush.getEmail());
        empresa.setSite(empresaPush.getSite());
        empresa.setEndereco(empresaPush.getEndereco());
        empresa.setNumero(empresaPush.getNumero());
        empresa.setBairro(empresaPush.getBairro());
        empresa.setCidade(empresaPush.getCidade());
        empresa.setEstado(empresaPush.getEstado());
        empresa.setCep(empresaPush.getCep());
        empresa.setAtivo(empresaPush.getAtivo());
        empresaRepository.save(empresa);
        return empresa;
    }

}