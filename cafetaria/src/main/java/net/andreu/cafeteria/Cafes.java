package net.andreu.cafeteria;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: cafes
 *
 */
@Entity
@Table(name="cafes")
public class Cafes implements Serializable {

	@Id
	private int id;
	private String nom;
	private static final long serialVersionUID = 1L;

	public Cafes() {
		super();
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}
   
}
