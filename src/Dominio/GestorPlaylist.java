package Dominio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
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
			System.out.println(respuesta.body());
			JSONTokener tokener = new JSONTokener(respuesta.body());
			return new JSONObject(tokener);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public JSONObject recomendarPlaylist(String tiempoActual, JSONObject JSONPlaylist) {
		Iterator<String> keys =  JSONPlaylist.keys();
		JSONObject JSONRecomendados = new JSONObject();
		while(keys.hasNext()) {
			String id = keys.next();
			int energy = JSONPlaylist.getJSONObject("canciones").getJSONObject(id).getInt("nrgy");
			int db = JSONPlaylist.getJSONObject("canciones").getJSONObject(id).getInt("dB");
			boolean triste = triste(energy,db);
			switch (tiempoActual) {
			case "Thunderstorm":
				if(triste) {
					
				}
				break;
			case "Rain":
				if(triste) {
					
				}
				break;
			case "Snow":
				if(triste) {
					
				}
				break;
			case "Mist":
				if(triste) {
					
				}
				break;
			case "Smoke":
				if(triste) {
					
				}
				break;
			case "Haze":
				if(triste) {
					
				}
				break;
			case "Dust":
				if(triste) {
					
				}
				break;
			case "Fog":
				if(triste) {
					
				}
				break;
			case "Sand":
				if(triste) {
					
				}
				break;
			case "Drizzle":
				if(triste) {
					
				}
				break;
			case "Ash":
				if(triste) {
					
				}
				break;
			case "Squall":
				if(triste) {
					
				}
				break;
			case "Tornado":
				if(triste) {
					
				}
				break;
			case "Clear":
				if(triste) {
					
				}
				break;
			case "Clouds":
				if(triste) {
					
				}
				break;

			}

		}
		return JSONRecomendados;

	}
	
	public boolean triste(int energy, int db) {
		if(energy <65 && db <=-5) return true;
		else return false;
	}
}

