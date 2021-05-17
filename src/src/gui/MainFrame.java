package gui;

import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		this.setTitle("IT_Magagement App");
		this.setSize(1000, 500);

		// create and add menuBar to JFrame
		this.setJMenuBar(this.createMenuBar());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * This method is used to create menus of the app
	 * 
	 * @return JMenuBar menuBar
	 */
	private JMenuBar createMenuBar() {

		// Creation of the menuBar
		JMenuBar menuBar = new JMenuBar();

		// Definition of the menuBar's File option
		JMenu mnuFile = new JMenu("File");
		mnuFile.setMnemonic('F');

		JMenuItem mnuNewFile = new JMenuItem("New File");
		mnuNewFile.setIcon(new ImageIcon("icons/new.png"));
		mnuNewFile.setMnemonic('N');
		mnuNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		mnuNewFile.addActionListener(this::mnuNewListener);
		mnuFile.add(mnuNewFile);

		mnuFile.addSeparator();

		JMenuItem mnuOpenFile = new JMenuItem("Open File ...");
		mnuOpenFile.setIcon(new ImageIcon("icons/open.png"));
		mnuOpenFile.setMnemonic('O');
		mnuOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		mnuFile.add(mnuOpenFile);

		JMenuItem mnuSaveFile = new JMenuItem("Save File ...");
		mnuSaveFile.setIcon(new ImageIcon("icons/save.png"));
		mnuSaveFile.setMnemonic('S');
		mnuSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		mnuFile.add(mnuSaveFile);

		JMenuItem mnuSaveFileAs = new JMenuItem("Save File As ...");
		mnuSaveFileAs.setIcon(new ImageIcon("icons/save_as.png"));
		mnuSaveFileAs.setMnemonic('A');
		mnuFile.add(mnuSaveFileAs);

		mnuFile.addSeparator();
		
		JMenuItem mnuSignOut = new JMenuItem("Sign Out");
		mnuSignOut.setIcon(new ImageIcon("icons/exit.png"));
		mnuSignOut.setMnemonic('D');
		mnuFile.add(mnuSignOut);

		JMenuItem mnuExit = new JMenuItem("Exit");
		mnuExit.setIcon(new ImageIcon("icons/exit.png"));
		mnuExit.setMnemonic('x');
		mnuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		mnuFile.add(mnuExit);

		menuBar.add(mnuFile);

		// Definition of the menuBar's Edit option
		JMenu mnuEdit = new JMenu("Edit");
		mnuEdit.setMnemonic('E');

		JMenuItem mnuUndo = new JMenuItem("Undo");
		mnuUndo.setIcon(new ImageIcon("icons/undo.png"));
		mnuUndo.setMnemonic('U');
		mnuUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		mnuEdit.add(mnuUndo);

		JMenuItem mnuRedo = new JMenuItem("Redo");
		mnuRedo.setIcon(new ImageIcon("icons/redo.png"));
		mnuRedo.setMnemonic('R');
		mnuRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK));
		mnuEdit.add(mnuRedo);

		mnuEdit.addSeparator();

		JMenuItem mnuCopy = new JMenuItem("Copy");
		mnuCopy.setIcon(new ImageIcon("icons/copy.png"));
		mnuCopy.setMnemonic('C');
		mnuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		mnuEdit.add(mnuCopy);

		JMenuItem mnuCut = new JMenuItem("Cut");
		mnuCut.setIcon(new ImageIcon("icons/cut.png"));
		mnuCut.setMnemonic('t');
		mnuCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
		mnuEdit.add(mnuCut);

		JMenuItem mnuPaste = new JMenuItem("Paste");
		mnuPaste.setIcon(new ImageIcon("icons/paste.png"));
		mnuPaste.setMnemonic('P');
		mnuPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
		mnuEdit.add(mnuPaste);

		menuBar.add(mnuEdit);

		return menuBar;
	}

	public void mnuNewListener(ActionEvent event) {
		JOptionPane.showMessageDialog(this, "Button clicked !");
	}

	public static void main(String[] args) throws Exception {
		MainFrame frame = new MainFrame();
	}

}
