package sv.edu.fia.ues.siatg;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListActivity;
import android.view.View;

public class TipoProyectoMenuActivity extends ListActivity {

	String[] menu={"Insertar Registro","Eliminar Registro","Consultar Registro","Actualizar Registro"};
	String[] activities={"TipoProyectoInsertarActivity","TipoProyectoEliminarActivity","TipoProyectoConsultarActivity","TipoProyectoActualizarActivity"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView listView = getListView();
		//Ver si se necesita background
		listView.setBackgroundColor(Color.rgb(0,128,64));
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l,View v,int position,long id) {
		super.onListItemClick(l, v, position, id);
		String nombreValue=activities[position];
		
		l.getChildAt(position).setBackgroundColor(Color.rgb(128, 128, 255));
		
		try{
			Class<?> clase=Class.forName("sv.edu.fia.ues.siatg."+nombreValue);
			Intent inte=new Intent(this,clase);
			this.startActivity(inte);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}

}
