package net.andreu.cafeteria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Linia
 *
 */
@Entity
@Table(name="linia")
public class Linia implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int linia_id;
	@ManyToOne
	@JoinColumn(name="cafe_id")
	private Cafes cafe;
	@ManyToMany
	@JoinTable(
	    name="linia_ingredients",
	    joinColumns=@JoinColumn(name="linia", referencedColumnName="linia_id"),
	    inverseJoinColumns=@JoinColumn(name="ingredients", referencedColumnName="id"))
	private List<Ingredients> ingredients;
	
	private static final long serialVersionUID = 1L;

	public Linia() {
		super();
	}   
	public int getLinia_id() {
		return this.linia_id;
	}

	public void setLinia_id(int linia_id) {
		this.linia_id = linia_id;
	}
   
}
