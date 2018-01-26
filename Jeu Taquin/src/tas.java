import java.util.Comparator;
import java.util.*;
import java.util.PriorityQueue;
public class tas implements Atraité {
	private PriorityQueue<Maillon> fileAPriorité;
	/**
	 * Constructeur de tas
	 * @param fonctionOrdre la fonction utilisé pour donner un ordre de priorité de traitement entre les maillons
	 */
	public tas(Comparator<Maillon> fonctionOrdre){
		fileAPriorité = new PriorityQueue<Maillon>(fonctionOrdre);
		
	}
	/**
	 * ajoute une position du jeu de taquin à la structure
	 */
	public void ajouterMaillon(Taquin taquin){
		MaillonSimple ajouter  = new MaillonSimple (taquin);
		
		fileAPriorité.add(ajouter);
	}
	/**
	 * ajoute une position du jeu de taquin à la structure
	 */
	public void ajouterMaillon(Maillon aAjouter){	
		fileAPriorité.add(aAjouter);
	}
	/**
	 * test si la structure est vide
	 */
	public boolean estVide(){
		return fileAPriorité.size()==0 ;
	}
	/**
	 * retourne le dernier maillon ajouté à la structure sans le retirer de la structure
	 * il est logiquement quel soit la position de victoire dans notre utilisation 
	 * @Exception retour d'erreur en cas de position retourné qui ne soit pas la position de victoire
	 */
	public Maillon positionSuivante() {
		Maillon suivante =  fileAPriorité.peek();
		return suivante;
	}
	/**
	 * retire le maillon a traité de la structure a traité et retourne ses successeurs sous forme de liste de maillon
	 */
	public ArrayList<Maillon> traiterMaillon (){
		if (!this.estVide()){
			Maillon aTraiter = fileAPriorité.poll();;
			return aTraiter.successeurs();
		}else{
			return null;
		}
		
	}
}
