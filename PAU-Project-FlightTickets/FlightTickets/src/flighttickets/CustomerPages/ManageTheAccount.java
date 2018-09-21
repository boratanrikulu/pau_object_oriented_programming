/**
 * @author boratanrikulu
 * If you have any question about the project, you can contact me at http://boratanrikulu.me/contact
 */
package flighttickets.CustomerPages;

import flighttickets.DataBaseProcesses.DataBaseConnecter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageTheAccount extends javax.swing.JFrame {

	private Connection connection;
	private int customerId;
	private DefaultTableModel model;
	private BuyTicket buyTicket;
	
	public ManageTheAccount(int customerId, BuyTicket buyTicket) {
		initComponents();
		
		DataBaseConnecter connecter = new DataBaseConnecter(); // makes a connection to the database
		
		this.customerId = customerId;
		this.connection = connecter.getConnection();
		this.model = (DefaultTableModel) myTicketsTable.getModel();
		this.buyTicket = buyTicket;
		setAccountInfos(); // shows the informations of the account
		setMyTickets(); // shows the tickets of the account
	}
	
	public void setAccountInfos() {
		String query = "select * from customers where id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, this.customerId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) { // sets the informations of the account
				this.nameArea.setText(resultSet.getString("name"));
				this.surnameArea.setText(resultSet.getString("surname"));
				this.emailArea.setText(resultSet.getString("email"));
				this.passwordArea.setText(resultSet.getString("password"));
				this.birthdateArea.setText(resultSet.getString("birthdate"));
				this.addressArea.setText(resultSet.getString("address"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(ManageTheAccount.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void setMyTickets() {
		model.setRowCount(0);
		
		String query = "select * from sold_tickets  where customerId = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, this.customerId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {				
				String query2 = "select * from flights where id = ?";
				
				preparedStatement = connection.prepareStatement(query2);
				preparedStatement.setInt(1, resultSet.getInt("planeId"));
				
				ResultSet resultSet2 = preparedStatement.executeQuery();
				while(resultSet2.next()) { // takes all tickets that is matching with customerId
					Object[] willAdd = {resultSet.getInt("planeId"),
							     resultSet2.getString("fromWhere"), resultSet2.getString("destination"),
							     resultSet2.getString("date"), resultSet2.getString("time"),
							     resultSet.getInt("seatNumber")};

					model.addRow(willAdd); // adds the tickets of the account to the model
				}
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(ManageTheAccount.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void cancelTheTicket(int planeId, int seatNumber) {
		int seatCapacityTemp = 0;
		String query = "delete from sold_tickets where planeId = ? and seatNumber = ? ";
		String query2 = "select * from flights where id = ?";
		String query3 = "update flights set seatCapacity = ? where id = ?";
		PreparedStatement preparedStatement = null;
		
		try {			
			if (JOptionPane.showConfirmDialog(this, "Are you sure to cancel the ticket ?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {				
				
				preparedStatement = connection.prepareStatement(query2);
				preparedStatement.setInt(1, planeId);
				ResultSet resultSet = preparedStatement.executeQuery();
				if( resultSet.next()) { // gets the seatCapacity value of the flight
					seatCapacityTemp = resultSet.getInt("seatCapacity");
				}
								
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, planeId);
				preparedStatement.setInt(2, seatNumber);
				preparedStatement.executeUpdate(); // cancels the ticket of the customer
				JOptionPane.showMessageDialog(this, "The ticket was canceled.");
				
				seatCapacityTemp++;
				preparedStatement = connection.prepareStatement(query3);
				preparedStatement.setInt(1, seatCapacityTemp);
				preparedStatement.setInt(2, planeId);
				preparedStatement.executeUpdate(); // increases the seatCapacity of the flight
				
				setMyTickets(); // sets my tickets again to see changes
				buyTicket.showAllFlights(); // refreshes the buyTicket object
			}
		} catch (SQLException ex) {
			Logger.getLogger(ManageTheAccount.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void changeInfo(String name, String surname, String email, String password, String birthdate, String address) {
		String query = "update customers set name = ?, surname = ?, email = ?, password = ?, birthdate = ?, address = ?  where id = ?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, birthdate);
			preparedStatement.setString(6, address);
			preparedStatement.setInt(7, this.customerId);

			preparedStatement.executeUpdate(); // update the information of the account with news

			JOptionPane.showMessageDialog(this, "The Account Info is changed.");
			setAccountInfos();
		} catch(SQLIntegrityConstraintViolationException ex) {
			JOptionPane.showMessageDialog(this, "Changing Info is failed.\nThis email is already registered.");
			setAccountInfos();
		} catch(SQLDataException ex) {
			JOptionPane.showMessageDialog(this, "Changing Info  is failed.\nSome formats are not acceptable.\nIt might be due to birthday date.\nMake an entry like 1984-01-01.");
			setAccountInfos();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Changing Info is failed.");
			setAccountInfos();
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

                jPanel2 = new javax.swing.JPanel();
                jPanel3 = new javax.swing.JPanel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();
                nameArea = new javax.swing.JTextField();
                surnameArea = new javax.swing.JTextField();
                birthdateArea = new javax.swing.JTextField();
                emailArea = new javax.swing.JTextField();
                addressArea = new javax.swing.JTextField();
                passwordArea = new javax.swing.JPasswordField();
                saveChanges = new javax.swing.JButton();
                jLabel2 = new javax.swing.JLabel();
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                cancelTheTicketButton = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                myTicketsTable = new javax.swing.JTable();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("Flight Tickets");
                setBounds(new java.awt.Rectangle(500, 390, 0, 0));
                setResizable(false);

                jPanel2.setBackground(new java.awt.Color(225, 255, 255));

                jPanel3.setBackground(new java.awt.Color(192, 241, 255));
                jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

                jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                jLabel3.setText("Name");

                jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                jLabel4.setText("Surname");

                jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                jLabel5.setText("Email");

                jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                jLabel6.setText("Password");

                jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                jLabel7.setText("Birthdate");

                jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                jLabel8.setText("Address");

                saveChanges.setText("Save Changes");
                saveChanges.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                saveChangesActionPerformed(evt);
                        }
                });

                jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
                jLabel2.setText("Account Info");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel7))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(saveChanges, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                                        .addComponent(nameArea)
                                                        .addComponent(addressArea, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                                        .addComponent(birthdateArea)
                                                        .addComponent(emailArea)
                                                        .addComponent(surnameArea)
                                                        .addComponent(passwordArea))
                                                .addGap(25, 25, 25))))
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(nameArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(surnameArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(emailArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(passwordArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(birthdateArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(addressArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(saveChanges)
                                .addGap(30, 30, 30))
                );

                jPanel1.setBackground(new java.awt.Color(225, 255, 255));
                jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
                jLabel1.setText("My Tickets");
                jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, -1, 40));

                cancelTheTicketButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                cancelTheTicketButton.setText("Cancel The Ticket");
                cancelTheTicketButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cancelTheTicketButtonActionPerformed(evt);
                        }
                });
                jPanel1.add(cancelTheTicketButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, -1, -1));

                myTicketsTable.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Plane ID", "From Where", "Destination", "Date", "Time", "Seat Number"
                        }
                ) {
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jScrollPane1.setViewportView(myTicketsTable);
                if (myTicketsTable.getColumnModel().getColumnCount() > 0) {
                        myTicketsTable.getColumnModel().getColumn(0).setPreferredWidth(65);
                        myTicketsTable.getColumnModel().getColumn(0).setMaxWidth(65);
                        myTicketsTable.getColumnModel().getColumn(1).setMinWidth(90);
                        myTicketsTable.getColumnModel().getColumn(1).setPreferredWidth(90);
                        myTicketsTable.getColumnModel().getColumn(2).setMinWidth(90);
                        myTicketsTable.getColumnModel().getColumn(2).setPreferredWidth(90);
                        myTicketsTable.getColumnModel().getColumn(3).setPreferredWidth(80);
                        myTicketsTable.getColumnModel().getColumn(3).setMaxWidth(80);
                        myTicketsTable.getColumnModel().getColumn(4).setPreferredWidth(70);
                        myTicketsTable.getColumnModel().getColumn(4).setMaxWidth(70);
                        myTicketsTable.getColumnModel().getColumn(5).setMinWidth(80);
                        myTicketsTable.getColumnModel().getColumn(5).setPreferredWidth(80);
                }

                jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 40, 520, 410));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(71, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void saveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChangesActionPerformed
		String name = this.nameArea.getText();
		String surname = this.surnameArea.getText();
		String email = this.emailArea.getText();
		String password = new String(this.passwordArea.getText());
		String birthdate = this.birthdateArea.getText();
		String address = this.addressArea.getText();

		if(name.equals("") || surname.equals("") || email.equals("") || password.equals("") || birthdate.equals("") || address.equals("")){
			JOptionPane.showMessageDialog(this, "Please fill out all information."); // shows an error if informations is not completed
			setAccountInfos(); // refreshes the account infos
		}
		else { 
			changeInfo(name, surname, email, password, birthdate, address); // takes all informations from the areas and sends them to the changeInfo
		}
        }//GEN-LAST:event_saveChangesActionPerformed

        private void cancelTheTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelTheTicketButtonActionPerformed
		if(this.myTicketsTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Please select a flight."); // shows an error if flight is not selected
		}
		else {
			int planeId = (int) this.myTicketsTable.getValueAt(this.myTicketsTable.getSelectedRow(), 0);
			int seatNumber = (int) this.myTicketsTable.getValueAt(this.myTicketsTable.getSelectedRow(), 5);

			cancelTheTicket(planeId, seatNumber); // takes planeId and seatNumber from the areas and sends them to the cancelTheTicket
		}	
        }//GEN-LAST:event_cancelTheTicketButtonActionPerformed

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
			java.util.logging.Logger.getLogger(ManageTheAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ManageTheAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ManageTheAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ManageTheAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		 //</editor-fold>

		/* Create and display the form */
		/*java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ShowMyTickets().setVisible(true);
			}
		 //</editor-fold>

		/* Create and display the form */
		/*java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ShowMyTickets().setVisible(true);
			}
		});*/
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JTextField addressArea;
        private javax.swing.JTextField birthdateArea;
        private javax.swing.JButton cancelTheTicketButton;
        private javax.swing.JTextField emailArea;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable myTicketsTable;
        private javax.swing.JTextField nameArea;
        private javax.swing.JPasswordField passwordArea;
        private javax.swing.JButton saveChanges;
        private javax.swing.JTextField surnameArea;
        // End of variables declaration//GEN-END:variables

}
