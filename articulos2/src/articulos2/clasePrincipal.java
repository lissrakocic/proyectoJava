
package articulos2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;




public class clasePrincipal extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf2;
	private JLabel labelResultado;
	private JButton btnConsultaPorCodigo;
	private JLabel lblIngreseCodigoDe;
	private JTextField tf3;
	//variables de clase de formulario
	private JTextField tf11;
	private JTextField tf22;
	private JLabel labelResultado2;
	private JButton btnConsultaPorCodigo2;
	private JTextField tf33;
	


	/**
	 * Create the frame
	 * 
	 */
	
	public clasePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,606,600); //posicion del frame
		contentPane = new JPanel(); //para introducir los elementos
		//caracteristicas del jpanel, bordes 
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescripcionDelArticulo = new JLabel("Descripcion del articulo");
		lblDescripcionDelArticulo.setBounds(23, 38, 193, 14);
		contentPane.add(lblDescripcionDelArticulo);
		tf1 = new JTextField();
		tf1.setBounds(247, 35, 193, 20);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(23, 74, 95, 14);
		contentPane.add(lblPrecio);
		
		tf2= new JTextField();
		tf2.setBounds(247, 71, 107, 20);
		contentPane.add(tf2);
		tf2.setColumns(10);
		
		JButton btnAlta = new JButton("Alta");
		//conectamos el boton al evento
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				labelResultado.setText("");
				//try para conectar la bd, localhost el nombre de nuestra bd db1 parametros usuario contraseña use unicode jdbc compleance propiedades que debe tener 
				try {  
					Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/db1?" + "user=root&password=Pollito2015&useUnicode=true&useJDBCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
					Statement comando=conexion.createStatement();
					//accede a la consulta sql 
					comando.executeUpdate("insert into articulos(descripcion,precio) values ('"+tf1.getText()+"',"+tf2.getText()+")");
					conexion.close();
					labelResultado.setText("se registraron los datos");
					//una vez insertado inicializamos los atributos
					tf1.setText("");
					tf2.setText("");
					
				} catch(SQLException ex) {
					setTitle(ex.toString());
				}
			}
			});
		
			btnAlta.setBounds(247,118,89,23);
			contentPane.add(btnAlta);
		
			labelResultado = new JLabel("Resultado");
			labelResultado.setBounds(361, 123, 229, 14);
			contentPane.add(labelResultado);
			btnConsultaPorCodigo = new JButton("Consulta por codigo");
			btnConsultaPorCodigo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					labelResultado.setText("");
					tf1.setText("");
					tf2.setText("");
					try {  
						Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/db1?" + "user=root&password=Pollito2015&useUnicode=true&useJDBCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
						Statement comando=conexion.createStatement();
						//accede a la consulta sql 
						//utlizamos select para consultar por descirpcion o por precio
						//en gettext lo almancenamos
						ResultSet registro = comando.executeQuery("select descripcion,precio from articulos where codigo="+tf3.getText());
						//si existe obtenemos en la tabla y lo guardamos
						if (registro.next()==true) {
							tf1.setText(registro.getString("descripcion"));
							tf2.setText(registro.getString("precio"));
						} else {
							//si no existe que nos avisa con un msj
							labelResultado.setText("No existe un articulo con dicho cdigo");
						}
						conexion.close();
					} catch(SQLException ex) {
						setTitle(ex.toString());
					}
				}
			});
			
			btnConsultaPorCodigo.setBounds(23, 212, 177, 23);
			contentPane.add(btnConsultaPorCodigo);
			
			lblIngreseCodigoDe = new JLabel("Ingrese codigo de articulo a consultar:");
			lblIngreseCodigoDe.setBounds(23, 179, 243, 14);
			contentPane.add(lblIngreseCodigoDe);
			
			tf3 = new JTextField();
			tf3.setBounds(247, 176, 86, 20);
			contentPane.add(tf3);
			tf3.setColumns(10);
			
			/* BLOQUE DE CLASE FORMULARIO INTERFACE*/
			JLabel lblDescripcionDelArticulo2 = new JLabel("Descripcion del articulo:");
			lblDescripcionDelArticulo2.setBounds(23, 300, 193, 14);
			contentPane.add(lblDescripcionDelArticulo2);
			
			tf11 = new JTextField();
			tf11.setBounds(247, 297, 193, 20);
			contentPane.add(tf11);
			tf11.setColumns(10);
			
			JLabel lblPrecio2 = new JLabel("Precio");
			lblPrecio2.setBounds(23, 336, 96, 14);
			contentPane.add(lblPrecio2);
			
			tf22 = new JTextField();
			tf22.setBounds(247, 333, 107, 20);
			contentPane.add(tf22);
			tf22.setColumns(10);
			
			labelResultado2 = new JLabel("Resultado");
			labelResultado2.setBounds(361, 374, 229, 14);
			contentPane.add(labelResultado2);
			
			btnConsultaPorCodigo2 = new JButton("Consulta por codigo");
			btnConsultaPorCodigo2.addActionListener(new ActionListener () {
				public void actionPerformed(ActionEvent arg0) {
					labelResultado2.setText("");
					tf11.setText("");
					tf22.setText("");
					try {  
						Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/db1?" + "user=root&password=Pollito2015&useUnicode=true&useJDBCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
						Statement comando=conexion.createStatement();
						//accede a la consulta sql 
						//utlizamos select para consultar por descirpcion o por precio
						//en gettext lo almancenamos
						ResultSet registro = comando.executeQuery("select descripcion,precio from articulos where codigo="+tf33.getText());
						//si existe obtenemos en la tabla y lo guardamos
						if (registro.next()==true) {
							tf11.setText(registro.getString("descripcion"));
							tf22.setText(registro.getString("precio"));
						} else {
							//si no existe que nos avisa con un msj
							labelResultado2.setText("No existe un articulo con dicho cdigo");
						}
						conexion.close();
					} catch(SQLException ex) {
						setTitle(ex.toString());
					}
				}
			});
			
			btnConsultaPorCodigo2.setBounds(25, 374, 177, 23);
			contentPane.add(btnConsultaPorCodigo2);
			tf33 = new JTextField();
			tf33.setBounds(247, 375, 86, 20);
			contentPane.add(tf33);
			tf33.setColumns(10);
			
			JButton btnNewButton2 = new JButton("Borrar");
			btnNewButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					labelResultado2.setText("");
					try {  
						Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/db1?" + "user=root&password=Pollito2015&useUnicode=true&useJDBCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
						Statement comando=conexion.createStatement();
						//accede a la consulta sql 
						//utlizamos select para consultar por descirpcion o por precio
						//en gettext lo almancenamos
						int cantidad = comando.executeUpdate("delete from articulos where codigo="+tf33.getText());
						//si existe obtenemos en la tabla y lo guardamos
						//si hay alguna cantidad es que hay elemento
						if (cantidad==1) {
							//borramos el contendio por si por casualidad tuvieramos algo almacneando de la anterior consulta
							tf11.setText("");
							tf22.setText("");
							labelResultado2.setText("Se borro el articulo con dicho codigo");
						} else {
							//si no existe que nos avisa con un msj
							labelResultado2.setText("No existe un articulo con dicho codigo");
						}
						conexion.close();
					} catch(SQLException ex) {
						setTitle(ex.toString());
					}
				}
			});
			btnNewButton2.setBounds(24, 408, 177, 23);
			contentPane.add(btnNewButton2);
			JButton btnNewButton_2 = new JButton("Modificar");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					labelResultado2.setText("");
					try {  
						Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/db1?" + "user=root&password=Pollito2015&useUnicode=true&useJDBCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
						Statement comando=conexion.createStatement();
						//accede a la consulta sql 
						//utlizamos select para consultar por descirpcion o por precio
						//en gettext lo almancenamos
						int cantidad = comando.executeUpdate("update articulos set descripcion='" +tf11.getText() + "'," + "precio=" + tf22.getText() + " where codigo="+tf33.getText());
						//si existe obtenemos en la tabla y lo guardamos
						//si hay alguna cantidad es que hay elemento por lo tanto podemos modificar
						if (cantidad==1) {	
							labelResultado2.setText("Se modifico la descripcion y el precio del articulo");
						} else {
							//si no existe que nos avisa con un msj
							labelResultado2.setText("No existe un articulo con dicho cdigo");
						}
						conexion.close();
					} catch(SQLException ex) {
						setTitle(ex.toString());
					}
					
				}
			});
			btnNewButton_2.setBounds(21, 441, 179, 23);
			contentPane.add(btnNewButton_2);
			
			cargarDriver();
		}	
	
	public void paint(Graphics g) {
		//interfaz grafica, faremos los recuadros para separar
		super.paint(g);
		g.setColor(Color.blue);
		g.drawRoundRect(15, 50, 500, 140, 50, 50);
		g.drawRoundRect(15, 200, 500, 90, 50, 50);
		g.drawRoundRect(15, 300, 500, 210, 50, 50);
	}
	//carga el driver que hemos instalado en nuestro proyecto
	private void cargarDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Exception ex) {
			setTitle(ex.toString());
		}
	}


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//no aseguramos que la invocacion es correcta
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//objeto de clase
					clasePrincipal frame = new clasePrincipal();
					frame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


	
	


