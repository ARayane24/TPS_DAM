package com.example.tp1.TP5;

import java.io.Serializable;

public class Etudiant implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    int id;
    String mat , nom , prenom;

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Etudiant(int id, String mat, String nom, String prenom, byte[] photo) {
        this.id = id;
        this.mat = mat;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
    }

    byte[] photo;
    public Etudiant(int id, String mat, String nom, String prenom) {
        this.id = id;
        this.mat = mat;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Etudiant( String mat, String nom, String prenom) {
        this.mat = mat;
        this.nom = nom;
        this.prenom = prenom;
    }




}
