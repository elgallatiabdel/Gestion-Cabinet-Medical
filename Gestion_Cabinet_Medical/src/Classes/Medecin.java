package Classes;

import java.io.Serializable;

public class Medecin implements Serializable {
	private static final long serialVersionUID = 3L;

  private long id;
	private int version;
	private String titre;
	private String nom;
	private String prenom;
	
	public Medecin(long id,int v, String t, String n, String p){
    this.setId(id);
    this.setVersion(v);
		this.setTitre(t);
		this.setNom(n);
		this.setPrenom(p);
	}

  public Medecin() {}

  public void setId(long id) {
		this.id = id;
	}
  public long getId() {return id;}

	public int getVersion() {return version;}
	public void setVersion(int version) {this.version = version;}

	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}

	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}

	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
}
