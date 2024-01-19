package com.app.mycar.service;

import com.app.mycar.data.VeiculoEntity;
import com.app.mycar.data.VeiculoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    @Autowired
    VeiculoRepository veiculoRepository;

    //1º - LISTAR todos os registros da base de dados
    public List<VeiculoEntity> getAllVeiculo() {
        return veiculoRepository.findAll();
    }

    //2º - ADICIONAR novo registro
    public VeiculoEntity newVeiculo(VeiculoEntity veiculo) {
        veiculo.setId(null);
        veiculoRepository.save(veiculo);
        return veiculo;
    }

    //3º - Pesquisar por ID -> Usado para Deletar e Atualizar
    public VeiculoEntity getIdVeiculo(Integer id) {
        return veiculoRepository.findById(id).orElse(null);
    }

    //4º - DELETAR registro
    public void deletVeiculo(Integer id) {
        VeiculoEntity veiculo = getIdVeiculo(id);
        veiculoRepository.deleteById(veiculo.getId());
    }

    //5º - ATUALIZAR registro
    public VeiculoEntity updateVeiculo(Integer id, VeiculoEntity veiculoPush) {
        VeiculoEntity veiculo = getIdVeiculo(id);
        veiculo.setMarca(veiculoPush.getMarca());
        veiculo.setModelo(veiculoPush.getModelo());
        veiculo.setVersao(veiculoPush.getVersao());
        veiculo.setAno_fabricacao(veiculoPush.getAno_fabricacao());
        veiculo.setAno_modelo(veiculoPush.getAno_modelo());
        veiculo.setPlaca(veiculoPush.getPlaca());
        veiculo.setRenavam(veiculoPush.getRenavam());
        veiculo.setChassis(veiculoPush.getChassis());
        veiculo.setCod_motor(veiculoPush.getCod_motor());
        veiculo.setValvulas(veiculoPush.getValvulas());
        veiculo.setCilindros(veiculoPush.getCilindros());
        veiculo.setCilindradas(veiculoPush.getCilindradas());
        veiculo.setTracao(veiculoPush.getTracao());
        veiculo.setCategoria(veiculoPush.getCategoria());
        veiculo.setEspecie(veiculoPush.getEspecie());
        veiculo.setTipo(veiculoPush.getTipo());
        veiculo.setCor(veiculoPush.getCor());
        veiculo.setCombustivel(veiculoPush.getCombustivel());
        veiculo.setOpcionais(veiculoPush.getOpcionais());
        veiculo.setAtivo(veiculoPush.getAtivo());
        veiculoRepository.save(veiculo);
        return veiculo;
    }

}
