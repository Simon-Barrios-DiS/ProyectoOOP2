package interfaz;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DB {
    private static DB DB = new DB();
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String driverDB = "org.postgresql.Driver";
    private String dbName = "Usuarios";
    private String urlDB = "jdbc:postgresql://localhost:5432/" + this.dbName;
    private String userDB = "postgres";
    private String passDB = "sazandora";


    DB(){
        try {
            Class.forName(driverDB);
            this.conn = DriverManager.getConnection(urlDB, userDB, passDB);
            System.out.println("Conexión establecida");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DB getInstances() {
        return DB;
    }

    public ResultSet dbStatement(String query) {
        try {
            this.stmt = this.conn.createStatement();
            this.rs = this.stmt.executeQuery(query);
            }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                this.stmt.close();
                this.rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
    
    public ArrayList<Users> getUsers() {
    	String query = "select * from users order by id;";
    	ArrayList<Users> result = new ArrayList();
        try {
            this.stmt = this.conn.createStatement();
            this.rs = this.stmt.executeQuery(query);
            
            while (rs.next()) {
            	String id = rs.getString("id");
			    String nick = rs.getString("nick");
			    String email = rs.getString("email");
			    String nombre = rs.getString("nombre");
			    String apellido = rs.getString("apellido");
			    String password = rs.getString("password");
			    String pais = rs.getString("pais");
			    
			    Users usuario = new Users(id,nick,email,nombre,apellido,password,pais);

			    result.add(usuario);
			}
            
            
            
            } catch (SQLException e) {
            	e.printStackTrace();
        } finally {
            try {
                this.stmt.close();
                this.rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public void addUser(String nick, String email, String nombre, String apellido, String password, String pais) {
    	String SQL = "insert into users(nick, email, nombre, apellido, password, pais)" + "values( ?, ?, ?, ?, ?, ?)";
    	try {
            this.pstmt = this.conn.prepareStatement(SQL);
            pstmt.setString(1, nick);
            pstmt.setString(2, email);
            pstmt.setString(3, nombre);
            pstmt.setString(4, apellido); 
            pstmt.setString(5, password);
            pstmt.setString(6, pais);
            this.pstmt.executeUpdate();
            //this.stmt.executeQuery(query);
            
            } catch (SQLException e) {
            	e.printStackTrace();
        } finally {
            try {
                this.pstmt.close();                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }      
    }
    
    public void deleteUser(String id) {
    	String SQL = "delete from users where id = ? ;";
    	
    	try {
    		this.pstmt = this.conn.prepareStatement(SQL);
    		
            this.stmt = this.conn.createStatement();
            pstmt.setInt(1, Integer.valueOf(id));
            this.pstmt.executeUpdate();
            
            } catch (SQLException e) {
            	e.printStackTrace();
        } finally {
            try {
                this.stmt.close();                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }        
    }
    
    public void modUser(String nick, String email, String nombre, String apellido, String password, String pais, String id) {
    	String SQL = "Update users" + " SET nick = ?" + ", email = ?" + ", nombre = ?" + ", apellido = ?" + ", password = ?" + ", pais = ?" + " where id = ? " + ";";
    	try {
            this.pstmt = this.conn.prepareStatement(SQL);
            pstmt.setString(1, nick);
            pstmt.setString(2, email);
            pstmt.setString(3, nombre);
            pstmt.setString(4, apellido); 
            pstmt.setString(5, password);
            pstmt.setString(6, pais);            
            pstmt.setInt(7, Integer.valueOf(id));
            //System.out.println("query " + pstmt.toString());
            this.pstmt.executeUpdate();
            //this.stmt.executeQuery(query);
            
            } catch (SQLException e) {
            	e.printStackTrace();
        } finally {
            try {
                this.pstmt.close();                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }      
    }
   

    public void dbClose() {
        try {
            this.conn.close();
            System.out.println("Conexion cerrada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}