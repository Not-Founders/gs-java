package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Clientes;
import org.acme.repository.ClienteRepository;
import org.acme.validation.ClienteValidation;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    public boolean cadastrarCliente(Clientes clientes) throws SQLException {
        if (!ClienteValidation.validarCliente(clientes)) return false;
        clienteRepository.inserirPaciente(clientes);
        return true;
    }

    public List<Clientes> listarClientes() throws SQLException {
        return clienteRepository.listarClientes();
    }

    public boolean atualizarCliente(int id, String nomeCompleto, String cpfNumero, String emailCliente, String password) throws SQLException {
        if (id <= 0) return false;
        Clientes cliente = new Clientes(id, nomeCompleto, cpfNumero, emailCliente, password);
        if (!ClienteValidation.validarCliente(cliente)) return false;
        return clienteRepository.atualizarCliente(id, nomeCompleto, cpfNumero, emailCliente, password);
    }

    public boolean deletarCliente(int id) throws SQLException {
        if (id <= 0) return false;
        return clienteRepository.deletarCliente(id);
    }
}
