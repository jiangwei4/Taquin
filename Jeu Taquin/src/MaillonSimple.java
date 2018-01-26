import java.util.*;
public class MaillonSimple extends Maillon {
	private MaillonSimple père; // maillon représentant la position du jeu par lequel on est arrivé pour atteindre le maillon
	public MaillonSimple getPère() {
		return père;
	}
	public void setPère(MaillonSimple père) {
		this.père = père;
	}
	/**
	 * Constructeur de maillon via une position de taquin (utilisé pour l'ajout de la position initial dans le graphe)
	 * @param taquin position du graphe a ajouter
	 */
	public MaillonSimple (Taquin taquin){
		setPosition(taquin);
		père = null;
		setSuivant(null);
		setProfondeur(0);
	}
	/**
	 * Constructeur de maillonSimple en se basant sur le déplacement effectué depuis le maillon prédécesseur
	 * @param déplacement mouvement de la case vide dans le jeu de taquin menant de la position du maillon père a la position du maillon
	 * @param père maillon représentant la position immédiatement précédente par laquelle on est arrivé pour atteindre cette position
	 */
	public MaillonSimple (String déplacement,MaillonSimple père ){
		this.setPosition (père.getPosition().clone());
		this.getPosition().permutation(déplacement);
		this.père = père;
		this.setProfondeur(père.getProfondeur()+1);
		setSuivant(null);
	}
	/**
	 * retourne la solution sous la forme d'une liste de position du jeu de taquin
	 * @return liste de position successive menant de la position initial à la solution
	 */
	public ArrayList<Taquin> solution () throws Exception{
		if (!this.getPosition().testVictoire()){
			throw new Exception ("La solution ne commence pas par la position de victoire");
		}
		MaillonSimple pointeur = this;
		ArrayList<Taquin> solution = new ArrayList<Taquin>();
		while (pointeur!=null){
			Taquin position = pointeur.getPosition();
			solution.add(0,position);
			pointeur = pointeur.père;
		}
		return solution;
	}
	/**
	 * retourne les maillon représentant les positions accessibles en un déplacement depuis le maillon instancié 
	 */
	public ArrayList<Maillon> successeurs(){
		ArrayList<Maillon>result = new ArrayList<Maillon>();
		HashMap<String,Case>déplacements = this.getPosition().choixPossibles();
		Set<String>cle = déplacements.keySet();
		Iterator<String> it = cle.iterator();
		while (it.hasNext()){
			String déplacementEnTraitement = it.next();
			result.add(new MaillonSimple(déplacementEnTraitement,this));
		}
		return result;
	}
	
}
