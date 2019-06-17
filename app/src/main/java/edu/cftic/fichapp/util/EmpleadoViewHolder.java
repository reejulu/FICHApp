package edu.cftic.fichapp.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.cftic.fichapp.R;
import edu.cftic.fichapp.bean.Empleado;

class EmpleadoViewHolder extends RecyclerView.ViewHolder {

    //private ImageView imagen;
    private TextView id, nombre;


    public EmpleadoViewHolder(@NonNull View itemView) {
        super(itemView);
        // imagen = itemView.findViewById(R.id.image_fila);
        id = itemView.findViewById(R.id.idEmpleado);
        nombre = itemView.findViewById(R.id.NombreEmpleado);

    }

    public void cargarEmpleadoenHolder(Empleado l) {
        // imagen.setImageResource(R.drawable.boton_selector);
        id.setText(l.getId_empleado() + "");
        nombre.setText(l.getNombre());
    }
}
