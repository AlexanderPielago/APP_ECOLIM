package Vista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.ecolim_app.R;

import java.util.List;

import Controller.ReportController;
import Model.RecoleccionConResiduo;

public class ReporteView extends AppCompatActivity {

    private LinearLayout llReportes;
    private ReportController controller;
    private int idUsuario = 1; // ejemplo, cambia según usuario logueado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordform);

        llReportes = findViewById(R.id.llReportes);
        controller = new ReportController(this);

        cargarReportes();
    }

    private void cargarReportes() {
        controller.obtenerReportes(idUsuario, reportes -> {
            llReportes.removeAllViews();

            for (RecoleccionConResiduo reco : reportes) {
                CardView card = (CardView) LayoutInflater.from(this)
                        .inflate(R.layout.item_historial, llReportes, false);

                TextView tvTipo = card.findViewById(R.id.tvTipo);
                TextView tvCantidad = card.findViewById(R.id.tvCantidad);
                TextView tvFecha = card.findViewById(R.id.tvFecha);

                tvTipo.setText(reco.nombreResiduo);
                tvCantidad.setText("⚖️ Cantidad: " + reco.cantidad + " kg");
                tvFecha.setText("📅 Fecha: " + reco.fecha);

                llReportes.addView(card);
            }
        });
    }
}
