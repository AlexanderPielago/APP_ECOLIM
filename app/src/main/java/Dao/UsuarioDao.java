package Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import Model.UsuarioModel;

@Dao
public interface UsuarioDao {

    @Insert
    void insertarUsuario(UsuarioModel usuario);

    @Query("SELECT * FROM usuarios WHERE usuario = :usuario LIMIT 1")
    UsuarioModel obtenerPorNombre(String usuario);

    @Query("SELECT * FROM usuarios")
    List<UsuarioModel> obtenerTodosUsuarios();

    @Delete
    void eliminarUsuario(UsuarioModel usuario);
}
