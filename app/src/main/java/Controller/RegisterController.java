package Controller;

import Vista.RegisterCollection;
import android.widget.ArrayAdapter;
import android.view.View;

public class RegisterController {

    private final RegisterCollection vista;

    // El constructor recibe una instancia de la clase Vista.
    public RegisterController(RegisterCollection vista) {
        this.vista = vista;
    }

    public void initController() {
        // Inicializar el adaptador para el AutoCompleteTextView
        setupSpinnerAdapter();

        // Configurar el listener para el botón
        setupButtonListener();
    }

    private void setupSpinnerAdapter() {
        // Debes obtener los datos para el adaptador de un recurso de tu app (arrays.xml)
        // o de otra fuente (base de datos, API).
        String[] tiposDeResiduo = {"Plástico", "Vidrio", "Papel", "Orgánico"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                vista,
                android.R.layout.simple_dropdown_item_1line,
                tiposDeResiduo
        );

        // Configurar el adaptador en el AutoCompleteTextView
        vista.spinnerTipo.setAdapter(adapter);
    }

    private void setupButtonListener() {
        // Configurar un OnClickListener para el botón de enviar
        // El botón en la vista se llama btnEnviar, no btn.
        vista.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarRegistro();
            }
        });
    }

    private void enviarRegistro() {
        // Obtener los datos de los campos
        String nombre = vista.etNombre.getText().toString();
        String cantidadStr = vista.etCantidad.getText().toString();
        String tipo = vista.spinnerTipo.getText().toString();

        // Validar los datos
        if (nombre.isEmpty() || cantidadStr.isEmpty() || tipo.isEmpty()) {
            vista.showToast("Por favor, complete todos los campos.");
            return;
        }

        try {
            double cantidad = Double.parseDouble(cantidadStr);
            vista.showToast("Datos listos para enviar: Nombre: " + nombre + ", Cantidad: " + cantidad + ", Tipo: " + tipo);
        } catch (NumberFormatException e) {
            vista.showToast("Cantidad inválida. Ingrese un número.");
        }
    }
}