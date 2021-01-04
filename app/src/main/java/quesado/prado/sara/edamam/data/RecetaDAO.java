package quesado.prado.sara.edamam.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import quesado.prado.sara.edamam.model.Receta;

@Dao
public interface RecetaDAO {
    @Insert
    public long insertReceta(Receta receta);
    @Update
    public int updateReceta(Receta receta);
    @Delete
    public int deleleReceta(Receta receta);
    @Query("SELECT * from recetas")
    public LiveData<List<Receta>> getRecetas();
}
