package edu.cftic.fichapp.servicios;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ServicioReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("MIAPP", "El servicio ha finalizado, lanzo una notificación");
        NotificaMensaje.lanzarNotificiacion(context);
        // si quiero volver a gestionar la alarma
        //     GestorAlarma gestorAlarma = new GestorAlarma(context);
        //     gestorAlarma.programarAlarma();


    }
}
