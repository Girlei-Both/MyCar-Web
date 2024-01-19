package com.app.mycar.service;

import com.app.mycar.data.ClienteEntity;
import com.app.mycar.data.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    //1º - LISTAR todos os registros da base de dados
    public List<ClienteEntity> getAllCliente() {
        return clienteRepository.findAll();
    }

    //2º - ADICIONAR novo registro
    public ClienteEntity newCliente(ClienteEntity cliente) {
        cliente.setId(null);
        clienteRepository.save(cliente);
        return cliente;
    }

    //3º - Pesquisar por ID -> Usado para Deletar e Atualizar
    public ClienteEntity getIdCliente(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    //4º - DELETAR registro
    public void deletCliente(Integer id) {
        ClienteEntity cliente = getIdCliente(id);
        clienteRepository.deleteById(cliente.getId());
    }

    //5º - ATUALIZAR registro
    public ClienteEntity updateCliente(Integer id, ClienteEntity clientePush) {
        ClienteEntity cliente = getIdCliente(id);
        cliente.setNome(clientePush.getNome());
        cliente.setCpf(clientePush.getCpf());
        cliente.setData_nascimento(clientePush.getData_nascimento());
        cliente.setGenero(clientePush.getGenero());
        cliente.setTelefone(clientePush.getTelefone());
        cliente.setCelular(clientePush.getCelular());
        cliente.setEmail(clientePush.getEmail());
        cliente.setEndereco(clientePush.getEndereco());
        cliente.setNumero(clientePush.getNumero());
        cliente.setBairro(clientePush.getBairro());
        cliente.setCidade(clientePush.getCidade());
        cliente.setEstado(clientePush.getEstado());
        cliente.setCep(clientePush.getCep());
        cliente.setData_cadastro(clientePush.getData_cadastro());
        clienteRepository.save(cliente);
        return cliente;
    }

}
