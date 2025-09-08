package Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuarios")
public class UsuarioModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String usuario;

    @NonNull
    public String password;
}
