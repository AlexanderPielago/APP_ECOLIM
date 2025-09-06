package Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import Model.InformacionModel;

import java.util.List;

@Dao
public interface InformacionDao {

    @Insert
    void insertarInformacion(InformacionModel info);

    @Update
    void actualizarInformacion(InformacionModel info);

    @Delete
    void eliminarInformacion(InformacionModel info);

    @Query("SELECT * FROM informacion WHERE id_informacion = :id")
    InformacionModel obtenerInformacion(int id);

    @Query("SELECT * FROM informacion WHERE id_usuario = :idUsuario")
    List<InformacionModel> obtenerPorUsuario(int idUsuario);
}
