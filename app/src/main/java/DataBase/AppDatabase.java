package DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import Dao.UsuarioDao;
import Model.UsuarioModel;

@Database(entities = {UsuarioModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UsuarioDao usuarioDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "ECOLIM.db" // 🔹 este es el nombre interno de la BD
                            )
                            .createFromAsset("ECOLIM.db") // 🔹 busca en assets/
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries() // 🔹 solo para pruebas, mejor usar hilo en producción
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
