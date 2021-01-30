package lapr.project.model;

import java.time.LocalDate;

/**
 * This class serves as a container for the Credit Card information.
 * 
 * @author lapr3-2020-g052
 */
public class CreditCard {

    /**
     * The credit card number.
     */
    private long creditCardNumber;

    /**
     * The credit cards expiration date.
     */
    private LocalDate validityDate;

    /**
     * The credit cards CCV.
     */
    private short ccv;

    /**
     * Creates a instance of the CreditCard Object with the given arguments.
     * @param creditCardNumber
     * @param validityDate
     * @param ccv
     */
    public CreditCard(long creditCardNumber, LocalDate validityDate, short ccv) {

        this.creditCardNumber = creditCardNumber;
        setValidityDate(validityDate);
        this.ccv = ccv;
    }

    /**
     * Retrieves the Credit Card Number field of this CreditCard object.
     * @return long containing the credit card number.
     */
    public long getCreditCardNumber() {

        return creditCardNumber;
    }

    /**
     * Retrieves the Expiration Date field of this CreditCard object.
     * @return LocalDate containing the Expiration Date.
     */
    public LocalDate getValidityDate() {

        return validityDate;
    }

    /**
     * Retrieves the CCV field of this CreditCard object.
     * @return short containing the CCV.
     */
    public short getCcv() {

        return ccv;
    }

    /**
     * Sets a new Credit Card number for this CreditCard object.
     * @param creditCardNumber new Credit Card number.
     */
    public void setCreditCardNumber(long creditCardNumber) {

        this.creditCardNumber = creditCardNumber;
    }

    /**
     * Sets a new Credit Card expiration date for this CreditCard object.
     * @param validityDate new Credit card expiration date.
     */
    public final void setValidityDate(LocalDate validityDate) {

        this.validityDate = validityDate;
    }

    /**
     * Sets a new CCV for this CreditCard object.
     * @param ccv new CCV.
     */
    public void setCcv(short ccv) {

        this.ccv = ccv;
    }
    
    /**
     * CreditCard String format.
     * @return string with this CreditCard information.
     */
    @Override
    public String toString() {

        return "CreditCard{" + "creditCardNumber=" + creditCardNumber + ", validityDate=" + validityDate + ", ccv=" + ccv + '}';
    }

    /**
     * Returns the hash code for this CreditCard object.
     * @return hash code for the object.
     */
    @Override
    public int hashCode() {

        int hash = 3;
        hash = 97 * hash + (int)(this.creditCardNumber ^ (this.creditCardNumber >>> 32));
        return hash;
    }
    
    /**
     * Compares a CreditCard object to another object and checks if they are equal.
     * @param obj the object to compare to.
     * @return true if equal, false if not.
     */
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
