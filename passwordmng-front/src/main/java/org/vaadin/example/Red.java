package org.vaadin.example;

public class Red {
    private int redId;
    private int userId;
    private String mail;
    private String password;
    private String nomRed;

    public Red(int redId, int userId, String mail, String password, String nomRed) {
        this.redId = redId;
        this.userId = userId;
        this.mail = mail;
        this.password = password;
        this.nomRed = nomRed;
    }

    public Red(){}

    public int getRedId() {
        return redId;
    }

    public void setRedId(int redId) {
        this.redId = redId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomRed() {
        return nomRed;
    }

    public void setNomRed(String nomRed) {
        this.nomRed = nomRed;
    }
}
