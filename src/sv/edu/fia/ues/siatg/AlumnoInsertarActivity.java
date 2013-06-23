package sv.edu.fia.ues.siatg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AlumnoInsertarActivity extends Activity {

	ControlBD helper;
	EditText editCarnet;
	EditText editId_integrante;
	EditText editTelefono_a;
	EditText editCorreo_a;
	EditText editNombres_a;
	EditText editApellidos_a;
	EditText editDireccion;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alumno_insertar);
		helper = new ControlBD(this);
		editCarnet = (EditText) findViewById(R.id.editCarnet);
		editId_integrante = (EditText) findViewById(R.id.editId_integrante);
		editTelefono_a = (EditText) findViewById(R.id.editTelefono_a);	
		editCorreo_a = (EditText) findViewById(R.id.editCorreo_a);
		editNombres_a= (EditText) findViewById(R.id.editNombres_a);
		editApellidos_a= (EditText) findViewById(R.id.editApellidos_a);
		editDireccion= (EditText) findViewById(R.id.editDireccion);		
	}

	public void insertarAlumno(View v) {
		String carnet = editCarnet.getText().toString();
		String id_integrante = editId_integrante.getText().toString();
		String telefono_a = editTelefono_a.getText().toString();
		String correo_a = editCorreo_a.getText().toString();
		String nombres_a = editNombres_a.getText().toString();
		String apellidos_a = editApellidos_a.getText().toString();			
		String direccion = editDireccion.getText().toString();
		
		
		String regInsertados;
		Talumno alumno = new Talumno();
		
		alumno.setCarnet(carnet);
		alumno.setId_integrante(id_integrante);
		alumno.setTelefono_a(telefono_a);
		alumno.setCorreo_a(correo_a);
		alumno.setNombres_a(nombres_a);
		alumno.setApellidos_a(apellidos_a);
		alumno.setDireccion(direccion);	
		
		helper.abrir();
		regInsertados = helper.insertar(alumno);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
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