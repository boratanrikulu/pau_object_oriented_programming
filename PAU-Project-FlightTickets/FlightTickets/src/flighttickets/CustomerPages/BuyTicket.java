/**
 * @author boratanrikulu
 * If you have any question about the project, you can contact me at http://boratanrikulu.me/contact
 */
package flighttickets.CustomerPages;

import flighttickets.DataBaseProcesses.DataBaseConnecter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BuyTicket extends javax.swing.JFrame {
	
	private Connection connection;
	private DefaultTableModel model;
	private LoginPage loginPage;
	private int customerId;
	
	public BuyTicket(int customerId, LoginPage loginPage) {
		initComponents();
		
		DataBaseConnecter connecter = new DataBaseConnecter(); // makes a connection to the database
		
		this.loginPage = loginPage;
		this.customerId = customerId;
		this.connection = connecter.getConnection();
		this.model = (DefaultTableModel) flightsTable.getModel();
		
		setFromWheres(); // takes fromWheres options from database an sends them to the fromWhereArea
		setDestinations(); // takes destinations options from database an sends them to the destinationArea
		showAllFlights(); // shows all flights as default
	}
	
	public void showAllFlights() {
		model.setRowCount(0);
		
		String query = "select * from flights";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Object[] willAdd = {resultSet.getInt("id"),
						     resultSet.getString("fromWhere"), resultSet.getString("destination"),
						     resultSet.getString("date"), resultSet.getString("time"),
						     resultSet.getString("seatCapacity")};
				model.addRow(willAdd); // adds all flights to the model
			}
		} catch (SQLException ex) {
			Logger.getLogger(BuyTicket.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void setFromWheres() {
		String query = "select * from flights";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				boolean flag = true;
				String fromWhereTemp = resultSet.getString("fromWhere");
				for(int counter=0; counter<fromWhereArea.getItemCount(); counter++) {
					if(fromWhereArea.getItemAt(counter).equals(fromWhereTemp)){
						flag = false; // checks whether it already exists
						break;
					}
				}
				if(flag) {
					fromWhereArea.addItem(fromWhereTemp); // takes fromWheres options from database an sends them to the fromWhereArea
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(BuyTicket.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void setDestinations() {
		String query = "select * from flights";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				boolean flag = true;
				String destinationTemp = resultSet.getString("destination");
				for(int counter=0; counter<destinationArea.getItemCount(); counter++) {
					if(destinationArea.getItemAt(counter).equals(destinationTemp)){
						flag = false; // checks whether it already exists
						break;
					}
				}
				if(flag) {
					destinationArea.addItem(destinationTemp); // takes destinations options from database an sends them to the destinationArea
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(BuyTicket.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void showTickets(String fromWhere, String destination, String date) {
		int flag = 0;
		model.setRowCount(0);
		
		String query = "select * from flights where fromWhere = ? and destination = ? and date = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, fromWhere);
			preparedStatement.setString(2, destination);
			preparedStatement.setString(3, date);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Object[] willAdd = {resultSet.getInt("id"),
						     resultSet.getString("fromWhere"), resultSet.getString("destination"),
						     resultSet.getString("date"), resultSet.getString("time"),
						     resultSet.getString("seatCapacity")};
				model.addRow(willAdd); // adds the flight to the model
				flag++; // increases the flag if there is flight that is searched.
			}
			
			if(flag == 0) {
				JOptionPane.showMessageDialog(this, "No flight that is searched was found."); // shows a message if there is not any flight that is searched.
			}
		} catch (SQLException ex) {
			Logger.getLogger(BuyTicket.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public String getFromWhere() {
		String fromWhere = this.fromWhereArea.getSelectedItem().toString();
		return fromWhere;
	}

	public String getDestination() {
		String destination = this.destinationArea.getSelectedItem().toString();
		return destination;
	}

	public String getDate() {
		String date = this.dateArea.getText();
		return date;
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	 @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                signOutButton = new javax.swing.JButton();
                manageTheAccountButton = new javax.swing.JButton();
                jPanel2 = new javax.swing.JPanel();
                showFilteredFlightsButton = new javax.swing.JButton();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                dateArea = new javax.swing.JTextField();
                fromWhereArea = new javax.swing.JComboBox<>();
                destinationArea = new javax.swing.JComboBox<>();
                showAllFlightsButton = new javax.swing.JButton();
                jPanel3 = new javax.swing.JPanel();
                chooseASeatButton = new javax.swing.JButton();
                jScrollPane2 = new javax.swing.JScrollPane();
                flightsTable = new javax.swing.JTable();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Flight Tickets");
                setBounds(new java.awt.Rectangle(390, 280, 0, 0));
                setResizable(false);

                jPanel1.setBackground(new java.awt.Color(225, 255, 255));

                signOutButton.setText("Sign Out");
                signOutButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                signOutButtonActionPerformed(evt);
                        }
                });

                manageTheAccountButton.setText("Manage The Account");
                manageTheAccountButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                manageTheAccountButtonActionPerformed(evt);
                        }
                });

                jPanel2.setBackground(new java.awt.Color(192, 241, 255));
                jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

                showFilteredFlightsButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                showFilteredFlightsButton.setText("Show Filtered Flights");
                showFilteredFlightsButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                showFilteredFlightsButtonActionPerformed(evt);
                        }
                });

                jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
                jLabel1.setText("From Where");

                jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
                jLabel2.setText("Destination");

                jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
                jLabel3.setText("Date");

                dateArea.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                dateArea.setText("2018-05-23");
                dateArea.setToolTipText("");

                fromWhereArea.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

                destinationArea.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

                showAllFlightsButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                showAllFlightsButton.setText("Show All Flights");
                showAllFlightsButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                showAllFlightsButtonActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1)
                                        .addComponent(showAllFlightsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fromWhereArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(destinationArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dateArea)
                                        .addComponent(showFilteredFlightsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(34, 34, 34))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(fromWhereArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(destinationArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(dateArea))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(showFilteredFlightsButton)
                                        .addComponent(showAllFlightsButton))
                                .addGap(46, 46, 46))
                );

                jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                chooseASeatButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                chooseASeatButton.setText("Choose A Seat");
                chooseASeatButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                chooseASeatButtonActionPerformed(evt);
                        }
                });
                jPanel3.add(chooseASeatButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, -1, -1));

                flightsTable.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                flightsTable.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "ID", "From Where", "Destination", "Date", "Time", "Seat Capacity"
                        }
                ) {
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jScrollPane2.setViewportView(flightsTable);
                if (flightsTable.getColumnModel().getColumnCount() > 0) {
                        flightsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
                        flightsTable.getColumnModel().getColumn(0).setMaxWidth(40);
                        flightsTable.getColumnModel().getColumn(3).setPreferredWidth(100);
                        flightsTable.getColumnModel().getColumn(3).setMaxWidth(100);
                        flightsTable.getColumnModel().getColumn(4).setPreferredWidth(85);
                        flightsTable.getColumnModel().getColumn(4).setMaxWidth(85);
                        flightsTable.getColumnModel().getColumn(5).setPreferredWidth(100);
                        flightsTable.getColumnModel().getColumn(5).setMaxWidth(100);
                }

                jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 540));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(manageTheAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(signOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(45, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(manageTheAccountButton)
                                        .addComponent(signOutButton))
                                .addGap(59, 59, 59)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void showFilteredFlightsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showFilteredFlightsButtonActionPerformed
		String fromWhere = this.fromWhereArea.getSelectedItem().toString();
		String destination = this.destinationArea.getSelectedItem().toString();
		String date = this.dateArea.getText();

		showTickets(fromWhere, destination, date); // takes fromWhere, destination and date from the areas and sends them to the showTickets
        }//GEN-LAST:event_showFilteredFlightsButtonActionPerformed

        private void chooseASeatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseASeatButtonActionPerformed
		if(this.flightsTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Please select a flight.");
		}
		else {
			int planeId = (int) this.flightsTable.getValueAt(this.flightsTable.getSelectedRow(), 0);

			ChooseSeat chooseSeat = new ChooseSeat(planeId, customerId, this);
			chooseSeat.setVisible(true); // opens a chooseSeat
		}
        }//GEN-LAST:event_chooseASeatButtonActionPerformed

        private void signOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutButtonActionPerformed
		if (JOptionPane.showConfirmDialog(this, "You are attempting to sign out.\nDo you want to continue ?", "WARNING",
			JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			this.dispose();
			this.loginPage.setVisible(true); // closes the current object and re-opens the loginPage
		}
        }//GEN-LAST:event_signOutButtonActionPerformed

        private void manageTheAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTheAccountButtonActionPerformed
		ManageTheAccount showMyTickets = new ManageTheAccount(this.customerId, this);

		showMyTickets.setVisible(true); // opens a showMyTickets
        }//GEN-LAST:event_manageTheAccountButtonActionPerformed

        private void showAllFlightsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAllFlightsButtonActionPerformed
		showAllFlights();
        }//GEN-LAST:event_showAllFlightsButtonActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		 /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(BuyTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(BuyTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(BuyTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(BuyTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		 //</editor-fold>

		/* Create and display the form */
		/*java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BuyTicket().setVisible(true);
			}
		});*/
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton chooseASeatButton;
        private javax.swing.JTextField dateArea;
        private javax.swing.JComboBox<String> destinationArea;
        private javax.swing.JTable flightsTable;
        private javax.swing.JComboBox<String> fromWhereArea;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JButton manageTheAccountButton;
        private javax.swing.JButton showAllFlightsButton;
        private javax.swing.JButton showFilteredFlightsButton;
        private javax.swing.JButton signOutButton;
        // End of variables declaration//GEN-END:variables

}
