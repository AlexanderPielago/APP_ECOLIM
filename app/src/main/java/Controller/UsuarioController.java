package Controller;

import android.content.Context;
import androidx.room.Room;
import DataBase.AppDatabase;
import Dao.UsuarioDao;
import Model.UsuarioModel;

import java.util.List;

public class UsuarioController {

    private AppDatabase db;
    private UsuarioDao usuarioDao;

    public UsuarioController(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "App_Ecolim")
                .allowMainThreadQueries() // SOLO PARA PRUEBAS
                .build();
        usuarioDao = db.usuarioDao();
    }

    public void agregarUsuario(String usuario, String password) {
        UsuarioModel u = new UsuarioModel();
        u.usuario = usuario;
        u.password = password;
        usuarioDao.insertarUsuario(u);
    }

    public List<UsuarioModel> obtenerUsuarios() {
        return usuarioDao.obtenerTodosUsuarios();
    }

    public void eliminarUsuario(UsuarioModel u) {
        usuarioDao.eliminarUsuario(u);
    }
}
