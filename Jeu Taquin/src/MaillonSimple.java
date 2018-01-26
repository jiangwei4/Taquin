import java.util.*;
public class MaillonSimple extends Maillon {
	private MaillonSimple p�re; // maillon repr�sentant la position du jeu par lequel on est arriv� pour atteindre le maillon
	public MaillonSimple getP�re() {
		return p�re;
	}
	public void setP�re(MaillonSimple p�re) {
		this.p�re = p�re;
	}
	/**
	 * Constructeur de maillon via une position de taquin (utilis� pour l'ajout de la position initial dans le graphe)
	 * @param taquin position du graphe a ajouter
	 */
	public MaillonSimple (Taquin taquin){
		setPosition(taquin);
		p�re = null;
		setSuivant(null);
		setProfondeur(0);
	}
	/**
	 * Constructeur de maillonSimple en se basant sur le d�placement effectu� depuis le maillon pr�d�cesseur
	 * @param d�placement mouvement de la case vide dans le jeu de taquin menant de la position du maillon p�re a la position du maillon
	 * @param p�re maillon repr�sentant la position imm�diatement pr�c�dente par laquelle on est arriv� pour atteindre cette position
	 */
	public MaillonSimple (String d�placement,MaillonSimple p�re ){
		this.setPosition (p�re.getPosition().clone());
		this.getPosition().permutation(d�placement);
		this.p�re = p�re;
		this.setProfondeur(p�re.getProfondeur()+1);
		setSuivant(null);
	}
	/**
	 * retourne la solution sous la forme d'une liste de position du jeu de taquin
	 * @return liste de position successive menant de la position initial � la solution
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
			pointeur = pointeur.p�re;
		}
		return solution;
	}
	/**
	 * retourne les maillon repr�sentant les positions accessibles en un d�placement depuis le maillon instanci� 
	 */
	public ArrayList<Maillon> successeurs(){
		ArrayList<Maillon>result = new ArrayList<Maillon>();
		HashMap<String,Case>d�placements = this.getPosition().choixPossibles();
		Set<String>cle = d�placements.keySet();
		Iterator<String> it = cle.iterator();
		while (it.hasNext()){
			String d�placementEnTraitement = it.next();
			result.add(new MaillonSimple(d�placementEnTraitement,this));
		}
		return result;
	}
	
}
