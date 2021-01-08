package lapr.project.model;

public class Client extends RegisteredUser {

    private String name;
    private int nif;
    private int creditCard;
    private String address;
    private int credits;

    public Client(String email, String password, String role, String name, int nif, int creditCard, String address, int credits) {
        
        super(email, password, role);
        this.name = name;
        this.nif = nif;
        this.creditCard = creditCard;
        this.address = address;
        this.credits = credits;
    }

    public Client(RegisteredUser user, String name, int nif, int creditCard, String address, int credits) {
        
        super(user);
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

    public int getCreditCard() {

        return creditCard;
    }

    public String getAddress() {

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

    public void setCreditCard(int creditCard) {

        this.creditCard = creditCard;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public void setCredits(int credits) {

        this.credits = credits;
    }

    @Override
    public String toString() {

        return "Client{" + "email=" + super.getEmail() + ", name=" + name + ", nif=" + nif + ", creditCard=" + creditCard + ", address=" + address + ", credits=" + credits + '}';
    }
}
