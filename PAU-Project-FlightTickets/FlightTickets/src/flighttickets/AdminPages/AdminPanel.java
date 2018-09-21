/**
 * @author boratanrikulu
 * If you have any question about the project, you can contact me at http://boratanrikulu.me/contact
 */
package flighttickets.AdminPages;

import flighttickets.CustomerPages.BuyTicket;
import flighttickets.DataBaseProcesses.DataBaseConnecter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminPanel extends javax.swing.JFrame {

	private Connection connection;
	private DefaultTableModel modelFlight;
	private DefaultTableModel modelCustomer;
	private AdminLoginPage adminLoginPage;
	
	public AdminPanel(AdminLoginPage adminLoginPage) {
		initComponents();
		
		DataBaseConnecter connecter = new DataBaseConnecter(); // makes a connection to the database
		
		this.adminLoginPage = adminLoginPage;
		this.connection = connecter.getConnection();
		this.modelFlight = (DefaultTableModel) this.flightsTable.getModel();
		this.modelCustomer = (DefaultTableModel) this.customersTable.getModel();
		
		showFlights(); // shows all flights and all customers
		showCustomers();
	}
	
	public void showFlights() {
		modelFlight.setRowCount(0);
		
		String query = "select * from flights";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Object[] willAdd = {resultSet.getInt("id"),
						     resultSet.getString("fromWhere"), resultSet.getString("destination"),
						     resultSet.getString("date"), resultSet.getString("time"),
						     resultSet.getString("seatCapacity")};
				modelFlight.addRow(willAdd); // adds all flights to the modelFlight
			}
		} catch (SQLException ex) {
			Logger.getLogger(BuyTicket.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void showCustomers() {
		modelCustomer.setRowCount(0);
		
		String query = "select * from customers";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Object[] willAdd = {resultSet.getInt("id"),
						     resultSet.getString("name"), resultSet.getString("surname"),
						     resultSet.getString("email"), resultSet.getString("password")};
				modelCustomer.addRow(willAdd); // adds all customers to the modelFlight
			}
		} catch (SQLException ex) {
			Logger.getLogger(BuyTicket.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void deleteTheFlight(int planeId) {
		String query = "delete from flights where id = ?";
		String query2 = "delete from sold_tickets where planeId = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			if (JOptionPane.showConfirmDialog(this, "Are you sure to remove the flight ?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
				connection.setAutoCommit(false); // makes disable autocommit

				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, planeId); // deletes the flight
				preparedStatement.executeUpdate();

				preparedStatement = connection.prepareStatement(query2);
				preparedStatement.setInt(1, planeId);
				preparedStatement.executeUpdate(); // deletes the tickets of the flights
				
				connection.commit(); // writes the changes to the database
				JOptionPane.showMessageDialog(this, "The flight was removed.");
				showFlights(); // refreshes the modelFlights

				connection.setAutoCommit(true); // makes enable autocommit
			}
		} catch (SQLException ex) {
			Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void deleteTheCustomer(int customerId) {
		String query = "delete from customers where id = ?";
		String query2 = "delete from sold_tickets where customerId = ?";
		String query3 = "select * from sold_tickets where customerId= ?";
		String query4 = "select * from flights where id = ?";
		String query5 = "update flights set seatCapacity = ? where id = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			if (JOptionPane.showConfirmDialog(this, "Are you sure to remove the customer ?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
				connection.setAutoCommit(false); // makes disable autocommit
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, customerId);
				preparedStatement.executeUpdate(); // deletes the customer
				
				preparedStatement = connection.prepareStatement(query3);
				preparedStatement.setInt(1, customerId);
				ResultSet resultSet = preparedStatement.executeQuery(); // gets the planeIDs of the tickets that will be canceled.
				
				preparedStatement = connection.prepareStatement(query2);
				preparedStatement.setInt(1, customerId);
				preparedStatement.executeUpdate(); // cancel the all tickets of the customer
				
				while(resultSet.next()) {
					int flightIdTemp = resultSet.getInt("planeId");
					int seatCapacityTemp = 0;
					
					preparedStatement = connection.prepareStatement(query4);
					preparedStatement.setInt(1, flightIdTemp);
					ResultSet resultSet2 = preparedStatement.executeQuery(); // gets the seatCapacity value of the flight
					if( resultSet2.next()) {
						seatCapacityTemp = resultSet2.getInt("seatCapacity");
					}
					
					seatCapacityTemp++;
					preparedStatement = connection.prepareStatement(query5);
					preparedStatement.setInt(1, seatCapacityTemp);
					preparedStatement.setInt(2, flightIdTemp);
					preparedStatement.executeUpdate(); // increases the seatCapacity of the flight
				}
				
				connection.commit(); // writes the changes to the database
				JOptionPane.showMessageDialog(this, "The customer was removed.");
				showCustomers();
				showFlights();
				
				connection.setAutoCommit(true); // makes enable autocommit
			}
		} catch (SQLException ex) {
			Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
		}
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
                jLabel1 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                deleteFlightButton = new javax.swing.JButton();
                updateFlightButton = new javax.swing.JButton();
                addFlightButton = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                flightsTable = new javax.swing.JTable();
                jLabel2 = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                deleteCustomerButton = new javax.swing.JButton();
                updateCustomerButton = new javax.swing.JButton();
                addCustomerButton = new javax.swing.JButton();
                jScrollPane2 = new javax.swing.JScrollPane();
                customersTable = new javax.swing.JTable();
                signOutButton = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Flight Tickets");
                setBounds(new java.awt.Rectangle(400, 250, 0, 0));
                setResizable(false);

                jPanel1.setBackground(new java.awt.Color(43, 46, 55));

                jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("Manage The Flights");

                jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                deleteFlightButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                deleteFlightButton.setText("Delete");
                deleteFlightButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                deleteFlightButtonActionPerformed(evt);
                        }
                });
                jPanel2.add(deleteFlightButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 400, 110, -1));

                updateFlightButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                updateFlightButton.setText("Update");
                updateFlightButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                updateFlightButtonActionPerformed(evt);
                        }
                });
                jPanel2.add(updateFlightButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 110, -1));

                addFlightButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                addFlightButton.setText("Add");
                addFlightButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                addFlightButtonActionPerformed(evt);
                        }
                });
                jPanel2.add(addFlightButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 110, -1));

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
                jScrollPane1.setViewportView(flightsTable);
                if (flightsTable.getColumnModel().getColumnCount() > 0) {
                        flightsTable.getColumnModel().getColumn(0).setPreferredWidth(20);
                        flightsTable.getColumnModel().getColumn(1).setPreferredWidth(80);
                        flightsTable.getColumnModel().getColumn(2).setPreferredWidth(80);
                        flightsTable.getColumnModel().getColumn(3).setPreferredWidth(75);
                        flightsTable.getColumnModel().getColumn(3).setMaxWidth(75);
                        flightsTable.getColumnModel().getColumn(4).setPreferredWidth(70);
                        flightsTable.getColumnModel().getColumn(4).setMaxWidth(70);
                        flightsTable.getColumnModel().getColumn(5).setMinWidth(80);
                        flightsTable.getColumnModel().getColumn(5).setPreferredWidth(80);
                }

                jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 480));

                jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                jLabel2.setText("Manage The Customers");

                jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                deleteCustomerButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                deleteCustomerButton.setText("Delete");
                deleteCustomerButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                deleteCustomerButtonActionPerformed(evt);
                        }
                });
                jPanel3.add(deleteCustomerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 400, 110, -1));

                updateCustomerButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                updateCustomerButton.setText("Update");
                updateCustomerButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                updateCustomerButtonActionPerformed(evt);
                        }
                });
                jPanel3.add(updateCustomerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 110, -1));

                addCustomerButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                addCustomerButton.setText("Add");
                addCustomerButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                addCustomerButtonActionPerformed(evt);
                        }
                });
                jPanel3.add(addCustomerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 110, -1));

                customersTable.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "ID", "Name", "Surname", "Email", "Password"
                        }
                ) {
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jScrollPane2.setViewportView(customersTable);
                if (customersTable.getColumnModel().getColumnCount() > 0) {
                        customersTable.getColumnModel().getColumn(0).setPreferredWidth(40);
                        customersTable.getColumnModel().getColumn(0).setMaxWidth(40);
                        customersTable.getColumnModel().getColumn(1).setPreferredWidth(90);
                        customersTable.getColumnModel().getColumn(1).setMaxWidth(90);
                        customersTable.getColumnModel().getColumn(2).setPreferredWidth(100);
                        customersTable.getColumnModel().getColumn(2).setMaxWidth(100);
                        customersTable.getColumnModel().getColumn(3).setMinWidth(60);
                        customersTable.getColumnModel().getColumn(3).setPreferredWidth(60);
                        customersTable.getColumnModel().getColumn(4).setPreferredWidth(90);
                        customersTable.getColumnModel().getColumn(4).setMaxWidth(90);
                }

                jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 480));

                signOutButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                signOutButton.setText("Sign Out");
                signOutButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                signOutButtonActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(65, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(206, 206, 206)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(59, 59, 59)
                                .addComponent(signOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(signOutButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void signOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutButtonActionPerformed
		if (JOptionPane.showConfirmDialog(this, "You are attempting to sign out.\nDo you want to continue ?", "WARNING",
			JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			this.dispose();
			this.adminLoginPage.setVisible(true);
		}
        }//GEN-LAST:event_signOutButtonActionPerformed

        private void deleteFlightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFlightButtonActionPerformed
		if(this.flightsTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Please select a flight.");
		}
		else {
			int planeId = (int) this.flightsTable.getValueAt(this.flightsTable.getSelectedRow(), 0);

			deleteTheFlight(planeId);
		}
        }//GEN-LAST:event_deleteFlightButtonActionPerformed

        private void deleteCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCustomerButtonActionPerformed
		if(this.customersTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Please select a customer.");
		}
		else {
			int customerId = (int) this.customersTable.getValueAt(this.customersTable.getSelectedRow(), 0);

			deleteTheCustomer(customerId);
		}
        }//GEN-LAST:event_deleteCustomerButtonActionPerformed

        private void addFlightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFlightButtonActionPerformed
		AddFlight addFlight = new AddFlight(this);
		addFlight.setVisible(true);
        }//GEN-LAST:event_addFlightButtonActionPerformed

        private void updateFlightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateFlightButtonActionPerformed
		if(this.flightsTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Please select a flight.");
		}
		else {
			int planeId = (int) this.flightsTable.getValueAt(this.flightsTable.getSelectedRow(), 0);

			UpdateFlight updateFlight = new UpdateFlight(this, planeId);
			updateFlight.setVisible(true);
		}
        }//GEN-LAST:event_updateFlightButtonActionPerformed

        private void addCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerButtonActionPerformed
		AddCustomer addCustomer = new AddCustomer(this);
		addCustomer.setVisible(true);
        }//GEN-LAST:event_addCustomerButtonActionPerformed

        private void updateCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCustomerButtonActionPerformed
		if(this.customersTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Please select a customer.");
		}
		else {
			int customerId = (int) this.customersTable.getValueAt(this.customersTable.getSelectedRow(), 0);

			UpdateCustomer updateCustomer = new UpdateCustomer(this, customerId);
			updateCustomer.setVisible(true);
		}
        }//GEN-LAST:event_updateCustomerButtonActionPerformed

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
			java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		 //</editor-fold>

		/* Create and display the form */
		/*java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new AdminPanel().setVisible(true);
			}
		});*/
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton addCustomerButton;
        private javax.swing.JButton addFlightButton;
        private javax.swing.JTable customersTable;
        private javax.swing.JButton deleteCustomerButton;
        private javax.swing.JButton deleteFlightButton;
        private javax.swing.JTable flightsTable;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JButton signOutButton;
        private javax.swing.JButton updateCustomerButton;
        private javax.swing.JButton updateFlightButton;
        // End of variables declaration//GEN-END:variables

}
