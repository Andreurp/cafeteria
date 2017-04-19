package net.andreu.cafeteria;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ingredients
 *
 */
@Entity
@Table(name="ingredients")
public class Ingredients implements Serializable {

	@Id
	private int id;
	private String nom;
	private int preu;
	private static final long serialVersionUID = 1L;

	public Ingredients() {
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
	
	public int getPreu() {
		return this.preu;
	}
	public void setPreu(int preu) {
		this.preu = preu;
	}

	@Override
	public String toString() {
		return nom;
	}
   
}
