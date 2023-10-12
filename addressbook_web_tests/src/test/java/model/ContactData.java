package model;

public record ContactData(String id, String firstName,
                          String lastName,
                          String address,
                          String cellPhone,
                          String email) {

    public ContactData(){
        this("", "","","","","");
    }

    public ContactData withFirstAndLastName(String firstName, String lastName) {
        return new ContactData(this.id, firstName, lastName, this.address, this.cellPhone, this.email);
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address, this.cellPhone, this.email);
    }
    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address, this.cellPhone, this.email);
    }
    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address, this.cellPhone, this.email);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.cellPhone, email);
    }

    public ContactData withPhone(String cellPhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, cellPhone, this.email);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address, this.cellPhone, this.email);
    }
}