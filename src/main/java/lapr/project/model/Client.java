package lapr.project.model;

import java.util.Objects;

public class Client {

    private String email;
    private String name;
    private int nif;
    private int creditCard;
    private String address;
    private int credits;

    public Client(String email, String name, int nif, int creditCard, String address, int credits) {

        this.email = email;
        this.name = name;
        this.nif = nif;
        this.creditCard = creditCard;
        this.address = address;
        this.credits = credits;
    }

    public Client() {

    }

    public String getEmail() {

        return email;
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

    public void setEmail(String email) {

        this.email = email;
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

        return "Client{" + "email=" + email + ", name=" + name + ", nif=" + nif + ", creditCard=" + creditCard + ", address=" + address + ", credits=" + credits + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.email);
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
        final Client other = (Client) obj;

        return this.email.equals(other.email);
    }
}
