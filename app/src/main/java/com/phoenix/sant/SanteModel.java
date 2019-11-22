package com.phoenix.sant;

public class SanteModel {
    public String id;
    public String Taille;
    public String Poids;
    public String Age;
    public String Calcule;
    public String msg;
    public String Date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaille() {
        return Taille;
    }

    public void setTaille(String taille) {
        Taille = taille;
    }

    public String getPoids() {
        return Poids;
    }

    public void setPoids(String poids) {
        Poids = poids;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getCalcule() {
        return Calcule;
    }

    public void setCalcule(String calcule) {
        Calcule = calcule;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "SanteModel{" +
                "id='" + id + '\'' +
                ", Taille='" + Taille + '\'' +
                ", Poids='" + Poids + '\'' +
                ", Age='" + Age + '\'' +
                ", Calcule='" + Calcule + '\'' +
                ", msg='" + msg + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }

}
