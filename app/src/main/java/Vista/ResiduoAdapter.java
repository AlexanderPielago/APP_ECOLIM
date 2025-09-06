package Vista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecolim_app.R;
import Model.ResiduoModel;

import java.util.List;

public class ResiduoAdapter extends RecyclerView.Adapter<ResiduoAdapter.ResiduoViewHolder> {

    private List<ResiduoModel> residuos;

    public ResiduoAdapter(List<ResiduoModel> residuos) {
        this.residuos = residuos;
    }

    @NonNull
    @Override
    public ResiduoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_residuo, parent, false);
        return new ResiduoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResiduoViewHolder holder, int position) {
        ResiduoModel r = residuos.get(position);
        holder.tvNombre.setText(r.nombre);
        holder.tvDescripcion.setText(r.descripcion);

        switch (r.nombre) {
            case "Orgánico":
                holder.ivIcon.setImageResource(R.drawable.food);
                break;
            case "Plástico":
                holder.ivIcon.setImageResource(R.drawable.plastic);
                break;
            case "Vidrio":
                holder.ivIcon.setImageResource(R.drawable.glass);
                break;
            case "Metal":
                holder.ivIcon.setImageResource(R.drawable.steel);
                break;
            default:
                holder.ivIcon.setImageResource(R.drawable.defaul);
        }
    }

    @Override
    public int getItemCount() {
        return residuos.size();
    }

    // Cambiado a public y no static
    public class ResiduoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDescripcion;
        ImageView ivIcon;

        public ResiduoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }
}
