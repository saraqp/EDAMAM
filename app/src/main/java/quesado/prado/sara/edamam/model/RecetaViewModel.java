package quesado.prado.sara.edamam.model;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import quesado.prado.sara.edamam.data.DataBaseRoom;
import quesado.prado.sara.edamam.utils.QueryUtils;

public class RecetaViewModel extends AndroidViewModel {
    private Application application;
    private static MutableLiveData<List<Receta>> recetas;
    private static  String JSON_RESPONSE="https://api.edamam.com/search";


    public RecetaViewModel(@NonNull Application application) {
        super(application);
        this.application=application;

    }
    public LiveData<List<Receta>> obtenerRecetas(String diet,String health,String meatType,String busqueda){
        if (recetas==null){
            recetas=new MutableLiveData<>();
            cargarRecetas(diet,health,meatType,busqueda);
        }
        cargarRecetas(diet,health,meatType,busqueda);
        return recetas;
    }

    private void cargarRecetas(String diet,String health,String meatType,String busqueda) {
        Uri baseuri= Uri.parse(JSON_RESPONSE);
        Uri.Builder builder=baseuri.buildUpon();
        builder.appendQueryParameter("q",busqueda);
        builder.appendQueryParameter("app_id","39ec9271");
        builder.appendQueryParameter("app_key","6253acdee434c6aa962113964c52eac3");


        if ((!diet.equals("all"))&&(!health.equals("all"))&&(!meatType.equals("all"))){
            builder.appendQueryParameter("diet",diet);
            builder.appendQueryParameter("health",health);
            builder.appendQueryParameter("meattype",meatType);

        }else if ((!diet.equals("all"))&&(!health.equals("all"))&&meatType.equals("all")){
            builder.appendQueryParameter("diet",diet);
            builder.appendQueryParameter("health",health);

        }else if ((!diet.equals("all"))&&(health.equals("all"))&&meatType.equals("all")){
            builder.appendQueryParameter("diet",diet);

        }else if ((!diet.equals("all"))&&(health.equals("all"))&&(!meatType.equals("all"))){
            builder.appendQueryParameter("diet",diet);
            builder.appendQueryParameter("meattype",meatType);
        }else if ((diet.equals("all"))&&(!health.equals("all"))&&(!meatType.equals("all"))){
            builder.appendQueryParameter("health",health);
            builder.appendQueryParameter("meattype",meatType);
        }else if ((diet.equals("all"))&&(!health.equals("all"))&&(meatType.equals("all"))){
            builder.appendQueryParameter("health",health);
        }else if (diet.equals("all")&&health.equals("all")&&(!meatType.equals("all"))){
            builder.appendQueryParameter("meattype",meatType);
        }

        JSON_RESPONSE=builder.toString();

        RequestQueue requestQueue= Volley.newRequestQueue(application);
        StringRequest request=new StringRequest(Request.Method.GET, JSON_RESPONSE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Receta> recetasList = QueryUtils.extraerRecetas(response);
                recetas.setValue(recetasList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ErrorVolley",error.toString());
            }
        });
        requestQueue.add(request);
    }


}
