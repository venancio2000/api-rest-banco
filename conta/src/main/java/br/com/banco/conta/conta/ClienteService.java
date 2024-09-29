package br.com.banco.conta.conta;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository cliente;

    public Collection<Cliente> findAll(){
        var contaDoclientes = cliente.findAll();
        return contaDoclientes;
    }
    public Cliente findById(UUID id){
        var contaDocliente = cliente.findById(id).orElseThrow(() -> new ControllerNotFoundException("Cliente não encontrado"));
        return contaDocliente;
    }
    public Cliente save(Cliente contaDocliente){
        contaDocliente = cliente.save(contaDocliente);
        return contaDocliente;
    }
    public Cliente update(UUID id, Cliente contaDocliente){
        try {
            Cliente buscaCliente = cliente.getOne(id);
            buscaCliente.setNome(contaDocliente.getNome());
            buscaCliente.setCpf(contaDocliente.getCpf());
            buscaCliente.setNumeroDaConta(contaDocliente.getNumeroDaConta());
            buscaCliente.setSaldo(contaDocliente.getSaldo());
            buscaCliente = cliente.save(buscaCliente);
            return buscaCliente;
        }catch (EntityNotFoundException e){
            throw new ControllerNotFoundException(e.getMessage());
        }

    }
    public void delete(UUID id){
        cliente.deleteById(id);
    }

}


