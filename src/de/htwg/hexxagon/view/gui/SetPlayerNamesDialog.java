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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import de.htwg.hexxagon.controller.IHexxagonController;
import de.htwg.hexxagon.util.ConstantColors;

public class SetPlayerNamesDialog extends JDialog implements ConstantColors {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfPlayer1;
	private JTextField tfPlayer2;
	private static IHexxagonController controller;
	private GraphicUI gui;

	public SetPlayerNamesDialog(IHexxagonController controller, GraphicUI gui) {
		SetPlayerNamesDialog.controller = controller;
		this.gui = gui;
		setTitle("Enter player names");
		setBounds(100, 100, 251, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			tfPlayer1 = new JTextField();
			tfPlayer1.setColumns(10);
		}
		JLabel lbPlayer1 = new JLabel("Player 1:");
		JLabel lbPlayer2 = new JLabel("Player 2:");
		
		tfPlayer2 = new JTextField();
		tfPlayer2.setColumns(10);
		
		GroupLayout glContentPanel = new GroupLayout(contentPanel);
		glContentPanel.setHorizontalGroup(
			glContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glContentPanel.createSequentialGroup()
					.addGap(4)
					.addGroup(glContentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(glContentPanel.createSequentialGroup()
							.addComponent(lbPlayer1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tfPlayer1, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, glContentPanel.createSequentialGroup()
							.addComponent(lbPlayer2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tfPlayer2, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
					.addContainerGap())
		);
		glContentPanel.setVerticalGroup(
			glContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfPlayer1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbPlayer1))
					.addGap(18)
					.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbPlayer2)
						.addComponent(tfPlayer2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		contentPanel.setLayout(glContentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btOk = new JButton("OK");
				btOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setNames();
					}
				});
				btOk.setActionCommand("OK");
				buttonPane.add(btOk);
				getRootPane().setDefaultButton(btOk);
			}
			{
				JButton btCancel = new JButton("Cancel");
				btCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
				btCancel.setActionCommand("Cancel");
				buttonPane.add(btCancel);
			}
		}
	}
	
	private void cancel() {
		dispose();
	}
	
	private void setNames() {
		controller.setPlayers(tfPlayer1.getText(), COLOR_PLAYER1, tfPlayer2.getText(), COLOR_PLAYER2);
		gui.update();
		dispose();
	}
}
