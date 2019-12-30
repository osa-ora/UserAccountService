package osa.ora.beans;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class User implements Serializable{

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
	
	private long id;
	private String login;
	private String password;
	private String email;

	public User(){
		
	}
	
	public User(long id, String login, String password, String email) {
		super();
		this.id =id;
		this.login = login;
		this.password = password;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

        @Override
    public String toString() {
        return "Account {" + "id=" + id + ", login=" + getLogin() + 
                ", email=" + getEmail() + '}';
    }

}
