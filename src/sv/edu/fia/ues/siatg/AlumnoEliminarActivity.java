package sv.edu.fia.ues.siatg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AlumnoEliminarActivity extends Activity {
	EditText editCarnet;
	ControlBD controlhelper;
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_alumno_eliminar);
	controlhelper=new ControlBD (this);
	editCarnet=(EditText)findViewById(R.id.editCarnet);
}
public void eliminarAlumno(View v){
	String regEliminadas;
	Talumno alumno=new Talumno();
	alumno.setCarnet(editCarnet.getText().toString());
	controlhelper.abrir();
	regEliminadas=controlhelper.eliminar(alumno);
	controlhelper.cerrar();
	Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
}
}

