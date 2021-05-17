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
		this.setTitle("IT_Magagement App Connexion");
		this.setSize(500, 500);

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
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("name or pseudo : ",SwingConstants.RIGHT),c);
		
		
		this.userID = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(this.userID,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Password : ",SwingConstants.RIGHT),c);
		
		
		this.userPWD = new JPasswordField();
		this.userPWD.setPreferredSize(new Dimension(100, 20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
		c.gridx = 1;
		c.gridy = 1;
		panel.add(this.userPWD,c);
		
		

		this.exit = new JButton("Exit");
		this.exit.addActionListener(new ExitController());
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 0;       //aligned with button 2
		c.gridwidth = 1;   //2 columns wide
		c.gridy = 2;       //third row
		panel.add(this.exit, c);

		this.connect = new JButton("Connect");
		this.connect.addActionListener(new ConnexionController(this));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 2;       //aligned with button 2
		c.gridwidth = 1;   //2 columns wide
		c.gridy = 2;       //third row
		panel.add(this.connect, c);
	}
	
	public static void main(String[] args) throws Exception {
		Connexion frame = new Connexion();
	}
}
