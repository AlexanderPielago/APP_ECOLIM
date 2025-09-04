package Vista;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Controller.RegisterController;
import com.example.ecolim_app.R;

public class RegisterCollection extends AppCompatActivity {

    public EditText etNombre, etCantidad;
    public AutoCompleteTextView spinnerTipo;
    public Button btnEnviar;

    private RegisterController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registercollectionform); // ¡Esta línea es crucial!

        // 1. Vincular los elementos de la interfaz de usuario del XML
        etNombre = findViewById(R.id.etNombre);
        etCantidad = findViewById(R.id.etCantidad);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        btnEnviar = findViewById(R.id.btnEnviar);

        // 2. Crear el controlador y pasarle la instancia de esta vista (this)
        controller = new RegisterController(this);

        // 3. Inicializar la lógica del controlador
        controller.initController();
    }

    // Método para mostrar mensajes Toast, accesible desde el controlador
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}