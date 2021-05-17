package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Connexion;

public class ConnexionController implements ActionListener{
	private Connexion view;
	
	public ConnexionController(Connexion connexion_view) {
		this.view = connexion_view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("User ID : " + this.view.getUserID());
		System.out.println("User PWD : " + this.view.getUserPassword());
		
		if(this.view.checkConnect()) {
			System.out.println("--- Connexion Success ---");
		}
		else {
			System.out.println("--- Connexion Failed ---");
		}
	}
}
