import java.util.ArrayList;
import java.util.List;

public class Plat {
		protected String nom;
		protected List<Ingredient> ingredients;
		protected int price;
		
		public Plat(String nom) {
			this.nom = nom;
			this.ingredients = new ArrayList<>();
		}
		
		//Ajouter un ingrédient à la composition du plat
		public void ajouterIngredient(Ingredient ingredient) {
	        ingredients.add(ingredient);
	    }
		
		//récupérer la liste des ingrédients du Plat
	    public List<Ingredient> getListeIngredients() {
	        return ingredients;					
	    }	    
	    
}




