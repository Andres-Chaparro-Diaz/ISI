package Presentacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Playlist extends JPanel {
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnExpPlaylist;
	private JButton btnAtras;
	private JLabel lblLogo;
	private JLabel lblFondo;

	/**
	 * Create the panel.
	 */
	public Playlist() {
		setBounds(100, 100, 900, 650);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 172, 801, 366);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblLogo = new JLabel("");
		lblLogo.setBounds(673, 11, 150, 150);
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
		btnAtras.setBounds(22, 583, 89, 23);
		add(btnAtras);
		
		btnExpPlaylist = new JButton("Exportar Playlist");
		btnExpPlaylist.setBounds(786, 583, 89, 23);
		add(btnExpPlaylist);
		
		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 900, 650);
		try {
			Image imagenOriginal = ImageIO.read(Principal.class.getResource("/recursos/Fondo.jpg"));
			Image imagenEscalada = imagenOriginal.getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(),
					java.awt.Image.SCALE_SMOOTH);
			ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
			lblFondo.setIcon(iconoLabel);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		add(lblFondo);
	}
}
