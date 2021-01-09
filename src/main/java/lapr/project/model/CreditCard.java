package lapr.project.model;

import java.time.LocalDate;

public class CreditCard {

    private long creditCardNumber;
    private LocalDate validityDate;
    private short ccv;

    public CreditCard(long creditCardNumber, LocalDate validityDate, short ccv) {

        this.creditCardNumber = creditCardNumber;
        setValidityDate(validityDate);
        this.ccv = ccv;
    }

    public long getCreditCardNumber() {

        return creditCardNumber;
    }

    public LocalDate getValidityDate() {

        return validityDate;
    }

    public short getCcv() {

        return ccv;
    }

    public void setCreditCardNumber(long creditCardNumber) {

        this.creditCardNumber = creditCardNumber;
    }

    public final void setValidityDate(LocalDate validityDate) {

        this.validityDate = validityDate;
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
        hash = 97 * hash + (int)(this.creditCardNumber ^ (this.creditCardNumber >>> 32));
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
