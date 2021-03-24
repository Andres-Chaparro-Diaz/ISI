package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Dominio.GestorUsuario;
import Dominio.Usuario;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Principal {

	private JFrame frameApp;
	private JLabel lblLogo;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private JButton btnIniciarSesion;
	
	private Usuario usuario;
	private JLabel lblFondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frameApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameApp = new JFrame();
		frameApp.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Presentacion/Recursos/Logo.png")));
		frameApp.setTitle("Iniciar sesión");
		frameApp.setBounds(100, 100, 900, 650);
		frameApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameApp.getContentPane().setLayout(null);
		{
			lblLogo = new JLabel("");
			lblLogo.setBounds(335, 73, 200, 200);
			try {
				Image imagenOriginal = ImageIO.read(Principal.class.getResource("/Presentacion/Recursos/Logo.png"));
				Image imagenEscalada = imagenOriginal.getScaledInstance(lblLogo.getWidth(),
						lblLogo.getHeight(), java.awt.Image.SCALE_SMOOTH);
				ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
				lblLogo.setIcon(iconoLabel);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			frameApp.getContentPane().add(lblLogo);
		}
		{
			lblUsuario = new JLabel("Usuario");
			lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lblUsuario.setBounds(204, 331, 142, 40);
			frameApp.getContentPane().add(lblUsuario);
		}
		{
			lblContrasea = new JLabel("Contraseña");
			lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
			lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lblContrasea.setBounds(204, 393, 142, 40);
			frameApp.getContentPane().add(lblContrasea);
		}
		{
			txtUsuario = new JTextField();
			txtUsuario.setBorder(new LineBorder(Color.BLACK, 1, true));
			txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtUsuario.setBounds(386, 331, 257, 40);
			frameApp.getContentPane().add(txtUsuario);
			txtUsuario.setColumns(10);
		}
		{
			txtContrasena = new JTextField();
			txtContrasena.setBorder(new LineBorder(Color.BLACK, 1, true));
			txtContrasena.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtContrasena.setColumns(10);
			txtContrasena.setBounds(386, 393, 257, 40);
			frameApp.getContentPane().add(txtContrasena);
		}
		{
			btnIniciarSesion = new JButton("Iniciar sesión");
			btnIniciarSesion.setBorder(new LineBorder(Color.BLACK, 1, true));
			btnIniciarSesion.addActionListener(new BtnIniciarSesionActionListener());
			btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnIniciarSesion.setBounds(487, 443, 156, 40);
			frameApp.getContentPane().add(btnIniciarSesion);
		}
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 886, 613);
			try {
				Image imagenOriginal = ImageIO.read(Principal.class.getResource("/Presentacion/Recursos/Fondo.jpg"));
				Image imagenEscalada = imagenOriginal.getScaledInstance(lblFondo.getWidth(),
						lblFondo.getHeight(), java.awt.Image.SCALE_SMOOTH);
				ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
				lblFondo.setIcon(iconoLabel);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			frameApp.getContentPane().add(lblFondo);
		}
	}
	private class BtnIniciarSesionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!txtUsuario.getText().isEmpty() && !txtContrasena.getText().isEmpty()) {
				usuario = GestorUsuario.comprobarUsuario(txtUsuario.getText().toString(), txtContrasena.getText().toString());
				if(usuario == null) {
					JOptionPane.showMessageDialog(null, "Datos incorrectos.");
				} else {
					JOptionPane.showMessageDialog(null, "Ha iniciado sesion.\n Bienvenido\n"+usuario.getNombre());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Debe rellenar los campos anteriores.");
			}
		}
	}
}
