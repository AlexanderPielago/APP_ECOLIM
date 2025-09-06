package Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recoleccion")
public class RecoleccionModel {

    @PrimaryKey(autoGenerate = true)
    public int id_recoleccion;

    public int id_usuario;
    public int id_residuo;
    public double cantidad;
    public String fecha; // Formato 'YYYY-MM-DD HH:MM:SS'
}
