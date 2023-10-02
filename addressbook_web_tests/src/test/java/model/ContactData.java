package model;

public record ContactData(String firstName,
                          String lastName,
                          String street,
                          String cellPhone,
                          String email) {

    public ContactData(){
        this("","","","","");
    }

    public ContactData withFirstAndLastName(String firstName, String lastName) {
        return new ContactData(firstName, lastName, this.street, this.cellPhone, this.email);
    }

    public ContactData withEmailOnly(String email) {
        return new ContactData(this.firstName, this.lastName, this.street, this.cellPhone, email);
    }
}