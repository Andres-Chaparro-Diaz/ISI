package Dominio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorPlaylist {

	public static JSONArray leerPlaylist() throws IOException {
		convertirPlaylist();
		JSONArray obj;
		try {
			//JSONTokener tokener = new JSONTokener(
			//		new FileReader(System.getProperty("user.dir") + "\\src\\recursos\\playlist.json"));
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.home") +"\\Desktop\\RecursosApp\\playlist2.json"));
			obj = new JSONArray(tokener);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERROR: El archivo 'playlist.json' no se ha encontrado.\nAsegúrese de que el archivo se encuentra en una carpeta llamada 'RecursosApp' en su escritorio.");
			System.exit(0);
			return null;
		} catch (JSONException e) {
			JOptionPane.showMessageDialog(null, "ERROR: El formato 'playlist.json' no es correcto.");
			System.exit(0);
			return null;
		}
		return obj;
	}

	public static Object[][] devolverCanciones(String tiempoActual) throws JSONException, IOException {
		JSONArray jsonPlayAux = leerPlaylist();
		Iterator<Object> it = jsonPlayAux.iterator();
		ArrayList<Object[]> lista = new ArrayList<Object[]>();
		int i = 0;
		while (it.hasNext()) {
			JSONObject jsonPlay = (JSONObject) it.next();
			String title = String.valueOf(jsonPlay.get("title"));
			String artist = String.valueOf(jsonPlay.get("artist"));
			String topGenre = String.valueOf(jsonPlay.get("top genre"));
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
				if (sad(nrgy, dB))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur) });
			}

			if (tiempoActual.equals("Dust") || tiempoActual.equals("Sand") || tiempoActual.equals("Ash")) {
				if (anger(nrgy, dB, val))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur) });
			}

			if (tiempoActual.equals("Clear")) {
				if (happy(val, nrgy))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur) });
			}

			if (tiempoActual.equals("Thunderstorm") || tiempoActual.equals("Tornado")) {
				if (amped(nrgy))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur) });
			}

			if (tiempoActual.equals("Clouds")) {
				if (soft(nrgy, dB, val))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur) });
			}

			if (tiempoActual.equals("Snow")) {
				if (danceable(dnce))
					lista.add(new Object[] { i++, title, artist, topGenre, String.valueOf(year), String.valueOf(bpm),
							String.valueOf(nrgy), String.valueOf(dnce), String.valueOf(dB), String.valueOf(val),
							String.valueOf(dur) });
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

	public static void convertirPlaylist() throws IOException {
		//File archivo = new File(System.getProperty("user.dir") + "\\src\\recursos\\playlist2.json");
		File archivo = new File(System.getProperty("user.home") +"\\Desktop\\RecursosApp\\playlist.json");
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea, linea2 = null;
		String texto = "";
		while ((linea = br.readLine()) != null) {
			for (int i = 0; i < linea.length(); i++) {
				if (linea.charAt(i) != ' ') {
					linea2 = linea.substring(i);
					break;
				}
			}
			texto += linea2;
		}
		texto = texto.replace(",\"\": \"\"", "");
		texto = texto.substring(1);
		// Escritura
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			//fichero = new FileWriter(System.getProperty("user.dir") + "\\src\\recursos\\playlist.json");
			fichero = new FileWriter(System.getProperty("user.home") +"\\Desktop\\RecursosApp\\playlist2.json");
			pw = new PrintWriter(fichero);
			pw.print(texto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void crearPlaylist(String userId) throws IOException {
		//"https://api.spotify.com/v1/users/claudiodiaz29100/playlists"
		//"https://api.spotify.com/v1/playlists/{playlist_id}/tracks"

	}
}
