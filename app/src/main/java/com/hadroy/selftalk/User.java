package com.hadroy.selftalk;

import java.util.Arrays;
import java.util.Objects;

public class User {

    private String name;
    private String email;
    private long id;
    private char[] password;

    public User(String email, char[] password) {
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name) && email.equals(user.email) && Arrays.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, email, id);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }
}
