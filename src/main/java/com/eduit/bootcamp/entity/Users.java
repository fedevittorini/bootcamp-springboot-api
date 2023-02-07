package com.eduit.bootcamp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = Users.TABLE_NAME)
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "users";
	
	public static final String ID_FIELD = "id";
	public static final String USERNAME_FIELD = "username";
	public static final String FIRST_NAME_FIELD = "first_name";
	public static final String LAST_NAME_FIELD = "last_name";
	public static final String PASSWORD_FIELD = "password";
	public static final String DATE_CREATED_FIELD = "date_created";
	public static final String DATE_DELETED_FIELD = "date_deleted";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = USERNAME_FIELD, nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
    private String firstName;
	@Column(nullable = false)
    private String lastName;
	@Column(nullable = false)
    private String password;
	@Column(nullable = false)
    private Date dateCreated;
	@Column(nullable = true)
    private Date dateDeleted;
    
    public Users() {
    	
    }
    
	public Users(final Long theId, final String theUsername, final String theFirstName,
			final String theLastName, final String thePassword, final Date theDateCreated, 
			final Date theDateDeleted) {
		this.id = theId;
		this.username = theUsername;
		this.firstName = theFirstName;
		this.lastName = theLastName;
		this.password = thePassword;
		this.dateCreated = theDateCreated;
		this.dateDeleted = theDateDeleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateDeleted() {
		return dateDeleted;
	}

	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", dateCreated=" + dateCreated + ", dateDeleted=" + dateDeleted + "]";
	}

	
}
