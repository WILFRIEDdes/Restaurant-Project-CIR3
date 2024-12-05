
public abstract class Employe {
	protected String nom;
	protected String prenom;
	protected double salaire;
	protected int joursConsecutifs;
	
	// Constructeur
	public Employe(String nom, String prenom, double salaire) {
		this.nom = nom;
		this.prenom = prenom;
		this.salaire = salaire;
		this.joursConsecutifs = 0;
	}
	
	// Setters
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}
	public void setJours(int joursConsecutifs) {
		this.joursConsecutifs = joursConsecutifs;
	}
	
	// Getters
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public double getSalaire() {
		return salaire;
	}
	
	// Ajout d'un nouvel employé avec Generics
	public static <T extends Employe> void ajouterEmploye(Class<T> typeEmploye, String nom, String prenom, double salaire) throws Exception{
		T newEmploye = typeEmploye.getDeclaredConstructor(String.class, String.class, double.class).newInstance(nom, prenom, salaire);
			
		Restaurant.listeEmployes.add(newEmploye);
	}
	// Suppression d'un employé
	public static void retirer(Employe employe) {
		Restaurant.listeEmployes.remove(employe);
	}
}
