package quesado.prado.sara.edamam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import quesado.prado.sara.edamam.R;

public class FragmentIngredientes extends Fragment {
    public TextView ingredientes;
    public FragmentIngredientes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ingredientes,container,false);
        ingredientes=view.findViewById(R.id.textviewFragmentIngredientes);
        return view;
    }
    public void changeText(String ingrediente){
        String vermas= getResources().getString(R.string.verMas)+"....";
        ingredientes.setText(ingrediente+"\n\n"+vermas+"\n\n");
    }
}