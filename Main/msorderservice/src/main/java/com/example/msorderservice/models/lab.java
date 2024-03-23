package com.example.msorderservice.models;

public class lab {

    private String l_id;
    private String l_name;
    private String l_address;
    private String l_password;
    private String l_email;
    private String l_phoneN;


    public String getL_id() {
        return l_id;
    }

    public void setL_id(String l_id) {
        this.l_id = l_id;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getL_address() {
        return l_address;
    }

    public void setL_address(String l_address) {
        this.l_address = l_address;
    }

    public String getL_password() {
        return l_password;
    }

    public void setL_password(String l_password) {
        this.l_password = l_password;
    }

    public String getL_email() {
        return l_email;
    }

    public void setL_email(String l_email) {
        this.l_email = l_email;
    }

    public String getL_phoneN() {
        return l_phoneN;
    }

    public void setL_phoneN(String l_phoneN) {
        this.l_phoneN = l_phoneN;
    }

    @Override
    public String toString() {
        return "lab{" +
                "l_id=" + l_id +
                ", l_name='" + l_name + '\'' +
                ", l_address='" + l_address + '\'' +
                ", l_password='" + l_password + '\'' +
                ", l_email='" + l_email + '\'' +
                ", l_phoneN='" + l_phoneN + '\'' +
                '}';
    }

    public lab(String l_id, String l_name, String l_address, String l_password, String l_email, String l_phoneN) {
        this.l_id = l_id;
        this.l_name = l_name;
        this.l_address = l_address;
        this.l_password = l_password;
        this.l_email = l_email;
        this.l_phoneN = l_phoneN;
    }
}
