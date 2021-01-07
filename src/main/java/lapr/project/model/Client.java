package lapr.project.model;

import java.util.Objects;

public class Client {

    private String email;
    private String name;
    private int nif;
    private int credit_card;
    private String address;
    private int credits;

    public Client(String email, String name, int nif, int credit_card, String address, int credits) {

        this.email = email;
        this.name = name;
        this.nif = nif;
        this.credit_card = credit_card;
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

    public int getCredit_card() {

        return credit_card;
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

    public void setCredit_card(int credit_card) {

        this.credit_card = credit_card;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public void setCredits(int credits) {

        this.credits = credits;
    }

    @Override
    public String toString() {

        return "Client{" + "email=" + email + ", name=" + name + ", nif=" + nif + ", credit_card=" + credit_card + ", address=" + address + ", credits=" + credits + '}';
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
