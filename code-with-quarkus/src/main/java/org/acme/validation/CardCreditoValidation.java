package org.acme.validation;

import org.acme.model.CardCreditos;

public class CardCreditoValidation {

    public static boolean validarCardCredito(CardCreditos card) {
        if (card == null) return false;

        if (card.getNumCard() == null || card.getNumCard().trim().isEmpty()) return false;
        if (card.getDataValCard() == null || card.getDataValCard().trim().isEmpty()) return false;
        if (card.getNomeTitular() == null || card.getNomeTitular().trim().isEmpty()) return false;
        if (card.getCpfTitular() == null || card.getCpfTitular().trim().isEmpty()) return false;
        if (card.getNumCard().trim().length() < 13 || card.getNumCard().trim().length() > 19) return false;
        if (card.getCvvCard() < 100 || card.getCvvCard() > 999) return false;
        if (card.getCpfTitular().trim().length() != 11) return false;

        return true;
    }
}
