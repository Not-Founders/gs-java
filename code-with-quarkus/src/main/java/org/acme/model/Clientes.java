package org.acme.model;

public class Clientes {
    private int id;
    private String nomeCompleto;
    private String cpfNumero;
    private String emailCliente;
    private String password;

    public Clientes() {}

    public Clientes(int id, String nomeCompleto, String cpfNumero, String emailCliente, String password) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpfNumero = cpfNumero;
        this.emailCliente = emailCliente;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpfNumero() {
        return cpfNumero;
    }

    public void setCpfNumero(String cpfNumero) {
        this.cpfNumero = cpfNumero;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
