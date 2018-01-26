import java.util.Comparator;
import java.util.*;
import java.util.PriorityQueue;
public class tas implements Atrait� {
	private PriorityQueue<Maillon> fileAPriorit�;
	/**
	 * Constructeur de tas
	 * @param fonctionOrdre la fonction utilis� pour donner un ordre de priorit� de traitement entre les maillons
	 */
	public tas(Comparator<Maillon> fonctionOrdre){
		fileAPriorit� = new PriorityQueue<Maillon>(fonctionOrdre);
		
	}
	/**
	 * ajoute une position du jeu de taquin � la structure
	 */
	public void ajouterMaillon(Taquin taquin){
		MaillonSimple ajouter  = new MaillonSimple (taquin);
		
		fileAPriorit�.add(ajouter);
	}
	/**
	 * ajoute une position du jeu de taquin � la structure
	 */
	public void ajouterMaillon(Maillon aAjouter){	
		fileAPriorit�.add(aAjouter);
	}
	/**
	 * test si la structure est vide
	 */
	public boolean estVide(){
		return fileAPriorit�.size()==0 ;
	}
	/**
	 * retourne le dernier maillon ajout� � la structure sans le retirer de la structure
	 * il est logiquement quel soit la position de victoire dans notre utilisation 
	 * @Exception retour d'erreur en cas de position retourn� qui ne soit pas la position de victoire
	 */
	public Maillon positionSuivante() {
		Maillon suivante =  fileAPriorit�.peek();
		return suivante;
	}
	/**
	 * retire le maillon a trait� de la structure a trait� et retourne ses successeurs sous forme de liste de maillon
	 */
	public ArrayList<Maillon> traiterMaillon (){
		if (!this.estVide()){
			Maillon aTraiter = fileAPriorit�.poll();;
			return aTraiter.successeurs();
		}else{
			return null;
		}
		
	}
}
