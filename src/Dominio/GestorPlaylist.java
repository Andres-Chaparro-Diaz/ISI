package Dominio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Iterator;

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
	
	public static JSONObject leerAPI(String ciudad, String APIkey) {
		String city = "?q="+ciudad;
		String key = "&appid="+APIkey;
		String URL = "http://api.openweathermap.org/data/2.5/weather"+city+key;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).GET().build();

		try {
			HttpResponse<String> respuesta = client.send(request, BodyHandlers.ofString());
			JSONTokener tokener = new JSONTokener(respuesta.body());
			return new JSONObject(tokener);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object[][] recomendarPlaylist(String tiempoActual, JSONObject JSONPlaylist) {
		Iterator<String> keys =  JSONPlaylist.getJSONObject("canciones").keys();		
		ArrayList<Object[]> lista = new ArrayList<Object[]>();
		
		while(keys.hasNext()) {
			String id = keys.next();
			int energy = JSONPlaylist.getJSONObject("canciones").getJSONObject(id).getInt("nrgy");
			int db = JSONPlaylist.getJSONObject("canciones").getJSONObject(id).getInt("dB");
			int dance = JSONPlaylist.getJSONObject("canciones").getJSONObject(id).getInt("dnce");
			int val = JSONPlaylist.getJSONObject("canciones").getJSONObject(id).getInt("val");
			String title = JSONPlaylist.getJSONObject("canciones").getJSONObject(id).getString("title");
			String artist = JSONPlaylist.getJSONObject("canciones").getJSONObject(id).getString("artist");
			String genre = JSONPlaylist.getJSONObject("canciones").getJSONObject(id).getString("top genre");
            String topGenre = JSONPlaylist.getJSONObject("canciones").getJSONObject(String.valueOf(id)).getString("top genre");
            int year = JSONPlaylist.getJSONObject("canciones").getJSONObject(String.valueOf(id)).getInt("year");
            int bpm = JSONPlaylist.getJSONObject("canciones").getJSONObject(String.valueOf(id)).getInt("bpm");
            int dur = JSONPlaylist.getJSONObject("canciones").getJSONObject(String.valueOf(id)).getInt("dur");
            int pop = JSONPlaylist.getJSONObject("canciones").getJSONObject(String.valueOf(id)).getInt("pop");

            
			boolean sad = sad(energy,db);
			boolean danceable = danceable(dance);
			boolean happy = happy(val, energy);
			boolean amped = amped(energy);
			boolean anger = anger(energy,db,val);
			boolean soft = soft(energy,db,val);
			int i = 0;
			
			if((tiempoActual.equals("Rain") || tiempoActual.equals("Mist") || tiempoActual.equals("Drizzle")|| tiempoActual.equals("Smoke") || tiempoActual.equals("Fog")|| tiempoActual.equals("Squall") || tiempoActual.equals("Haze")) && sad) {
	            lista.add(new Object[]{i++,title,artist,topGenre,String.valueOf(year),String.valueOf(bpm),String.valueOf(energy),
	                    String.valueOf(dance),String.valueOf(db),String.valueOf(val),String.valueOf(dur),
	                    String.valueOf(pop)});
			}
			
			if((tiempoActual.equals("Dust")  || tiempoActual.equals("Sand")  ||  tiempoActual.equals("Ash")) && anger ) {
	            lista.add(new Object[]{i++,title,artist,topGenre,String.valueOf(year),String.valueOf(bpm),String.valueOf(energy),
	                    String.valueOf(dance),String.valueOf(db),String.valueOf(val),String.valueOf(dur),
	                    String.valueOf(pop)});
			}
			
			if(tiempoActual.equals("Clear")  && happy) {
	            lista.add(new Object[]{i++,title,artist,topGenre,String.valueOf(year),String.valueOf(bpm),String.valueOf(energy),
	                    String.valueOf(dance),String.valueOf(db),String.valueOf(val),String.valueOf(dur),
	                    String.valueOf(pop)});
			}
			
			if((tiempoActual.equals("Thunderstorm") || tiempoActual.equals("Tornado")) && amped) {
	            lista.add(new Object[]{i++,title,artist,topGenre,String.valueOf(year),String.valueOf(bpm),String.valueOf(energy),
	                    String.valueOf(dance),String.valueOf(db),String.valueOf(val),String.valueOf(dur),
	                    String.valueOf(pop)});
			}
			
			if(tiempoActual.equals("Clouds") && soft) {
	            lista.add(new Object[]{i++,title,artist,topGenre,String.valueOf(year),String.valueOf(bpm),String.valueOf(energy),
	                    String.valueOf(dance),String.valueOf(db),String.valueOf(val),String.valueOf(dur),
	                    String.valueOf(pop)});
			}
			
			if(tiempoActual.equals("Snow") && danceable) {
	            lista.add(new Object[]{i++,title,artist,topGenre,String.valueOf(year),String.valueOf(bpm),String.valueOf(energy),
	                    String.valueOf(dance),String.valueOf(db),String.valueOf(val),String.valueOf(dur),
	                    String.valueOf(pop)});
			}
		}
		Object[][] canciones = new Object[lista.size()][12];
		for(int i = 0;i<lista.size();i++) {
			canciones[i]=lista.get(i);
		}
		return canciones;

	}
	
	public static boolean sad(int val, int energy) {
		if(val <20 && energy <20) return true;
		else return false;
	}
	
	public static boolean happy(int val, int energy) {
		if(val >80 && energy >80) return true;
		else return false;
	}
	
	public static boolean danceable(int dance) {
		if(dance >80) return true;
		else return false;
	}
	
	public static boolean amped(int energy) {
		if(energy >80) return true;
		else return false;
	}
	
	public static boolean anger (int energy, int db, int val) {
		if(energy >80 && db >= -5 && val < 20) return true;
		else return false;
	}
	
	public static boolean soft (int energy, int db, int val) {
		if(energy <50 && val <20 && db <-5) return true;
		else return false;
	}
}

