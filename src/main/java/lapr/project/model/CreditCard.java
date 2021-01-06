package lapr.project.model;

import java.util.Date;
import java.util.Objects;

public class CreditCard {

    private int creditCard;
    private String clientEmail;
    private Date ccValidityDate;
    private short ccCCV;

    public CreditCard(){
        this.creditCard=0;
        this.clientEmail=null;
        this.ccValidityDate=null;
        this.ccCCV=0;
    }

    public CreditCard(int creditCard, String clientEmail, Date ccValidityDate, short ccCCV) {
        this.creditCard = creditCard;
        this.clientEmail = clientEmail;
        this.ccValidityDate = ccValidityDate;
        this.ccCCV = ccCCV;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Date getCcValidityDate() {
        return ccValidityDate;
    }

    public void setCcValidityDate(Date ccValidityDate) {
        this.ccValidityDate = ccValidityDate;
    }

    public short getCcCCV() {
        return ccCCV;
    }

    public void setCcCCV(short ccCCV) {
        this.ccCCV = ccCCV;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CreditCard cc = (CreditCard) obj;
        return creditCard == cc.creditCard;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCard, clientEmail, ccValidityDate, ccCCV);
    }

    @Override
    public String toString() {
        return "creditCard=" + creditCard +
                ", clientEmail='" + clientEmail +
                ", ccValidityDate=" + ccValidityDate +
                ", ccCCV=" + ccCCV;
    }
}
