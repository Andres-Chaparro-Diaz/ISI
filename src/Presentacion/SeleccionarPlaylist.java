package Presentacion;

import javax.swing.JPanel;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;

import org.json.JSONException;

import Dominio.Usuario;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeleccionarPlaylist extends JPanel {

	private Usuario usuario;
	private JLabel lblLogo;
	private JLabel lblTipoDia;
	private JLabel lblEnlacePlaylist;
	private JTextField txtTipoDia;
	private JTextField txtEnlacePlaylist;
	private JButton btnSalir;
	private JLabel lblFondo;
	private String APIkey = "2f3043f9f6a0d2c127fe9656e1b335c2";
	private JButton btnSeleccionarCanciones;
	private Principal principal;
	/**
	 * Create the panel.
	 */
	public SeleccionarPlaylist(Principal principal) {
		this.principal = principal;
		setLayout(null);
		setBounds(0, 0, 900, 650);
		
		lblLogo = new JLabel("");
		lblLogo.setBounds(335, 73, 200, 200);
		try {
			Image imagenOriginal = ImageIO.read(SeleccionarPlaylist.class.getResource("/recursos/moodapp.png"));
			Image imagenEscalada = imagenOriginal.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(),
					java.awt.Image.SCALE_SMOOTH);
			ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
			lblLogo.setIcon(iconoLabel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		add(lblLogo);
		
		btnSeleccionarCanciones = new JButton("Seleccionar canciones");
		btnSeleccionarCanciones.addActionListener(new BtnSeleccionarCancionesActionListener());
		btnSeleccionarCanciones.setBounds(487, 444, 156, 33);
		btnSeleccionarCanciones.setBorder(new LineBorder(Color.BLACK, 1, true));
		btnSeleccionarCanciones.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(btnSeleccionarCanciones);

		lblTipoDia = new JLabel("Tipo de dia");
		lblTipoDia.setBounds(151, 331, 195, 40);
		lblTipoDia.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTipoDia.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTipoDia);

		lblEnlacePlaylist = new JLabel("Enlace playlist");
		lblEnlacePlaylist.setBounds(151, 393, 195, 40);
		lblEnlacePlaylist.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEnlacePlaylist.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEnlacePlaylist);

		txtTipoDia = new JTextField();
		txtTipoDia.setEditable(false);
		txtTipoDia.setBounds(386, 331, 257, 40);
		txtTipoDia.setBorder(new LineBorder(Color.BLACK, 1, true));
		txtTipoDia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(txtTipoDia);
		txtTipoDia.setColumns(10);

		txtEnlacePlaylist = new JTextField();
		txtEnlacePlaylist.setBounds(386, 393, 257, 40);
		txtEnlacePlaylist.setBorder(new LineBorder(Color.BLACK, 1, true));
		txtEnlacePlaylist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(txtEnlacePlaylist);
		txtEnlacePlaylist.setColumns(10);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new BtnSalirActionListener());
		btnSalir.setBounds(40, 552, 134, 33);
		btnSalir.setBorder(new LineBorder(Color.BLACK, 1, true));
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(btnSalir);
		
		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 900, 650);
		try {
			Image imagenOriginal = ImageIO.read(SeleccionarPlaylist.class.getResource("/recursos/Fondo.jpg"));
			Image imagenEscalada = imagenOriginal.getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(),
					java.awt.Image.SCALE_SMOOTH);
			ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
			lblFondo.setIcon(iconoLabel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		add(lblFondo);
		leerAPI("Madrid");

	}

	public void setUsuario(Usuario u) throws JSONException {
		usuario = u;
	}
	
	private class BtnSalirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			principal.salirPulsado();
		}
	}
	private class BtnSeleccionarCancionesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!txtEnlacePlaylist.getText().isEmpty())
				principal.seleccionarCancionesPulsado();
			else
				JOptionPane.showMessageDialog(null, "Indique la playlist.");
		}
	}
	
	public void leerAPI(String ciudad) {
		String city = "?q="+ciudad;
		String key = "&appid="+APIkey;
		String URL = "api.openweathermap.org/data/2.5/weather"+city+key;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("URL:"+URL)).GET().build();
		

		try {
			HttpResponse<String> respuesta = client.send(request, BodyHandlers.ofString());
			System.out.println(respuesta.body());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
