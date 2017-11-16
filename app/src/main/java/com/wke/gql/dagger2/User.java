package com.wke.gql.dagger2;

/**
 * Created by Administrator on 2017/11/16.
 */

public class User {
    String email;
    String psw;

    public User(String email, String psw) {
        this.email = email;
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }
}
