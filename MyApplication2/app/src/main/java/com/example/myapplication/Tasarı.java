package com.example.myapplication;

public class Tasarı {

    private String kelime, anlam;
    private int id;

    public Tasarı(String kelime, String anlam, int id) {
        this.kelime = kelime;
        this.anlam = anlam;
        this.id = id;
    }

    public String getKelime() {
        return kelime;
    }

    public String getAnlam() {
        return anlam;
    }

    public int getId() {
        return id;
    }
}
