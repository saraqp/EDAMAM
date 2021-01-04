package quesado.prado.sara.edamam.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;

import quesado.prado.sara.edamam.model.Receta;

public class QueryUtils {
    private QueryUtils(){}
    public static ArrayList<Receta> extraerRecetas(String json_response){
        ArrayList<Receta> recetas=new ArrayList<>();
        try {
            JSONObject root=new JSONObject(json_response);
            JSONArray hits=root.getJSONArray("hits");
            for (int i=0;i<hits.length();i++){
                JSONObject object=hits.getJSONObject(i);
                JSONObject recipe=object.getJSONObject("recipe");
                //receta datos
                String nombre=recipe.getString("label");
                String urlImagen=recipe.getString("image");
                String url=recipe.getString("url");
                int rendimiento=(Integer)recipe.getInt("yield");
                float calorias= BigDecimal.valueOf(recipe.getDouble("calories")).floatValue();
                int totaltime=(Integer)recipe.getInt("totalTime");
                String ingredientes = "";
                JSONArray ingredient=recipe.getJSONArray("ingredientLines");
                for (int a=0;a<ingredient.length();a++){
                    String ingrediente=ingredient.getString(a);
                    if (a==ingredient.length()-1){
                        ingredientes+=ingrediente+" ";
                    }else {
                        ingredientes += ingrediente + "\n";
                    }
                }

                //nutrientes receta datos
                JSONObject totalnutrients=recipe.getJSONObject("totalNutrients");
                //Energia
                JSONObject energyKcal=totalnutrients.getJSONObject("ENERC_KCAL");
                float energy=BigDecimal.valueOf(energyKcal.getDouble("quantity")).floatValue();
                String unitenergy=energyKcal.getString("unit");
                //grasa
                JSONObject fatDatos=totalnutrients.getJSONObject("FAT");
                float fat=BigDecimal.valueOf(fatDatos.getDouble("quantity")).floatValue();
                String unitfat=fatDatos.getString("unit");
                //grasa saturadas
                JSONObject sat=totalnutrients.getJSONObject("FASAT");
                float saturadas=BigDecimal.valueOf(sat.getDouble("quantity")).floatValue();
                String unitsat=sat.getString("unit");
                //azucar
                JSONObject azucDatos=totalnutrients.getJSONObject("SUGAR");
                float azucar=BigDecimal.valueOf(azucDatos.getDouble("quantity")).floatValue();
                String unitazucar=azucDatos.getString("unit");
                //Proteina
                JSONObject prot=totalnutrients.getJSONObject("PROCNT");
                float proteina=BigDecimal.valueOf(prot.getDouble("quantity")).floatValue();
                String unitproteina=prot.getString("unit");


                recetas.add(new Receta(nombre,urlImagen,url,rendimiento,calorias,totaltime,ingredientes,energy,unitenergy,fat,unitfat,saturadas,unitsat,azucar,unitazucar,proteina,unitproteina));
            }
        } catch (JSONException e) {
            Log.e("QueryUtils","error al parsear json "+e.getMessage());
        }
        return recetas;
    }

}
