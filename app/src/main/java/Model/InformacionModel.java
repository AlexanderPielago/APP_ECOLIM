package Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "informacion",
        foreignKeys = @ForeignKey(entity = Model.UsuarioModel.class,
                parentColumns = "id",
                childColumns = "id_usuario",
                onDelete = ForeignKey.CASCADE))
public class InformacionModel {

    @PrimaryKey(autoGenerate = true)
    public int id_informacion;

    public int id_usuario;
    public String nombre_completo;
    public String correo;
    public String telefono;
    public String direccion;
}
