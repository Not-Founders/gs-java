package org.acme.model;

public class CardCreditos {
    private String numCard;
    private String dataValCard;
    private int cvvCard;
    private String nomeTitular;
    private String cpfTitular;


    public CardCreditos() {}

    public CardCreditos(String numCard, String dataValCard, int cvvCard, String nomeTitular, String cpfTitular) {
        this.numCard = numCard;
        this.dataValCard = dataValCard;
        this.cvvCard = cvvCard;
        this.nomeTitular = nomeTitular;
        this.cpfTitular = cpfTitular;
    }

    public String getNumCard() {
        return numCard;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public String getDataValCard() {
        return dataValCard;
    }

    public void setDataValCard(String dataValCard) {
        this.dataValCard = dataValCard;
    }

    public int getCvvCard() {
        return cvvCard;
    }

    public void setCvvCard(int cvvCard) {
        this.cvvCard = cvvCard;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }
}
