package sv.edu.fia.ues.siatg;

import android.os.Bundle;
import android.app.Activity;
import java.util.List;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.widget.Toast;
//import java.util.GregorianCalendar;

@SuppressLint("NewApi")

public class GPSActivity extends Activity {
Button obtenerDir;

Button guardarDir;

EditText edtlatitud;
EditText edtlongitud;
EditText edtaltitud;
TextView edtdireccion;
LocationManager locationManager;

private String urlExterno = "http://ef04002pdm115.comuf.com/ws_insertardireccion.php";
//private String urlLocal = "http://192.168.87.41:8080/CarnetWebApplicationBASE/webresources/sv.ues.fia.carnet.entidad.nota/";
private String urlLocal = "http://localhost:8080/EF04002WebApplicationDB/webresources/sv.ues.fia.siatg.entidad.direccion";
@Override
@SuppressLint("NewApi")
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_gps);

//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//StrictMode.setThreadPolicy(policy);

obtenerDir = (Button) findViewById(R.id.btnObtenerDatosPos);
edtlatitud = (EditText) findViewById(R.id.edtLatitud);
edtlongitud = (EditText) findViewById(R.id.edtLongitud);
edtaltitud = (EditText) findViewById(R.id.edtAltitud);
edtdireccion = (TextView) findViewById(R.id.edtDireccion);
obtenerDir.setOnClickListener(onClickDireccion);
locationManager = (LocationManager) 
this.getSystemService(Context.LOCATION_SERVICE);
}
OnClickListener onClickDireccion = new OnClickListener() {
@Override
public void onClick(View v) {
// TODO Auto-generated method stub
Geocoder g = new Geocoder(getApplicationContext());
List<Address> ad = null;
try {
ad = g.getFromLocation(
Double.valueOf(edtlatitud.getText().toString()),
Double.valueOf(edtlongitud.getText().toString()), 
1);
} catch (Exception e) {
e.printStackTrace();
}
if (ad != null && ad.isEmpty() == false) {
edtdireccion.setText(ad.get(0).getThoroughfare() + ","
+ ad.get(0).getSubAdminArea() + ","
+ ad.get(0).getCountryName());
}
}
};
LocationListener locationListener = new LocationListener() {
public void onLocationChanged(Location location) {
// TODO Auto-generated method stub
edtlatitud.setText(String.valueOf(location.getLatitude()));
edtlongitud.setText(String.valueOf(location.getLongitude()));
edtaltitud.setText(String.valueOf(location.getAltitude()));
}
public void onProviderDisabled(String provider) {
// TODO Auto-generated method stub
}
public void onProviderEnabled(String provider) {
// TODO Auto-generated method stub
}
public void onStatusChanged(String provider, int status, Bundle extras) {
// TODO Auto-generated method stub
}
};
@Override
public void onPause() {
super.onPause();
locationManager.removeUpdates(locationListener);
}
@Override
public void onResume() {
super.onResume();
locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
0, locationListener);
locationManager.requestLocationUpdates(
LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
}

public void insertarDir(View v){
	String latitud=edtlatitud.getText().toString();
	String longitud=edtlongitud.getText().toString();
	String altitud=edtaltitud.getText().toString();
	String direccion=edtdireccion.getText().toString();
	//GregorianCalendar fecha=new GregorianCalendar();
	
	String url=null;
	
	//agregar en servicio local
	
	JSONObject datosDir= new JSONObject();
	try{
		datosDir.put("latitud", latitud);
		datosDir.put("longitud", longitud);
		datosDir.put("altitud", altitud);
		datosDir.put("direccion", direccion);
		//datosDir.put("fechaacceso", fecha);
		
		//ControladorServicio.insertarDir(urlLocal,datosDir,this);
		
	}catch (Exception e){
		Toast.makeText(this, "Error en los datos", Toast.LENGTH_LONG).show();
	}
	
	url=urlExterno+"?latitud="+latitud+"&longitud="+longitud+"&altitud="+altitud+"&direccion="+direccion;
	ControladorServicioM.insertarDirExterno(url,this);
	
	
}

}
