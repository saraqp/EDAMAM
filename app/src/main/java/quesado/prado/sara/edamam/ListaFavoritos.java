package quesado.prado.sara.edamam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import quesado.prado.sara.edamam.adapters.FavoritosAdapter;
import quesado.prado.sara.edamam.data.DataBaseRoom;
import quesado.prado.sara.edamam.model.Receta;
import quesado.prado.sara.edamam.model.RecetasFavoritosViewModel;

public class ListaFavoritos extends AppCompatActivity implements FavoritosAdapter.OnButtonClickedListener {
    private static DataBaseRoom db;
    private List<Receta> recetas;
    private RecyclerView recyclerView;
    private FavoritosAdapter adapter;

    private RecetasFavoritosViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_favoritos);

        recetas=new ArrayList<>();
        db=DataBaseRoom.getInstance(getApplication());



        model=new RecetasFavoritosViewModel(getApplication());
        model.getRecetasList().observe(this, new Observer<List<Receta>>() {
            @Override
            public void onChanged(List<Receta> recetas) {
                adapter.addReceta(recetas);
            }
        });

        recyclerView=findViewById(R.id.recyclerViewFavoritos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new FavoritosAdapter(this,recetas,this);
        recyclerView.setAdapter(adapter);

    }


    private void delReceta(Receta receta){
        model.delRecetas(receta);
    }

    @Override
    public void onButtonClicked(View view, Receta receta) {
        if (view.getId()==R.id.ic_delete){
            delReceta(receta);
        }
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

}