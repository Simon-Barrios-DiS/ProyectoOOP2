package interfaz;

public class Users {
	private String _id;
	private String _nick;
    private String _email;
    private String _nombre;
    private String _apellido;
    private String _password;
    private String _pais;
    
    public Users(String id, String nick, String email, String nombre, String apellido, String password, String pais) {
    	this.setId(id);
    	this._nick = nick;
    	this._email = email;
    	this._nombre= nombre;
    	this._apellido= apellido;
    	this._password= password;
    	this._pais = pais;
    	}
    
    public Users() {}
    
	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String getNick() {
		return _nick;
	}
	
	public void setNick(String nick) {
		this._nick = nick;
	}
	
	public String getEmail() {
		return _email;
	}
	
	public void setEmail(String email) {
		this._email = email;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		this._nombre = nombre;
	}

	public String getApellido() {
		return _apellido;
	}

	public void setApellido(String apellido) {
		this._apellido = apellido;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		this._password = password;
	}

	public String getPais() {
		return _pais;
	}

	public void setPais(String pais) {
		this._pais = pais;
	}
    
    	
    
}
