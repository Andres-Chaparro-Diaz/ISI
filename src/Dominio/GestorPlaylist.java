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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorPlaylist {
	
	public static JSONArray leerPlaylist() {
		JSONArray obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\src\\recursos\\playlist.json"));
			obj = new JSONArray(tokener);
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo playlist2.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println(e.toString());
			return null;
		}
		return obj;
	}

	public static Object[][] devolverCanciones(String tiempoActual) throws JSONException {
		JSONArray jsonPlayAux = leerPlaylist();
		Iterator<Object> it = jsonPlayAux.iterator();
		ArrayList<Object[]> lista = new ArrayList<Object[]>();
		int i = 0;
		while (it.hasNext()) {
			JSONObject jsonPlay = (JSONObject) it.next();
			String title = jsonPlay.getString("title");
			String artist = jsonPlay.getString("artist");
			String topGenre = jsonPlay.getString("top genre");
			int year = jsonPlay.getInt("year");
			int bpm = jsonPlay.getInt("bpm");
			int nrgy = jsonPlay.getInt("nrgy");
			int dnce = jsonPlay.getInt("dnce");
			int dB = jsonPlay.getInt("dB");
			int val = jsonPlay.getInt("val");
			int dur = jsonPlay.getInt("dur");

			if (tiempoActual.equals("Rain") || tiempoActual.equals("Mist") || tiempoActual.equals("Drizzle")
					|| tiempoActual.equals("Smoke") || tiempoActual.equals("Fog") || tiempoActual.equals("Squall")
					|| tiempoActual.equals("Haze")) {
				if (sad(val, nrgy))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur)});
			}

			if (tiempoActual.equals("Dust") || tiempoActual.equals("Sand") || tiempoActual.equals("Ash")) {
				if (anger(nrgy, dB, val))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur)});
			}

			if (tiempoActual.equals("Clear")) {
				if (happy(val, nrgy))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur)});
			}

			if (tiempoActual.equals("Thunderstorm") || tiempoActual.equals("Tornado")) {
				if (amped(nrgy))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur)});
			}

			if (tiempoActual.equals("Clouds")) {
				if (soft(nrgy, dB, val))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur)});
			}

			if (tiempoActual.equals("Snow")) {
				if (danceable(dnce))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur)});
			}
		}
		Object[][] canciones = new Object[lista.size()][11];
		for (int j = 0; j < lista.size(); j++) {
			canciones[j] = lista.get(j);
		}
		return canciones;
	}

	public static JSONObject leerAPI(String ciudad, String APIkey) {
		String city = "?q=" + ciudad;
		String key = "&appid=" + APIkey;
		String URL = "http://api.openweathermap.org/data/2.5/weather" + city + key;
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

	public static boolean sad(int val, int energy) {
		if (val < 20 && energy < 20)
			return true;
		else
			return false;
	}

	public static boolean happy(int val, int energy) {
		if (val > 80 && energy > 80)
			return true;
		else
			return false;
	}

	public static boolean danceable(int dance) {
		if (dance > 80)
			return true;
		else
			return false;
	}

	public static boolean amped(int energy) {
		if (energy > 80)
			return true;
		else
			return false;
	}

	public static boolean anger(int energy, int db, int val) {
		if (energy > 80 && db >= -5 && val < 20)
			return true;
		else
			return false;
	}

	public static boolean soft(int energy, int db, int val) {
		if (energy < 50 && val < 20 && db < -5)
			return true;
		else
			return false;
	}
}

