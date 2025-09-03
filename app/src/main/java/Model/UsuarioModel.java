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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @NonNull
    public String getUsuario() { return usuario; }
    public void setUsuario(@NonNull String usuario) { this.usuario = usuario; }

    @NonNull
    public String getPassword() { return password; }
    public void setPassword(@NonNull String password) { this.password = password; }
}
