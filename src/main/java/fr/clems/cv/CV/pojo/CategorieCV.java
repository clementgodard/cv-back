package fr.clems.cv.CV.pojo;

import java.util.ArrayList;
import java.util.List;

import fr.clems.cv.CV.entity.Categorie;
import fr.clems.cv.CV.entity.Ligne;

public class CategorieCV {
	private Long id;
	private String libelle;
	private int position;
	private boolean active;
	private List<Ligne> lignes;
	public List<CategorieCV> enfants;
	private boolean imageCategorie;
	
	public CategorieCV(Categorie c) {
		this.setId(c.getId());
		this.setLibelle(c.getLibelle());
		this.setPosition(c.getPosition());
		this.setActive(c.isActive());
		this.setLignes(c.getLignes());
		this.setImageCategorie(c.isImageCategorie());
		
		this.enfants = new ArrayList<CategorieCV>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Ligne> getLignes() {
		return lignes;
	}

	public void setLignes(List<Ligne> lignes) {
		this.lignes = lignes;
	}
	
	public boolean isImageCategorie() {
		return imageCategorie;
	}

	public void setImageCategorie(boolean imageCategorie) {
		this.imageCategorie = imageCategorie;
	}

	public boolean equals(Categorie cat) {
		return this.getId() == cat.getId();
	}

}
