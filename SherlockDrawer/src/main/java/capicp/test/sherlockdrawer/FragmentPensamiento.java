package capicp.test.sherlockdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

import capicp.test.sherlockdrawer.data.Pensamiento;

/**
 * Created by capi on 22/06/13.
 */
public class FragmentPensamiento extends SherlockFragment {

    private static final String PENSAMIENTO_KEY = "mensaje";
    private static final String AUTOR_KEY = "autor";


    private String mensaje;
    private String autor;
    private String autor_descripcion;

    public FragmentPensamiento(){
        mensaje = "Seleccione un penasdf";
    }

    public FragmentPensamiento(String mensaje) {

        this.mensaje = mensaje;
    }

    public FragmentPensamiento(Pensamiento pensamiento){

        mensaje = pensamiento.getCita();
        autor = pensamiento.getAutor_nombre();
        autor_descripcion = pensamiento.getAutor_descripcion();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pensamiento_view = inflater.inflate(R.layout.fragment_pensamiento, container, false);

        TextView texto = (TextView) pensamiento_view.findViewById(R.id.pensamiento_cuerpo);
        texto.setText(mensaje);

        if (autor != null ){
            texto = (TextView) pensamiento_view.findViewById(R.id.autor);
            texto.setText("- " + autor + ",");
        }

        if ( autor_descripcion != null ){
            texto = (TextView) pensamiento_view.findViewById(R.id.descripcion_autor);
            texto.setText(autor_descripcion);
        }

        ImageView i = (ImageView) pensamiento_view.findViewById(R.id.foto);
        i.setImageResource(R.drawable.steve);
        i.setAdjustViewBounds(true);

        return pensamiento_view;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(PENSAMIENTO_KEY, mensaje);
        outState.putString(AUTOR_KEY, autor);
    }
}
