import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Boisson implements Serializable{

	private static final long serialVersionUID = 5L; 
	protected String nom;
	protected double prix;
	protected int quantite;
	
	// Constructeur
	public Boisson(String nom, double prix, int quantite) {
		this.nom = nom;	
		this.prix = prix;
		this.quantite = quantite;
	}
		
	// Setters  -> pour modifier les attributs
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	// Getters
	public String getNom() {
		return nom;
	}
	public double getPrix() {
		return prix;
	}
	public int getQuantite() {
		return quantite;
	}

	public static void ajouterBoisson() throws IOException, ClassNotFoundException {
		// Demande d'ajout de boisson à l'utilisateur
		char rep = '\0';
		Scanner sc = new Scanner(System.in);
		while(rep != 'Y' && rep != 'N') {
			System.out.println("Ajouter une facture ? (Y/N)");
			rep = sc.next().toUpperCase().charAt(0);
	        sc.nextLine();
		}
		
		// Initialisation des variables de création de la facture
		String nom = null;
		String description = null;
		double prix = 0;
		char plus;

		// Liste de factures
		List<Facture> factures = new ArrayList<>();
		
		// Fichier où les factures sont stockées
		final String dataFile = "src\\factures";
		
		Facture facture1;
		
		// Lecture du fichier
		try (
			ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(
					new FileInputStream(dataFile)
				)
			)
		) {
			while(true) {
                Object obj = in.readObject();
                if (obj instanceof Facture) {
                	// On recréé chaque objet facture déjà existant pour les remettre dans la liste
					facture1 = (Facture) obj;
					factures.add(facture1);
					System.out.println("ok");
                }
                else if (obj instanceof String && ((String) obj).isEmpty()) {
					System.out.println("ok1");
                } else {
					System.out.println("ok2");
                }
			}
		}	
		catch(IOException e) {
            System.err.println("Erreur lors de la lecture dans le fichier : " + e.getMessage());
        }

		int nbFactures = factures.size();
		int nbAjout = 0;
		
		// Ajouter une facture
		if(rep == 'Y') {
			do {
				plus = '\0';
				Facture facture = new Facture();
				
				System.out.println("Nom de la facture ?");
				nom = sc.nextLine();
				
				facture.setNom(nom);
				
				System.out.println("Description de la facture ?");
				description = sc.nextLine();

				facture.setDescription(description);
				
				System.out.println("Prix de la facture ?");
				prix = sc.nextDouble();

				facture.setPrix(prix);
		        
				// Prix positif obligatoire
		        if(prix < 0) {
		        	do {
			        	System.out.println("Le prix ne peut pas être négatif. Veuillez entrer un prix positif.");
						prix = sc.nextDouble();
		        	} while (prix < 0);
		        }
		        sc.nextLine();
				
		        // Incrémentation du nombre de factures
				nbFactures += 1;
				nbAjout += 1;
				
				// Ecriture des nouvelles factures dans le fichier
				try (
					ObjectOutputStream out = new ObjectOutputStream(
						new BufferedOutputStream(
							new FileOutputStream(dataFile, true)
						)
					)
				) {
					out.writeObject(facture);
					out.writeObject("\n\n");
				}	
				catch(IOException e) {
		            System.err.println("Erreur lors de la lecture dans le fichier : " + e.getMessage());
		        }
				
				factures.add(facture);
				
				// Demande d'ajout de facture supplémentaires
				while(plus != 'Y' && plus != 'N') {
					System.out.println("Ajouter plus de factures ? (Y/N)");
					plus = sc.next().toUpperCase().charAt(0);
			        sc.nextLine();
				}
			} while(plus == 'Y');
			
			if(nbAjout == 1) {
				System.out.println("Vous avez ajouté " + nbAjout + " facture");
			}
			else {
				System.out.println("Vous avez ajouté " + nbAjout + " factures");
			}
		}
		
		// Affichage des factures dans le terminal
		if(nbFactures == 0) {
			System.out.println("Vous n'avez aucune facture");
		}
		if(nbFactures == 1) {
			System.out.println("Vous avez " + nbFactures + " facture :");
			for(int i = 0; i < nbFactures; i++) {
				Facture facture = factures.get(i);
				System.out.println("facture" + (i+1) + " : " + facture.getNom() + ", " + facture.getDescription() + ", " + facture.getPrix() + "€");
			}
		}
		if(nbFactures > 1) {
			System.out.println("Vous avez " + nbFactures + " factures :");
			for(int i = 0; i < nbFactures; i++) {
				Facture facture = factures.get(i);
				System.out.println("facture" + (i+1) + " : " + facture.getNom() + ", " + facture.getDescription() + ", " + facture.getPrix() + "€");
			}
		}
	}
}
