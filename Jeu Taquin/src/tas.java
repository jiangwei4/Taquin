import java.util.Comparator;
import java.util.*;
import java.util.PriorityQueue;
public class tas implements AtraitÚ {
	private PriorityQueue<Maillon> fileAPrioritÚ;
	/**
	 * Constructeur de tas
	 * @param fonctionOrdre la fonction utilisÚ pour donner un ordre de prioritÚ de traitement entre les maillons
	 */
	public tas(Comparator<Maillon> fonctionOrdre){
		fileAPrioritÚ = new PriorityQueue<Maillon>(fonctionOrdre);
		
	}
	/**
	 * ajoute une position du jeu de taquin Ó la structure
	 */
	public void ajouterMaillon(Taquin taquin){
		MaillonSimple ajouter  = new MaillonSimple (taquin);
		
		fileAPrioritÚ.add(ajouter);
	}
	/**
	 * ajoute une position du jeu de taquin Ó la structure
	 */
	public void ajouterMaillon(Maillon aAjouter){	
		fileAPrioritÚ.add(aAjouter);
	}
	/**
	 * test si la structure est vide
	 */
	public boolean estVide(){
		return fileAPrioritÚ.size()==0 ;
	}
	/**
	 * retourne le dernier maillon ajoutÚ Ó la structure sans le retirer de la structure
	 * il est logiquement quel soit la position de victoire dans notre utilisation 
	 * @Exception retour d'erreur en cas de position retournÚ qui ne soit pas la position de victoire
	 */
	public Maillon positionSuivante() {
		Maillon suivante =  fileAPrioritÚ.peek();
		return suivante;
	}
	/**
	 * retire le maillon a traitÚ de la structure a traitÚ et retourne ses successeurs sous forme de liste de maillon
	 */
	public ArrayList<Maillon> traiterMaillon (){
		if (!this.estVide()){
			Maillon aTraiter = fileAPrioritÚ.poll();;
			return aTraiter.successeurs();
		}else{
			return null;
		}
		
	}
}
