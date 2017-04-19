package net.andreu.cafeteria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comanda
 *
 */
@Entity
@Table(name="comanda")
public class Comanda implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToMany
	@JoinColumn(name="comanda_id")
	List<Linia> Linies;
	private static final long serialVersionUID = 1L;

	public Comanda() {
		super();
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public List<Linia> getLinies() {
		return Linies;
	}

	public void setLinies(List<Linia> linies) {
		Linies = linies;
	}
   
	
}
