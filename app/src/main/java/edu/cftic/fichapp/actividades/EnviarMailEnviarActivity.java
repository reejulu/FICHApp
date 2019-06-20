package edu.cftic.fichapp.actividades;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.cftic.fichapp.R;

public class EnviarMailEnviarActivity extends AppCompatActivity {
    String path;
    String pathsource;
    private static final String[] PERMISOS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int CODIGO_PETICION_PERMISOS = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        ActivityCompat.requestPermissions(this, PERMISOS, CODIGO_PETICION_PERMISOS);

        // EL FICHERO CON EL INFORME IRA GUARDADO EN path:

        // creo un fichero llamado INFORME_FICHAJE_.pdf
        // en storage/emulated/0/ en lugar de donde iria realmente
        //    storage/emulated/0/PDF/  ( no he podido crear el directorio)

        String content = "hello world";
        File file2;
        FileOutputStream outputStream;
        {
            try {
                // file = File.createTempFile("MyCache", null, getCacheDir());
                file2 = new File(Environment.getExternalStorageDirectory().toString()+File.separator , "INFORME_FICHAJE.pdf");

                outputStream = new FileOutputStream(file2);
                outputStream.write(content.getBytes());
                outputStream.close();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            // PASO 1: BORRAR EL FICHERO TEMPORAL EN
            //            path = "data/data/a.bb.bbbb/files/informe.pdf"
            //            SI EL FICHERO EXISTE HAY QUE BORRARLO PUES ESO SIGNIFICA QUE ES ANTIGUO
            path = "data/data/edu.cftic.fichapp/files/informe.pdf";
            File f = new File(path);
            f.delete();


            // FICHERO ORIGEN : DONDE ESTA EL INFORME
            // las pruebas las hice con:
            //                 src/main/assets/test.txt * esta localizado en directorio assets
            // storage/emulated/0/PDF/ ( pruebo con storage/emulated/0/)
            // FICHERO DESTINO : DONDE VAMOS A GUARDAR TEMPORALMENTE EL INFORME
            // storage/emulated/0/INFORME_FICHAJE_.pdf a --> data/data/a.bb.bbbb/files/informe.pdf

            // BUSCAMOS SI EL FICHERO DESTION ORIGEN EXISTE:
            // 1- SI EXISTE : LO COPIAMOS AL DESTINO
            // 2- NO EXISTE : ENVIAREMOS EL E-MAIL SIN EL INFORME INDICANDO QUE NO ESTA DISPONIBLE
            try {
                Context context = getApplicationContext();
                //opening text file located in assets directory
                AssetManager assetManager = getAssets();
                //InputStream is = assetManager.open("test.txt");
                File file4 = new File(Environment.getExternalStorageDirectory().getPath()+"/INFORME_FICHAJE.pdf");
                FileInputStream is = new FileInputStream(file4);


                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                Log.i("MIAPP", "path interno es : " + path);
                FileOutputStream fos = openFileOutput("informe.pdf", MODE_PRIVATE);
                //FileOutputStream fos = new FileOutputStream(f);
                fos.write(buffer);
                fos.close();
                Log.i("MIAPP", "Se ha creado informe.pdf en data/data... : " + path);

            } catch (
                    Exception e) {
                Log.i("MIAPP", "No exite el fichero en assest- continuo");
                //throw new RuntimeException(e);
            }

            // REQUERIMOS ENVIAR EL E-MAIL: CON O SIN FICHERO ADJUNTADO
            Intent intent = new Intent();
            intent.putExtra("MESSAGE", path);

            setResult(2, intent);

            finish();//finishing activity
            Log.i("MIAPP", "el E-mail ha sido ordenado en SendFileEmail.send ");
            // AHORA LA EJECUCION DEL PROGRAMA CONTINUARA EN ENVIARMAILACTIVITY
        }


    }
    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
                                             @NonNull int[] grantResults){

        if (requestCode == CODIGO_PETICION_PERMISOS) {
            // en 0 corresponde al primer valor del array PERMISOS -camera y 1 a write_external_storage
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED)
            ) {
                Log.i("MIAPP", "Me ha concedido Permisos");
            } else {
                Log.i("MIAPP", "NO ME ha concedido Permisos");
                Toast.makeText(this, "NO PUEDES SEGUIR", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }

        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
