package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.CardCreditos;
import org.acme.repository.CardCreditoRepository;
import org.acme.validation.CardCreditoValidation;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class CardCreditoService {

    @Inject
    CardCreditoRepository cardCreditoRepository;

    public boolean cadastrarCardCredito(CardCreditos cardCredito) throws SQLException {
        if (!CardCreditoValidation.validarCardCredito(cardCredito)) return false;
        cardCreditoRepository.inserirCardCredito(cardCredito);
        return true;
    }

    public List<CardCreditos> listarCardCredito() throws SQLException {
        return cardCreditoRepository.listarCardCredito();
    }

    public boolean cancelarCardCredito(int id) throws SQLException {
        if (id <= 0) return false;
        return cardCreditoRepository.cancelarCardCredito(id);
    }
    public boolean atualizarCardCredito(int id, String numCard, String dataValCard, int cvvCard, String nomeTitular, String cpfTitular) throws SQLException {
        if (id <= 0) return false;

        CardCreditos card = new CardCreditos(numCard, dataValCard, cvvCard, nomeTitular, cpfTitular);
        if (!CardCreditoValidation.validarCardCredito(card)) return false;

        return cardCreditoRepository.atualizarCardCredito(id, numCard, dataValCard, cvvCard, nomeTitular, cpfTitular);
    }

}
