package com.example.david.conecta;

public class Mensajes{

    String mensaje, categoria;

    public Mensajes(){

    }


    public Mensajes(String mensaje, String categoria) {
        this.mensaje = mensaje;
        this.categoria = categoria;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
