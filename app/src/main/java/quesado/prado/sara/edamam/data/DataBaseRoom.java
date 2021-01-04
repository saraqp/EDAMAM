package quesado.prado.sara.edamam.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import quesado.prado.sara.edamam.model.Receta;

@Database(entities = {Receta.class},version = 1,exportSchema = false)
public abstract class DataBaseRoom extends RoomDatabase {
    public abstract RecetaDAO recetaDAO();
    private static DataBaseRoom INSTANCE=null;

    private static final int NUMBER_OF_THREADS=4;
    public static final ExecutorService databasewriteExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DataBaseRoom getInstance(final Context context){
        if (INSTANCE==null){

            if (INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context.getApplicationContext(),DataBaseRoom.class,"lisa_compra.db").fallbackToDestructiveMigration().build();
            }
        }
        return INSTANCE;
    }
}
