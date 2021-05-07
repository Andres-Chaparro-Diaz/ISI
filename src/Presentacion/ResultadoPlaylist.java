package Presentacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.json.JSONObject;

import Dominio.GestorPlaylist;
import Dominio.Usuario;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResultadoPlaylist extends JPanel {
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblLogo;
	private JButton btnAtras;
	private JButton btnExpPlaylist;
	private JLabel lblFondo;
	private Object[][] canciones;
	private Principal principal;
	private JLabel lblTipoDia;
	JSONObject JSONTiempo;
	/**
	 * Create the panel.
	 * 
	 * @throws JSONException
	 */
	public ResultadoPlaylist(Principal principal, JSONObject JSONTiempo) throws JSONException{
		this.principal = principal;
		this.JSONTiempo = JSONTiempo;
		setBounds(0, 0, 900, 650);
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 214, 840, 324);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblLogo = new JLabel("");
		lblLogo.setBounds(710, 53, 150, 150);
		try {
			Image imagenOriginal = ImageIO.read(Principal.class.getResource("/recursos/moodapp.png"));
			Image imagenEscalada = imagenOriginal.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(),
					java.awt.Image.SCALE_SMOOTH);
			ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
			lblLogo.setIcon(iconoLabel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		add(lblLogo);

		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new BtnAtrasActionListener());
		btnAtras.setBounds(20, 559, 128, 33);
		btnAtras.setBorder(new LineBorder(Color.BLACK, 1, true));
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(btnAtras);

		btnExpPlaylist = new JButton("Exportar Playlist");
		btnExpPlaylist.setBounds(732, 559, 128, 33);
		btnExpPlaylist.setBorder(new LineBorder(Color.BLACK, 1, true));
		btnExpPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(btnExpPlaylist);

		//canciones = GestorPlaylist.recomendarPlaylist(JSONTiempo.getJSONArray("weather").getJSONObject(0).getString("main"),GestorPlaylist.leerPlaylist());
		canciones = GestorPlaylist.devolverCanciones();
		table.setModel(new DefaultTableModel(canciones, new String[] { "ID", "Titulo", "Artista", "Genero", "Anio",
				"BPM", "Energia", "Danzabilidad", "dB", "Valor", "Duracion", "Popularidad" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		lblTipoDia = new JLabel("New label");
		lblTipoDia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipoDia.setBounds(20, 157, 306, 44);
		add(lblTipoDia);
		
		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 900, 650);
		try {
			Image imagenOriginal = ImageIO.read(Principal.class.getResource("/recursos/Fondo.jpg"));
			Image imagenEscalada = imagenOriginal.getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(),
					java.awt.Image.SCALE_SMOOTH);
			ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
			lblFondo.setIcon(iconoLabel);
			add(lblFondo);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(5).setPreferredWidth(10);
		table.getColumnModel().getColumn(6).setPreferredWidth(10);
		table.getColumnModel().getColumn(7).setPreferredWidth(10);
		table.getColumnModel().getColumn(8).setPreferredWidth(10);
		table.getColumnModel().getColumn(9).setPreferredWidth(10);
		table.getColumnModel().getColumn(10).setPreferredWidth(10);
		table.getColumnModel().getColumn(11).setPreferredWidth(10);

	}
	
	private class BtnAtrasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			principal.atrasPulsado();
		}
	}
	
	public void setTipoDia(double temp, String tipoDia) {
		lblTipoDia.setText("Temperatura: "+temp+" ºC, Tipo de dia: "+tipoDia);
	}
}
