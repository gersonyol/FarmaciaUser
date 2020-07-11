package com.example.ejerciciofarmaciauser;

public class DatosVo {

    private String descripcion;
    private int imagen;

    public DatosVo(){

    }

    public DatosVo(String descripcion, int imagen) {
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
