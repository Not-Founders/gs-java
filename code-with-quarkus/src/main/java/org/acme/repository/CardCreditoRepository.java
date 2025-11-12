package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.CardCreditos;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CardCreditoRepository {

    @Inject
    DataSource dataSource;

    public void inserirCardCredito(CardCreditos cardcreditos) throws SQLException {
        String sql = "INSERT INTO cardcreditos(numCard, dataValCard, cvvCard, nomeTitular, cpfTitular) VALUES(?,?,?,?,?)";
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cardcreditos.getNumCard());
            ps.setString(2, cardcreditos.getDataValCard());
            ps.setInt(3, cardcreditos.getCvvCard());
            ps.setString(4, cardcreditos.getNomeTitular());
            ps.setString(5, cardcreditos.getCpfTitular());

            ps.executeUpdate();
        }
    }

    public List<CardCreditos> listarCardCredito() throws SQLException {
        String sql = "SELECT * FROM cardcreditos";
        List<CardCreditos> lista = new ArrayList<>();
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CardCreditos cardcredito = new CardCreditos(
                        rs.getString("numCard"),
                        rs.getString("dataValCard"),
                        rs.getInt("cvvCard"),
                        rs.getString("nomeTitular"),
                        rs.getString("cpfTitular")
                );
                lista.add(cardcredito);
            }
        }
        return lista;
    }

    public boolean cancelarCardCredito(int id) throws SQLException {
        String sql = "DELETE FROM cardcreditos WHERE id=?";
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
    public boolean atualizarCardCredito(int id, String numCard, String dataValCard, int cvvCard, String nomeTitular, String cpfTitular) throws SQLException {
        String sql = "UPDATE cardcreditos SET numCard=?, dataValCard=?, cvvCard=?, nomeTitular=?, cpfTitular=? WHERE id=?";
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numCard);
            ps.setString(2, dataValCard);
            ps.setInt(3, cvvCard);
            ps.setString(4, nomeTitular);
            ps.setString(5, cpfTitular);
            ps.setInt(6, id);
            return ps.executeUpdate() > 0;
        }
    }

}
