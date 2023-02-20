package com.eduit.bootcamp.springbootapi.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = RoleEntity.TABLE_NAME)
/**
 * This class is mean to represent the database {@link ProductEntity.TABLE_NAME} table.
 * 
 * @author Federico Vittorini.
 *
 */
public class RoleEntity {

	public static final String TABLE_NAME = "roles";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**
	 * This is the entity id.
	 * If this attribute is null, that means the entity is not persisted yet.
	 */
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column( nullable = true)
	private Integer level;
	
	@Column(name = "date_created", nullable = false)
	private Date dateCreated;
	
	@Column(name = "date_deleted", nullable = true)
	private Date dateDeleted;
	
	/**
	 * This is the default empty class constructor required by Hibernate. 
	 */
	public RoleEntity() {}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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

	
}
