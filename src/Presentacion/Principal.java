package Presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Dominio.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JPanel {
	private JLabel lblLogo;
	private JLabel lblTipDia;
	private JLabel lblEnlPlaylist;
	private JTextField txtTipDia;
	private JTextField txtEnlPlaylist;
	private JButton btnSeleccionar;

	private Usuario usuario;
	private JLabel lblFondo;

	/**
	 * Create the panel.
	 */
	public Principal() {
		setBounds(100, 100, 900, 650);
		setLayout(null);

		lblLogo = new JLabel("");
		lblLogo.setBounds(335, 73, 200, 200);
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

		lblTipDia = new JLabel("Tipo de Dia:");
		lblTipDia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipDia.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTipDia.setBounds(115, 331, 231, 40);
		add(lblTipDia);

		lblEnlPlaylist = new JLabel("Enlace Playlist:");
		lblEnlPlaylist.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnlPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEnlPlaylist.setBounds(115, 393, 231, 40);
		add(lblEnlPlaylist);

		txtTipDia = new JTextField();
		txtTipDia.setEditable(false);
		txtTipDia.setBorder(new LineBorder(Color.BLACK, 1, true));
		txtTipDia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTipDia.setBounds(386, 331, 257, 40);
		add(txtTipDia);
		txtTipDia.setColumns(10);

		txtEnlPlaylist = new JTextField();
		txtEnlPlaylist.setBorder(new LineBorder(Color.BLACK, 1, true));
		txtEnlPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEnlPlaylist.setColumns(10);
		txtEnlPlaylist.setBounds(386, 393, 257, 40);
		add(txtEnlPlaylist);

		btnSeleccionar = new JButton("Seleccionar Canciones");
		btnSeleccionar.setBorder(new LineBorder(Color.BLACK, 1, true));
		btnSeleccionar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSeleccionar.setBounds(467, 443, 176, 40);
		add(btnSeleccionar);
		
		

		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.addActionListener(new BtnCerrarSesionActionListener());
		btnCerrarSesion.setBounds(10, 554, 116, 46);
		add(btnCerrarSesion);


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
	private class BtnCerrarSesionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		}
	}
}
