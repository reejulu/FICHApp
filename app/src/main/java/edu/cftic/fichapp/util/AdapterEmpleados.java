package edu.cftic.fichapp.util;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.cftic.fichapp.R;
import edu.cftic.fichapp.acitividades.EnviarMailActivity;
import edu.cftic.fichapp.acitividades.RegistroEmpleadoActivity;
import edu.cftic.fichapp.acitividades.SeleccionarInforme;
import edu.cftic.fichapp.bean.Empleado;

public class AdapterEmpleados extends RecyclerView.Adapter<EmpleadoViewHolder> implements View.OnClickListener {

    public AdapterEmpleados(ArrayList<Empleado> datos) {
        this.datos = datos;
    }

    private ArrayList<Empleado> datos;

    @NonNull
    @Override
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        EmpleadoViewHolder empleadoViewHolder = null;

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        final View itemView = inflater.inflate(R.layout.activity_layout_empleado_item, viewGroup, false);

        empleadoViewHolder = new EmpleadoViewHolder(itemView);
        // para abrir la opcion de onclick para que al tocar un elemento de la lista se detecte
        itemView.setOnClickListener(this);

        return empleadoViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder empleadoViewHolder, int i) {

        Empleado libro = datos.get(i);
        empleadoViewHolder.cargarEmpleadoenHolder(libro);
        // (*1)para marcar la posicion..truco,,,asignar un Tag,,con la posicion -i
        //      ademas de posicion i,,se puede añadir cualquier objeto
        empleadoViewHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


    @Override
    public void onClick(View view) {
        // recupero la posicion previamente marcada con setTag,,(*1)
        int posicion_tocada = (int) view.getTag();
        Empleado empleado = datos.get(posicion_tocada);
        Log.i("MIAPP", " se ha tocado un elemento de la lista : " + posicion_tocada);
        Log.i("MIAPP", " se ha seleccionado el empleado : " + empleado.toString());

        //    Intent intent = new Intent(view.getContext(),RegistroEmpleadoActivity.class);
        // AÑADO LOS DATOS DEL EMPLEADO EN EL INTENT
        //    intent.putExtra(Constantes.EMPLEADO,empleado);
        //    view.getContext().startActivity(intent);

        // TEMPORAL PARA ENVIAR EL MAIL

        // Intent intent =new Intent(view.getContext(), EnviarMailActivity.class);
        Intent intent = new Intent(view.getContext(), SeleccionarInforme.class);
        // AÑADO LOS DATOS DEL EMPLEADO EN EL INTENT
        intent.putExtra("identidadEmpleado", empleado.getId_empleado());
        view.getContext().startActivity(intent);
    }
}
