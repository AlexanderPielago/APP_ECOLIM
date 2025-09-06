package Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "residuos")
public class ResiduoModel {

    @PrimaryKey(autoGenerate = true)
    public int id_residuo;

    @NonNull
    public String nombre;

    public String descripcion;
}
