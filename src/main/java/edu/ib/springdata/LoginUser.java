package edu.ib.springdata;

public class LoginUser {

    private String name;
    private String password;

    public LoginUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public LoginUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
