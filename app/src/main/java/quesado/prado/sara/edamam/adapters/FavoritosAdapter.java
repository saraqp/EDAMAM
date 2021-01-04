package quesado.prado.sara.edamam.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import quesado.prado.sara.edamam.R;
import quesado.prado.sara.edamam.data.DataBaseRoom;
import quesado.prado.sara.edamam.model.Receta;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.ViewHolder> {
    private Context context;
    private List<Receta> recetas;
    private static DataBaseRoom db;
    private OnButtonClickedListener listener;

    public FavoritosAdapter(Context context, List<Receta> recetas,OnButtonClickedListener listener) {
        this.context = context;
        this.recetas = recetas;
        this.listener=listener;
    }
    public void addReceta(List<Receta> lista){
        recetas=lista;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FavoritosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_list_favoritos,parent,false);
        db=DataBaseRoom.getInstance(context);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritosAdapter.ViewHolder holder, int position) {
        final Receta receta=recetas.get(position);

        Picasso.get().load(receta.getUrlImagenReceta()).into(holder.imagenRecetas);
        holder.nombreReceta.setText(receta.getNombreReceta());
        holder.rendimientoReceta.setText(receta.getRendimiento()+"");
        holder.tiempototalReceta.setText(receta.getTiempototal()+"");
        holder.borrarFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClicked(v,receta);
            }
        });
    }


    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenRecetas,borrarFav;
        TextView nombreReceta,rendimientoReceta,tiempototalReceta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenRecetas=itemView.findViewById(R.id.imagenLista);
            nombreReceta=itemView.findViewById(R.id.nombreReceta);
            rendimientoReceta=itemView.findViewById(R.id.rendimiento);
            tiempototalReceta=itemView.findViewById(R.id.tiempo_total);
            borrarFav=itemView.findViewById(R.id.ic_delete);
        }
    }

    public interface OnButtonClickedListener{
        void onButtonClicked(View view,Receta receta);
    }

}
