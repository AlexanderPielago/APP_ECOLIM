package Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Residuo")
public class ResiduoModel {
    @PrimaryKey(autoGenerate = true)
    public int id_residuo;
    public String nombre;
    public String descripcion;
}
