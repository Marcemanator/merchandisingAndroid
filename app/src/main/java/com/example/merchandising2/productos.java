package com.example.merchandising2;

// Definición de la clase productos
public class productos {
    // Declaración de variables miembro
    String Nomenclatura;
    String Tipo;
    String Cantidad;
    String Precio;

    // Constructor de la clase productos
    public productos(String nomenclatura, String tipo, String cantidad, String precio) {
        this.Nomenclatura = nomenclatura;
        this.Tipo = tipo;
        this.Cantidad = cantidad;
        this.Precio = precio;
    }

    // Métodos getter y setter para la variable Nomenclatura
    public String getNomenclatura() {
        return Nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.Nomenclatura = nomenclatura;
    }

    // Métodos getter y setter para la variable Tipo
    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        this.Tipo = tipo;
    }

    // Métodos getter y setter para la variable Cantidad
    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        this.Cantidad = cantidad;
    }

    // Métodos getter y setter para la variable Precio
    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        this.Precio = precio;
    }
}
