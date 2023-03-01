package org.vaadin.example;

public class User {
    private int userIndex;
    private String mail;
    private String password;
    private String nombre;
    private String apellidos;

    public User(int userIndex, String mail, String password, String nombre, String apellidos){
        this.userIndex = userIndex;
        this.mail = mail;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public void setUserIndex(int userIndex) {
        this.userIndex = userIndex;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
