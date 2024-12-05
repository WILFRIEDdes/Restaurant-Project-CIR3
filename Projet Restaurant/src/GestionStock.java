import java.io.*;

public class GestionStock {

    private File file;

    public GestionStock(String filePath) {
        this.file = new File(filePath);
    }

    public void addIngredient(String ingredientName, int quantityToAdd) {
    	
        File tempFile = new File("tempIngredients.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean ingredientFound = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String name = parts[0].trim();

                if (name.equals(ingredientName)) {
                    int quantity = Integer.parseInt(parts[1].trim());
                    quantity += quantityToAdd;
                    line = name + ":" + quantity;
                    ingredientFound = true;
                }

                writer.write(line + System.getProperty("line.separator"));
            }

            if (!ingredientFound) {
                // Si l'ingrédient n'a pas été trouvé, ajouter une nouvelle entrée
                writer.write(ingredientName + ":" + quantityToAdd + System.getProperty("line.separator"));
            }

            reader.close();
            writer.close();

            // Supprimer le fichier original et renommer le fichier temporaire
            if (!file.delete()) {
                System.out.println("Impossible de supprimer le fichier original");
                return;
            }
            if (!tempFile.renameTo(file)) {
                System.out.println("Impossible de renommer le fichier temporaire");
            }

            System.out.println("La quantité de " + ingredientName + " a été modifiée de " + quantityToAdd);

        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur d'entrée/sortie : " + e.getMessage());
        }
    }


public void removeIngredient(String ingredientName, int quantityToRemove) {
    	
        File tempFile = new File("tempIngredients.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean ingredientFound = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String name = parts[0].trim();

                if (name.equals(ingredientName)) {
                    int quantity = Integer.parseInt(parts[1].trim());
                    if( quantityToRemove < quantity) {
	                    quantity -= quantityToRemove;
	                    line = name + ":" + quantity;
                    }
                    else { System.out.println("Impossible de retirer cette quantité, stock insuffisant");
                    }
                    ingredientFound = true;
                    
                }

                writer.write(line + System.getProperty("line.separator"));
            }

            if (!ingredientFound) {
            	System.out.println("L'ingrédient n'existe pas ou a mal été écrit");
            }

            reader.close();
            writer.close();

            // Supprimer le fichier original et renommer le fichier temporaire
            if (!file.delete()) {
                System.out.println("Impossible de supprimer le fichier original");
                return;
            }
            if (!tempFile.renameTo(file)) {
                System.out.println("Impossible de renommer le fichier temporaire");
            }

            System.out.println("La quantité de " + ingredientName + " a été modifiée de " + quantityToRemove);

        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur d'entrée/sortie : " + e.getMessage());
        }
    }
}
