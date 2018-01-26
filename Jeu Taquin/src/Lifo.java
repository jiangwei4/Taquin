import java.util.ArrayList;


public abstract class Lifo implements Atrait� {
	private Maillon sommet; // dernier maillon ajout� et le prochain a �tre trait� (principe FIFO)
	public Maillon getSommet() {
		return sommet;
	}

	public void setSommet(Maillon sommet) {
		this.sommet = sommet;
	}

	/**
	 * Constructeur pile vide
	 */
	public Lifo (){
		sommet = null;
	}
	
	/**
	 * test si la pile est vide
	 */
	public boolean estVide (){
		return (sommet==null);
	}
	/**
	 * retourne le dernier maillon ajouter sans le retirer de la structure
	 * il est logiquement notre position de victoire dans notre utilisation
	 * on renvoie donc une Exception dans le cas o� ce n'est pas le cas
	 */
	public Maillon positionSuivante (){
		Maillon suivant = this.sommet;
		return suivant;
		
	}
	/**
	 * retire le maillon a trait� de la structure a trait� et retourne ses successeurs sous forme de liste de maillon
	 */
	public ArrayList<Maillon> traiterMaillon (){
		if (!this.estVide()){
			Maillon aTraiter = sommet;
			sommet = sommet.getSuivant();
			return aTraiter.successeurs();
		}else{
			return null;
		}
		
	}
	/**
	 * ajoute une position du jeu de taquin � la pile
	 */
	public void ajouterMaillon (Maillon aAjouter) {	
		if (this.estVide()){
			sommet = aAjouter;
		}else{
			aAjouter.setSuivant(sommet) ;
			sommet = aAjouter;
		}	
	}
}
