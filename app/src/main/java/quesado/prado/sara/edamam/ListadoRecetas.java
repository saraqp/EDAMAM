package quesado.prado.sara.edamam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import quesado.prado.sara.edamam.adapters.RecetasAdapter;
import quesado.prado.sara.edamam.fragments.FragmentIngredientes;
import quesado.prado.sara.edamam.model.Receta;
import quesado.prado.sara.edamam.model.RecetaViewModel;

public class ListadoRecetas extends AppCompatActivity {
    ListView listView;
    RecetasAdapter adapter;
    List<Receta> recetas;
    TextView empty;

    ConnectivityManager connectivityManager;
    NetworkInfo activeNetwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_recetas);
        recetas=new ArrayList<>();
        listView=findViewById(R.id.listviewListaRecetas);
        connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork=connectivityManager.getActiveNetworkInfo();
        empty=findViewById(R.id.empty);
        boolean isConnected= activeNetwork!=null && activeNetwork.isConnected();

        String diet=getIntent().getExtras().getString("diet");
        String health=getIntent().getExtras().getString("health");
        String mealtype=getIntent().getExtras().getString("mealtype");
        String busqueda=getIntent().getExtras().getString("busqueda");

        updateUI();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Receta receta=recetas.get(position);
                mostrarIngredientes(receta.getIngredientes(),receta);
            }
        });

        if (isConnected){
            RecetaViewModel viewModel=ViewModelProviders.of(this).get(RecetaViewModel.class);
            viewModel.obtenerRecetas(diet,health,mealtype,busqueda).observe(this, new Observer<List<Receta>>() {
                @Override
                public void onChanged(List<Receta> recetasList) {

                    adapter.clear();
                    if (recetasList!=null){
                        adapter.addAll(recetasList);

                    }
                }
            });
        }else {
            empty.setText("No se han encontrado recetas");

        }
    }

    private void updateUI() {
        adapter=new RecetasAdapter(getApplicationContext(),recetas);
        listView.setAdapter(adapter);
    }

    public void mostrarIngredientes(String ingredientes,Receta recetaSeleccionadda){
        FragmentIngredientes frag_ingredientes=(FragmentIngredientes) getSupportFragmentManager().findFragmentById(R.id.fragmentIngredientes);
        frag_ingredientes.changeText(ingredientes);
        frag_ingredientes.ingredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),DescripcionReceta.class);
                intent.putExtra("receta",recetaSeleccionadda);
                startActivity(intent);
            }
        });

    }
}