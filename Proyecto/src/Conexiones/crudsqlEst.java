package Conexiones;
import java.sql.*; 
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import Clases.Estudiante;
import Clases.administradores;

public class crudsqlEst extends Conexion {
	public boolean registrarEst(Estudiante est) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		String sql = "INSERT INTO estudiante (Cedula,Nombres,Apellidos,Direccion,Email,Telefono,Genero,FechaNac) VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, est.getCedula());
			ps.setString(2, est.getNombres());
			ps.setString(3, est.getApellidos());
			ps.setString(4, est.getDireccion());
			ps.setString(5, est.getEmail());
			ps.setInt(6, est.getTelefono());
			ps.setString(7, est.getGenero());
			ps.setString(8, est.getFechaNac());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
				
			}catch (SQLException e) {
				System.err.println(e);
			}
		}	
	}
	
	
	public boolean modificarEst(Estudiante est) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		String sql = "UPDATE estudiante SET Cedula=?,Nombres=?,Apellidos=?,Direccion=?,Email=?,Telefono=?,Genero=?,FechaNac=?"
				+ "WHERE Id=?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, est.getCedula());
			ps.setString(2, est.getNombres());
			ps.setString(3, est.getApellidos());
			ps.setString(4, est.getDireccion());
			ps.setString(5, est.getEmail());
			ps.setInt(6, est.getTelefono());
			ps.setString(7, est.getGenero());
			ps.setString(8, est.getFechaNac());
			ps.setInt(9, est.getId());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
				
			}catch (SQLException e) {
				System.err.println(e);
			}
		}	
	}
 
	public boolean eliminarEst(Estudiante est) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		String sql = "DELETE FROM estudiante WHERE Id=?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, est.getId());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
				
			}catch (SQLException e) {
				System.err.println(e);
			}
		}	
	}

	public DefaultTableModel buscarEst(Estudiante est, int aux) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		DefaultTableModel model = null;
	
		
		try {
		
			
			model = new DefaultTableModel(){
    			//bloquear columnas
    			public boolean isCellEditable(int filas, int col) {
    				if((col == 0) || (col == 1) || (col == 2) || (col == 3) || (col == 4) || (col == 5)) {
    					return false;
    				}
    				else {
    					return true;
    				}
    				
    			}};
    			model.addColumn("Nº");
    			model.addColumn("cedula");
    			model.addColumn("nombre");
    			model.addColumn("apellido");
    			model.addColumn("direccion");
    			model.addColumn("correo");
    			model.addColumn("telefono");
    			model.addColumn("genero");
    			model.addColumn("Fecha_de_nacimiento");
    	
    			if (aux==0)
				{
    				ps=con.prepareStatement("SELECT * FROM estudiante WHERE Cedula=?");
					ps.setInt(1, est.getCedula());	
					}
				
			
    			rs = ps.executeQuery();
          
           

            ResultSetMetaData rstabla = (ResultSetMetaData) rs.getMetaData();
			int numCampos = rstabla.getColumnCount();
            while(rs.next())
            {
     
            	Object [] filas = new Object[numCampos];
				for (int iterator = 0; iterator<numCampos; iterator++) {
					filas[iterator] = rs.getObject(iterator+1);	
				}
				model.addRow(filas);
				
			}
		} catch (SQLException e) {
			System.err.println(e);
			
		} finally {
			try {
				con.close();
				
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
		return model;	
	}
	public DefaultTableModel mostrarEstudiantes() {
		
        
        DefaultTableModel model = null;
        
        String sql = "SELECT * FROM estudiante";
        
        
        PreparedStatement pst = null;
        
        ResultSet rs = null;
        Connection cn = getConexion();

        try
        {
        	model = new DefaultTableModel(){
    			//bloquear columnas
    			public boolean isCellEditable(int filas, int col) {
    				if((col == 0) || (col == 1) || (col == 2) || (col == 3) || (col == 4) || (col == 5) || (col == 6) || (col == 7) || (col == 8)) {
    					return false;
    				}
    				else {
    					return true;
    				}
    				
    			}};
    			model.addColumn("Nº");
    			model.addColumn("cedula");
    			model.addColumn("nombre");
    			model.addColumn("apellido");
    			model.addColumn("direccion");
    			model.addColumn("correo");
    			model.addColumn("telefono");
    			model.addColumn("genero");
    			model.addColumn("Fecha_de_nacimiento");
    	
    			
            pst = cn.prepareStatement(sql);                        
            
            rs = pst.executeQuery();
           

            ResultSetMetaData rstabla = (ResultSetMetaData) rs.getMetaData();
			int numCampos = rstabla.getColumnCount();
            while(rs.next())
            {
     
            	Object [] filas = new Object[numCampos];
				for (int iterator = 0; iterator<numCampos; iterator++) {
					filas[iterator] = rs.getObject(iterator+1);	
				}
				model.addRow(filas);
				
			}
                
            
            
           
        }
        catch(SQLException e)
        {
            
            JOptionPane.showMessageDialog(null,"Error al conectar");
            
        }
        finally
        {
            try
            {
                if (rs != null) rs.close();
                
                if (pst != null) pst.close();
                
                if (cn != null) cn.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
         return model;
	}
	
	public boolean login(administradores usr) {
		String sql = "SELECT Usuario, Pass FROM administrador Where Usuario = ?";
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		try
        {
			
			ps = con.prepareStatement(sql);                        
			ps.setString(1, usr.getAdmin());
            rs = ps.executeQuery();
            while(rs.next())
            {
            	if(usr.getPass().equals(rs.getString(2))) {
            		usr.setAdmin(rs.getString(1));
            		usr.setPass(rs.getString(2));
            		return true;
            	}
            	else{
            	return false;
            	}
            }
            return false;
        }
        catch(SQLException e)
        {
            
            JOptionPane.showMessageDialog(null,"Error al conectar");
            return false;
            
        }
        finally
        {
            try
            {
                if (rs != null) rs.close();
                
                if (ps != null) ps.close();
                
                if (con != null) con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }   
		
	}
	
}
