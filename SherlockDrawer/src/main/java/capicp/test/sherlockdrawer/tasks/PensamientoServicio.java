package capicp.test.sherlockdrawer.tasks;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import capicp.test.sherlockdrawer.R;
import capicp.test.sherlockdrawer.data.Pensamiento;
import capicp.test.sherlockdrawer.data.PensamientoBDHelper;

/**
 * Created by capi on 12/07/13.
 */
public class PensamientoServicio extends IntentService {

    private static final int MINUTOS_ESPERA = 1;
    private static final int MILISEGS_EN_MINUTO = 60*1000;

    public PensamientoServicio() {
        super("PensamientoProgramandoServicio");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        long tiempo_espera = System.currentTimeMillis() + MINUTOS_ESPERA*MILISEGS_EN_MINUTO;
        int n, i;
        Pensamiento p = new Pensamiento();

        synchronized (this){
            try{

                SQLiteDatabase db = new PensamientoBDHelper(this).getReadableDatabase();

                String query = "SELECT cita, nombre, descripcion, foto FROM pensamientos, autores WHERE autores._id=autor_id";

                Cursor c = db.rawQuery(query, null);

                i = (int) (Math.random() * c.getCount()-1 );
                c.moveToPosition(i);
                p.setCita(c.getString(0));
                p.setAutor_nombre(c.getString(1));
                p.setAutor_foto(c.getString(3));

                db.close();

                Log.d("Servicio", p.toString());

                wait(tiempo_espera - System.currentTimeMillis());


                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(PensamientoBDHelper.obtenerFoto(p.getAutor_foto()))
                        .setContentTitle(p.getAutor_nombre())
                        .setContentText(p.getCita());

                NotificationManager nmanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                nmanager.notify(24, mBuilder.build());


            }catch (Exception e){}
        }


    }
}
