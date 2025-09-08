package DataBase;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import Dao.UsuarioDao;
import Dao.ResiduoDao;
import Dao.InformacionDao;
import Dao.RecoleccionDao;

import Model.UsuarioModel;
import Model.ResiduoModel;
import Model.InformacionModel;
import Model.RecoleccionModel;

import java.util.concurrent.Executors;

@Database(
        entities = {
                UsuarioModel.class,
                ResiduoModel.class,
                InformacionModel.class,
                RecoleccionModel.class
        },
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();
    public abstract ResiduoDao residuoDao();
    public abstract InformacionDao informacionDao();
    public abstract RecoleccionDao recoleccionDao();

    /**
     * Callback para insertar datos iniciales.
     * Ahora no depende de App.getContext().
     * Se debe pasar el DAO desde DatabaseClient al crear la base de datos.
     */
    public static void insertInitialData(ResiduoDao dao) {
        Executors.newSingleThreadExecutor().execute(() -> {
            // Revisar si ya hay residuos
            if (dao.obtenerTodos().isEmpty()) {
                ResiduoModel organico = new ResiduoModel();
                organico.nombre = "Orgánico";
                organico.descripcion = "Residuos biodegradables";

                ResiduoModel plastico = new ResiduoModel();
                plastico.nombre = "Plástico";
                plastico.descripcion = "Residuos de plástico reciclable";

                ResiduoModel vidrio = new ResiduoModel();
                vidrio.nombre = "Vidrio";
                vidrio.descripcion = "Botellas y frascos de vidrio";

                ResiduoModel metal = new ResiduoModel();
                metal.nombre = "Metal";
                metal.descripcion = "Latas y chatarra metálica";

                dao.insertarResiduo(organico);
                dao.insertarResiduo(plastico);
                dao.insertarResiduo(vidrio);
                dao.insertarResiduo(metal);
            }
        });
    }
}
