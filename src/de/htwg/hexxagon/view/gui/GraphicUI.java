/**
 * Hexxagon
 * 
 * @author Michael Walz & Tarek Schneider
 * @version 2013-01-24
 *  
 */

package de.htwg.hexxagon.view.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import de.htwg.hexxagon.controller.IHexxagonController;
import de.htwg.hexxagon.controller.impl.HexxagonController;
import de.htwg.hexxagon.observer.IObserver;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class GraphicUI extends JFrame implements IObserver {

	private static final long serialVersionUID = 1L;
	private JLabel lbScore;
	private IHexxagonController controller;

	public GraphicUI(HexxagonController controller) {
		this.controller = controller;
		controller.addObserver(this);
		setResizable(false);
		this.setVisible(true);
		setTitle("Hexxagon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 713);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JSeparator separator = new JSeparator();
		mnGame.add(separator);
		
		JMenuItem menuQuit = new JMenuItem("Quit");
		menuQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quit();
			}
		});

		mnGame.add(menuQuit);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelGridPanel = new GridPanel(controller);
		
		lbScore = new JLabel("");
		lbScore.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout glContentPane = new GroupLayout(contentPane);
		glContentPane.setHorizontalGroup(
			glContentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(glContentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(glContentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lbScore, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
						.addComponent(panelGridPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		glContentPane.setVerticalGroup(
			glContentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(glContentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelGridPanel, GroupLayout.PREFERRED_SIZE, 607, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(lbScore)
					.addContainerGap())
		);
		contentPane.setLayout(glContentPane);
		
		init();
	}
	
	public void setScoreLabel(String string) {
		lbScore.setText(string);
	}
	
	private void init() {
		enterNames();
		setScoreLabel();
	}
	
	private void enterNames() {
		JDialog enterNames = new SetPlayerNamesDialog(controller, this);
		enterNames.setVisible(true);
	}
	
	private void setScoreLabel() {
		lbScore.setText(controller.getStatus() + "     |     " + controller.getPlayerName() + "'s turn!");
	}
	
	private void quit() {
		System.exit(0);
	}

	public void update() {
		setScoreLabel();
	}
	
}
