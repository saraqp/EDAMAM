package quesado.prado.sara.edamam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Button listadoRecetas,favoritos;
    Spinner dietSpinner,healthSpinner,mealTypeSpinner;
    EditText buscador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listadoRecetas= findViewById(R.id.boton_Buscar);
        favoritos= findViewById(R.id.boton_favoritos);

        //rellenar spinners
        dietSpinner=findViewById(R.id.spinner_diet);
        String[] opcionesSpinnerDiet=new String[]{"All","balanced","high-fiber","high-protein","low-carb","low-fat"};
        ArrayAdapter<String> adapterDiet=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,opcionesSpinnerDiet);
        dietSpinner.setAdapter(adapterDiet);

        healthSpinner=findViewById(R.id.spinner_Salud);
        String[] opcionesSpinnerHealth=new String[]{"All","alcohol-free","vegan","vegetarian","sugar-conscious","peanut-free","tree-nut-free"};
        ArrayAdapter<String> adapterHealth=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,opcionesSpinnerHealth);
        healthSpinner.setAdapter(adapterHealth);

        mealTypeSpinner=findViewById(R.id.spinner_Tipo);
        String[] opcionesSpinnerMealType=new String[]{"All","Breakfast","Lunch","Dinner","Snack","Teatime"};
        ArrayAdapter<String> adaptermealType=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,opcionesSpinnerMealType);
        mealTypeSpinner.setAdapter(adaptermealType);

        buscador=findViewById(R.id.buscadorEditText);



        listadoRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String diet= dietSpinner.getSelectedItem().toString();
                String health=healthSpinner.getSelectedItem().toString();
                String mealType=mealTypeSpinner.getSelectedItem().toString();
                String busqueda=buscador.getText().toString();

                Intent intent=new Intent(getApplicationContext(),ListadoRecetas.class);
                intent.putExtra("diet",diet.toLowerCase());
                intent.putExtra("health",health.toLowerCase());
                intent.putExtra("mealtype",mealType.toLowerCase());
                intent.putExtra("busqueda",busqueda);
                startActivity(intent);
            }
        });

        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ListaFavoritos.class);
                startActivity(intent);
            }
        });
    }
}