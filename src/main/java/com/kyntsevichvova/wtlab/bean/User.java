package com.kyntsevichvova.wtlab.bean;

public class User extends BaseEntity {
    private String login;
    private String password;

    public User() {
        login = "";
        password = "";
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
