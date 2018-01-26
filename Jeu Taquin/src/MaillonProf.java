import java.util.*;
public class MaillonProf extends Maillon {
	private String chemin; // s�quence de caract�re repr�sentant le chemin parcouru par la case vide depuis la position initial
	public String getChemin() {
		return chemin;
	}
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	// pour atteindre la position du maillon (G = gauche , D = droite , H = haut , B = bas)
	/**
	 * Constructeur de maillon via une position de taquin (utilis� pour l'ajout de la position initial dans le graphe)
	 * @param taquin position du graphe a ajouter
	 */
	public MaillonProf (Taquin taquin){
		setPosition(taquin);
		chemin = "";
		setSuivant(null);
		setProfondeur (0);
	}
	/**
	 * Constructeur de maillonProf en se basant sur le d�placement effectu� depuis le maillon pr�d�cesseur
	 * @param d�placement mouvement de la case vide dans le jeu de taquin menant de la position du maillon p�re a la position du maillon
	 * @param p�re maillon repr�sentant la position imm�diatement pr�c�dente par laquelle on est arriv� pour atteindre cette position
	 */
	public MaillonProf (String d�placement,MaillonProf p�re ){
		this.setPosition (p�re.getPosition().clone());
		this.getPosition().permutation(d�placement);
		this.chemin = p�re.chemin+d�placement.charAt(0);
		this.setProfondeur (chemin.length());
		setSuivant(null);
	}
	/**
	 * retourne la solution sous la forme d'une liste de position du jeu de taquin
	 * @param initial position de d�part du jeu de taquin (donn�e par le fichier pass� en param�tre)
	 * @return liste de position successive menant de la position initial � la solution
	 */
	public ArrayList<Taquin> solution(Taquin initial) throws Exception{
		if (!this.getPosition().testVictoire()){
			throw new Exception ("La solution ne commence par la position de victoire");
		}
		ArrayList<Taquin>solution = new ArrayList<Taquin>();
		solution.add(initial);
		String d�placement = this.chemin;
		Taquin enTraitement = initial.clone();
		for (int i=0 ; i<d�placement.length();i++){
			enTraitement= enTraitement.clone();
			enTraitement.permutation(chemin.charAt(i));
			solution.add(enTraitement);
		}
		return solution;
	}
	/**
	 * retourne les maillon repr�sentant les positions accessibles en un d�placement depuis le maillon instanci� 
	 */
	public ArrayList<Maillon> successeurs(){
		ArrayList<Maillon>result = new ArrayList<Maillon>();
		HashMap<String,Case>d�placements = this.getPosition().choixPossibles();
		Set<String> cle = d�placements.keySet();
		Iterator<String> it = cle.iterator();
		while (it.hasNext()){
			String d�placementEnTraitement = it.next();
			result.add(new MaillonProf(d�placementEnTraitement,this));
		}
		return result;
	}
}
