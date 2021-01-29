package lapr.project.model;

/**
 * This class serves as a container for the Client role and it's specific
 * information.
 * @author lapr3-2020-g052
 */
public class Client extends RegisteredUser {

    /**
     * The name of the client.
     */
    private String name;

    /**
     * The NIF of the client.
     */
    private int nif;

    /**
     * The Credit Card information of the client.
     */
    private CreditCard creditCard;

    /**
     * The Address information of the client.
     */
    private Address address;

    /**
     * The number of credits of the client.
     */
    private int credits;

    /**
     * Creates a instance of the client Object with the given arguments.
     * 
     * @param email the clients email.
     * @param password the clients password.
     * @param name the name of the client.
     * @param nif the NIF of the client.
     * @param creditCard the Credit Card information of the client.
     * @param address the Address information of the client.
     * @param credits the number of credits of the client.
     */
    public Client(String email, String password, String name, int nif, CreditCard creditCard, Address address, int credits) {

        super(email, password, "client");
        this.name = name;
        this.nif = nif;
        this.creditCard = creditCard;
        this.address = address;
        this.credits = credits;
    }

    /**
     * Retrieves the name field of this client object.
     * @return string containing the name.
     */
    public String getName() {

        return name;
    }

    /**
     * Retrieves the NIF field of this client object.
     * @return int containing the NIF.
     */
    public int getNif() {

        return nif;
    }

    /**
     * Retrieves the Credit Card field of this client object.
     * @return CreditCard Object containing the Credit Card information.
     */
    public CreditCard getCreditCard() {

        return creditCard;
    }

    /**
     * Retrieves the Address field of this client object.
     * @return Address Object containing the Address information.
     */
    public Address getAddress() {

        return address;
    }

    /**
     * Retrieves the credits field of this Client object.
     * @return int containing this clients credits information.
     */
    public int getCredits() {

        return credits;
    }

    /**
     * Sets a new name for this client object.
     * @param name new name.
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Sets a new NIF for this client object.
     * @param nif new NIF.
     */
    public void setNif(int nif) {

        this.nif = nif;
    }

    /**
     * Sets a new Credit Card for this client object.
     * @param creditCard new Credit Card inforamtion.
     */
    public void setCreditCard(CreditCard creditCard) {

        this.creditCard = creditCard;
    }

    /**
     * Sets a new Address for this client object.
     * @param address new Address inforamtion.
     */
    public void setAddress(Address address) {

        this.address = address;
    }

    /**
     * Sets a new credits value for this client object.
     * @param credits new credit value.
     */
    public void setCredits(int credits) {

        this.credits = credits;
    }

    /**
     * Client String format.
     * @return string with this client information.
     */
    @Override
    public String toString() {

        return "Client{" + "email=" + super.getEmail() + ", name=" + name + ", nif=" + nif + ", creditCard=" + creditCard + ", address=" + address + ", credits=" + credits + '}';
    }
    
    /**
     * Returns the hash code for this Client object.
     * @return hash code for the object.
     */
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 29 * hash + this.nif;
        return hash;
    }

    /**
     * Compares a Client object to another object and checks if they are equal.
     * @param obj the object to compare to.
     * @return true if equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Client) {
            return super.equals(obj) && nif == ((Client) obj).nif;
        }
        return false;
    }

}
