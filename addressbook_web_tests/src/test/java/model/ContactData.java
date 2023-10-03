package model;

public record ContactData(String firstName,
                          String lastName,
                          String address,
                          String cellPhone,
                          String email) {

    public ContactData(){
        this("","","","","");
    }

    public ContactData withFirstAndLastName(String firstName, String lastName) {
        return new ContactData(firstName, lastName, this.address, this.cellPhone, this.email);
    }

    public ContactData withEmailOnly(String email) {
        return new ContactData(this.firstName, this.lastName, this.address, this.cellPhone, email);
    }

    public ContactData withPhoneOnly(String cellPhone) {
        return new ContactData(this.firstName, this.lastName, this.address, cellPhone, this.email);
    }

    public ContactData withAddressOnly(String address) {
        return new ContactData(this.firstName, this.lastName, address, this.cellPhone, this.email);
    }
}