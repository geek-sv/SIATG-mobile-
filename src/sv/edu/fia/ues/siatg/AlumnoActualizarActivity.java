package sv.edu.fia.ues.siatg;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AlumnoActualizarActivity extends Activity {
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
		setContentView(R.layout.activity_alumno_actualizar);
		helper = new ControlBD(this);
		editCarnet= (EditText) findViewById(R.id.editCarnet);
		editId_integrante = (EditText) findViewById(R.id.editId_integrante);
		editTelefono_a = (EditText) findViewById(R.id.editTelefono_a);
		editCorreo_a = (EditText) findViewById(R.id.editCorreo_a);
		editNombres_a= (EditText) findViewById(R.id.editNombres_a);
		editApellidos_a= (EditText) findViewById(R.id.editApellidos_a);
		editDireccion= (EditText) findViewById(R.id.editDireccion);
	}

	public void actualizarAlumno(View v) {
		Talumno alumno = new Talumno();
		alumno.setCarnet(editCarnet.getText().toString());
		alumno.setId_integrante(editId_integrante.getText().toString());
		alumno.setTelefono_a(editTelefono_a.getText().toString());
		alumno.setCorreo_a(editCorreo_a.getText().toString());
		alumno.setNombres_a(editNombres_a.getText().toString());
		alumno.setApellidos_a(editApellidos_a.getText().toString());
		alumno.setDireccion(editDireccion.getText().toString());
		
		helper.abrir();
		String estado = helper.actualizar(alumno);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
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

