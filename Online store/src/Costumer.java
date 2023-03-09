public class Costumer {
    private String name, address, phoneNumber, email;

    public Costumer(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String addres) {
        this.address = addres;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void printCostumerInfo() {
        System.out.println("\n" + "Name: " + this.getName() + "\n" + "Address: " + this.getAddress() + "\n" +
                "Phone Number: " + this.getPhoneNumber() +"\n" + "Email: " + this.getEmail() + "\n");
    }

}
