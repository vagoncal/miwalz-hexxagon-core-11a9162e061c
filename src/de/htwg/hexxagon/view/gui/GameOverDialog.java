/**
 * Hexxagon
 * 
 * @author Michael Walz & Tarek Schneider
 * @version 2013-01-24
 *  
 */

package de.htwg.hexxagon.view.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import de.htwg.hexxagon.controller.impl.HexxagonController;
import de.htwg.hexxagon.util.ConstantColors;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOverDialog extends JDialog implements ConstantColors {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private HexxagonController controller;
	private JLabel lbGameOver;

	public GameOverDialog(HexxagonController controller) {
		setTitle("Congratulations!");
		this.setVisible(true);
		this.controller = controller;
		setBounds(100, 100, 338, 109);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lbGameOver = new JLabel("");
			contentPanel.add(lbGameOver);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		setGameOverText();
	}
	
	private void setGameOverText() {
		String gameOverText;
		if (controller.getPlayerScore(COLOR_PLAYER1) > controller.getPlayerScore(COLOR_PLAYER2)) {
			gameOverText = controller.getPlayerName(0);
		} else {
			gameOverText = controller.getPlayerName(1);
		}
		lbGameOver.setText(gameOverText + " wins!");
	}

}
