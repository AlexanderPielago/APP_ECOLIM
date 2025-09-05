package Model;

public class RegisterModel {
    private String nombre;
    private double cantidad;
    private String tipoResiduo;

    // Constructor
    public RegisterModel(String nombre, double cantidad, String tipoResiduo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipoResiduo = tipoResiduo;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(String tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    // Método opcional para mostrar info del registro
    @Override
    public String toString() {
        return "Registro: " +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", tipoResiduo='" + tipoResiduo + '\'';
    }
}
