import java.util.*;
public interface Atraité {
	public void ajouterMaillon(Taquin taquin); // ajoute un maillon de position Taquin a la structure de donnée
	public void ajouterMaillon(Maillon aAjouter ); // ajoute un maillon a la structure
	public boolean estVide(); // test si la structure de donnée est vide
	public Maillon positionSuivante() ; // récupére la position de victoire sans la retiré
	public ArrayList<Maillon> traiterMaillon(); // retire le maillon a traité de la structure a traité et retourne ses successeurs
												// sous la forme d'une liste de maillon
}
