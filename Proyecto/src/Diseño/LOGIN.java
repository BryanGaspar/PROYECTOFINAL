package Diseño;

import java.awt.BorderLayout; 
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Clases.administradores;
import Conexiones.crudsqlEst;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LOGIN extends JFrame {

	private JPanel contentPane;
	private JTextField txtuser;
	private JTextField txtpassword;
	private JProgressBar progressBar;
	crudsqlEst objcrud = new crudsqlEst();//crud
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGIN frame = new LOGIN();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LOGIN() {
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 403);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN-INICIO DE SESION");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(242, 33, 343, 78);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USER:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(193, 136, 56, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2.setBounds(150, 183, 94, 25);
		contentPane.add(lblNewLabel_2);
		
		txtuser = new JTextField();
		txtuser.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtuser.setBounds(268, 137, 276, 20);
		contentPane.add(txtuser);
		txtuser.setColumns(10);
		
		txtpassword = new JTextField();
		txtpassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtpassword.setBounds(265, 186, 279, 20);
		contentPane.add(txtpassword);
		txtpassword.setColumns(10);
		
		JButton btnIngresar = new JButton("SING-IN");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administradores mod = new administradores();
				String usuario, passw;
				MENU men = new MENU();
				usuario = txtuser.getText();
				passw = txtpassword.getText();
				if(usuario.isEmpty() || passw.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Error, algun campo esta vacio");
				} else {
					mod.setAdmin(usuario);
					mod.setPass(passw);
					if(objcrud.login(mod)) {
						Thread hilo = new Thread() {
							@Override
							public void run() {
								for(int i=1; i<=100;i++) {
									 try {
										 progressBar.setVisible(true);
										Thread.sleep(10);
										progressBar.setValue(i);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									 
								 }
								JOptionPane.showMessageDialog(null,"Bienvenido" + " " + usuario.toUpperCase());
								men.setExtendedState(MAXIMIZED_BOTH);
								
								men.setVisible(true);
								dispose();
							}
						};
						hilo.start();
					}else {
						JOptionPane.showMessageDialog(null,"Su usario o contraseña es incorrecta");
					}
					
				}
			}
		});
		btnIngresar.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnIngresar.setBounds(268, 238, 267, 46);
		contentPane.add(btnIngresar);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(193, 314, 431, 20);
		contentPane.add(progressBar);
	}
}
