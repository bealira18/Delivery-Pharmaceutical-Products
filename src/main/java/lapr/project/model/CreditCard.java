package lapr.project.model;

import java.util.Date;

public class CreditCard {

    private int creditCardNumber;
    private Date validityDate;
    private short ccv;

    public CreditCard(int creditCardNumber, Date validityDate, short ccv) {

        this.creditCardNumber = creditCardNumber;
        setValidityDate(validityDate);
        this.ccv = ccv;
    }

    public CreditCard() {

    }

    public int getCreditCardNumber() {

        return creditCardNumber;
    }

    public Date getValidityDate() {

        return (Date) validityDate.clone();
    }

    public short getCcv() {

        return ccv;
    }

    public void setCreditCardNumber(int creditCardNumber) {

        this.creditCardNumber = creditCardNumber;
    }

    public final void setValidityDate(Date validityDate) {

        this.validityDate = (Date) validityDate.clone();
    }

    public void setCcv(short ccv) {

        this.ccv = ccv;
    }

    @Override
    public String toString() {

        return "CreditCard{" + "creditCardNumber=" + creditCardNumber + ", validityDate=" + validityDate + ", ccv=" + ccv + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 97 * hash + this.creditCardNumber;
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

        return this.creditCardNumber == other.creditCardNumber;
    }
}
