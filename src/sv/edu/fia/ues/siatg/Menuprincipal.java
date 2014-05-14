package sv.edu.fia.ues.siatg;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Menuprincipal extends ListActivity {

	String[] menu={"Tipo de proyecto","Perfil","Revisión de perfil","Docente","Grupo TG","Integrantes de grupo","Alumno","Trabajo de Graduacion","WEB Services","Defensa","Etapa","Asesoria","Detalle Nota","Asistencia","Ubicacion","Docente Asignado","GPS","LLenar Base de Datos"};
	String[] activities={"TipoProyectoMenuActivity","PerfilMenuActivity","RevisionperfilMenuActivity","DocenteMenuActivity","GrupoTGMenuActivity","IntegrantegrupoMenuActivity","AlumnoMenuActivity","TGMenuActivity","WebServiceHostingActivity","DefensaMenuActivity","11","AsesoriaMenuActivity","DetalleNotaMenuActivity","AsistenciaMenuActivity","UbicacionMenuActivity","DocenteasignadoMenuActivity","GPSActivity"};
	ControlBD BDhelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
		BDhelper=new ControlBD(this);
	}
	@Override
	protected void onListItemClick(ListView l,View v,int position,long id){
		super.onListItemClick(l, v, position, id);
		if(position!=17){
			String nombreValue=activities[position];
		try{
			Class<?> clase=Class.forName("sv.edu.fia.ues.siatg."+nombreValue);
			Intent inte = new Intent(this,clase);
			this.startActivity(inte);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		}else{
			BDhelper.abrir();
			String tost=BDhelper.llenarBD();
			BDhelper.cerrar();
			Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();
		}
	}

}
