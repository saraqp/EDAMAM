package quesado.prado.sara.edamam.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "recetas")
public class Receta implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String nombreReceta;
    @NonNull
    private String urlImagenReceta;
    @NonNull
    private String urlPaginaReceta;
    @NonNull
    private int rendimiento;
    @NonNull
    private float caloriasReceta;
    @NonNull
    private int tiempototal;
    @NonNull
    private String ingredientes;
    //Nutrientes receta y su unidad de medida
    @NonNull
    private float energia;
    @NonNull
    private String energiaUnidad;
    @NonNull
    private float grasa;
    @NonNull
    private String grasaUnidad;
    @NonNull
    private float grasasSaturadas;
    @NonNull
    private String grasasSaturadasUnidad;
    @NonNull
    private float azucar;
    @NonNull
    private String azucarUnidad;
    @NonNull
    private float proteina;
    @NonNull
    private String proteinaUnidad;

    public Receta(@NonNull String nombreReceta, @NonNull String urlImagenReceta, @NonNull String urlPaginaReceta,int tiempototal,String ingredientes) {
        this.nombreReceta = nombreReceta;
        this.urlImagenReceta = urlImagenReceta;
        this.urlPaginaReceta = urlPaginaReceta;
        this.tiempototal=tiempototal;
        this.ingredientes=ingredientes;
        this.rendimiento= 0;
        this.tiempototal=20;
    }
    @Ignore
    public Receta(String nombreReceta, String urlImagenReceta, String urlPaginaReceta, int rendimiento, float caloriasReceta, int tiempototal, String ingredientes, float energia, String energiaUnidad, float grasa, String grasaUnidad, float grasasSaturadas, String grasasSaturadasUnidad, float azucar, String azucarUnidad, float proteina, String proteinaUnidad) {
        this.nombreReceta = nombreReceta;
        this.urlImagenReceta = urlImagenReceta;
        this.urlPaginaReceta = urlPaginaReceta;
        this.rendimiento = rendimiento;
        this.caloriasReceta = caloriasReceta;
        this.tiempototal=tiempototal;
        this.ingredientes = ingredientes;
        this.energia = energia;
        this.energiaUnidad = energiaUnidad;
        this.grasa = grasa;
        this.grasaUnidad = grasaUnidad;
        this.grasasSaturadas = grasasSaturadas;
        this.grasasSaturadasUnidad = grasasSaturadasUnidad;
        this.azucar = azucar;
        this.azucarUnidad = azucarUnidad;
        this.proteina = proteina;
        this.proteinaUnidad = proteinaUnidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getUrlImagenReceta() {
        return urlImagenReceta;
    }

    public void setUrlImagenReceta(String urlImagenReceta) {
        this.urlImagenReceta = urlImagenReceta;
    }

    public String getUrlPaginaReceta() {
        return urlPaginaReceta;
    }

    public void setUrlPaginaReceta(String urlPaginaReceta) {
        this.urlPaginaReceta = urlPaginaReceta;
    }

    public int getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(int rendimiento) {
        this.rendimiento = rendimiento;
    }

    public float getCaloriasReceta() {
        return caloriasReceta;
    }

    public void setCaloriasReceta(float caloriasReceta) {
        this.caloriasReceta = caloriasReceta;
    }

    public int getTiempototal() {
        return tiempototal;
    }

    public void setTiempototal(int tiempototal) {
        this.tiempototal = tiempototal;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public float getEnergia() {
        return energia;
    }

    public void setEnergia(float energia) {
        this.energia = energia;
    }

    public String getEnergiaUnidad() {
        return energiaUnidad;
    }

    public void setEnergiaUnidad(String energiaUnidad) {
        this.energiaUnidad = energiaUnidad;
    }

    public float getGrasa() {
        return grasa;
    }

    public void setGrasa(float grasa) {
        this.grasa = grasa;
    }

    public String getGrasaUnidad() {
        return grasaUnidad;
    }

    public void setGrasaUnidad(String grasaUnidad) {
        this.grasaUnidad = grasaUnidad;
    }

    public float getGrasasSaturadas() {
        return grasasSaturadas;
    }

    public void setGrasasSaturadas(float grasasSaturadas) {
        this.grasasSaturadas = grasasSaturadas;
    }

    public String getGrasasSaturadasUnidad() {
        return grasasSaturadasUnidad;
    }

    public void setGrasasSaturadasUnidad(String grasasSaturadasUnidad) {
        this.grasasSaturadasUnidad = grasasSaturadasUnidad;
    }

    public float getAzucar() {
        return azucar;
    }

    public void setAzucar(float azucar) {
        this.azucar = azucar;
    }

    public String getAzucarUnidad() {
        return azucarUnidad;
    }

    public void setAzucarUnidad(String azucarUnidad) {
        this.azucarUnidad = azucarUnidad;
    }

    public float getProteina() {
        return proteina;
    }

    public void setProteina(float proteina) {
        this.proteina = proteina;
    }

    public String getProteinaUnidad() {
        return proteinaUnidad;
    }

    public void setProteinaUnidad(String proteinaUnidad) {
        this.proteinaUnidad = proteinaUnidad;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "id=" + id +
                ", nombreReceta='" + nombreReceta + '\'' +
                ", urlImagenReceta='" + urlImagenReceta + '\'' +
                ", urlPaginaReceta='" + urlPaginaReceta + '\'' +
                ", rendimiento=" + rendimiento +
                ", caloriasReceta=" + caloriasReceta +
                ", tiempototal=" + tiempototal +
                ", ingredientes='" + ingredientes + '\'' +
                ", energia=" + energia +
                ", energiaUnidad='" + energiaUnidad + '\'' +
                ", grasa=" + grasa +
                ", grasaUnidad='" + grasaUnidad + '\'' +
                ", grasasSaturadas=" + grasasSaturadas +
                ", grasasSaturadasUnidad='" + grasasSaturadasUnidad + '\'' +
                ", azucar=" + azucar +
                ", azucarUnidad='" + azucarUnidad + '\'' +
                ", proteina=" + proteina +
                ", proteinaUnidad='" + proteinaUnidad + '\'' +
                '}';
    }
}

