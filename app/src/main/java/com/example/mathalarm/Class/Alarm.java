package com.example.mathalarm.Class;

public class Alarm {
    private String subjek;
    private String jam;

    public Alarm(String subjek, String jam) {
        this.subjek = subjek;
        this.jam = jam;
    }

    public String getSubjek() {
        return subjek;
    }

    public void setSubjek(String subjek) {
        this.subjek = subjek;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
}
