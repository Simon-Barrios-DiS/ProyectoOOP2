package interfaz;
import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.util.ArrayList;

//Imports
	import javax.swing.*;
import javax.swing.table.DefaultTableModel;

	public class Interfaz {
		//Attributes
		private JFrame view;
		private JPanel errorPanel;
		private JLabel errorLabel;
		private JButton buttonError;
		private JPanel dataPanel;
		private JPanel tablePanel;
		private JTable jt;   
		private JButton buttonShow;
		private JButton buttonAdd;
		private JButton buttonDelete;
		private JButton buttonMod;
		private JLabel label1;
		private JLabel label2;
		private JLabel label3;
		private JLabel label4;
		private JLabel label5;
		private JLabel label6;
		private JLabel labelID;
		private JTextField textField1;
		private JTextField textField2;
		private JTextField textField3;
		private JTextField textField4;
		private JTextField textField5;
		private JTextField textField6;
		private JTextField textFieldID;
		private JLabel mensaje = new JLabel();
		private JButton actionButton = new JButton("");
		private JButton cancelButton = new JButton("Cancelar");
		private DB database;
		
		private int editionMode = 0;
		
		private boolean show = false;
	    
		//Constructor
		public Interfaz(){
			this.database = new DB();
			this.view = new JFrame("Base de Datos");
			
			jt=new JTable();   
		    jt.setBounds(10, 10, 1350, 790);
			
			dataPanel = new JPanel();
			dataPanel.setLayout(null);			
			dataPanel.setBounds(0,0,1400,800);
			
			tablePanel = new JPanel();
			tablePanel.setLayout(null);			
			tablePanel.setBounds(0,0,1400,800);
			
			buttonShow = new JButton("Mostrar Usuarios");
			buttonShow.setBounds(100, 900, 200, 50);
			
			buttonAdd = new JButton("Agregar Usuarios");
			buttonAdd.setBounds(400, 900, 200, 50);
			
			buttonDelete = new JButton("Eliminar Usuario");
			buttonDelete.setBounds(700, 900, 200, 50);
			
			buttonMod = new JButton("Modificar Usuario");
			buttonMod.setBounds(1000, 900, 200, 50);
			
			label1 = new JLabel("Nick");
			label1.setBounds(100, 50, 100, 50);
			dataPanel.add(label1);
			
			textField1 = new JTextField();  
			textField1.setBounds(100, 100, 1100, 50); 
			dataPanel.add(textField1);
			
			label2 = new JLabel("Email");
			label2.setBounds(100, 150, 100, 50);
			dataPanel.add(label2);
			
			textField2 = new JTextField();  
			textField2.setBounds(100, 200, 1100, 50); 
			dataPanel.add(textField2);
			
			label3 = new JLabel("Nombre");
			label3.setBounds(100, 250, 100, 50);
			dataPanel.add(label3);
			
			textField3 = new JTextField();  
			textField3.setBounds(100, 300, 1100, 50); 
			dataPanel.add(textField3);
			
			label4 = new JLabel("Apellido");
			label4.setBounds(100, 350, 100, 50);
			dataPanel.add(label4);
			
			textField4 = new JTextField();  
			textField4.setBounds(100, 400, 1100, 50); 
			dataPanel.add(textField4);
			
			label5 = new JLabel("Password");
			label5.setBounds(100, 450, 100, 50);
			dataPanel.add(label5);
			
			textField5 = new JTextField();  
			textField5.setBounds(100, 500, 1100, 50); 
			dataPanel.add(textField5);
			
			label6 = new JLabel("Pais");
			label6.setBounds(100, 550, 100, 50);
			dataPanel.add(label6);
			
			textField6 = new JTextField();  
			textField6.setBounds(100, 600, 1100, 50); 
			dataPanel.add(textField6);
			
			labelID = new JLabel("ID, solo para modificar o borrar");
			labelID.setBounds(100, 650, 300, 50);
			dataPanel.add(labelID);
			
			textFieldID = new JTextField();  
			textFieldID.setBounds(100, 700, 100, 50);
			
			mensaje.setBounds(1000,700,200,50);
			dataPanel.add(mensaje);
			actionButton.setBounds(1000,750,100,50);
			dataPanel.add(actionButton);
			cancelButton.setBounds(1100,750,100,50);
			dataPanel.add(cancelButton);
			
			
			
			
			
			dataPanel.add(textFieldID);
			
			tablePanel.add(jt);
			
			view.invalidate();
			view.validate();
			
			/*labelID.disable();
			textFieldID.disable();
			dataPanel.setVisible(false);
			tablePanel.setVisible(false);*/
			showTable();
			
			
			this.buttonShow.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	editionMode = 0;
		        	showTable();
					buttonAdd.setEnabled(true);
					buttonDelete.setEnabled(true);
					buttonMod.setEnabled(true);
		        	
		        }
		    });
			
			this.buttonAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editionMode = 1;
					tablePanel.setVisible(false);
					dataPanel.setVisible(true);
					enableControls();
					mensaje.setText("Desea Agregar Usuario ?");
					actionButton.setText("Si");
					textField1.setText( "" );
					textField2.setText( "" );
					textField3.setText( "" );
					textField4.setText( "" );
					textField5.setText( "" );
					textField6.setText( "" );
					textFieldID.setText( "" );
					
					buttonAdd.setEnabled(false);
					buttonDelete.setEnabled(false);
					buttonMod.setEnabled(false);
				}});
			
			this.buttonDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (jt.getSelectedRow() > -1) {
						editionMode = 2;
						tablePanel.setVisible(false);
						dataPanel.setVisible(true);
						disableControls();
						
						mensaje.setText("Esta seguro de eliminar ?");
						actionButton.setText("Si");
						
						textFieldID.setEnabled(false);
						buttonAdd.setEnabled(false);
						buttonDelete.setEnabled(false);
						buttonMod.setEnabled(false);
						
						textField1.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 1).toString() );
						textField2.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 2).toString() );
						textField3.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 3).toString() );
						textField4.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 4).toString() );
						textField5.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 5).toString() );
						textField6.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 6).toString() );
						textFieldID.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 0).toString() );
					} else {
						alerta("Debe seleccionar un usuario", false);
					}
				}
			});
			
			this.buttonMod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (jt.getSelectedRow() > -1) {
					editionMode = 3;
					tablePanel.setVisible(false);
					dataPanel.setVisible(true);
					enableControls();
					mensaje.setText("Esta seguro de modificar ?");
					actionButton.setText("Si");
					
					textFieldID.setEnabled(false);
					buttonAdd.setEnabled(false);
					buttonDelete.setEnabled(false);
					buttonMod.setEnabled(false);
					
					textField1.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 1).toString() );
					textField2.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 2).toString() );
					textField3.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 3).toString() );
					textField4.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 4).toString() );
					textField5.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 5).toString() );
					textField6.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 6).toString() );
					textFieldID.setText( jt.getModel().getValueAt(jt.getSelectedRow(), 0).toString() );
					} else {
						alerta("Debe seleccionar un usuario", false);
					}
					
				}});
			
			
			this.actionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (editionMode == 1) {						
						String nick = textField1.getText();
						String email = textField2.getText();
						String nombre = textField3.getText();
						String apellido = textField4.getText();
						String password = textField5.getText();
						String pais = textField6.getText();
						boolean agregar=true;
						if (nick.isEmpty()) { 
							agregar = false;
							alerta("No puede dejar el nickname en blanco", true);
						}
						if (email.isEmpty() && agregar) {
							agregar = false;
							alerta("no puede dejar el email en blanco", true);
						}
						if (nombre.isEmpty() && agregar) {
							agregar = false;
							alerta("no puede dejar el nombre en blanco", true);
						}
						if (apellido.isEmpty() && agregar) {
							agregar = false;
							alerta("no puede dejar el apellido en blanco", true);
						}
						if (password.isEmpty() && agregar) {
							agregar = false;
							alerta("no puede dejar el password en blanco", true);
						}
						if (pais.isEmpty() && agregar) {
							agregar = false;
							alerta("no puede dejar el pais en blanco", true);
						}
						
						if (agregar) {	
							database.addUser(nick, email, nombre, apellido, password, pais);
							editionMode = 0;
							showTable();
							buttonAdd.setEnabled(true);
							buttonDelete.setEnabled(true);
							buttonMod.setEnabled(true);
						}
						
					} else if (editionMode == 2) {
						String id = textFieldID.getText();
						database.deleteUser(id);
						editionMode = 0;
						showTable();
						buttonAdd.setEnabled(true);
						buttonDelete.setEnabled(true);
						buttonMod.setEnabled(true);
					} else if (editionMode == 3) {
						String nick = textField1.getText();
						String email = textField2.getText();
						String nombre = textField3.getText();
						String apellido = textField4.getText();
						String password = textField5.getText();
						String pais = textField6.getText();
						String id = textFieldID.getText();
						database.modUser(nick, email, nombre, apellido, password, pais, id);
						editionMode = 0;
						showTable();
						buttonAdd.setEnabled(true);
						buttonDelete.setEnabled(true);
						buttonMod.setEnabled(true);
					}				
					
				}
			});
			
			this.cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField1.setText( "" );
					textField2.setText( "" );
					textField3.setText( "" );
					textField4.setText( "" );
					textField5.setText( "" );
					textField6.setText( "" );
					textFieldID.setText( "" );
					editionMode = 0;
					showTable();
					buttonAdd.setEnabled(true);
					buttonDelete.setEnabled(true);
					buttonMod.setEnabled(true);
				}
			});
			
			view.add(dataPanel);

			view.add(tablePanel);
			
			view.add(buttonShow);
			
			view.add(buttonAdd);
			
			view.add(buttonDelete);
			
			view.add(buttonMod);
			
			view.setSize(1400, 1000);
			view.setLayout(null);
			view.setVisible(true);
		}
		
		void disableControls() {
			label1.setEnabled(false);
			textField1.setEnabled(false);
			label2.setEnabled(false);
			textField2.setEnabled(false);
			label3.setEnabled(false);
			textField3.setEnabled(false);
			label4.setEnabled(false);
			textField4.setEnabled(false);
			label5.setEnabled(false);
			textField5.setEnabled(false);
			label6.setEnabled(false);
			textField6.setEnabled(false);
			labelID.setEnabled(true);
			textFieldID.setEnabled(true);
		}
		
		void enableControls() {
			label1.setEnabled(true);
			textField1.setEnabled(true);
			label2.setEnabled(true);
			textField2.setEnabled(true);
			label3.setEnabled(true);
			textField3.setEnabled(true);
			label4.setEnabled(true);
			textField4.setEnabled(true);
			label5.setEnabled(true);
			textField5.setEnabled(true);
			label6.setEnabled(true);
			textField6.setEnabled(true);
			labelID.setEnabled(false);
			textFieldID.setEnabled(false);
		}
		
		void alerta( String msj, boolean modo ) {
			tablePanel.setVisible(false);
			dataPanel.setVisible(false);
			errorPanel = new JPanel();
			errorPanel.setLayout(null);			
			errorPanel.setBounds(0,0,1400,800);
			view.add(errorPanel);
			
			errorLabel = new JLabel(msj);
			errorLabel.setBounds(600, 350, 200, 50);
			
			buttonError = new JButton("Regresar a la pantalla anterior");
			buttonError.setBounds(600, 450, 200, 50);
			
			errorPanel.add(errorLabel);
			errorPanel.add(buttonError);
			errorPanel.setVisible(true);
			
			buttonShow.setEnabled(false);

			this.buttonError.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (modo) {
					tablePanel.setVisible(false);
					dataPanel.setVisible(true);
					errorPanel.setVisible(false);
					buttonShow.setEnabled(true);
					}
					else {
						tablePanel.setVisible(true);
						dataPanel.setVisible(false);
						errorPanel.setVisible(false);
						buttonShow.setEnabled(true);
					}
				}
				});
		}
		void showTable() {
			tablePanel.setVisible(true);
			dataPanel.setVisible(false);
			String[] columnNames = { "ID", "Nick", "Email", "Nombre", "Apellido", "Password", "Pais"};
		    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		    DB database = new DB();
		    
	    	ArrayList<Users> usuarios=database.getUsers();
	    	
	    	for (Users usuario: usuarios) {
	    		String[] data = { usuario.getId(), usuario.getNick(), usuario.getEmail(), usuario.getNombre(), usuario.getApellido(), usuario.getPassword(), usuario.getPais() } ;
	    		tableModel.addRow(data);
	    	}			
	
			jt.setModel(tableModel);
		}
		
	}

