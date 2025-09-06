package Vista;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import DataBase.DatabaseClient;
import Dao.ResiduoDao;
import Model.ResiduoModel;
import com.example.ecolim_app.R;

public class ResiduoView extends AppCompatActivity {

    private RecyclerView rvResiduos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typewaseform);
        rvResiduos = findViewById(R.id.rvResiduos);
        rvResiduos.setLayoutManager(new LinearLayoutManager(this));

        cargarResiduos();
    }

    private void cargarResiduos() {
        ResiduoDao dao = DatabaseClient.getInstance(this).getAppDatabase().residuoDao();
        List<ResiduoModel> residuos = dao.obtenerTodos();

        ResiduoAdapter adapter = new ResiduoAdapter(residuos);
        rvResiduos.setAdapter(adapter);
    }
}
