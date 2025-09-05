package Vista;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Controller.RegisterController;
import com.example.ecolim_app.R;

public class RegisterView extends AppCompatActivity {

    private EditText etNombre, etCantidad;
    private AutoCompleteTextView spinnerTipo;
    private Button btnEnviar;

    private RegisterController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registercollectionform);

        // vincular vistas
        etNombre = findViewById(R.id.etNombre);
        etCantidad = findViewById(R.id.etCantidad);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        btnEnviar = findViewById(R.id.btnEnviar);

        // crear controlador (pasa vista y contexto) — o usa solo uno si cambias el constructor
        controller = new RegisterController(this, this);
        controller.initController();
    }

    // === getters ===
    public EditText getEtNombre() { return etNombre; }
    public EditText getEtCantidad() { return etCantidad; }
    public AutoCompleteTextView getSpinnerTipo() { return spinnerTipo; }
    public Button getBtnEnviar() { return btnEnviar; }

    // showToast delegado
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (controller != null) controller.shutdown(); // libera executor
    }
}
