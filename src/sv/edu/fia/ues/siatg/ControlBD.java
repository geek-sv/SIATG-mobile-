package sv.edu.fia.ues.siatg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlBD{
	
	public ControlBD(Context context, DatabaseHelper dBHelper, SQLiteDatabase db) {
		super();
		this.context = context;
		DBHelper = dBHelper;
		this.db = db;
	}

	
	private static final String[]camposAlumno = new String [] 
			{"carnet","id_integrante", "telefono_a", "correo_a","nombre_a","apellido_a", "direccion"};
			private static final String[]camposAsesoria = new String [] 
			{"id_asesoria","id_tg","hora_asesoria","num_asesoria", "fecha_asesoria", "observacion_a"};
			private static final String[] camposAsistencia = new String [] 
			{"id_asistencia","id_asesoria","estado_as"};
			private static final String[] camposDefensa = new String [] 
			{"id_defensa","id_tg","id_ubicacion","hora","fecha_def"};
			private static final String[] camposDetalle_nota = new String [] 
			{"id_defensa","carnet","nota_ase","nota_obs"};
			private static final String[] camposDocente = new String [] 
			{"id_docente","can_perfiles_apr","can_perfiles_rep","correo_d","nombres_d","apellidos_d"};
			private static final String[] camposDocente_asignado = new String [] 
			{"id_docente","porcentaje_doc","modalidad"};
			private static final String[] camposGrupo_tg = new String [] 
			{"id_grupo_tg","anio"};
			private static final String[] camposIntegrante_grupo = new String [] 
			{"representante","nota_final","id_integrante","id_grupo_tg"};
			private static final String[] camposPerfil = new String [] 
			{"id_perfil","id_grupo_tg","id_tg","id_tipo_proy","tema","estado","carrera"};	
			private static final String[] camposRevision_perfil = new String [] 
			{"id_rev_perfil","id_docente","fecha_rev","observacion_rev"};
			private static final String[] camposTipo_proyecto = new String [] 
			{"id_tipo_proy","nombre_pry"};
			private static final String[] camposTrabajo_graduacion = new String [] 
			{"id_tg","id_perfil","fecha_inicio","fecha_fin"};
			private static final String[] camposUbicacion = new String [] 
			{"id_ubicacion","local","descripcion_ub"};

private final Context context;
private DatabaseHelper DBHelper;
private SQLiteDatabase db;

public ControlBD(Context ctx) {
this.context = ctx;
DBHelper = new DatabaseHelper(context);
}


private static class DatabaseHelper extends SQLiteOpenHelper {
private static final String BASE_DATOS = "basesiatg.s3db";
private static final int VERSION = 1;

public DatabaseHelper(Context context) {
	super(context, BASE_DATOS, null, VERSION);
}

@Override

public void onCreate(SQLiteDatabase db) {
	try{
		
		db.execSQL("CREATE TABLE alumno(carnet VARCHAR(7) NOT NULL PRIMARY KEY, id_integrante VARCHAR(7), nombre_a VARCHAR(50), apellido_a VARCHAR(50), direccion VARCHAR(100), telefono_a VARCHAR(9), correo_a VARCHAR(100));");
		db.execSQL("CREATE TABLE asesoria(id_asesoria VARCAHR(7) NOT NULL PRIMARY KEY, id_tg VARCHAR(7), hora_asesoria VARCHAR(8), num_asesoria INTEGER,fecha_asesoria VARCHAR(8), observacion_a VARCHAR(100));");
		db.execSQL("CREATE TABLE asistencia(id_asistencia VARCHAR(7) NOT NULL PRIMARY KEY, id_asesoria VARCHAR(7), estado_as VARCHAR(8));");
		db.execSQL("CREATE TABLE defensa(id_defensa VARCHAR(7) NOT NULL PRIMARY KEY, id_tg VARCHAR(7), id_ubicacion VARCHAR(7), hora VARCHAR(8), fecha_def VARCHAR(8));");
		db.execSQL("CREATE TABLE detalle_nota(id_defensa VARCHAR(7) NOT NULL PRIMARY KEY, carnet VARCHAR(7), nota_ase FLOAT,  nota_obs FLOAT);");
		db.execSQL("CREATE TABLE docente(id_docente VARCHAR(7) NOT NULL PRIMARY KEY, can_perfiles_apr INTEGER(7), can_perfiles_rep INTEGER, correo_d VARCHAR(100), nombres_d VARCHAR(50), apellidos_d VARCHAR(50));");
		db.execSQL("CREATE TABLE docente_asignado(id_docente VARCHAR(7) NOT NULL PRIMARY KEY, porcentaje_doc FLOAT(3), modalidad VARCHAR(25));");
		db.execSQL("CREATE TABLE etapa(id_etapa VARCHAR(7) NOT NULL PRIMARY KEY, id_defensa VARCHAR(7), nombre VARCHAR(50), descripcion_et VARCHAR(50), porcentaje_etapa FLOAT(3));");
		db.execSQL("CREATE TABLE grupo_tg(id_grupo_tg VARCHAR(7) NOT NULL PRIMARY KEY, anio VARCHAR(7));");
		db.execSQL("CREATE TABLE integrante_grupo(representante VARCHAR(50), nota_final FLOAT(3), id_integrante VARCHAR(7) NOT NULL PRIMARY KEY, id_grupo_tg VARCHAR(7));");
		db.execSQL("CREATE TABLE revision_perfil(id_rev_perfil VARCHAR(7)  NOT NULL PRIMARY KEY, id_perfil  VARCHAR(7), id_docente VARCHAR(7), fecha_rev VARCHAR(8), observacion_rev VARCHAR(100));");
		db.execSQL("CREATE TABLE tipo_proyecto(id_tipo_proy VARCHAR(7) NOT NULL PRIMARY KEY, nombre_pry VARCHAR(50));");
		db.execSQL("CREATE TABLE trabajo_graduacion(id_tg VARCHAR(7) NOT NULL PRIMARY KEY, id_perfil VARCHAR(7), fecha_inicio VARCHAR(8),fecha_fin VARCHAR(8));");
		db.execSQL("CREATE TABLE ubicacion(id_ubicacion VARCHAR(7) NOT NULL PRIMARY KEY, local VARCHAR(50), descripcion_ub VARCHAR(8));");
		db.execSQL("CREATE TABLE perfil(id_perfil VARCHAR(7)  NOT NULL PRIMARY KEY, id_grupo_tg  VARCHAR(7), id_tg VARCHAR(7), id_tipo_proy VARCHAR(7), tema VARCHAR(100), estado VARCHAR(15), carrera VARCHAR(50))");
	    ///DIANA
		//db.execSQL("CREATE TRIGGER fk_defensa_ubicacion BEFORE INSERT ON defensa FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT id_ubicacion FROM ubicacion WHERE id_ubicacion = NEW.id_ubicacion) IS NULL) THEN RAISE(ABORT, 'No existe dicha ubicacion') END; END;");
		//db.execSQL("CREATE TRIGGER fk_defensa_trabajograduacion BEFORE INSERT ON defensa FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT id_tg FROM trabajo_graduacion WHERE id_tg = NEW.id_tg) IS NULL) THEN RAISE(ABORT, 'No existe dicha tg') END; END;");
		//db.execSQL("CREATE TRIGGER fk_asistencia_asesoria BEFORE INSERT ON asistencia FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT id_asesoria FROM asesoria WHERE id_asesoria = NEW.id_asesoria) IS NULL) THEN RAISE(ABORT, 'No existe dicha asesoria') END; END;");
		
		
				
		}catch(SQLException e){
			e.printStackTrace();
		}
}
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
	}
}
public void abrir() throws SQLException{
	db = DBHelper.getWritableDatabase();
	return;
}
public void cerrar(){
	DBHelper.close();
}


public String insertar(Tasesoria tasesoria){
	String regInsertados="Registro Insertado Nº= ";
	
	long contador=0;
	//if(verificarIntegridad(nota,1))
	//{
		ContentValues ase = new ContentValues();
		ase.put("id_asesoria", tasesoria.getid_asesoria());
		ase.put("id_tg", tasesoria.getid_tg());
		ase.put("hora_asesoria", tasesoria.gethora_asesoria());
		ase.put("num_asesoria", tasesoria.getnum_asesoria());
		ase.put("fecha_asesoria", tasesoria.getfecha_asesoria());
		ase.put("observacion_a", tasesoria.getobservacion_a());
		
		contador=db.insert("asesoria", null, ase);
	//}
	if(contador==-1 || contador==0)
	{
		regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
	}
	//else {
		//regInsertados=regInsertados+contador;
	//}
	return regInsertados;
	}


public Tasesoria consultarAsesoria(String id_asesoria ){
	
	String[] id = {id_asesoria};
	Cursor cursor = db.query("asesoria", camposAsesoria, "id_asesoria = ? ", id, null, null, null);
	
	if(cursor.moveToFirst()){
		Tasesoria tasesoria = new Tasesoria();
		tasesoria.setid_asesoria(cursor.getString(0));
		tasesoria.setid_tg(cursor.getString(1));
		tasesoria.sethora_asesoria(cursor.getString(2));
		tasesoria.setnum_asesoria(cursor.getInt(3));
		tasesoria.setfecha_asesoria(cursor.getString(4));
		tasesoria.setobservacion_a(cursor.getString(5));
	
		
		
		
	return tasesoria;
	
	}else{
		return null;
	}
}

public String actualizar(Tasesoria tasesoria){
	//if(verificarIntegridad(nota, 2)){
		String[] id = {tasesoria.getid_asesoria(),tasesoria.getid_tg()};
		ContentValues cv = new ContentValues();
		
		cv.put("hora_asesoria", ""+tasesoria.gethora_asesoria());
		cv.put("num_asesoria", ""+tasesoria.getnum_asesoria());
		cv.put("fecha_asesoria", ""+tasesoria.getfecha_asesoria());
		cv.put("observacion_a", ""+tasesoria.getobservacion_a());
		
		db.update("asesoria", cv, "id_asesoria = ? AND id_tg = ? ", id);
		return "Registro Actualizado Correctamente";
	//}else{
		//return "Registro no Existe";
	}


public String eliminar(Tasesoria tasesoria){
	
	String regAfectados="filas afectadas= ";
	int contador=0;
	String where="id_asesoria='"+tasesoria.getid_asesoria()+"'";
	where=where+" AND id_tg='"+tasesoria.getid_tg()+"'";
	//where=where+" AND ciclo="+nota.getCiclo();
	contador+=db.delete("asesoria", where, null);
	regAfectados+=contador;
	return regAfectados;
}





public String insertar(Tperfil tperfil){
	String regInsertados="Registro Insertado Nº= ";
	long contador=0;
	ContentValues perf = new ContentValues();
	perf.put("id_perfil", tperfil.getid_perfil());
	perf.put("id_grupo_tg", tperfil.getid_grupo_tg());
	perf.put("id_tg", tperfil.getid_tg());
	perf.put("id_tipo_proy", tperfil.getid_tipo_ptoy());
	perf.put("tema", tperfil.gettema());
	perf.put("estado", tperfil.getestado());
	perf.put("carrera",tperfil.getcarrera());
	
	
	
	contador=db.insert("perfil", null, perf);
	if(contador==-1 || contador==0)
	{
		regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
	}
	else {
		regInsertados=regInsertados+contador;
	}
	return regInsertados;
}

public Tperfil consultarperfil(String id_perfil){
	String[] id = {id_perfil};
	Cursor cursor = db.query("perfil", camposPerfil, "id_perfil = ?", id, 
	null, null, null);
	if(cursor.moveToFirst()){
		Tperfil tperfil = new Tperfil();
		tperfil.setid_perfil(cursor.getString(0));
		tperfil.setid_grupo_tg(cursor.getString(1));
		tperfil.setid_tg(cursor.getString(2));
		tperfil.setid_tipo_proy(cursor.getString(3));
		tperfil.settema(cursor.getString(4));
		tperfil.setestado(cursor.getString(5));
		tperfil.setcarrera(cursor.getString(6));
		
		
		return tperfil;
	}else{
		return null;
	}
}


public String actualizar(Tperfil tperfil){
	//if(verificarIntegridad(tperfil, 5)){
		String[] id = {tperfil.getid_perfil()};
		ContentValues cv = new ContentValues();
		cv.put("id_perfil",tperfil.getid_perfil());
		cv.put("id_grupo_tg",tperfil.getid_grupo_tg());
		cv.put("id_tg", tperfil.getid_tg());
		cv.put("id_tipo_proy", tperfil.getid_tipo_ptoy());
		cv.put("tema", tperfil.gettema());
		cv.put("estado", tperfil.getestado());
		cv.put("carrera", tperfil.getcarrera());
		db.update("perfil", cv, "id_perfil = ?", id);
		
		return "Registro Actualizado Correctamente";
	//}else{
		//return "Registro con Id_perfil " + tperfil.getid_perfil() + " no existe";
	//}
}


public String eliminar(Tperfil tperfil){
	String regAfectados="filas afectadas= ";
	int contador=0;
	//if (verificarIntegridad(alumno,3)) {
	//contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
	//}
	contador+=db.delete("perfil", "id_perfil='"+tperfil.getid_perfil()+"'", null);
	regAfectados+=contador;
	return regAfectados;
}


////////////////////////////////////FUNCIONES DIANA/////////////////////////////////////////////////////////

//insertar Ubicacion

	public String insertar(Tubicacion ubicacion) {

		String regInsertados = "Registro Insertado Nº= ";
		long contador = 0;
		ContentValues ubi = new ContentValues();
		ubi.put("id_ubicacion", ubicacion.getId_ubicacion());
		ubi.put("local", ubicacion.getLocal());
		ubi.put("descripcion_ub", ubicacion.getDescripcion_ub());

		contador = db.insert("ubicacion", null, ubi);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;

	}

	
	 //consultar Ubicacion
	
	public Tubicacion consultarUbicacion(String id_ubicacion){
		String[] id = {id_ubicacion};
		Cursor cursor = db.query("ubicacion", camposUbicacion, "id_ubicacion = ?", id,null, null, null);
		if(cursor.moveToFirst()){
			Tubicacion ubicacion = new Tubicacion();
			ubicacion.setId_ubicacion(cursor.getString(0));
			ubicacion.setLocal(cursor.getString(1));
			ubicacion.setDescripcion_ub(cursor.getString(2));
			
			return ubicacion;
		}
		else
		{
			return null;
		}
	}
	
	
	//actualizar ubicacion

	public String actualizar(Tubicacion ubicacion){
		
		if(verificarIntegridadDiana(ubicacion, 4)){
			
			String[] id = {ubicacion.getId_ubicacion()};
			ContentValues cv = new ContentValues();
			cv.put("local",ubicacion.getLocal());
			cv.put("descripcion_ub",ubicacion.getDescripcion_ub());
			db.update("ubicacion", cv, "id_ubicacion = ?", id);
			return "Registro Actualizado Correctamente";
			} else{
			return "Registro con id_ubicacion" + ubicacion.getId_ubicacion() + " no existe";
			}
	}
	
	
	//eliminar ubicacion
	public String eliminar(Tubicacion ubicacion){
		String regAfectados="filas afectadas= ";
		int contador=0;
		if (verificarIntegridadDiana(ubicacion,3)) {
		contador+=db.delete("defensa", "id_ubicacion='"+ubicacion.getId_ubicacion()+"'", null);
		}
		contador+=db.delete("ubicacion", "id_ubicacion='"+ubicacion.getId_ubicacion()+"'", null);
		regAfectados+=contador;
		return regAfectados;
	}
	
	
	// insertar asistencia
		public String insertar(Tasistencia asistencia) {

			String regInsertados = "Registro Insertado Nº= ";
			long contador = 0;
			ContentValues asi = new ContentValues();
			asi.put("id_asistencia", asistencia.getId_asistencia());
			asi.put("id_asesoria", asistencia.getId_asesoria());
			asi.put("estado_as", asistencia.getEstado_as());

			contador = db.insert("asistencia", null, asi);
			if (contador == -1 || contador == 0) {
				regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
			} else {
				regInsertados = regInsertados + contador;
			}
			return regInsertados;

		}
	// consultar asistencia
		public Tasistencia consultarAsistencia(String id_asistencia){
			String[] id = {id_asistencia};
			Cursor cursor = db.query("asistencia", camposAsistencia, "id_asistencia = ?", id,null, null, null);
			if(cursor.moveToFirst()){
			Tasistencia asistencia = new Tasistencia();
			asistencia.setId_asistencia(cursor.getString(0));
			asistencia.setId_asesoria(cursor.getString(1));
			asistencia.setEstado_as(cursor.getString(2));
			
			return asistencia;
			}
			else
			{
			return null;
			}
		}
		
		
		//actualizar asistencia
		public String actualizar(Tasistencia asistencia){
			if(verificarIntegridadDiana(asistencia, 5)){
				String[] id = {asistencia.getId_asistencia()};
				ContentValues cv = new ContentValues();
				cv.put("id_asesoria", asistencia.getId_asesoria());
				cv.put("estado_as", asistencia.getEstado_as());
				db.update("asistencia", cv, "id_asistencia = ?", id);
				return "Registro Actualizado Correctamente";
				}else{
				return "Registro con codigo de asistencia " + asistencia.getId_asistencia() + " no existe";
				}
		}

		
		//eliminar asistencia
			public String eliminar(Tasistencia asistencia){
				
					String regAfectados="filas afectadas= ";
					int contador=0;
					
					contador+=db.delete("asistencia", "id_asistencia='"+asistencia.getId_asistencia()+"'", null);
					regAfectados+=contador;
					return regAfectados;
				
			}
	
			// insertar Defensa

			public String insertar(Tdefensa defensa) {
				String regInsertados = "Registro Insertado Nº= ";

				long contador = 0;
				//if (verificarIntegridadDiana(defensa, 1)) {
					ContentValues def = new ContentValues();

					def.put("id_defensa", defensa.getId_defensa());
					def.put("id_tg", defensa.getId_tg());
					def.put("id_ubicacion", defensa.getId_ubicacion());
					//def.put("id_etapa", defensa.getId_etapa());
					def.put("hora", defensa.getHora());
					def.put("fecha_def", defensa.getFecha());
					contador = db.insert("defensa", null, def);
				//}
				if (contador == -1 || contador == 0) {
					regInsertados = "Error, Verifique que exista ubicacion,tg o etapa asociada al registro"
							+ defensa.getId_ubicacion();
				} else {
					regInsertados = regInsertados + contador;
				}
				return regInsertados;
			}
			
			
			 //consultar Defensa
			
				public Tdefensa consultarDefensa(String id_defensa){
					String[] id = {id_defensa};
					Cursor cursor = db.query("defensa", camposDefensa, "id_defensa = ?", id,null, null, null);
					if(cursor.moveToFirst()){
						Tdefensa defensa = new Tdefensa();
						defensa.setId_defensa(cursor.getString(0));
						defensa.setId_tg(cursor.getString(1));
						defensa.setId_ubicacion(cursor.getString(2));
						//defensa.setId_etapa(cursor.getString(3));
						defensa.setHora(cursor.getString(3));
						defensa.setFecha(cursor.getString(4));
						
						return defensa;
					}
					else
					{
						return null;
					}
				}
				
				//actualizar Defensa


				public String actualizar(Tdefensa defensa){
					
						if(verificarIntegridadDiana(defensa, 2)){
							String[] id = {defensa.getId_defensa()};
							ContentValues cv = new ContentValues();
							cv.put("id_tg", defensa.getId_tg());
							cv.put("id_ubicacion", defensa.getId_ubicacion());
							//cv.put("id_etapa", defensa.getId_etapa());
							cv.put("hora", defensa.getHora());
							cv.put("fecha_def", defensa.getFecha());
							db.update("defensa", cv, "id_defensa = ?", id);
							return "Registro Actualizado Correctamente";
							}else{
							return "Registro con codigo de defensa " + defensa.getId_defensa() + " no existe";
							}
					}
				
				//eliminar defensa
				public String eliminar(Tdefensa defensa){
					String regAfectados="filas afectadas= ";
					int contador=0;
					//if (verificarIntegridad(defensa, 4 =3)) {
					//contador+=db.delete("defensa", "id_ubicacion='"+ubicacion.getId_ubicacion()+"'", null);
					//}
					contador+=db.delete("defensa", "id_defensa='"+defensa.getId_defensa()+"'", null);
					regAfectados+=contador;
					return regAfectados;
				}
				
				
				public String insertar(Tintegrantegrupo tintegrantegrupo){
					String regInsertados="Registro Insertado Nº= ";
					
					long contador=0;
					
					//if(verificarIntegridadjls(tperfil,1))
					//{
					ContentValues integ = new ContentValues();
					integ.put("representante", tintegrantegrupo.getrepresentante());
					integ.put("nota_final", tintegrantegrupo.getnota_final());
					integ.put("id_integrante", tintegrantegrupo.getid_integrante());
					integ.put("id_grupo_tg", tintegrantegrupo.getid_grupo_tg());
					contador=db.insert("integrante_grupo", null, integ);
					
					if(contador==-1 || contador==0)
					{
						regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
					}
					else {
						regInsertados=regInsertados+contador;
					}
					return regInsertados;
				}
				
				// consultar integrante
				public Tintegrantegrupo consultarintegrante(String id_integrante){
					String[] id = {id_integrante};
					Cursor cursor = db.query("integrante_grupo", camposIntegrante_grupo, "id_integrante = ?", id,null, null, null);
					if(cursor.moveToFirst()){
					Tintegrantegrupo integrante = new Tintegrantegrupo();
					integrante.setrepresentante(cursor.getString(0));
					integrante.setnota_final(cursor.getFloat(1));
					integrante.setid_integrante(cursor.getString(2));
					integrante.setid_grupo_tg(cursor.getString(3));
					
					
					return integrante;
					}
					else
					{
					return null;
					}
				}
				
				
				public String actualizar(Tintegrantegrupo tintegrante){
					
				//	if(verificarIntegridadjls(integrantegrupo, 2)){
						String[] id = {tintegrante.getid_integrante()};
						ContentValues cv = new ContentValues();
						cv.put("representante", tintegrante.getrepresentante());
						cv.put("nota_final", tintegrante.getnota_final());
						
						cv.put("id_integrante", tintegrante.getid_integrante());
						cv.put("id_grupo_tg", tintegrante.getid_integrante());
						db.update("integrante_grupo", cv, "id_integrante = ?", id);
						return "Registro Actualizado Correctamente";
					//	}
						//else{
						//return "Registro con codigo de integrante " + tintegrante.getid_integrante() + " no existe";
						}
				//}

				public String eliminar(Tintegrantegrupo tintegrante){
					String regAfectados="filas afectadas= ";
					int contador=0;
					//if (verificarIntegridad(defensa, 4 =3)) {
					//contador+=db.delete("defensa", "id_ubicacion='"+ubicacion.getId_ubicacion()+"'", null);
					//}
					contador+=db.delete("integrante_grupo", "id_integrante ='"+tintegrante.getid_integrante() +"'", null);
					regAfectados+=contador;
					return regAfectados;
				}
				
				
	///////////////////////////////////////martin//////////////////////////////////////////////
				
				public String insertar(Ttipoproyecto tipoproyecto){
					
					String regInsertados="Registro Insertado Nº= ";
					long contador=0;
					
					ContentValues tipoproy=new ContentValues();
					tipoproy.put("id_tipo_proy",tipoproyecto.getIdTipoProy());
					tipoproy.put("nombre_pry",tipoproyecto.getNombre_pry());
					contador=db.insert("TIPO_PROYECTO", null, tipoproy);
					
					if(contador==-1|| contador==0){
						regInsertados="Error al insertar el registro, Registro Duplicado. Verificar insercion";
					}
					else{
						regInsertados=regInsertados+contador;
					}
					return regInsertados;
					}
					
					public String actualizar(Ttipoproyecto tipoproyecto){
						
						if(verificarIntegridadMartin(tipoproyecto,1)){
							String[] id = {tipoproyecto.getIdTipoProy()};
							ContentValues cv= new ContentValues();
							cv.put("nombre_pry",tipoproyecto.getNombre_pry());
							db.update("tipo_proyecto",cv,"id_tipo_proy = ?",id);
							return "Registro Actualizado Correctamente";
							
						}else{
							return "Registro con ID "+tipoproyecto.getIdTipoProy()+"no existe";
						}
					}
					
					public Ttipoproyecto consultarTipoProyecto(String id_tipo_proy){
						String[] id={id_tipo_proy};
						Cursor cursor=db.query("tipo_proyecto",camposTipo_proyecto,"id_tipo_proy = ?",id,null,null,null);
						if(cursor.moveToFirst()){
							Ttipoproyecto tipoProy=new Ttipoproyecto();
							tipoProy.setIdTipoProy(cursor.getString(0));
							tipoProy.setNombre_pry(cursor.getString(1));
							return tipoProy;
						}else{
							return null;
						}
						
					}
					
					public String eliminarTipoProyecto(Ttipoproyecto tipoproyecto){
						String regAfectados="filas afectadas= ";
						int contador=0;
						if(verificarIntegridadMartin(tipoproyecto,2)){
							contador+=db.delete("perfil", "id_tipo_proy='"+tipoproyecto.getIdTipoProy()+"'",null);
						}
						contador+=db.delete("tipo_proyecto", "id_tipo_proy='"+tipoproyecto.getIdTipoProy()+"'", null);
						regAfectados+=contador;
						return regAfectados;
					}
				//////////////////////////////////////////////////////////////////
					///Verificar integridad para tablas Tipo Proyecto, Trabajo Graduacion, Etapa
					
						private boolean verificarIntegridadMartin(Object dato,int relacion) throws SQLException{
							
							switch(relacion){
							
							case 1:{
								//verificar que exista tipo Proyecto
								Ttipoproyecto tipoproy2 = (Ttipoproyecto)dato;
								String[] id={tipoproy2.getIdTipoProy()};
								abrir();
								Cursor c2 =db.query("tipo_proyecto",null,"id_tipo_proy = ?",id,null,null,null);
								if(c2.moveToFirst()){
									return true;
								}
								return false;
							}
							
							case 2:
							{
								Ttipoproyecto tipoproy= (Ttipoproyecto) dato;
								Cursor c=db.query(true,"perfil", new String[]{"id_tipo_proy"},"id_tipo_proy= '"+tipoproy.getIdTipoProy()+"'",null, null,null,null,null);
								if(c.moveToFirst())
									return true;
								else 
									return false;
							}
							
							
							default:
								return false;
								
						 }
					   }

						
	////////////////////////////////////
						
						//revison perfil
						public String insertar (Trevisionperfil trevisionperfil){
							String regInsertados="Registro Insertado Nº= ";
							long contador=0;
								ContentValues revisionperfil = new ContentValues();
								revisionperfil.put("id_rev_perfil", trevisionperfil.getId_rev_perfil());
								revisionperfil.put("id_perfil", trevisionperfil.getId_perfil());
								revisionperfil.put("id_docente", trevisionperfil.getId_docente());
								revisionperfil.put("fecha_rev", trevisionperfil.getFecha_rev());
								revisionperfil.put("observacion_rev", trevisionperfil.getObservacion_rev());
								contador=db.insert("revision_perfil", null, revisionperfil);
							if(contador==-1 || contador==0)
							{
								regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
							}
							else {
								regInsertados=regInsertados+contador;
							}
							return regInsertados;
						}
						
						//funcion insertar docente asignado
						public String insertar (Tdocenteasignado tdocenteasignado){
							String regInsertados="Registro Insertado Nº= ";
							long contador=0;
							//if(verificarIntegridadJC(tdocenteasignado,1))
							//{
								ContentValues docenteasignado = new ContentValues();
								docenteasignado.put("id_docente", tdocenteasignado.getId_docente());
								docenteasignado.put("porcentaje_doc", tdocenteasignado.getPorcentaje_doc());
								docenteasignado.put("modalidad", tdocenteasignado.getModalidad());
								contador=db.insert("docente_asignado", null, docenteasignado);
							//}
							if(contador==-1 || contador==0)
							{
								regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
							}
							else {
								regInsertados=regInsertados+contador;
							}
							return regInsertados;
						}
						
						//actualizar docente_asginado
						public String actualizar(Tdocenteasignado tdocenteasignado){
							//if(verificarIntegridadJC(tdocenteasignado, 2)){
								String[] id = {tdocenteasignado.getId_docente()};
								ContentValues cv = new ContentValues();
								cv.put("porcentaje_doc", tdocenteasignado.getPorcentaje_doc());
								cv.put("modalidad", tdocenteasignado.getModalidad());
								db.update("docente_asignado", cv, "id_docente = ?", id);
								return "Registro Actualizado Correctamente";
							//}else{
								//return "Registro con carnet " + tdocenteasignado.getId_docente() + " no existe";
							//}
						}
						
						//actualizar revision_perfil
						public String actualizar(Trevisionperfil trevisionperfil){
							//if(verificarIntegridadJC(tdocenteasignado, 2)){
								String[] id = {trevisionperfil.getId_rev_perfil()};
								ContentValues cv = new ContentValues();
								cv.put("id_perfil", trevisionperfil.getId_docente());
								cv.put("id_docente", trevisionperfil.getId_docente());
								cv.put("fecha_rev", trevisionperfil.getFecha_rev());
								cv.put("observacion_rev", trevisionperfil.getObservacion_rev());
								db.update("revision_perfil", cv, "id_rev_perfil = ?", id);
								return "Registro Actualizado Correctamente";
							//}else{
								//return "Registro con carnet " + tdocenteasignado.getId_docente() + " no existe";
							//}
						}
							
						//consultar docente asignado
							public Tdocenteasignado consultardocenteasginado(String id_docente){
								String[] id = {id_docente};
								Cursor cursor = db.query("docente_asignado", camposDocente_asignado, "id_docente = ?", id, null, null, null);
								if(cursor.moveToFirst()){
									Tdocenteasignado tdocenteasignado = new Tdocenteasignado();
									tdocenteasignado.setId_docente(cursor.getString(0));
									tdocenteasignado.setPorcentaje_doc(Float.valueOf(cursor.getString(1)));
									tdocenteasignado.setModalidad(cursor.getString(2));
												
									return tdocenteasignado;
								}else{
									return null;
								}
							}
							
							//consultar revision perfil
							public Trevisionperfil consultarrevisionperfil(String id_rev_perfil){
								String[] id = {id_rev_perfil};
								Cursor cursor = db.query("revision_perfil", camposRevision_perfil, "id_rev_perfil = ?", id, null, null, null);
								if(cursor.moveToFirst()){
									Trevisionperfil trevisionperfil = new Trevisionperfil();
									trevisionperfil.setId_rev_perfil(cursor.getString(0));
									trevisionperfil.setId_perfil(cursor.getString(1));
									trevisionperfil.setId_docente(cursor.getString(2));
									trevisionperfil.setFecha_rev(cursor.getString(3));
									trevisionperfil.setObservacion_rev(cursor.getString(4));;
												
									return trevisionperfil;
								}else{
									return null;
								}
							}
							//fucnion eliminar docente asignado
							public String eliminar(Tdocenteasignado docenteasignado){
								String regAfectados="filas afectadas= ";
								int contador=0;
								contador+=db.delete("docente_asignado", "id_docente='"+docenteasignado.getId_docente()+"'", null);
								regAfectados+=contador;
								return regAfectados;
							}
						
							//fucnion eliminar docente asignado
							public String eliminar(Trevisionperfil revisionperfil){
								String regAfectados="filas afectadas= ";
								int contador=0;
								contador+=db.delete("revision_perfil", "id_rev_perfil='"+revisionperfil.getId_rev_perfil()+"'", null);
								regAfectados+=contador;
								return regAfectados;
							}
						
						//////////////////////////marce/////////////////////////////////////////
						
						public String insertargrupotg(Tgrupotg tgrupotg){
							String regInsertados="Registro Insertado Nº= ";
							long contador=0;
							ContentValues grupotg = new ContentValues();
							grupotg.put("id_grupo_tg", tgrupotg.getId_grupo_tg());
							grupotg.put("anio", tgrupotg.getAnio());
							
							contador=db.insert("grupo_tg", null, grupotg);
							if(contador==-1 || contador==0)
							{
								regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
							}
							else {
								regInsertados=regInsertados+contador;
							}
							return regInsertados;
						}



						public String insertardocente(Tdocente tdocente){
							String regInsertados="Registro Insertado Nº= ";
							long contador=0;
							ContentValues docente = new ContentValues();
							docente.put("id_docente", tdocente.getId_docente());
							docente.put("can_perfiles_apr", tdocente.getCan_perfiles_apr());
							docente.put("can_perfiles_rep", tdocente.getCan_perfiles_rep());
							docente.put("correo_d", tdocente.getCorreo_d());
							docente.put("nombres_d", tdocente.getNombres_d());
							docente.put("apellidos_d", tdocente.getApellidos_d());
							
							contador=db.insert("docente", null, docente);
							if(contador==-1 || contador==0)
							{
								regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
							}
							else {
								regInsertados=regInsertados+contador;
							}
							return regInsertados;
						}

						public Tgrupotg consultargrupotg(String id_grupo_tg){
							String[] id = {id_grupo_tg};
							Cursor cursor = db.query("grupo_tg", camposGrupo_tg, "id_grupo_tg = ?", id, 
							null, null, null);
							if(cursor.moveToFirst()){
								Tgrupotg tgrupotg = new Tgrupotg();
								tgrupotg.setId_grupo_tg(cursor.getString(0));
								tgrupotg.setAnio(cursor.getString(1));
								
								
								
								return tgrupotg;
							}else{
								return null;
							}
						}




						public Tdocente consultardocente(String id_docente){
							String[] id = {id_docente};
							Cursor cursor = db.query("docente", camposDocente, "id_docente = ?", id, 
							null, null, null);
							if(cursor.moveToFirst()){
								Tdocente tdocente = new Tdocente();
								tdocente.setId_docente(cursor.getString(0));
								tdocente.setCan_perfiles_apr(cursor.getInt(1));
								tdocente.setCan_perfiles_rep(cursor.getInt(2));
								tdocente.setCorreo_d(cursor.getString(3));
								tdocente.setNombres_d(cursor.getString(4));
								tdocente.setApellidos_d(cursor.getString(5));
								
								
								
								return tdocente;
							}else{
								return null;
							}
						}
						
						public String actualizargrupotg(Tgrupotg tgrupotg){
							//if(verificarIntegridad(tperfil, 5)){
								String[] id = {tgrupotg.getId_grupo_tg()};
								ContentValues cv = new ContentValues();
								cv.put("id_grupo_tg",tgrupotg.getId_grupo_tg());
								cv.put("anio",tgrupotg.getAnio());
									db.update("grupo_tg", cv, "id_grupo_tg = ?", id);
								
								return "Registro Actualizado Correctamente";
						}


						public String actualizardocente(Tdocente tdocente){
							//if(verificarIntegridad(tperfil, 5)){
								String[] id = {tdocente.getId_docente()};
								ContentValues cv = new ContentValues();
								cv.put("id_docente",tdocente.getId_docente());
								cv.put("can_perfiles_apr",tdocente.getCan_perfiles_apr());
								cv.put("can_perfiles_rep", tdocente.getCan_perfiles_rep());
								cv.put("correo_d", tdocente.getCorreo_d());
								cv.put("nombres_d", tdocente.getNombres_d());
								cv.put("apellidos_d", tdocente.getApellidos_d());
									db.update("docente", cv, "id_docente = ?", id);
								
								return "Registro Actualizado Correctamente";
						}
						
						public String eliminardocente(Tdocente docente){
							String regAfectados="filas afectadas= ";
							int contador=0;
							//if (verificarIntegridad(alumno,3)) {
								//contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null); 
							//}
							contador+=db.delete("docente", "id_docente='"+docente.getId_docente()+"'", null);
							regAfectados+=contador;
							return regAfectados;
						}

						public String eliminargrupotg(Tgrupotg grupotg){
							String regAfectados="filas afectadas= ";
							int contador=0;
							contador+=db.delete("grupo_tg", "id_grupo_tg='"+grupotg.getId_grupo_tg()+"'", null);
							regAfectados+=contador;
							return regAfectados;
						}
						
						//////////////////////////////nestor////////////////////////////////
						
						
				
						
					
						public String insertar(Talumno alumno) {

							String regInsertados = "Registro Insertado Nº= ";
							long contador = 0;
							ContentValues alu = new ContentValues();
							alu.put("carnet", alumno.getCarnet());
							alu.put("id_integrante", alumno.getId_integrante());
							alu.put("telefono_a", alumno.getTelefono_a());
							alu.put("correo_a", alumno.getCorreo_a());
							alu.put("nombre_a", alumno.getNombres_a());
							alu.put("apellido_a", alumno.getApellidos_a());
							alu.put("direccion", alumno.getDireccion());
							
																	
							contador = db.insert("alumno", null, alu);
							if (contador == -1 || contador == 0) {
								regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
							} else {
								regInsertados = regInsertados + contador;
							}
							return regInsertados;

						}
							
						 //consultar Alumno
						
						public Talumno consultarAlumno(String carnet){
							String[] id = {carnet};
							Cursor cursor = db.query("alumno", camposAlumno, "carnet = ?", id,null, null, null);
							if(cursor.moveToFirst()){
								Talumno alumno = new Talumno();
								alumno.setCarnet(cursor.getString(0));
								alumno.setId_integrante(cursor.getString(1));
								alumno.setTelefono_a(cursor.getString(2));
								alumno.setCorreo_a(cursor.getString(3));
								alumno.setNombres_a(cursor.getString(4));
								alumno.setApellidos_a(cursor.getString(5));
								alumno.setDireccion(cursor.getString(6));
							
								return alumno;
							}
							else
							{
								return null;
							}
						}
						
					
						//actualizar Alumno

						public String actualizar(Talumno alumno){
							
							if(verificarIntegridadDiana(alumno, 6)){
								
								String[] id = {alumno.getCarnet()};
								ContentValues cv = new ContentValues();
								cv.put("id_integrante",alumno.getId_integrante());
								cv.put("telefono_a",alumno.getTelefono_a());
								cv.put("correo_a",alumno.getCorreo_a());
								cv.put("nombre_a",alumno.getNombres_a());
								cv.put("apellido_a",alumno.getApellidos_a());
								cv.put("direccion",alumno.getDireccion());
								
								db.update("alumno", cv, "carnet = ?", id);
								return "Registro Actualizado Correctamente";
								} 
							else{
								return "Registro con carnet " + alumno.getCarnet() + " no existe";
								}
						}
						
						//eliminar Alumno
						public String eliminar(Talumno alumno){
							String regAfectados="filas afectadas= ";
							int contador=0;
							if (verificarIntegridadDiana(alumno,7)) {
							contador+=db.delete("detalle_nota", "carnet='"+alumno.getCarnet()+"'", null);
							}
							contador+=db.delete("alumno", "carnet='"+alumno.getCarnet()+"'", null);
							regAfectados+=contador;
							return regAfectados;
						}
						
						// insertar Detalle Nota

						public String insertar(DetalleNota detallenota) {
							String regInsertados = "Registro Insertado Nº= ";

							long contador = 0;
							//if (verificarIntegridadDiana(detallenota, 1)) {
								ContentValues def = new ContentValues();

								def.put("id_defensa", detallenota.getId_defensa());
								def.put("carnet", detallenota.getCarnet());
								def.put("nota_ase", detallenota.getNota_ase());
								def.put("nota_obs", detallenota.getNota_obs());
								contador = db.insert("detalle_nota", null, def);
							//}
							if (contador == -1 || contador == 0) {
								regInsertados = "Error, Verifique que exista detalle de nota"
										+ detallenota.getId_defensa();
							} else {
								regInsertados = regInsertados + contador;
							}
							return regInsertados;
						}
						
						
						 //consultar Detalle Nota
						
							public DetalleNota consultarDetalleNota(String id_defensa){
								String[] id = {id_defensa};
								Cursor cursor = db.query("detalle_nota", camposDetalle_nota, "id_defensa = ?", id,null, null, null);
								if(cursor.moveToFirst()){
									DetalleNota detallenota = new DetalleNota();
									detallenota.setId_defensa(cursor.getString(0));
									detallenota.setCarnet(cursor.getString(1));
									detallenota.setNota_ase(cursor.getString(2));
									detallenota.setNota_obs(cursor.getString(3));

									
									return detallenota;
								}
								else
								{
									return null;
								}
							}
							
							//actualizar Detalle Nota


							public String actualizar(DetalleNota detallenota){
								
									//if(verificarIntegridadDiana(detallenota, 2)){
										String[] id = {detallenota.getId_defensa()};
										ContentValues cv = new ContentValues();
										cv.put("carnet", detallenota.getCarnet());
										cv.put("nota_ase", detallenota.getNota_ase());
										cv.put("nota_obs", detallenota.getNota_obs());
										db.update("detalle_nota", cv, "id_defensa = ?", id);
										return "Registro Actualizado Correctamente";
										//}else{
										//return "Registro con codigo de defensa " + detallenota.getId_defensa() + " no existe";
										//}
								}
							
							//eliminar Detalle Nota
							public String eliminar(DetalleNota detallenota){
								String regAfectados="filas afectadas= ";
								int contador=0;
								contador+=db.delete("detalle_nota", "id_defensa='"+detallenota.getId_defensa()+"'", null);
								regAfectados+=contador;
								return regAfectados;
							}
							
							
							////carolina///
						
							public String insertar(TG tg) {
								String regInsertados = "Registro Insertado Nº= ";

								long contador = 0;
								
									ContentValues def = new ContentValues();

									def.put("id_tg", tg.getIdtg());
									def.put("id_perfil", tg.getIdperfil());
									def.put("fecha_inicio", tg.getFechainicio());
									def.put("fecha_fin", tg.getFechafin());
									contador = db.insert("trabajo_graduacion", null, def);
								//}
								if (contador == -1 || contador == 0) {
									regInsertados = "Error, Verifique que exista TG"
											+ tg.getIdtg();
								} else {
									regInsertados = regInsertados + contador;
								}
								return regInsertados;
							}

							

							 //consultar TG
							
								public TG consultarTG(String idtg){
									String[] id = {idtg};
									Cursor cursor = db.query("trabajo_graduacion", camposTrabajo_graduacion, "idtg = ?", id,null, null, null);
									if(cursor.moveToFirst()){
										TG tg = new TG();
										tg.setIdtg(cursor.getString(0));
										tg.setIdperfil(cursor.getString(1));
										tg.setFechainicio(cursor.getString(2));
										tg.setFechafin(cursor.getString(3));
									
										return tg;
									}
									else
									{
										return null;
									}
								}

	private boolean verificarIntegridadDiana(Object dato, int relacion)
			throws SQLException {

		switch (relacion) {
		case 1: {
			// verificar que al insertar defensa exista id_ubicacion y id_tg

			Tdefensa defensa = (Tdefensa) dato;
			String[] id1 = { defensa.getId_ubicacion() };
			// String[] id2 = {defensa.getId_tg()};
			// abrir();
			Cursor cursor1 = db.query("ubicacion", null, "id_ubicacion = ?",id1, null, null, null);
			// Cursor cursor2 = db.query("trabajo_graduacion", null, "id_tg = ?", id2,null, null, null);

			if (cursor1.moveToFirst()) {
				// && cursor2.moveToFirst()){
				// Se encontraron datos

				return true;
			}

			return false;
		}// cierra case 1

		case 2: {// verificar que exista defensa
			Tdefensa defensa2 = (Tdefensa) dato;
			String[] idm = { defensa2.getId_defensa() };
			abrir();
			Cursor cm = db.query("defensa", null, "id_defensa = ?", idm,null, null, null);

			if (cm.moveToFirst()) {
				// Se encontro defensa
				return true;
			}
			return false;
		}
		case 3: {
			//verifica integirdad con defensa al eliminar ubicacion
			Tubicacion ubicacion = (Tubicacion) dato;
			Cursor c = db.query(true, "defensa",
					new String[] { "id_ubicacion" }, "id_ubicacion='"+ ubicacion.getId_ubicacion() + "'", null, null,null, null, null);

			if (c.moveToFirst())

				return true;
			else

				return false;
		}// cierra case 3

		case 4: {
			// verificar que exista ubicacion al actualizar ubicacion
			Tubicacion ubicacion2 = (Tubicacion) dato;
			String[] id = { ubicacion2.getId_ubicacion() };
			abrir();
			Cursor c2 = db.query("ubicacion", null, "id_ubicacion = ?", id,null, null, null);

			if (c2.moveToFirst()) {
				// Se encontro ubicacion
				return true;
			}
			return false;
		}// cierra case 4
		
		case 5: {

			// verificar que exista Asistencia
			Tasistencia asistencia2 = (Tasistencia) dato;
			String[] idm = { asistencia2.getId_asistencia() };
			abrir();
			Cursor cm = db.query("asistencia", null, "id_asistencia = ?", idm,null, null, null);

			if (cm.moveToFirst()) {
				// Se encontro Asistencia
				return true;
			}
			return false;
		}
		
case 6: {
			// verificar que exista ubicacion al actualizar ubicacion
			Talumno alumno2 = (Talumno) dato;
			String[] id = { alumno2.getCarnet() };
			abrir();
			Cursor c2 = db.query("alumno", null, "carnet = ?", id,null, null, null);

			if (c2.moveToFirst()) {
				// Se encontro alumno
				return true;
			}
			return false;
		}// cierra case 4
/*		
		case 7: {
			//verifica integirdad con detalle_nota al eliminar ubicacion
			Alumno alumno = (Alumno) dato;
			Cursor c = db.query(true, "detalle_nota",
					new String[] { "carnet" }, "carnet='"+ alumno.getCarnet() + "'", null, null,null, null, null);

			if (c.moveToFirst())

				return true;
			else

				return false;
		}// cierra case 7
		*/
		default:

			return false;
		}
	}// cierra switch



//////////////////////////////////////////////////////////////////////////////

public String llenarBD(){
	//campos para la tabla tipo proyecto
	final String[] VTPtipo = {"0000001","0000002"};
	final String[] VTPnombreproy = {"SISTEMA","INVESTIGACION"};
	
	
	//campos para la tabla perfil
		final String[] VPidperfil = {"PER0001","PER0002","PER0003","PER0004","PER0005","PER0006","PER0007","PER0008","PER0009","PER0010"};
		final String[] VPidgrupotg = {"GRP0001","GRP0002","GRP0003","GRP0004","GRP0005","GRP0006","GRP0007","GRP0008","GRP0009","GRP0010"};
		final String[] VPidtg = {"TG00001","TG00002","TG00003","TG00004","TG00005","TG00006","TG00007","TG00008","TG00009","TG00010"};
		final String[] VPidtipoproy = {"0000001","0000002","0000001","0000002","0000001","0000002","0000001","0000002","0000001","0000002"};
		final String[] VPtema = {"TEMA 1","TEMA 2","TEMA 3","TEMA 4","TEMA 5","TEMA 6","TEMA 7","TEMA 8","TEMA 9","TEMA 10"};
		final String[] VPestado = {"APROBADO","APROBADO","REPROBADO","REPROBADO","APROBADO","APROBADO","REPROBADO","REPROBADO","APROBADO","APROBADO"};
		final String[] VPcarrera = {"ING. INDUSRIAL","ING. CIVIL","ING DE SISTEMAS INF.","ING. QUIMICA","ING. INDUSTRIAL","ING. DE ALIMENTOS","ING. INDUSRIAL","ING. CIVIL","ING DE SISTEMAS INF.","ING. QUIMICA"};
		
		//campos para la tabla revision perfil
		final String[] VRPidrevperfil = {"REVP001","REVP002","REVP003","REVP004","REVP005","REVP006","REVP007","REVP008","REVP009","REVP010"};
		final String[] VRPidperfil = {"PER0001","PER0002","PER0003","PER0004","PER0005","PER0006","PER0007","PER0008","PER0009","PER0010"};
		final String[] VRPiddocente = {"CG02001","EC02002","AN03001","BM04002","CA05015","JM06025","LB14010","BD16025","YV02036","NH14025"};
		final String[] VRPfecharev = {"01/05/13","25/04/13","14/01/13","10/02/13","20/03/13","02/03/13","25/04/13","14/04/13","10/07/13","20/10/13"};
		final String[] VRPobservacionrev = {"CORREGIR OBJETIVOS","CORREGIR MODELO FISICO","NINGUNA","REPLANTEAR REQUERIMIENTOS","CORREGIR OBJETIVOS","CORREGIR MODELO FISICO","NINGUNA","REPLANTEAR REQUERIMIENTOS","CORREGIR MODELO FISICO","NINGUNA"};
		
		//campos para la tabla docente
		final String[] VDiddocente = {"CG02001","EC02002","AN03001","BM04002","CA05015","JM06025","LB14010","BD16025","YV02036","NH14025"};
		final int[] VDcanperfilesapr = {5,7,9,8,4,14,10,20,3,5};
		final int[] VDcanperfilesrep = {1,2,4,3,2,5,6,2,1,3};
		final String[] VDcorreod = {"CGONZALEZ@GMAIL.COM","ECARBALLO@GMAIL.COM","ANUILA@GMAIL.COM","BMONTANO@GMAIL.COM","CAYALA@GMAIL.COM","JMARIA@GMAIL.COM","SMONTANO@GMAIL.COM","BDIAZ@GMAIL.COM","YVIGIL@GMAIL.COM","NHENRIQUEZ@GMAIL.COM"};
		final String[] VDnombresd = {"CESAR","ELMER","ANGELICA","BORIS","CAROLINA","JOSE MARIA","SILVIA","BLADIMIR","YESENIA","NELLY"};
		final String[] VDapellidosd = {"GONZALEZ","CARBALLO","NUILA","MONTANO","AYALA","SANCHEZ","MONTANO","DIAZ","VIGIL","HENRIQUEZ"};
		
		//campos para la tabla docente asignado
		final String[] VDAiddocente = {"CG02001","EC02002","AN03001","BM04002","CA05015","JM06025","SM14010","BD16025","YV02036","NH14025"};
		final float[] VDAporcentajedoc = {60,40,60,40,60,40,30,60,30,40,60,40,20,60,15,20,60,40,20,40};
		final String[] VDAmodalidad = {"ASESOR","OBSERVADOR","ASESOR","OBSERVADOR","ASESOR","OBSERVADOR","ASESOR","OBSERVADOR","ASESOR","OBSERVADOR"};
		
		//campos para la tabla grupo tg
		final String[] VGTGidgrupotg = {"GRP0001","GRP0002","GRP0003","GRP0004","GRP0005","GRP0006","GRP0007","GRP0008","GRP0009","GRP0010"};
		final String[] VGTGanio = {"2012","2013","2012","2013","2012","2012","2013","2012","2013","2012"};

		//campos para la tabla integrante grupo
		final String[] VIGrepresentante = {"JONATAN TORRES","MARCELA ALDANA","JUAN SERRANO","MARTIN ESPINAL","DIANA MARTINEZ","JOSE FELICIANO","SUSANA TORRES","JOSE VIILANUEVA","JEYSSON LOPEZ","SUSANA SARAI"};
		final float[] VIGnotafinal = {7,8,6,9,8,7,9,9,6,7};
		final String[] VIGidintegrante = {"LS04016","AP05074","SM06043","TA02004","MO0517","CC03070","VB03012","VP03021","TM02004","EF04002"};
		final String[] VIGidgrupotg = {"GRP0001","GRP0002","GRP0003","GRP0004","GRP0005","GRP0006","GRP0007","GRP0008","GRP0009","GRP0010"};

		//campos para la tabla alumno
		final String[] VAcarnet = {"LS04016","AP05074","SM06043","TA02004","MO0517","CC03070","VB03012","VP03021","TM02004","EF04002"};
		final String[] VAidintegrante = {"INT0001","INT0002","INT00003","INT004","INT0005","INT0006","INT0007","INT0008","INT0009","INT0010"};
		final String[] VAtelefonoa = {"2450-1015","7520-4030","7829-1351","7995-0258","7398-1498","2225-9262","2224-6020","2210-1478","2531-1361","7410-1514"};
		final String[] VAcoreoa = {"JLOPEZ@GMAIL.COM","MALDANA@GMAIL.COM","JCMENDOZA@GMAIL.COM","JTORRES@GMAIL.COM","DMARTINEZ@GMAIL.COM","ACASTILLO@GMAIL.COM","JBELTRAN@GMAIL.COM","JVILLA@GMAIL.COM","SUTORRES@GMAIL.COM","MARESPINAL@GMAIL.COM"};
		final String[] VAnombresa = {"JEYSSON RICARDO","MARCELA YASMIN","JUAN CARLOS","JONATAN ESAU","DIANA CAROLINA","ANA GUADALUPE","JOSE FELICIANO","JOSE ALBERTO","SUSANA SARAI","MARTIN ALEXANDER"};
		final String[] VAapellidos = {"LOPEZ SARMIENTO","ALDANA PALACIOS","SERRANO MENDOZA","TORRES ARAUJO","MARTINEZ ORELLANA","CASTILLO CALLES","VENTURA BELTRAN","VILLANUEVA PEREZ","TORRES MENJIVAR","ESPINAL FERNANDEZ"};
		final String[] VAdireccion = {"COL. ATONAL SONSONATE","COL. AIDA SAN SALVADOR","COL. ZACAMIL MEJICANOS","COL. SANTAMARIA USULUTAN","COL. BARRA DE SANTIAGO, AHUACHAPAN","COL. CIMA 4 SAN SALVADOR","COL. LIBERTAD MEJICANOS","COL. SAN ANTONIO SOYAPANGO","COL. AVE MARIA OPICO","COL. AMERICA CUSCATANCINGO"};
		
		//campos para la tabla trabajo graduacion
		final String[] VTGidtg = {"TG00001","TG00002","TG00003","TG00004","TG00005","TG00006","TG00007","TG00008","TG00009","TG00010"};
		final String[] VTGidperfil = {"PER0001","PER0002","PER0003","PER0004","PER0005","PER0006","PER0007","PER0008","PER0009","PER0010"};
		final String[] VTGfechainicio = {"01/02/13","25/02/13","16/01/13","10/06/13","20/01/13","02/01/13","25/03/13","14/01/13","10/01/13","20/01/13"};
		final String[] VTGfechafin = {"02/11/13","25/11/13","14/11/13","11/12/13","20/11/13","02/12/13","25/11/13","14/12/13","11/12/13","20/12/13"};

		//campos para la tabla asesoria
		final String[] VASEidasesoria = {"ASE0001","ASE0002","ASE0003","ASE0004","ASE0005","ASE0006","ASE0007","ASE0008","ASE0009","ASE0010"};
		final String[] VASEidtg = {"TG00001","TG00002","TG00003","TG00004","TG00005","TG00006","TG00007","TG00008","TG00009","TG00010"};
		final String[] VASEhoraasesoria = {"8:00 AM","10:00 AM","2:00 PM","4:30 PM","11:00 AM","8:00 AM","10:00 AM","2:00 PM","4:30 PM","11:00 AM"};
		final int[] VASEnumasesoria = {1,4,7,3,5,2,4,6,5,4};
		final String[] VASEfechaasesoria = {"01/02/13","25/02/13","16/01/13","10/06/13","20/01/13","02/01/13","25/03/13","14/01/13","10/01/13","20/01/13"};
		final String[] VASEobservaciona = {"CORREGIR OBJETIVOS","CORREGIR MODELO FISICO","NINGUNA","REPLANTEAR REQUERIMIENTOS","CORREGIR OBJETIVOS","CORREGIR MODELO FISICO","NINGUNA","REPLANTEAR REQUERIMIENTOS","CORREGIR MODELO FISICO","NINGUNA"};

		//campos para la tabla asistencia
		final String[] VASIidasistencia = {"ASI0001","ASI0002","ASI0003","ASI0004","ASI0005","ASI0006","ASI0007","ASI0008","ASI0009","ASI0010"};
		final String[] VASIidasesoria = {"ASE0001","ASE0002","ASE0003","ASE0004","ASE0005","ASE0006","ASE0007","ASE0008","ASE0009","ASE0010"};
		final String[] VASIestadoas = {"COMPLETADA","COMPLETADA","PENDIENTE","COMPLETADA","PENDIENTE","COMPLETADA","COMPLETADA","PENDIENTE","COMPLETADA","PENDIENTE"};

		//campos para la tabla defensa
		final String[] VDEiddefensa = {"DEF0001","DEF0002","DEF0003","DEF0004","DEF0005","DEF0006","DEF0007","DEF0008","DEF0009","DEF0010"};
		final String[] VDEidtg = {"TG00001","TG00002","TG00003","TG0004","TG00005","TG00006","TG00007","TG00008","TG00009","TG00010"};
		final String[] VDEidubicacion = {"UBI0001","UBI0002","UBI0003","UBI004","UBI0005","UBI0006","UBI0007","UBI0008","UBI0009","UBI0010"};
		final String[] VDEhora = {"8:00 AM","10:00 AM","2:00 PM","4:30 PM","11:00 AM","8:00 AM","10:00 AM","2:00 PM","4:30 PM","11:00 AM"};
		final String[] VDEfechadef = {"02/06/13","25/05/13","14/06/13","10/06/13","20/05/13","02/06/13","25/04/13","14/04/13","10/07/13","20/10/13"};
		
		//campos para la tabla ubicacion
		final String[] VUidubicacion = {"UBI0001","UBI0002","UBI0003","UBI004","UBI0005","UBI0006","UBI0007","UBI0008","UBI0009","UBI0010"};
		final String[] VUlocal = {"A-340","B-11","B-21","B-31","C-11","C-21","C-31","D-11","D-21","D-31"};
		final String[] VUdescripcionub = {"AUDITORIO MARMOL","AULA 11 EDIFICIO B","AULA 21 EDIFICIO B","AULA 31 EDIFICIO B","AULA 11 EDIFICIO C","AULA 21 EDIFICIO C","AULA 31 EDIFICIO C","AULA 11 EDIFICIO D","AULA 21 EDIFICIO D","AULA 31 EDIFICIO D"};

		//campos para la tabla etapa
		final String[] VEidetapa = {"ETA0001","ETA0002","ETA0003","ETA004","ETA0005","ETA0006","ETA0007","ETA0008","ETA0009","ETA0010"};
		final String[] VEiddefensa = {"DEF0001","DEF0002","DEF0003","DEF004","DEF0005","DEF0006","DEF0007","DEF0008","DEF0009","DEF0010"};
		final String[] VEnombre = {"ANTEPROYECTO","PROYECTO","SISTEMA","PRESENTACION","FINAL","ANTEPROYECTO","PROYECTO","SISTEMA","PRESENTACION","FINAL"};
		final String[] VEdescripcionet = {"DEFENSA ANTEPROYECTO","DEFENSA PROYECTO","DEFENSA SISTEMA","PRESENTACION SISTEMA","DEFENSA FINAL","DEFENSA ANTEPROYECTO","DEFENSA PROYECTO","DEFENSA SISTEMA","PRESENTACION SISTEMA","DEFENSA FINAL"};
		final String[] VEporcentajeetapa = {"20","20","30","10","40","20","20","30","10","40"};
		
		//campos para la tabla detalle nota
		final String[] VDNiddefensa = {"DEF0001","DEF0002","DEF0003","DEF0004","DEF0005","DEF0006","DEF0007","DEF0008","DEF0009","DEF0010"};
		final String[] VDNcarnet = {"LS04016","AP05074","SM06043","TA02004","MO0517","CC03070","VB03012","VP03021","TM02004","EF04002"};
		final String[] VDNnotaase = {"8","9","9","7","6","7","6","8","9","10"};
		final String[] VDNnotaobs = {"8","9","9","7","6","7","6","8","9","10"};
	
	abrir();
	//db.execSQL("DELETE FROM alumno");
	//db.execSQL("DELETE FROM asesoria");
	//db.execSQL("DELETE FROM asistencia");
	
	Tperfil tperfil = new Tperfil();
	for(int i=0;i<10;i++){
		
		tperfil.setid_perfil(VPidperfil[i]);
		tperfil.setid_grupo_tg(VPidgrupotg[i]);
		tperfil.setid_tg(VPidtg[i]);
		tperfil.setid_tipo_proy(VPidtipoproy[i]);
		tperfil.settema(VPtema[i]);
		tperfil.setestado(VPestado[i]);
		tperfil.setcarrera(VPcarrera[i]);
		
		insertar(tperfil);
	}
	
	Tubicacion tubicacion = new Tubicacion();
	for(int i=0;i<10;i++){
		
		tubicacion.setId_ubicacion(VUidubicacion[i]);
		tubicacion.setLocal(VUlocal[i]);
		tubicacion.setDescripcion_ub(VUdescripcionub[i]);
		
		insertar(tubicacion);
	}
	
	Tasistencia tasistencia = new Tasistencia();
	for(int i=0;i<10;i++){
		
		tasistencia.setId_asistencia(VASIidasistencia[i]);
		tasistencia.setId_asesoria(VASIidasesoria[i]);
		tasistencia.setEstado_as(VASIestadoas[i]);
		insertar(tasistencia);
	}
	
	Tdefensa tdefensa = new Tdefensa();
	for(int i=0;i<10;i++){
		
		tdefensa.setId_defensa(VDEiddefensa[i]);
		tdefensa.setId_tg(VDEidtg[i]);
		tdefensa.setId_ubicacion(VDEidubicacion[i]);
		tdefensa.setHora(VDEhora[i]);
		tdefensa.setFecha(VDEfechadef[i]);
		
		insertar(tdefensa);
	}
	
	Tintegrantegrupo tintegrantegrupo = new Tintegrantegrupo();
	for(int i=0;i<10;i++){
		
		tintegrantegrupo.setrepresentante(VIGrepresentante[i]);
		tintegrantegrupo.setnota_final(VIGnotafinal[i]);
		tintegrantegrupo.setid_integrante(VIGidintegrante[i]);
		tintegrantegrupo.setid_grupo_tg(VIGidgrupotg[i]);
		
		insertar(tintegrantegrupo);
	}
	
	Tdocente tdocente = new Tdocente();
	for(int i=0;i<10;i++){
		
		tdocente.setId_docente(VDiddocente[i]);
		tdocente.setCan_perfiles_apr(VDcanperfilesapr[i]);
		tdocente.setCan_perfiles_rep(VDcanperfilesrep[i]);
		tdocente.setCorreo_d(VDcorreod[i]);
		tdocente.setNombres_d(VDnombresd[i]);
		tdocente.setApellidos_d(VDapellidosd[i]);
		
		
		insertardocente(tdocente);
	}
	

	Tgrupotg tgrupotg = new Tgrupotg();
	for(int i=0;i<10;i++){
		
		tgrupotg.setId_grupo_tg(VGTGidgrupotg[i]);
		tgrupotg.setAnio(VGTGanio[i]);
		
		
		
		insertargrupotg(tgrupotg);
	}
	
	Trevisionperfil trevisionperfil = new Trevisionperfil();
	for(int i=0;i<10;i++){
		trevisionperfil.setId_rev_perfil(VRPidrevperfil[i]);
		trevisionperfil.setId_perfil(VRPidperfil[i]);
		trevisionperfil.setId_docente(VRPiddocente[i]);
		trevisionperfil.setFecha_rev(VRPfecharev[i]);
		trevisionperfil.setObservacion_rev(VRPobservacionrev[i]);
			
		insertar(trevisionperfil);
	}
	
	Tdocenteasignado tdocentea = new Tdocenteasignado();
	for(int i=0;i<10;i++){
		tdocentea.setId_docente(VDAiddocente[i]);
		tdocentea.setPorcentaje_doc(VDAporcentajedoc[i]);
		tdocentea.setModalidad(VDAmodalidad[i]);
			
		insertar(tdocentea);
	}


     Talumno alumno = new Talumno();
	for(int i=0;i<10;i++){
		alumno.setCarnet(VAcarnet[i]);
		alumno.setId_integrante(VAidintegrante[i]);
        alumno.setNombres_a(VAnombresa[i]);
		alumno.setApellidos_a(VAapellidos[i]);
		alumno.setDireccion(VAdireccion[i]);
		alumno.setTelefono_a(VAtelefonoa[i]);
		alumno.setCorreo_a(VAcoreoa[i]);
			
		insertar(alumno);
	}


 DetalleNota tdetalle_nota= new DetalleNota();
	for(int i=0;i<10;i++){
                tdetalle_nota.setId_defensa(VDNiddefensa [i]);
                tdetalle_nota.setCarnet(VDNcarnet [i]);
		tdetalle_nota.setNota_ase(VDNnotaase [i]);
		tdetalle_nota.setNota_obs(VDNnotaobs [i]);
		
			
		insertar(tdetalle_nota);
	}


	/*  Tetapa tetapa = new Tetapa();
		for(int i=0;i<4;i++){
			tetapa.setId_etapa(VEidetapa[i]);
			tetapa.setId_defensa(VEiddefensa[i]);
			tetapa.setNombre_etapa(VEnombre[i]);
			tetapa.setDescripcion_etapa(VEdescripcionet[i]);
			tetapa.setPorcentaje(VEporcentajeetapa[i]);
		
				
			insertar(tetapa);
		}*/

	 TG tg = new  TG();
	for(int i=0;i<10;i++){
		tg.setIdtg(VTGidtg[i]);
		tg.setIdperfil(VTGidperfil[i]);
		tg.setFechainicio(VTGfechainicio[i]);
		tg.setFechafin(VTGfechafin[i]);
			
		insertar(tg);
	}
		
	
		  Tasesoria tasesoria = new Tasesoria();
			for(int i=0;i<10;i++){
				tasesoria.setid_asesoria(VASEidasesoria[i]);
				tasesoria.setid_tg(VASEidtg[i]);
				tasesoria.sethora_asesoria(VASEhoraasesoria[i]);
				tasesoria.setnum_asesoria(VASEnumasesoria[i]);
				tasesoria.setfecha_asesoria(VASEfechaasesoria[i]);
				tasesoria.setobservacion_a(VASEobservaciona[i]);
					
				insertar(tasesoria);
			}
			
			DetalleNota detallenota = new DetalleNota();
			for(int i=0;i<10;i++){
				detallenota.setId_defensa(VDNiddefensa[i]);
				detallenota.setCarnet(VDNcarnet[i]);
				detallenota.setNota_ase( VDNnotaase[i]);
				detallenota.setNota_obs(VDNnotaobs[i]);
				
					
				insertar(detallenota);
			}
			
			Ttipoproyecto tipoproyecto = new Ttipoproyecto();
			for(int i=0;i<2;i++){
				tipoproyecto.setIdTipoProy(VTPtipo[i]);
				tipoproyecto.setNombre_pry(VTPnombreproy[i]);
			
					
				insertar(tipoproyecto);
			}
//////
	
			
	cerrar();
	return "Datos Guardados Correctamente";
}

}


	//las clases que se llaman Tnombreclase es la clase
	//correspondiente a la tabla de nombreclase
	
	/*Alumno alumno = new Alumno();
	for(int i=0;i<4;i++){
		alumno.setCarnet(VAcarnet[i]);
		alumno.setNombre(VAnombre[i]);
		alumno.setApellido(VAapellido[i]);
		alumno.setSexo(VAsexo[i]);
		alumno.setMatganadas(0);
		insertar(alumno);
	}
	
	
	
	cerrar();
	return "Guardo Correctamente";
}




/*
public String eliminar(Materia materia) {
	
	String regAfectados="filas afectadas= ";
	int contador=0;
	if (verificarIntegridad(materia,4)) {
	contador+=db.delete("nota", "codmateria='"+materia.getCodmateria()+"'", null);
	}
	contador+=db.delete("materia", "codmateria='"+materia.getCodmateria()+"'", null);
	regAfectados+=contador;
	
	return regAfectados;
}	











//insertar alumno

public String insertar(Alumno alumno){
	
	String regInsertados="Registro Insertado Nº= ";
	long contador=0;
	ContentValues alum = new ContentValues();
	alum.put("carnet", alumno.getCarnet());
	alum.put("nombre", alumno.getNombre());
	alum.put("apellido", alumno.getApellido());
	alum.put("sexo", alumno.getSexo());
	alum.put("matganadas", alumno.getMatganadas());
	contador=db.insert("alumno", null, alum);
	if(contador==-1 || contador==0)
	{
	regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
	}
	else {
	regInsertados=regInsertados+contador;
	}
	return regInsertados;
		
	
}

//insertar nota

public String insertar(Nota nota){
	String regInsertados="Registro Insertado Nº= ";
	
	long contador=0;
	if(verificarIntegridad(nota,1))
	{
		ContentValues notas = new ContentValues();
		notas.put("carnet", nota.getCarnet());
		notas.put("codmateria", nota.getCodmateria());
		notas.put("ciclo", nota.getCiclo());
		notas.put("notafinal", nota.getNotafinal());
		contador=db.insert("nota", null, notas);
	}
	if(contador==-1 || contador==0)
	{
		regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
	}
	else {
		regInsertados=regInsertados+contador;
	}
	return regInsertados;
	}

// INSERTAR MATERIA

public String insertar(Materia materia){
	
	String regInsertados="Registro Insertado Nº= ";
	long contador=0;
	ContentValues mat = new ContentValues();
	mat.put("codmateria", materia.getCodmateria());
	mat.put("nommateria", materia.getNommateria());
	mat.put("unidadesval", materia.getUnidadesval());
	
	contador=db.insert("materia", null, mat);
	if(contador==-1 || contador==0)
	{
	regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
	}
	else {
	regInsertados=regInsertados+contador;
	}
	return regInsertados;
	
	
	
	
}

//actualizar alumno

public String actualizar(Alumno alumno){
	
	if(verificarIntegridad(alumno, 5)){
		
		String[] id = {alumno.getCarnet()};
		ContentValues cv = new ContentValues();
		cv.put("nombre", alumno.getNombre());
		cv.put("apellido", alumno.getApellido());
		cv.put("sexo", alumno.getSexo());
		db.update("alumno", cv, "carnet = ?", id);
		return "Registro Actualizado Correctamente";
		} else{
		return "Registro con carnet " + alumno.getCarnet() + " no existe";
		}
}

//actualizar materia

public String actualizar(Materia materia){

	if(verificarIntegridad(materia, 6)){
		
		String[] id = {materia.getCodmateria()};
		ContentValues cv = new ContentValues();
		
		cv.put("nommateria", materia.getNommateria());
		cv.put("unidadesval",materia.getUnidadesval());
		db.update("materia", cv, "codmateria = ?", id);
		return "Registro Actualizado Correctamente";
		} else{
		return "Materia con codigo " + materia.getCodmateria() + " no existe";
		}
}

//actualizar nota

public String actualizar(Nota nota){
	
	if(verificarIntegridad(nota, 2)){
		String[] id = {nota.getCodmateria(), nota.getCarnet(),nota.getCiclo()};
		ContentValues cv = new ContentValues();
		cv.put("notafinal", nota.getNotafinal());
		db.update("nota", cv, "codmateria = ? AND carnet = ? AND ciclo = ?", id);
		return "Registro Actualizado Correctamente";
		}else{
			return "Registro no Existe";
		}
}

//eliminar alumno
public String eliminar(Alumno alumno){
	String regAfectados="filas afectadas= ";
	int contador=0;
	if (verificarIntegridad(alumno,3)) {
	contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
	}
	contador+=db.delete("alumno", "carnet='"+alumno.getCarnet()+"'", null);
	regAfectados+=contador;
	return regAfectados;
}


//eliminar nota

public String eliminar(Nota nota){
	
	String regAfectados="filas afectadas= ";
	int contador=0;
	String where="carnet='"+nota.getCarnet()+"'";
	where=where+" AND codmateria='"+nota.getCodmateria()+"'";
	where=where+" AND ciclo="+nota.getCiclo();
	contador+=db.delete("nota", where, null);
	regAfectados+=contador;
	return regAfectados;
}

//consultar alumno
public Alumno consultarAlumno(String carnet){
	String[] id = {carnet};
	Cursor cursor = db.query("alumno", camposAlumno, "carnet = ?", id,null, null, null);
	if(cursor.moveToFirst()){
		Alumno alumno = new Alumno();
		alumno.setCarnet(cursor.getString(0));
		alumno.setNombre(cursor.getString(1));
		alumno.setApellido(cursor.getString(2));
		alumno.setSexo(cursor.getString(3));
		alumno.setMatganadas(cursor.getInt(4));
		return alumno;
	}else{
		return null;
	}
	
	//////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
}
public Materia consultarMateria(String codmateria){
	String[] id = {codmateria};
	Cursor cursor = db.query("materia", camposMateria, "codmateria = ?", id,null, null, null);
	if(cursor.moveToFirst()){
		Materia materia = new Materia();
		materia.setCodmateria(cursor.getString(0));
		materia.setNommateria(cursor.getString(1));
		materia.setUnidadesval(cursor.getString(2));
		
		return materia;
	}else{
		return null;}
}

//CONSULTAR NOTA

public Nota consultarNota(String carnet, String codmateria, String ciclo){
	
	String[] id = {carnet, codmateria, ciclo};
	Cursor cursor = db.query("nota", camposNota, "carnet = ? AND codmateria = ? AND ciclo = ?", id, null, null, null);
	
	if(cursor.moveToFirst()){
		Nota nota = new Nota();
		nota.setCarnet(cursor.getString(0));
		nota.setCodmateria(cursor.getString(1));
		nota.setCiclo(cursor.getString(2));
		nota.setNotafinal(cursor.getFloat(3));
	return nota;
	
	}else{
		return null;
	}
	
	
	
	
	
}












private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
	
	switch(relacion){
	case 1:
	{
	//verificar que al insertar nota exista carnet del alumno y el
	//codigo de materia
	Nota nota = (Nota)dato;
	String[] id1 = {nota.getCarnet()};
	String[] id2 = {nota.getCodmateria()};
	
	
	abrir();
	
	Cursor cursor1 = db.query("alumno", null, "carnet = ?", id1, null,null, null);
	Cursor cursor2 = db.query("materia", null, "codmateria = ?", id2,null, null, null);
	
	if(cursor1.moveToFirst() && cursor2.moveToFirst()){
	//Se encontraron datos
	
	return true;
	}
	
	return false;
	}//cierra case 1
	
	case 2:
	{
	//verificar que al modificar nota exista carnet del alumno, el
	//codigo de materia y el ciclo
	
	Nota nota1 = (Nota)dato;
	String[] ids = {nota1.getCarnet(), nota1.getCodmateria(),
	nota1.getCiclo()};
	abrir();
	Cursor c = db.query("nota", null, "carnet = ? AND codmateria = ? AND ciclo = ?", ids, null, null, null);
	
	if(c.moveToFirst()){
	//Se encontraron datos
	
	return true;
	}
	return false;
	}//cierra case 2
	
	case 3:
	{
		Alumno alumno = (Alumno)dato;
		Cursor c=db.query(true, "nota", new String[] {"carnet" }, "carnet='"+alumno.getCarnet()+"'",null,null, null, null, null);
	
	if(c.moveToFirst())
	
		return true;
	else
	
		return false;
	}//cierra case 3
	
	case 4:
	{
		Materia materia = (Materia)dato;
		Cursor cmat=db.query(true, "nota", new String[] {"codmateria" },"codmateria='"+materia.getCodmateria()+"'",null, null, null, null, null);
		
		if(cmat.moveToFirst())
			return true;
		else
			return false;
		}//cierra case 4
		
	case 5:
		{
		//verificar que exista alumno
		Alumno alumno2 = (Alumno)dato;
		String[] id = {alumno2.getCarnet()};
		abrir();
		Cursor c2 = db.query("alumno", null, "carnet = ?", id, null, null,null);
		
		if(c2.moveToFirst()){
		//Se encontro Alumno
			return true;
		}
			return false;
		}//cierra case 5
	case 6:
		{
			
		
		//verificar que exista Materia
		Materia materia2 = (Materia)dato;
		String[] idm = {materia2.getCodmateria()};
		abrir();
		Cursor cm = db.query("materia", null, "codmateria = ?", idm, null, null, null);
		
		if(cm.moveToFirst()){
		//Se encontro Materia
			return true;
		}
			return false;
		}
		default:
		
			return false;
		}
		}//cierra switch
		
		public String llenarBDLS04016(){
		final String[] VAcarnet = {"OO12035","OF12044","GG11098","CC12021"};
		final String[] VAnombre = {"Carlos","Pedro","Sara","Gabriela"};
		final String[] VAapellido = {"Orantes","Ortiz","Gonzales","Coto"};
		final String[] VAsexo = {"M","M","F","F"};
		final String[] VMcodmateria = {"MAT115","PRN115","IEC115","TSI115"};
		final String[] VMnommateria = {"Matematica I","Programacion I","Ingenieria Economica","Teoria de Sistemas"};
		final String[] VMunidadesval = {"4","4","4","4"};
		final String[] VNcarnet = {"OO12035","OF12044","GG11098","CC12021","OO12035","GG11098","OF12044"};
		final String[] VNcodmateria = {"MAT115","PRN115","IEC115","TSI115","IC115","MAT115","PRN115"};
		final String[] VNciclo = {"1","1","2","2","2","1","2"};
		final float[] VNnotafinal = {7,5,8,7,6,10,7};
				abrir();
				
				db.execSQL("DELETE FROM alumno");
				db.execSQL("DELETE FROM materia");
				db.execSQL("DELETE FROM nota");
				
				Alumno alumno = new Alumno();
				for(int i=0;i<4;i++){
					alumno.setCarnet(VAcarnet[i]);
					alumno.setNombre(VAnombre[i]);
					alumno.setApellido(VAapellido[i]);
					alumno.setSexo(VAsexo[i]);
					alumno.setMatganadas(0);
					insertar(alumno);
				}
				
				Materia materia = new Materia();
				for(int i=0;i<4;i++){
					materia.setCodmateria(VMcodmateria[i]);
					materia.setNommateria(VMnommateria[i]);
					materia.setUnidadesval(VMunidadesval[i]);
					insertar(materia);
				}
				
				Nota nota = new Nota();
				for(int i=0;i<7;i++){
					nota.setCarnet(VNcarnet[i]);
					nota.setCodmateria(VNcodmateria[i]);
					nota.setCiclo(VNciclo[i]);
					nota.setNotafinal(VNnotafinal[i]);
					insertar(nota);
				}
				cerrar();
				return "Guardo Correctamente";
				 }
		public static String[] getCamposmateria() {
			return camposMateria;
		}
		public static String[] getCamposnota() {
			return camposNota;
		}
				}//cierra private boolean seguridad de integridad









/*
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public ControlBD(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context); 
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		private static final String BASE_DATOS = "siatg.s3db";
		private static final int VERSION = 1;
		
		public DatabaseHelper(Context context) {
			super(context, BASE_DATOS, null, VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			try{
				
				db.execSQL("" +
						"CREATE TABLE ASESORIA(" +
						"ID_ASESORIA varchar(7), " +
						"ID_TG varchar(7), " +
						"HORA_ASESORIA time, " +
						"NUM_ASESORIA integer, " +
						"FECHA_ASESORIA date, " +
						"OBSERVACION_A varchar(100), " +
						"primary key (ID_ASESORIA));");
				db.execSQL("" +
						"create table ASISTENCIA(" +
						"ID_ASISTENCIA        varchar(7)," +
						"ID_ASESORIA          varchar(7)," +
						"ESTADO_AS            char(1)," +
						"primary key (ID_ASISTENCIA));");
				db.execSQL("" +
						"create table DEFENSA(" +
						"ID_DEFENSA           varchar(7)," +
						"ID_TG                varchar(7)," +
						"ID_UBICACION         varchar(7)," +
						"HORA                 time," +
						"FECHA_DEF            date," +
						"primary key (ID_DEFENSA));");
				db.execSQL("" +
						"create table DETALLE_NOTA(" +
						"ID_DEFENSA           varchar(7)," +
						"CARNET               varchar(7)," +
						"NOTA_ASE             float," +
						"NOTA_OBS             float," +
						"primary key (ID_DEFENSA));");
				db.execSQL("" +
						"create table DOCENTE(" +
						"ID_DOCENTE           varchar(7)," +
						"CAN_PERFILES_APR     integer," +
						"CAN_PERFILES_REP     integer," +
						"CORREO_D             varchar(100)," +
						"NOMBRES_D            varchar(50)," +
						"APELLIDOS_D          varchar(50)," +
						"primary key (ID_DOCENTE));");
				db.execSQL("" +
						"create table DOCENTE_ASIGNADO(" +
						"ID_DOCENTE           varchar(7)," +
						"PORCENTAJE_DOC       float(3)," +
						"MODALIDAD            varchar(25)," +
						"primary key (ID_DOCENTE));");
				db.execSQL("" +
						"create table ETAPA(" +
						"ID_ETAPA             varchar(7)," +
						"ID_DEFENSA           varchar(7)," +
						"NOMBRE               varchar(50)," +
						"DESCRIPCION_ET       varchar(50)," +
						"PORCENTAJE_ETAPA     float(3)," +
						"primary key (ID_ETAPA));");
				db.execSQL("" +
						"create table GRUPO_TG(" +
						"ID_GRUPO_TG          varchar(7)," +
						"ANIO                 date," +
						"primary key (ID_GRUPO_TG));");
				
				db.execSQL(
						"CREATE TABLE integrante_grupo(representante VARCHAR(50), nota_final FLOAT(3), id_integrante VARCHAR(7) NOT NULL PRIMARY KEY, id_grupo_tg VARCHAR(7)");
				db.execSQL(
						"CREATE TABLE perfil(id_perfil VARCHAR(7)  NOT NULL PRIMARY KEY, id_grupo_tg  VARCHAR(7), id_tg VARCHAR(7), id_tipo_proy VARCHAR(7), tema VARCHAR(100), estado VARCHAR(15), carrera VARCHAR(50)");
				db.execSQL("" +
						"create table REVISION_PERFIL(" +
						"ID_REV_PERFIL        varchar(7)," +
						"ID_PERFIL            varchar(7)," +
						"ID_DOCENTE           varchar(7)," +
						"FECHA_REV            date," +
						"OBSERVACION_REV      varchar(100)," +
						"primary key (ID_REV_PERFIL));");
				db.execSQL("" +
						"create table TIPO_PROYECTO(" +
						"ID_TIPO_PROY         varchar(7)," +
						"NOMBRE_PRY           varchar(50)," +
						"primary key (ID_TIPO_PROY));");
				db.execSQL("" +
						"create table TRABAJO_GRADUACION(" +
						"ID_TG                varchar(7)," +
						"ID_PERFIL            varchar(7)," +
						"FECHA_INICIO         date," +
						"FECHA_FIN            date," +
						"primary key (ID_TG));");
				db.execSQL("" +
						"create table UBICACION(" +
						"ID_UBICACION         varchar(7)," +
						"LOCAL                varchar(50)," +
						"DESCRIPCION_UB       varchar(50)," +
						"primary key (ID_UBICACION));");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		}
	}
	
	public void abrir() throws SQLException{
		db = DBHelper.getWritableDatabase();
		return;
	}
	public void cerrar(){
		DBHelper.close();
	}
	
	/*public String insertar(Alumno alumno){
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
		ContentValues alum = new ContentValues();
		alum.put("carnet", alumno.getCarnet());
		alum.put("nombre", alumno.getNombre());
		alum.put("apellido", alumno.getApellido());
		alum.put("sexo", alumno.getSexo());
		alum.put("matganadas", alumno.getMatganadas());
		contador=db.insert("alumno", null, alum);
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
		}
		else {
			regInsertados=regInsertados+contador;
		}
		return regInsertados;
	}*/
	
	/*public String actualizar(Alumno alumno){
		if(verificarIntegridad(alumno, 5)){
			String[] id = {alumno.getCarnet()};
			ContentValues cv = new ContentValues();
			cv.put("nombre", alumno.getNombre());
			cv.put("apellido", alumno.getApellido());
			cv.put("sexo", alumno.getSexo());
			db.update("alumno", cv, "carnet = ?", id);
			return "Registro Actualizado Correctamente";
		}else{
			return "Registro con carnet " + alumno.getCarnet() + " no existe";
		}
	}*/
	
	/*public String actualizar(Nota nota){
		if(verificarIntegridad(nota, 2)){
			String[] id = {nota.getCodmateria(), nota.getCarnet(), nota.getCiclo()};
			ContentValues cv = new ContentValues();
			//cv.put("notafinal", ""+nota.getNotafinal().toString());
			cv.put("notafinal", ""+nota.getNotafinal());
			db.update("nota", cv, "codmateria = ? AND carnet = ? AND ciclo = ?", id);
			return "Registro Actualizado Correctamente";
		}else{
			return "Registro no Existe";
		}
	}*/
	
	/*public String eliminar(Alumno alumno){
		String regAfectados="filas afectadas= ";
		int contador=0;
		if (verificarIntegridad(alumno,3)) {
			contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null); 
		}
		contador+=db.delete("alumno", "carnet='"+alumno.getCarnet()+"'", null);
		regAfectados+=contador;
		return regAfectados;
	}*/
	
	/*public String eliminar(Materia materia){
		String regAfectados="filas afectadas= ";
		int contador=0;
		if (verificarIntegridad(materia,7)) {
			contador+=db.delete("nota", "codmateria='"+materia.getCodmateria()+"'", null); 
		}
		contador+=db.delete("materia", "codmateria='"+materia.getCodmateria()+"'", null);
		regAfectados+=contador;
		return regAfectados;
	}*/
	
	/*public String eliminar(Nota nota){
		String regAfectados="filas afectadas= ";
		int contador=0;
		String where="carnet='"+nota.getCarnet()+"'";
		where=where+" AND codmateria='"+nota.getCodmateria()+"'";
		where=where+" AND ciclo="+nota.getCiclo();
		contador+=db.delete("nota", where, null);
		regAfectados+=contador;
		return regAfectados;
	}*/
	
	/*public Alumno consultarAlumno(String carnet){
		String[] id = {carnet};
		Cursor cursor = db.query("alumno", camposAlumno, "carnet = ?", id, 
		null, null, null);
		if(cursor.moveToFirst()){
			Alumno alumno = new Alumno();
			alumno.setCarnet(cursor.getString(0));
			alumno.setNombre(cursor.getString(1));
			alumno.setApellido(cursor.getString(2));
			alumno.setSexo(cursor.getString(3));
			alumno.setMatganadas(cursor.getInt(4));
			return alumno;
		}else{
			return null;
		}
	}*/
	
	/*private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
		switch(relacion){
			case 1:
			{
				//verificar que al insertar nota exista carnet del alumno y el codigo de materia
				Nota nota = (Nota)dato;
				String[] id1 = {nota.getCarnet()};
				String[] id2 = {nota.getCodmateria()};
				//abrir();
				Cursor cursor1 = db.query("alumno", null, "carnet = ?", id1, null, 
				null, null);
				Cursor cursor2 = db.query("materia", null, "codmateria = ?", id2, 
				null, null, null);
				if(cursor1.moveToFirst() && cursor2.moveToFirst()){
					//Se encontraron datos
					return true;
				}
				return false;
			}
			case 2:
			{
				//verificar que al modificar nota exista carnet del alumno, el codigo de materia y el ciclo
				Nota nota1 = (Nota)dato;
				String[] ids = {nota1.getCarnet(), nota1.getCodmateria(), 
				nota1.getCiclo()};
				abrir();
				Cursor c = db.query("nota", null, "carnet = ? AND codmateria = ? AND ciclo = ?", ids, null, null, null);
				if(c.moveToFirst()){
					//Se encontraron datos
					return true;
				}
				return false;
			}
			case 3:
			{
				Alumno alumno = (Alumno)dato;
				Cursor c=db.query(true, "nota", new String[] {
				"carnet" }, "carnet='"+alumno.getCarnet()+"'",null, 
				null, null, null, null);
				if(c.moveToFirst())
					return true;
				else
					return false;
			}
			case 4:
			{
				Materia materia = (Materia)dato;
				Cursor cmat=db.query(true, "nota", new String[] { 
				"codmateria" }, 
				"codmateria='"+materia.getCodmateria()+"'",null, null, null, null, null);
				if(cmat.moveToFirst())
					return true;
				else
					return false;
			}
			case 5:
			{
			//verificar que exista alumno
				Alumno alumno2 = (Alumno)dato;
				String[] id = {alumno2.getCarnet()};
				abrir();
				Cursor c2 = db.query("alumno", null, "carnet = ?", id, null, null, null);
				if(c2.moveToFirst()){
					//Se encontro Alumno
					return true;
				}
				return false;
			}
			case 6:
			{
				//verificar que exista Materia
				Materia materia2 = (Materia)dato;
				String[] idm = {materia2.getCodmateria()};
				abrir();
				Cursor cm = db.query("materia", null, "codmateria = ?", idm, null, null, null);
				if(cm.moveToFirst()){
					//Se encontro Materia
					return true;
				}
				return false;
			}
			case 7:
			{
				Materia materia = (Materia)dato;
				Cursor c=db.query(true, "nota", new String[] {
				"codmateria" }, "codmateria='"+materia.getCodmateria()+"'",null, 
				null, null, null, null);
				if(c.moveToFirst())
					return true;
				else
					return false;
			}
			default:
				return false;
		}
	}
	
	public String llenarBD(){
		//campos para la tabla tipo proyecto
		final String[] VTPtipo = {"0000001","0000002"};
		final String[] VTPnombreproy = {"Sistema","Investigacion"};
		
		//campos para la tabla perfil
		final String[] VPidperfil = {};
		final String[] VPidgrupotg = {};
		final String[] VPidtipoproy = {};
		final String[] VPtema = {};
		final String[] VPestado = {};
		final String[] VPcarrera = {};
		
		//campos para la tabla revision perfil
		final String[] VRPidrevperfil = {};
		final String[] VRPidperfil = {};
		final String[] VRPiddocente = {};
		final String[] VRPfecharev = {};
		final String[] VRPobservacionrev = {};
		
		//campos para la tabla docente
		final String[] VDiddocente = {};
		final String[] VDcanperfilesapr = {};
		final String[] VDcanperfilesrep = {};
		final String[] VDcorreod = {};
		final String[] VDnombresd = {};
		final String[] VDapellidosd = {};
		
		//campos para la tabla docente asignado
		final String[] VDAiddocente = {};
		final String[] VDAporcentajedoc = {};
		final String[] VDAmodalidad = {};
		
		//campos para la tabla grupo tg
		final String[] VGTGidgrupotg = {};
		final String[] VGTGanio = {};

		//campos para la tabla integrante grupo
		final String[] VIGrepresentante = {};
		final String[] VIGnotafinal = {};
		final String[] VIGidintegrante = {};
		final String[] VIGidgrupotg = {};

		//campos para la tabla alumno
		final String[] VAcarnet = {};
		final String[] VAidintegrante = {};
		final String[] VAtelefonoa = {};
		final String[] VAcoreoa = {};
		final String[] VAnombresa = {};
		final String[] VAapellidos = {};
		final String[] VAdireccion = {};
		
		//campos para la tabla trabajo graduacion
		final String[] VTGidtg = {};
		final String[] VTGidperfil = {};
		final String[] VTGfechainicio = {};
		final String[] VTGfechafin = {};

		//campos para la tabla asesoria
		final String[] VASEidasesoria = {};
		final String[] VASEidtg = {};
		final String[] VASEhoraasesoria = {};
		final String[] VASEnumasesoria = {};
		final String[] VASEfechaasesoria = {};
		final String[] VASEobservaciona = {};

		//campos para la tabla asistencia
		final String[] VASIidasistencia = {};
		final String[] VASIidasesoria = {};
		final String[] VASIestadoas = {};

		//campos para la tabla defensa
		final String[] VDEiddefensa = {};
		final String[] VDEidtg = {};
		final String[] VDEidubicacion = {};
		final String[] VDEhora = {};
		final String[] VDEfechadef = {};
		
		//campos para la tabla ubicacion
		final String[] VUidubicacion = {};
		final String[] VUlocal = {};
		final String[] VUdescripcionub = {};

		//campos para la tabla etapa
		final String[] VEidetapa = {};
		final String[] VEiddefensa = {};
		final String[] VEnombre = {};
		final String[] VEdescripcionet = {};
		final String[] VEporcentajeetapa = {};
		
		//campos para la tabla detalle nota
		final String[] VDNiddefensa = {};
		final String[] VDNcarnet = {};
		final String[] VDNnotaase = {};
		final String[] VDNnotaobs = {};

		
		abrir();
		db.execSQL("DELETE FROM alumno");
		db.execSQL("DELETE FROM asesoria");
		db.execSQL("DELETE FROM asistencia");
		
		//las clases que se llaman Tnombreclase es la clase
		//correspondiente a la tabla de nombreclase
		
		/*Alumno alumno = new Alumno();
		for(int i=0;i<4;i++){
			alumno.setCarnet(VAcarnet[i]);
			alumno.setNombre(VAnombre[i]);
			alumno.setApellido(VAapellido[i]);
			alumno.setSexo(VAsexo[i]);
			alumno.setMatganadas(0);
			insertar(alumno);
		}
		
		
		
		cerrar();
		return "Guardo Correctamente";
	}
}*/

