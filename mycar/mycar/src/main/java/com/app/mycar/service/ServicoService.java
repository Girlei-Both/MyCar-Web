package com.app.mycar.service;

import com.app.mycar.data.ServicoEntity;
import com.app.mycar.data.ServicoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    @Autowired
    ServicoRepository servicoRepository;

    //1º - LISTAR todos os registros da base de dados
    public List<ServicoEntity> getAllServico() {
        return servicoRepository.findAll();
    }

    //2º - ADICIONAR novo registro
    public ServicoEntity newServico(ServicoEntity servico) {
        servico.setId(null);
        servicoRepository.save(servico);
        return servico;
    }

    //3º - Pesquisar por ID -> Usado para Deletar e Atualizar
    public ServicoEntity getIdServico(Integer id) {
        return servicoRepository.findById(id).orElse(null);
    }

    //4º - DELETAR registro
    public void deletServico(Integer id) {
        ServicoEntity servico = getIdServico(id);
        servicoRepository.deleteById(servico.getId());
    }

    //5º - ATUALIZAR registro
    public ServicoEntity updateServico(Integer id, ServicoEntity servicoPush) {
        ServicoEntity servico = getIdServico(id);
        servico.setGrupo(servicoPush.getGrupo());
        servico.setItem(servicoPush.getItem());
        servico.setValor(servicoPush.getValor());
        servico.setId_cliente(servicoPush.getId_cliente());
        servico.setId_empresa(servicoPush.getId_empresa());
        servico.setData_emissao(servicoPush.getData_emissao());
        servicoRepository.save(servico);
        return servico;
    }

}
