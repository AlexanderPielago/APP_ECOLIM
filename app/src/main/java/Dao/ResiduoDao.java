package Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import Model.ResiduoModel;

import java.util.List;

@Dao
public interface ResiduoDao {

    @Insert
    void insertarResiduo(ResiduoModel residuo);

    @Update
    void actualizarResiduo(ResiduoModel residuo);

    @Delete
    void eliminarResiduo(ResiduoModel residuo);

    @Query("SELECT * FROM residuos WHERE id_residuo = :id")
    ResiduoModel obtenerResiduo(int id);

    @Query("SELECT * FROM residuos")
    List<ResiduoModel> obtenerTodos();
}
