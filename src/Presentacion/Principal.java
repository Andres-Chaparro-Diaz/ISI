package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.json.JSONException;

import Dominio.Usuario;

import java.awt.Toolkit;
import java.awt.CardLayout;

public class Principal {

	private JFrame frameApp;
	private Usuario usuario;

	Login login = new Login(this);
	SeleccionarPlaylist seleccionarPlaylist = new SeleccionarPlaylist(this);
	ResultadoPlaylist resultadoPlaylist;
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
	 * @throws JSONException 
	 */
	public Principal() throws JSONException {
		resultadoPlaylist = new ResultadoPlaylist(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameApp = new JFrame();
		frameApp.setTitle("MoodApp");
		frameApp.setResizable(false);
		frameApp.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Recursos/moodapp.png")));
		frameApp.setBounds(100, 100, 900, 650);
		frameApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameApp.getContentPane().setLayout(new CardLayout(0, 0));
		frameApp.getContentPane().add(login);
		frameApp.getContentPane().add(seleccionarPlaylist);
		frameApp.getContentPane().add(resultadoPlaylist);
	}

	public void inicioSesionPulsado(Usuario usuario){
		this.usuario = usuario;
		login.setVisible(false);
		seleccionarPlaylist.setVisible(true);
	}
	
	public void salirPulsado(){
		login.setVisible(true);
		seleccionarPlaylist.setVisible(false);
	}
	
	public void seleccionarCancionesPulsado(){
		seleccionarPlaylist.setVisible(false);
		resultadoPlaylist.setVisible(true);
	}
	
	public void atrasPulsado(){
		seleccionarPlaylist.setVisible(true);
		resultadoPlaylist.setVisible(false);
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
}
