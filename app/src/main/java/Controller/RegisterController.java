package Controller;

import Vista.RegisterView;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ecolim_app.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterController {

    private final RegisterView vista;
    private final Context context;
    private final ExecutorService executorService;
    private final Handler handler;

    // ✅ Inyección de dependencias más clara
    public RegisterController(RegisterView vista, Context context) {
        this.vista = vista;
        this.context = context;
        this.executorService = Executors.newSingleThreadExecutor();
        this.handler = new Handler(Looper.getMainLooper());
    }

    // Inicializa controladores de eventos y adaptadores
    public void initController() {
        setupSpinnerAdapter();
        setupButtonListener();
    }

    // ✅ Los datos se pueden extraer desde strings.xml para mejor mantenimiento
    private void setupSpinnerAdapter() {
        String[] tiposDeResiduo = context.getResources().getStringArray(
                R.array.tipo_residuo// Defínelo en res/values/strings.xml
        );
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_dropdown_item, // Mejor visual que 1line
                tiposDeResiduo
        );
        vista.getSpinnerTipo().setAdapter(adapter);
    }

    // ✅ Uso de lambda en vez de clase anónima
    private void setupButtonListener() {
        vista.getBtnEnviar().setOnClickListener(v -> enviarRegistro());
    }

    // ✅ Validación más robusta con trim() y cantidad > 0
    private void enviarRegistro() {
        String nombre = vista.getEtNombre().getText().toString().trim();
        String cantidadStr = vista.getEtCantidad().getText().toString().trim();
        String tipo = vista.getSpinnerTipo().getText().toString().trim();

        if (nombre.isEmpty() || cantidadStr.isEmpty() || tipo.isEmpty()) {
            showToast("Por favor, complete todos los campos.");
            return;
        }

        try {
            double cantidad = Double.parseDouble(cantidadStr);
            if (cantidad <= 0) {
                showToast("Ingrese una cantidad mayor a 0.");
                return;
            }

            // ✅ Ejecutar en segundo plano
            executorService.execute(() -> {
                // Simulación de guardar en BD
                boolean registroExitoso = true; // Aquí tu lógica real de BD

                // ✅ Volver al hilo principal
                handler.post(() -> {
                    if (registroExitoso) {
                        showToast("Registro exitoso: " + nombre);
                        limpiarCampos();
                    } else {
                        showToast("Error al guardar el registro.");
                    }
                });
            });

        } catch (NumberFormatException e) {
            showToast("Cantidad inválida. Ingrese un número válido.");
        }
    }

    // ✅ Método para limpiar campos
    private void limpiarCampos() {
        vista.getEtNombre().setText("");
        vista.getEtCantidad().setText("");
        vista.getSpinnerTipo().setText("");
    }

    // ✅ Centralizar mensajes
    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // ✅ Liberar recursos cuando ya no se use el controlador
    public void shutdown() {
        executorService.shutdown();
    }
}
