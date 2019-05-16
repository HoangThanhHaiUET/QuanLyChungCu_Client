package com.bb.quanlycc.Model;

import java.io.Serializable;

public class DSThanhtoan  implements Serializable {
    String id;
    public  String name;
    public String status;
    public  String link;
    public DSThanhtoan(){};
    public DSThanhtoan(String id, String name,String status,String link) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
