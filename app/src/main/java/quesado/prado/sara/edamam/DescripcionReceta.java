package quesado.prado.sara.edamam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import quesado.prado.sara.edamam.data.DataBaseRoom;
import quesado.prado.sara.edamam.model.Receta;

public class DescripcionReceta extends AppCompatActivity {
    ImageView imagenReceta,favorito;
    TextView nombreReceta,rendimiento,tiempototal,ingredientes,energia,energiaUnidad,grasas,grasasUnidad,saturadas,saturadasUnidad,azucar,azucarUnidad,proteina,proteinaUnidad;
    Button verWeb;
    private DataBaseRoom db;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_descripcion_receta);
        //obtienes del intent los datos de la receta
        Receta receta= (Receta) getIntent().getExtras().get("receta");
        db=DataBaseRoom.getInstance(this);
        imagenReceta=findViewById(R.id.imagenReceta);
        nombreReceta=findViewById(R.id.nombreReceta);
        rendimiento=findViewById(R.id.rendimiento);
        tiempototal=findViewById(R.id.tiempo_total);
        ingredientes=findViewById(R.id.ingredientes);
        energia=findViewById(R.id.energia);
        energiaUnidad=findViewById(R.id.unidadEnergia);
        grasas=findViewById(R.id.grasas);
        grasasUnidad=findViewById(R.id.unidadGrasa);
        saturadas=findViewById(R.id.saturadas);
        saturadasUnidad=findViewById(R.id.unidadSaturadas);
        azucar=findViewById(R.id.azucar);
        azucarUnidad=findViewById(R.id.unidadAzucar);
        proteina=findViewById(R.id.proteinas);
        proteinaUnidad=findViewById(R.id.unidadProteinas);
        verWeb=findViewById(R.id.verWeb);
        favorito=findViewById(R.id.ic_fav);


        Picasso.get().load(receta.getUrlImagenReceta()).into(imagenReceta);
        nombreReceta.setText(receta.getNombreReceta());
        rendimiento.setText(receta.getRendimiento()+"");
        tiempototal.setText(receta.getTiempototal()+"");
        String ingredientesReceta=receta.getIngredientes();
        String[] ingredientesarray=ingredientesReceta.split("\n");
        String mostrarIngr="";
        for (int i=0;i<ingredientesarray.length;i++){
            //añade al principio de cada ingrediente una flecha para diferenciarlos si ocupan varias lineas
            mostrarIngr+= "\u27b5"+ingredientesarray[i]+"\n";
        }
        ingredientes.setText(mostrarIngr);
        energia.setText(receta.getEnergia()+"");
        energiaUnidad.setText(receta.getEnergiaUnidad());
        grasas.setText(receta.getGrasa()+"");
        grasasUnidad.setText(receta.getGrasaUnidad());
        saturadas.setText(receta.getGrasasSaturadas()+"");
        saturadasUnidad.setText(receta.getGrasasSaturadasUnidad());
        azucar.setText(receta.getAzucar()+"");
        azucarUnidad.setText(receta.getAzucarUnidad());
        proteina.setText(receta.getProteina()+"");
        proteinaUnidad.setText(receta.getProteinaUnidad());

        verWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(receta.getUrlPaginaReceta()));
                startActivity(intent);
            }
        });

        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReceta(receta);
            }
        });

    }
    public void addReceta(Receta receta){
        new AsyncAddRecetaDb().execute(receta);
    }

    private class AsyncAddRecetaDb extends AsyncTask<Receta,Void,Long> {
        Receta receta;
        @Override
        protected Long doInBackground(Receta... recetas) {
            long id=-1;
            if (recetas.length!=0){
                receta=recetas[0];
                id=db.recetaDAO().insertReceta(receta);
                receta.setId(id);
            }
            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            String error=getResources().getString(R.string.errorInsercion);
            String correcto=getResources().getString(R.string.correctaInsercion);
            if (id==-1){
                Toast.makeText(getApplication(),error,Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplication(),correcto,Toast.LENGTH_LONG).show();
            }
        }
    }
}