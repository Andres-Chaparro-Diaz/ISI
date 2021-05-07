package Presentacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

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

	/**
	 * Create the panel.
	 * 
	 * @throws JSONException
	 */
	public ResultadoPlaylist(Principal principal) throws JSONException{
		this.principal = principal;
		setBounds(0, 0, 900, 650);
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 172, 801, 366);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblLogo = new JLabel("");
		lblLogo.setBounds(700, 11, 150, 150);
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
		btnAtras.setBounds(49, 559, 128, 33);
		btnAtras.setBorder(new LineBorder(Color.BLACK, 1, true));
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(btnAtras);

		btnExpPlaylist = new JButton("Exportar Playlist");
		btnExpPlaylist.setBounds(722, 559, 128, 33);
		btnExpPlaylist.setBorder(new LineBorder(Color.BLACK, 1, true));
		btnExpPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 15));
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

		canciones = GestorPlaylist.devolverCanciones();
		table.setModel(new DefaultTableModel(canciones, new String[] { "ID", "Titulo", "Artista", "Genero", "Anio",
				"BPM", "Energia", "Danzabilidad", "dB", "Valor", "Duracion", "Popularidad" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
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
}
