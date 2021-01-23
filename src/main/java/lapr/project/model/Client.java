package lapr.project.model;

public class Client extends RegisteredUser {

    private String name;
    private int nif;
    private CreditCard creditCard;
    private Address address;
    private int credits;

    public Client(String email, String password, String name, int nif, CreditCard creditCard, Address address, int credits) {

        super(email, password, "client");
        this.name = name;
        this.nif = nif;
        this.creditCard = creditCard;
        this.address = address;
        this.credits = credits;
    }

    public String getName() {

        return name;
    }

    public int getNif() {

        return nif;
    }

    public CreditCard getCreditCard() {

        return creditCard;
    }

    public Address getAddress() {

        return address;
    }

    public int getCredits() {

        return credits;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setNif(int nif) {

        this.nif = nif;
    }

    public void setCreditCard(CreditCard creditCard) {

        this.creditCard = creditCard;
    }

    public void setAddress(Address address) {

        this.address = address;
    }

    public void setCredits(int credits) {

        this.credits = credits;
    }

    @Override
    public String toString() {

        return "Client{" + "email=" + super.getEmail() + ", name=" + name + ", nif=" + nif + ", creditCard=" + creditCard + ", address=" + address + ", credits=" + credits + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 29 * hash + this.nif;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Client) {
            return super.equals(obj) && nif == ((Client) obj).nif;
        }
        return false;
    }

}
