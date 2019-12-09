package com.epam.finalproject.entity;

public class User extends Entity {

    private int userId;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private int role;
    private boolean status;

    public User() {
    }

    public User(String name, String lastName, String login, String password, String email) {
        this.firstName = name;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public boolean isStatus() {
        return status;
    }
}
