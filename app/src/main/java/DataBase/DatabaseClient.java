package DataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import Model.ResiduoModel;
import Dao.ResiduoDao;

import java.util.concurrent.Executors;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient instance;
    private AppDatabase appDatabase;

    // Constructor privado para singleton
    private DatabaseClient(Context context) {
        this.context = context;

        // Crear la base de datos Room con callback para insertar datos iniciales
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "db_ECOLIM")
                .fallbackToDestructiveMigration()
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        AppDatabase.insertInitialData(appDatabase.residuoDao());
                        Executors.newSingleThreadExecutor().execute(() -> {
                            // Usar directamente appDatabase para obtener el DAO
                            ResiduoDao dao = appDatabase.residuoDao();

                            // Crear residuos iniciales
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

                            // Insertar en la base de datos
                            dao.insertarResiduo(organico);
                            dao.insertarResiduo(plastico);
                            dao.insertarResiduo(vidrio);
                            dao.insertarResiduo(metal);
                        });
                    }
                })
                .allowMainThreadQueries() // solo para pruebas; en producción usar hilo aparte
                .build();
    }

    // Obtener la instancia del singleton
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    // Obtener la base de datos
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
