package mainClasses;

import java.io.Serializable;

public class Requisites implements Serializable {
    private String nameBank;
    private String idCard;
    private String fullNameOwner;


    public Requisites(String nameBank, String idCard, String fullNameOwner) {
        this.nameBank = nameBank;
        this.idCard = idCard;
        this.fullNameOwner = fullNameOwner;
    }

    public Requisites() {

    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getFullNameOwner() {
        return fullNameOwner;
    }

    public void setFullNameOwner(String fullNameOwner) {
        this.fullNameOwner = fullNameOwner;
    }

    @Override
    public String toString() {
        return "Requisites{" +
                "nameBank='" + nameBank + '\'' +
                ", idCard='" + idCard + '\'' +
                ", fullNameOwner='" + fullNameOwner + '\'' +
                '}';
    }
}
