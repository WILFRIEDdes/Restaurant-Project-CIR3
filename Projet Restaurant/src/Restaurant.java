
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Restaurant {
    public static List<Employe> listeEmployes = new ArrayList<>();
	
	public static void afficherEmployés() {
		for (Employe e : Restaurant.listeEmployes) {
			System.out.println(e.getClass().getName() + ", " + e.getNom() + ", " + e.getPrenom() + ", " + e.getSalaire());
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, Exception {
		Facture.ajouterFactures();
		
		// Initialisation des variables d'ajout d'un employé
		int choix = 0;
		String nom = null;
		String prenom = null;
		double salaire = 0;
		
		System.out.println("\n\n");
		System.out.println("===============================================");
		System.out.println("---------- AJOUTER UN NOUVEL EMPLOYE ----------");
		System.out.println("-----------------------------------------------");
		System.out.println("1) Manager");
		System.out.println("2) Serveur");
		System.out.println("3) Cuisinier");
		System.out.println("4) Barman");
		System.out.println("-----------------------------------------------");
		Scanner sc = new Scanner(System.in);
		// l'utilisateur doit choisir parmi les 4 options
		while(choix != 1 && choix != 2 && choix != 3 && choix != 4) {
			choix = sc.nextInt();
	        sc.nextLine();
		}
        
		// Informations du nouvel employé
		System.out.println("Nom ?");
		nom = sc.nextLine();
		
		System.out.println("Prénom ?");
		prenom = sc.nextLine();
		
		System.out.println("Salaire ?");
		salaire = sc.nextDouble();
        
		// Salaire positif obligatoire
        if(salaire < 0) {
        	do {
	        	System.out.println("Le prix ne peut pas être négatif. Veuillez entrer un prix positif.");
				salaire = sc.nextDouble();
        	} while (salaire < 0);
        }
        sc.nextLine();
		
        // Création du nouvel employé en fonction des choix de l'utilisateur
		switch (choix) {
			case 1: 
				Employe.ajouterEmploye(Manager.class, nom, prenom, salaire);
				break;
			case 2:
				Employe.ajouterEmploye(Serveur.class, nom, prenom, salaire);
				break;
			case 3: 
				Employe.ajouterEmploye(Cuisinier.class, nom, prenom, salaire);
				break;
			case 4:
				Employe.ajouterEmploye(Barman.class, nom, prenom, salaire);
				break;
		}

		// Affichage de la liste des employés
		System.out.println("Liste des employés : ");
		afficherEmployés();
		
		sc.close();
		
		String filePath = "src\\Boissons.txt";

        Carte carte = new Carte(filePath);
        carte.afficherCarteBoissons();
        
        
	}
}
