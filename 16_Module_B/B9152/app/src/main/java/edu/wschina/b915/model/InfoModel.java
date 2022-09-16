package edu.wschina.b915.model;

import java.io.Serializable;

public class InfoModel implements Serializable {
    private String time;
    private String date;
    private String type;

    public InfoModel() {
        time = date = type = "";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
