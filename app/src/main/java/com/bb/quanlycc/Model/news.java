package com.bb.quanlycc.Model;

import java.io.Serializable;

public class news implements Serializable  {
    public String ID;
    public String Title;
    public String Status;
    public String date;
    public news(){}
    public news(String ID, String title, String status, String date) {
        this.ID = ID;
        Title = title;
        Status = status;
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
