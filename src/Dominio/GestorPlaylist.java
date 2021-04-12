package Dominio;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorPlaylist {
	public static Object[][] devolverCanciones(JSONArray jsonPlay){
		Object[][] canciones = new Object[jsonPlay.length()][12];
		for(int i = 0; i<jsonPlay.length();i++) {

			JSONObject jcancion = jsonPlay.getJSONObject(i);
			String title = jcancion.getString("title");
			String artist = jcancion.getString("artist");
			String topGenre = jcancion.getString("top genre");
			int year = jcancion.getInt("year");
			int bpm = jcancion.getInt("bpm");
			int nrgy = jcancion.getInt("nrgy");
			int dnce = jcancion.getInt("dnce");
			int dB = jcancion.getInt("dB");
			int val = jcancion.getInt("val");
			int dur = jcancion.getInt("dur");
			int pop = jcancion.getInt("pop");

			canciones[i]= new Object[] {i,title,artist,topGenre,String.valueOf(year),String.valueOf(bpm),String.valueOf(nrgy),
					String.valueOf(dnce),String.valueOf(dB),String.valueOf(val),String.valueOf(dur),
					String.valueOf(pop)};
		}
		return canciones;
	}
	
	public static JSONArray leerPlaylist() {
		JSONArray obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\src\\recursos\\playlist.json"));
			JSONArray jsonArray = (JSONArray) new JSONTokener(builder.toString()).nextValue();
			obj = new JSONArray(tokener);
			for(int i = 0;i<obj.length();i++) {
				obj.getJSONObject(i);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo playlist.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo playlist.json no es valido.");
			return null;
		}
		return obj;
	}
}
