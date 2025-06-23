package dev.annopud.boot_validation.model;


public class UserRequest {
    private String isSelected;            // expected "Y" or "N"
    private String mobilePhone;           // e.g., "0999999999"
    private String isSelectedEasyAuthen;  // expected "Y" or "N"
    private String email;

    public String getIsSelected() {
        return isSelected;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getIsSelectedEasyAuthen() {
        return isSelectedEasyAuthen;
    }

    public String getEmail() {
        return email;
    }
}
