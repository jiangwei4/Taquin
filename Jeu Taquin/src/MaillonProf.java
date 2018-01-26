import java.util.*;
public class MaillonProf extends Maillon {
	private String chemin; // séquence de caractère représentant le chemin parcouru par la case vide depuis la position initial
	public String getChemin() {
		return chemin;
	}
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	// pour atteindre la position du maillon (G = gauche , D = droite , H = haut , B = bas)
	/**
	 * Constructeur de maillon via une position de taquin (utilisé pour l'ajout de la position initial dans le graphe)
	 * @param taquin position du graphe a ajouter
	 */
	public MaillonProf (Taquin taquin){
		setPosition(taquin);
		chemin = "";
		setSuivant(null);
		setProfondeur (0);
	}
	/**
	 * Constructeur de maillonProf en se basant sur le déplacement effectué depuis le maillon prédécesseur
	 * @param déplacement mouvement de la case vide dans le jeu de taquin menant de la position du maillon père a la position du maillon
	 * @param père maillon représentant la position immédiatement précédente par laquelle on est arrivé pour atteindre cette position
	 */
	public MaillonProf (String déplacement,MaillonProf père ){
		this.setPosition (père.getPosition().clone());
		this.getPosition().permutation(déplacement);
		this.chemin = père.chemin+déplacement.charAt(0);
		this.setProfondeur (chemin.length());
		setSuivant(null);
	}
	/**
	 * retourne la solution sous la forme d'une liste de position du jeu de taquin
	 * @param initial position de départ du jeu de taquin (donnée par le fichier passé en paramètre)
	 * @return liste de position successive menant de la position initial à la solution
	 */
	public ArrayList<Taquin> solution(Taquin initial) throws Exception{
		if (!this.getPosition().testVictoire()){
			throw new Exception ("La solution ne commence par la position de victoire");
		}
		ArrayList<Taquin>solution = new ArrayList<Taquin>();
		solution.add(initial);
		String déplacement = this.chemin;
		Taquin enTraitement = initial.clone();
		for (int i=0 ; i<déplacement.length();i++){
			enTraitement= enTraitement.clone();
			enTraitement.permutation(chemin.charAt(i));
			solution.add(enTraitement);
		}
		return solution;
	}
	/**
	 * retourne les maillon représentant les positions accessibles en un déplacement depuis le maillon instancié 
	 */
	public ArrayList<Maillon> successeurs(){
		ArrayList<Maillon>result = new ArrayList<Maillon>();
		HashMap<String,Case>déplacements = this.getPosition().choixPossibles();
		Set<String> cle = déplacements.keySet();
		Iterator<String> it = cle.iterator();
		while (it.hasNext()){
			String déplacementEnTraitement = it.next();
			result.add(new MaillonProf(déplacementEnTraitement,this));
		}
		return result;
	}
}
