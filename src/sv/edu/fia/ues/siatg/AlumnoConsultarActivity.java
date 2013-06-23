package sv.edu.fia.ues.siatg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AlumnoConsultarActivity extends Activity {

	ControlBD helper;
	EditText editCarnet;
	EditText editId_integrante;
	EditText editTelefono_a;
	EditText editCorreo_a;
	EditText editNombres_a;
	EditText editApellidos_a;
	EditText editDireccion;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_alumno_consultar);
		helper = new ControlBD(this);
		editCarnet= (EditText) findViewById(R.id.editCarnet);
		editId_integrante = (EditText) findViewById(R.id.editId_integrante);
		editTelefono_a = (EditText) findViewById(R.id.editTelefono_a);
		editCorreo_a = (EditText) findViewById(R.id.editCorreo_a);
		editNombres_a= (EditText) findViewById(R.id.editNombres_a);
		editApellidos_a= (EditText) findViewById(R.id.editApellidos_a);
		editDireccion= (EditText) findViewById(R.id.editDireccion);
		
	}
	public void consultarAlumno(View v) {
		helper.abrir();
		Talumno alumno = helper.consultarAlumno(editCarnet.getText().toString());
		helper.cerrar();
		
	if(alumno == null)
		Toast.makeText(this, "Alumno con carnet " + editCarnet.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
	else{
		editId_integrante.setText(alumno.getId_integrante());
		editTelefono_a.setText(alumno.getTelefono_a());
		editCorreo_a.setText(alumno.getCorreo_a());
		editNombres_a.setText(alumno.getNombres_a());
		editApellidos_a.setText(alumno.getApellidos_a());
		editDireccion.setText(alumno.getDireccion());
		}
	}
	public void limpiarTexto(View v) {
		editCarnet.setText("");
		editId_integrante.setText("");
		editTelefono_a.setText("");
		editCorreo_a.setText("");
		editNombres_a.setText("");
		editApellidos_a.setText("");
		editDireccion.setText("");
		
	}
	}

