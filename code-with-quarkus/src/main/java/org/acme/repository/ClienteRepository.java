package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Clientes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClienteRepository {

    @Inject
    DataSource dataSource;

    public void inserirPaciente(Clientes clientes) throws SQLException {
        String sql = "INSERT INTO clientes(nomecompleto, cpfnumero, emailcliente, password) VALUES(?,?,?,?)";
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, clientes.getNomeCompleto());
            ps.setString(2, clientes.getCpfNumero());
            ps.setString(3, clientes.getEmailCliente());
            ps.setString(4, clientes.getPassword());
            ps.executeUpdate();
        }
    }

    public List<Clientes> listarClientes() throws SQLException {
        String sql = "SELECT * FROM clientes";
        List<Clientes> lista = new ArrayList<>();
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Clientes paciente = new Clientes(
                        rs.getInt("id"),
                        rs.getString("nomecompleto"),
                        rs.getString("cpfnumero"),
                        rs.getString("emailcliente"),
                        rs.getString("password")
                );
                lista.add(paciente);
            }
        }
        return lista;
    }

    public boolean atualizarCliente(int id, String nomecompleto, String cpfnumero, String emailCliente, String password) throws SQLException {
        String sql = "UPDATE clientes SET nomecompleto=?, cpfnumero=?, emailcliente=?, password=? WHERE id=?";
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nomecompleto);
            ps.setString(2, cpfnumero);
            ps.setString(3, emailCliente);
            ps.setString(4, password);
            ps.setInt(5, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deletarCliente(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id=?";
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
