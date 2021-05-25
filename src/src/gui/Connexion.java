package gui;

import java.awt.Dimension;
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

	private JPanel panel;

	public UserSearchEngine userEngine;

	public Connexion() {
		this.setTitle("Gestionnaire du parc informatique - Authentification");
		this.setSize(480, 240);

		this.createView();
		
		this.getContentPane().add(panel);

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
		panel = new JPanel();

		panel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20,10,0,0);  //padding between grid elements
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Nom d'utilisateur :",SwingConstants.RIGHT),c);
		
		
		this.userID = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(this.userID,c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Mot de passe :",SwingConstants.RIGHT),c);
		
		
		this.userPWD = new JPasswordField();
		this.userPWD.setPreferredSize(new Dimension(100, 20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 1;
		panel.add(this.userPWD,c);

		this.exit = new JButton("Quitter");
		this.exit.addActionListener(new ExitController());
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.insets = new Insets(0, 0, 0, 0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridy = 2;       //third row
		panel.add(this.exit, c);

		this.connect = new JButton("Connexion");
		this.connect.addActionListener(new ConnexionController(this));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.insets = new Insets(0, 0, 0, 50);  //top padding
		c.gridx = 2;       //aligned with button 2
		c.gridy = 2;       //third row
		panel.add(this.connect, c);
	}
	
	public static void main(String[] args) throws Exception {
		Connexion frame = new Connexion();
	}
}
