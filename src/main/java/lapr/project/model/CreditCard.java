package lapr.project.model;

import java.util.Date;

public class CreditCard {

    private int credit_card;
    private Date validity_date;
    private short ccv;

    public CreditCard(int credit_card, Date validity_date, short ccv) {

        this.credit_card = credit_card;
        this.validity_date = validity_date;
        this.ccv = ccv;
    }

    public CreditCard() {

    }

    public int getCredit_card() {

        return credit_card;
    }

    public Date getValidity_date() {

        return validity_date;
    }

    public short getCcv() {

        return ccv;
    }

    public void setCredit_card(int credit_card) {

        this.credit_card = credit_card;
    }

    public void setValidity_date(Date validity_date) {

        this.validity_date = validity_date;
    }

    public void setCcv(short ccv) {

        this.ccv = ccv;
    }

    @Override
    public String toString() {

        return "CreditCard{" + "credit_card=" + credit_card + ", validity_date=" + validity_date + ", ccv=" + ccv + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 97 * hash + this.credit_card;
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

        return this.credit_card == other.credit_card;
    }
}
