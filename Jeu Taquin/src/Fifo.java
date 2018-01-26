import java.util.ArrayList;


public class Fifo implements Atraité {
	private Maillon tête ; // tête de file le prochain a sortir (principe FIFO)
	private Maillon queue ; // dernier maillon ajouté à la file
	/**
	 * Constructeur vide
	 */
	public Fifo (){
		tête = null;
		queue = null;
	}
	/**
	 * test si la file est vide
	 */
	public boolean estVide(){
		return (tête==null) ;
	}
	/**
	 * ajoute un maillon à la file
	 */
	public void ajouterMaillon(Taquin taquin){
		MaillonSimple aEnfiler = new MaillonSimple(taquin);
		if (this.estVide()){
			tête = aEnfiler;
			queue = aEnfiler;
		}else{
			aEnfiler.setSuivant(queue);
			queue = aEnfiler;
		}
	}
	/**
	 * ajoute un maillon a la file
	 */
	public void ajouterMaillon (Maillon aAjouter){
		if (this.estVide()){
			tête = aAjouter;
			queue = aAjouter;
		}else{
			aAjouter.setSuivant( queue);
			queue = aAjouter;
		}
	}
	/**
	 * retourne le dernier maillon ajouté à la structure sans le retirer de la structure
	 * il est logiquement la position de victoire dans notre utilisation 
	 */
	public Maillon positionSuivante () {
		Maillon suivant = this.queue ;
		return suivant;
	}
	/**
	 * retire le maillon a traité de la structure a traité et retourne ses successeurs sous forme de liste de maillon
	 */
	public ArrayList<Maillon> traiterMaillon (){
		if(this.estVide()){
			return null;
		}else{
			Maillon aTraiter;
			if (queue == tête){
				aTraiter = tête ;
				tête = null;
				queue = null;
			}else{
				Maillon pointeur= queue;
				while ((pointeur.getSuivant()!= null)&&(pointeur.getSuivant()!=tête)){
					pointeur = pointeur.getSuivant();
				}
				aTraiter = pointeur.getSuivant();
				pointeur.setSuivant(null);
				tête = pointeur;
			}	
			return aTraiter.successeurs();
			
		}
		
	}
}
