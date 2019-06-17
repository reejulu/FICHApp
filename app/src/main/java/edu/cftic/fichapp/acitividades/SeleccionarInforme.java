package edu.cftic.fichapp.acitividades;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import edu.cftic.fichapp.bean.Empleado;
import edu.cftic.fichapp.bean.Empresa;
import edu.cftic.fichapp.persistencia.DB;
import edu.cftic.fichapp.util.AdapterEmpleados;
import edu.cftic.fichapp.util.Constantes;

public class SeleccionarInforme extends AppCompatActivity {

    private ArrayList<Empleado> datos;
    private DB bdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        // CREO LISTA DE EMPLEADOS
        datos = new ArrayList<Empleado>();


        // EN intent viene la identidad ID del empleado
        // TODO hay que obener la empresa de este empleado y luego el e-mail de esta empresa

        int posicion = getIntent().getIntExtra("identidadEmpleado", 0);
        Empleado nu = DB.empleados.getEmpleadoId(posicion);

        String email = nu.getEmpresa().getEmail();

        Intent intent = new Intent(this, EnviarMailActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();

    }


}
