package Vista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.ecolim_app.R;

import Controller.RegisterController;

public class MenuView extends AppCompatActivity {

    private CardView btnregisterCollection, btntype_waste, btnreport, btnrecord, btnclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuform);

        // Vincular CardViews con los IDs del XML
        btnregisterCollection = findViewById(R.id.register_collection);
        btntype_waste = findViewById(R.id.type_waste);
        btnreport = findViewById(R.id.report);
        btnrecord = findViewById(R.id.record);
        btnclose = findViewById(R.id.close);

        // Asignar listeners
        btnregisterCollection.setOnClickListener(v -> {
            Toast.makeText(this, "Registrar recolección", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MenuView.this, RegisterView.class);
            startActivity(intent);
        });

        btntype_waste.setOnClickListener(v -> {
            Toast.makeText(this, "Tipos de residuos", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MenuView.this, TypeWasteView.class);
            startActivity(intent);
        });

        btnreport.setOnClickListener(v -> {
            Toast.makeText(this, "Reportes", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MenuView.this, ReportView.class);
            startActivity(intent);
        });

        btnrecord.setOnClickListener(v -> {
            Toast.makeText(this, "Historial", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MenuView.this, RecordView.class);
            startActivity(intent);
        });

        btnclose.setOnClickListener(v -> {
            Toast.makeText(this, "Cerrando sesión", Toast.LENGTH_SHORT).show();
            finish(); // Cierra esta actividad
        });
    }
}
