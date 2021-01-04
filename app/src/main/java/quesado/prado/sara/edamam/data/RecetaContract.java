package quesado.prado.sara.edamam.data;

import android.provider.BaseColumns;

public class RecetaContract {
    public RecetaContract() {
    }
    public static class RecetaEntry implements BaseColumns{
        public static final String TABLE_NAME="recetas";
        public static final String COLUMN_NAME_RECETA_NAME="nombre";
        public static final String COLUMN_NAME_RECETA_IMG="imagen";
        public static final String COLUMN_NAME_RECETA_PAG="paginaweb";
        public static final String COLUMN_NAME_RECETA_RENDIMIENTO="rendimiento";
        public static final String COLUMN_NAME_RECETA_CALORIAS="calorias";
        public static final String COLUMN_NAME_RECETA_INGREDIENTES="ingredientes";
        public static final String COLUMN_NAME_RECETA_ENERGIA="energia";
        public static final String COLUMN_NAME_RECETA_UNITENERGIA="unitenergia";
        public static final String COLUMN_NAME_RECETA_NGRASA="grasa";
        public static final String COLUMN_NAME_RECETA_UNITGRASA="unitgrasa";
        public static final String COLUMN_NAME_RECETA_GRASASAT="grasa_sat";
        public static final String COLUMN_NAME_RECETA_UNITGRASASAT="unitgrasa_sat";
        public static final String COLUMN_NAME_RECETA_AZUCAR="azucar";
        public static final String COLUMN_NAME_RECETA_UNITAZUCAR="unitazucar";
        public static final String COLUMN_NAME_RECETA_PROTEINA="proteina";
        public static final String COLUMN_NAME_RECETA_UNITPROTEINA="unitproteina";
    }
}
