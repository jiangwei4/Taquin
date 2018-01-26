import java.util.ArrayList;


public class Fifo implements Atrait� {
	private Maillon t�te ; // t�te de file le prochain a sortir (principe FIFO)
	private Maillon queue ; // dernier maillon ajout� � la file
	/**
	 * Constructeur vide
	 */
	public Fifo (){
		t�te = null;
		queue = null;
	}
	/**
	 * test si la file est vide
	 */
	public boolean estVide(){
		return (t�te==null) ;
	}
	/**
	 * ajoute un maillon � la file
	 */
	public void ajouterMaillon(Taquin taquin){
		MaillonSimple aEnfiler = new MaillonSimple(taquin);
		if (this.estVide()){
			t�te = aEnfiler;
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
			t�te = aAjouter;
			queue = aAjouter;
		}else{
			aAjouter.setSuivant( queue);
			queue = aAjouter;
		}
	}
	/**
	 * retourne le dernier maillon ajout� � la structure sans le retirer de la structure
	 * il est logiquement la position de victoire dans notre utilisation 
	 */
	public Maillon positionSuivante () {
		Maillon suivant = this.queue ;
		return suivant;
	}
	/**
	 * retire le maillon a trait� de la structure a trait� et retourne ses successeurs sous forme de liste de maillon
	 */
	public ArrayList<Maillon> traiterMaillon (){
		if(this.estVide()){
			return null;
		}else{
			Maillon aTraiter;
			if (queue == t�te){
				aTraiter = t�te ;
				t�te = null;
				queue = null;
			}else{
				Maillon pointeur= queue;
				while ((pointeur.getSuivant()!= null)&&(pointeur.getSuivant()!=t�te)){
					pointeur = pointeur.getSuivant();
				}
				aTraiter = pointeur.getSuivant();
				pointeur.setSuivant(null);
				t�te = pointeur;
			}	
			return aTraiter.successeurs();
			
		}
		
	}
}
