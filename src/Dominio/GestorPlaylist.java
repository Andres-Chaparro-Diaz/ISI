package Dominio;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorPlaylist {
	public static Object[][] devolverCanciones() throws JSONException{
		JSONObject jsonPlay = leerPlaylist();
		Object[][] canciones = new Object[jsonPlay.getInt("numCanciones")][12];
		int i = 0;
		for(int j = 0;j<jsonPlay.getInt("numCanciones");j++) {
			String title = jsonPlay.getJSONObject("canciones").getJSONObject(String.valueOf(j)).getString("title");
			String artist = jsonPlay.getJSONObject("canciones").getJSONObject(String.valueOf(j)).getString("artist");
			String topGenre = jsonPlay.getJSONObject("canciones").getJSONObject(String.valueOf(j)).getString("top genre");
			int year = jsonPlay.getJSONObject("canciones").getJSONObject(String.valueOf(j)).getInt("year");
			int bpm = jsonPlay.getJSONObject("canciones").getJSONObject(String.valueOf(j)).getInt("bpm");
			int nrgy = jsonPlay.getJSONObject("canciones").getJSONObject(String.valueOf(j)).getInt("nrgy");
			int dnce = jsonPlay.getJSONObject("canciones").getJSONObject(String.valueOf(j)).getInt("dnce");
			int dB = jsonPlay.getJSONObject("canciones").getJSONObject(String.valueOf(j)).getInt("dB");
			int val = jsonPlay.getJSONObject("canciones").getJSONObject(String.valueOf(j)).getInt("val");
			int dur = jsonPlay.getJSONObject("canciones").getJSONObject(String.valueOf(j)).getInt("dur");
			int pop = jsonPlay.getJSONObject("canciones").getJSONObject(String.valueOf(j)).getInt("pop");
			canciones[i++]= new Object[] {i,title,artist,topGenre,String.valueOf(year),String.valueOf(bpm),String.valueOf(nrgy),
					String.valueOf(dnce),String.valueOf(dB),String.valueOf(val),String.valueOf(dur),
					String.valueOf(pop)};
		}
		return canciones;
	}
	
	public static JSONObject leerPlaylist() {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\src\\recursos\\playlist.json"));
			obj = new JSONObject(tokener);
			obj.getInt("numCanciones");
			obj.getJSONObject("canciones");
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

