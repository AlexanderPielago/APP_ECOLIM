package Model;

import androidx.room.ColumnInfo;

public class RecoleccionResumen {

    // Los nombres deben coincidir con los alias del SELECT (nombre, total)
    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "total")
    public double total;

    public RecoleccionResumen() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
