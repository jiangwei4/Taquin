import java.util.*;
public interface Atrait� {
	public void ajouterMaillon(Taquin taquin); // ajoute un maillon de position Taquin a la structure de donn�e
	public void ajouterMaillon(Maillon aAjouter ); // ajoute un maillon a la structure
	public boolean estVide(); // test si la structure de donn�e est vide
	public Maillon positionSuivante() ; // r�cup�re la position de victoire sans la retir�
	public ArrayList<Maillon> traiterMaillon(); // retire le maillon a trait� de la structure a trait� et retourne ses successeurs
												// sous la forme d'une liste de maillon
}
