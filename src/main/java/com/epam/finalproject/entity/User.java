package com.epam.finalproject.entity;

public class User extends Entity {

    private int userId;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private String role;
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

    public void setRole(String role) {
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

    public String getRole() {
        return role;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        User user = (User) object;
        if (user.userId == userId && (user.firstName != null && user.firstName.equals(firstName)) &&
                (user.lastName != null && user.lastName.equals(lastName)) &&
                (user.login != null && user.login.equals(login)) &&
                (user.password != null && user.password.equals(password)) &&
                (user.email != null && user.email.equals(email) &&
                        (user.role != null && user.role.equals(role)) && user.status == status) ) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userId;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        if (status == true) {
            result += 1;
            result *= prime;
        }
        return result;
    }
}