import java.io.*;

public class Carte {
    private String filePath;

    public Carte(String filePath) {
        this.filePath = filePath;// Boissons -> /Projet Restaurant/src/Boissons.txt
    }

    public void afficherCarteBoissons() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            int counter = 2;
            System.out.println("Ingrédients disponibles :");
            System.out.println("1. Bouteille d'eau - gratuite");        
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String ingredientName = parts[0].trim();
                System.out.print(counter + ". " + ingredientName);
                int price;
                if (ingredientName.equals("limonade")) { // Vérifiez le nom de l'ingrédient pour attribuer un prix
                    price = 4; 
                    System.out.println(" - " + price + "€");
                } else if (ingredientName.equals("cidre doux")) {
                    price = 5; 
                    System.out.println(" - " + price + "€");
                }else if (ingredientName.equals("biere sans alcool")) {
	                price = 5; 
	                System.out.println(" - " + price + "€");
	            }
                else if (ingredientName.equals("jus de fruit")) {
	                price = 1; 
	                System.out.println(" - " + price + "€");
	            }         
                counter++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur d'entrée/sortie : " + e.getMessage());
        }
    }
}
