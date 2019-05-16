package com.bb.quanlycc.Model;

import java.io.Serializable;

public class apartment implements Serializable {
    private String Id;

    public apartment(String id) {
        Id = id;
    }
    public apartment(){};

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
