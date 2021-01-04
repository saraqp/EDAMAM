package quesado.prado.sara.edamam.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import quesado.prado.sara.edamam.R;
import quesado.prado.sara.edamam.model.Receta;

public class RecetasAdapter extends ArrayAdapter<Receta>{
    List<Receta> recetas;
    Context context;
    public RecetasAdapter(@NonNull Context context, List<Receta> recetas) {
        super(context,0,recetas);
        this.recetas=recetas;
        this.context=context;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView=convertView;

        if (listView==null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.item_list_lista,parent,false);
        }

        Receta receta=recetas.get(position);

        ImageView imagenReceta=listView.findViewById(R.id.imagenLista);
        TextView nombreReceta=listView.findViewById(R.id.nombreReceta);
        TextView rendimiento=listView.findViewById(R.id.rendimiento);
        TextView tiempototal=listView.findViewById(R.id.tiempo_total);

        Picasso.get().load(receta.getUrlImagenReceta()).into(imagenReceta);
        nombreReceta.setText(receta.getNombreReceta());
        rendimiento.setText(receta.getRendimiento()+"");
        tiempototal.setText(receta.getTiempototal()+"");

        return listView;

    }


}
