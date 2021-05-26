package gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;

import User.User;
import guiController.ConnexionController;
import guiController.ExitController;
import searchEngine.UserSearchEngine;

/**
 * TODO ajouter du style pour faire quelque chose de plus joli
 * 
 * @author thomasduvinage
 * 
 * L'objectif est de relier l'actionListener à la base de données afin de faire des requetes directement du la BDD 
 * et enlever l'arguement userDB dans le constructeur.
 *
 */

public class Connexion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton exit;
	private JButton connect;
	
	private JTextField userID;
	private JPasswordField userPWD;
	
	private JPanel panel_credentials;
	private JPanel panel_buttons;

	public UserSearchEngine userEngine;

	public Connexion() {
		this.setTitle("Gestionnaire du parc informatique - Authentification");
		this.setSize(480, 200);

		this.createView();
		
		this.add(panel_credentials);
		this.add(panel_buttons);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public boolean checkConnect() {
		return this.userEngine.checkConnect(getUserID(), getUserPassword());
	}
	
	public void setUserSearchEngine(UserSearchEngine engine) {
		this.userEngine = engine;
	}
	
	public String getUserPassword() {
		return String.copyValueOf(this.userPWD.getPassword());
	}
	
	public String getUserID() {
		return this.userID.getText();
	}

	private void createView() {
		setLayout(new BoxLayout (this.getContentPane(), BoxLayout.Y_AXIS));
		
		GridBagConstraints c = new GridBagConstraints();
		
		// CREDENTIALS BOX //
		panel_credentials = new JPanel();
		panel_credentials.setLayout(new GridBagLayout());

		// Username label //
		c.insets = new Insets(10,20,0,0); //padding for username label
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 0;
		panel_credentials.add(new JLabel("Nom d'utilisateur :",SwingConstants.RIGHT),c);
		
		this.userID = new JTextField();
		c.insets = new Insets(10,20,0,50); //padding for username textbox
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 0;
		panel_credentials.add(this.userID,c);
		
		
		// Password label //
		c.insets = new Insets(10,20,0,0); //padding for password label
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 1;
		panel_credentials.add(new JLabel("Mot de passe :",SwingConstants.RIGHT),c);
		
		this.userPWD = new JPasswordField();
		c.insets = new Insets(10,20,0,50); //padding for password textbox
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 1;
		panel_credentials.add(this.userPWD,c);
		
		// BUTTONS BOX //
		panel_buttons = new JPanel();
		panel_buttons.setLayout(new GridBagLayout());

		this.exit = new JButton("Quitter");
		this.exit.addActionListener(new ExitController());
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10; // 10 more pixels on Y axis
		c.insets = new Insets(0, 50, 0, 50);
		c.gridx = 0;
		c.gridy = 0;
		panel_buttons.add(this.exit, c);

		this.connect = new JButton("Connexion");
		this.connect.addActionListener(new ConnexionController(this));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10; // 10 more pixels on Y axis
		c.insets = new Insets(0, 50, 0, 50);  //top padding
		c.gridx = 1;
		c.gridy = 0;
		panel_buttons.add(this.connect, c);
	}
	
	public static void main(String[] args) throws Exception {
		Connexion frame = new Connexion();
	}
}
