package lapr.project.model;

import java.util.Date;

public class CreditCard {

    private int creditCard;
    private Date validityDate;
    private short ccv;

    public CreditCard(int creditCard, Date validityDate, short ccv) {

        this.creditCard = creditCard;
        setValidityDate(validityDate);
        this.ccv = ccv;
    }

    public CreditCard() {

    }

    public int getCreditCard() {

        return creditCard;
    }

    public Date getValidityDate() {

        return (Date) validityDate.clone();
    }

    public short getCcv() {

        return ccv;
    }

    public void setCreditCard(int creditCard) {

        this.creditCard = creditCard;
    }

    public final void setValidityDate(Date validityDate) {

        this.validityDate = (Date) validityDate.clone();
    }

    public void setCcv(short ccv) {

        this.ccv = ccv;
    }

    @Override
    public String toString() {

        return "CreditCard{" + "creditCard=" + creditCard + ", validityDate=" + validityDate + ", ccv=" + ccv + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 97 * hash + this.creditCard;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final CreditCard other = (CreditCard) obj;

        return this.creditCard == other.creditCard;
    }
}
