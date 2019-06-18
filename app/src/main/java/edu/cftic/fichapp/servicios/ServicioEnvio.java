package edu.cftic.fichapp.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import edu.cftic.fichapp.acitividades.EnviarMailActivity;
import edu.cftic.fichapp.bean.Empleado;
import edu.cftic.fichapp.persistencia.DB;

public class ServicioEnvio extends Service {
    public ServicioEnvio() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //
        try {

            Log.i("MIAPP", "Servicio iniciado!...enviando el correo");
            //TODO enviaríamos el correo
            String email = "reejulu1@gmail.com";

            Intent intent1 = new Intent(this, EnviarMailActivity.class);
            intent1.putExtra("email", email);
            startActivity(intent1);
            stopSelf(startId);  // y se ejecuta el metodo onDestroy

        } catch (Throwable t) {
            Log.e("MIAPP", "ERRORAZO", t);
        }

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


        Log.i("MIAPP", "Servicio Terminado");

        // en el manifest esta definico el receiver del servicio
        //            android:name=".ServicioReceiver"
        Intent intent_reciver = new Intent("SERVICIO_TERMINADO");
        sendBroadcast(intent_reciver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

