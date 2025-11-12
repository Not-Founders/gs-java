package org.acme.validation;

import org.acme.model.Clientes;

public class ClienteValidation {

    public static boolean validarCliente(Clientes cliente) {
        if (cliente == null) return false;

        if (cliente.getNomeCompleto() == null || cliente.getNomeCompleto().trim().isEmpty()) return false;
        if (cliente.getCpfNumero() == null || cliente.getCpfNumero().trim().isEmpty()) return false;
        if (cliente.getEmailCliente() == null || cliente.getEmailCliente().trim().isEmpty()) return false;
        if (cliente.getPassword() == null || cliente.getPassword().trim().isEmpty()) return false;
        if (cliente.getCpfNumero().trim().length() != 11) return false;
        if (cliente.getPassword().trim().length() < 6) return false;

        return true;
    }
}
