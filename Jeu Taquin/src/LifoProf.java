import java.util.*;
public class LifoProf extends Lifo {
	Comparator<Maillon> ordreDeTraitement;
	/**
	 * Constructeur pile pour parcourt en profondeur amélioré la chaîne de caractère passé en paramètre correspond 
	 * à la méthode de comparaison utilisé
	 */
	public LifoProf (String comparateur){
		super();
		switch (comparateur){
		case ("manhattan"):
			ordreDeTraitement = new ComparableManhattan();
			break;
		case ("choix"):
			ordreDeTraitement = new ComparableChoix();
			break;
		default:
			ordreDeTraitement = null;
		}
	}


	/**
	 * ajoute une position du jeu de taquin à sommet de la pile
	 */
	public void ajouterMaillon (Taquin taquin) {
		MaillonProf aEmpiler = new MaillonProf(taquin);
		if (this.estVide()){
			setSommet(aEmpiler) ;
		}else{
			aEmpiler.setSuivant(getSommet());
			setSommet(aEmpiler);
		}
			
	}
	public Comparator<Maillon> getOrdreDeTraitement() {
		return ordreDeTraitement;
	}


	public void setOrdreDeTraitement(Comparator<Maillon> ordreDeTraitement) {
		this.ordreDeTraitement = ordreDeTraitement;
	}


	/**
	 * retire le maillon a traité de la structure a traité et retourne ses successeurs sous forme de liste de maillon
	 */
	public ArrayList<Maillon> traiterMaillon (){
		ArrayList<Maillon>Successeurs = super.traiterMaillon();;
		if (ordreDeTraitement!=null){
			//Successeurs.
			Successeurs.sort(ordreDeTraitement);
			Collections.reverse(Successeurs);
		}	
		return Successeurs;
		
		
	}
}	
	