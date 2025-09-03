package Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import Model.UsuarioModel;

import java.util.List;

@Dao
public interface UsuarioDao {

    // Insertar usuario
    @Insert
    void insertarUsuario(UsuarioModel usuario);

    // Obtener todos los usuarios
    @Query("SELECT * FROM usuarios")
    List<UsuarioModel> obtenerTodosUsuarios();

    // Eliminar usuario
    @Delete
    void eliminarUsuario(UsuarioModel usuario);

    @Query("SELECT * FROM usuarios WHERE LOWER(usuario) = LOWER(:usuario) LIMIT 1")
    UsuarioModel findByUsuario(String usuario);
}
