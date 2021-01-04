package quesado.prado.sara.edamam.model;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import quesado.prado.sara.edamam.R;
import quesado.prado.sara.edamam.data.DataBaseRoom;

public class RecetasFavoritosViewModel extends AndroidViewModel {
    private LiveData<List<Receta>> recetasList;
    private static DataBaseRoom db;

    public RecetasFavoritosViewModel(@NonNull Application application) {
        super(application);
        db=DataBaseRoom.getInstance(application);
        recetasList=(LiveData<List<Receta>>)db.recetaDAO().getRecetas();
    }

    public LiveData<List<Receta>> getRecetasList(){
        return recetasList;
    }
    public void delRecetas(Receta receta){
        new AsyncDeleteRecetaDB().execute(receta);
        
    }

    private class AsyncDeleteRecetaDB extends AsyncTask<Receta,Void,Integer> {
        public AsyncDeleteRecetaDB() {
        }

        @Override
        protected Integer doInBackground(Receta... recetas) {
            int delrow=0;
            if (recetas.length!=0){
                delrow=db.recetaDAO().deleleReceta(recetas[0]);
            }
            return delrow;
        }

        @Override
        protected void onPostExecute(Integer delrow) {
            String error=getApplication().getResources().getString(R.string.errorBorrado);
            String correcto=getApplication().getResources().getString(R.string.correctoBorrado);
            if (delrow==0){
                Toast.makeText(getApplication(),error,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplication(),correcto,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
