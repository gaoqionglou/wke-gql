package com.wke.gql.net.retrofit;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/13.
 */

public class Hint implements Serializable {
    public String hint;

    @Override
    public String toString() {
        return "Hint{" +
                "hint='" + hint + '\'' +
                '}';
    }
}
