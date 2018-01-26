import java.util.*;
public class Marqu�Position implements Marqu� {
	private ArrayList<Taquin> marqu�; // liste des positions trait�s du jeu de taquin
	public ArrayList<Taquin> getMarqu�() {
		return marqu�;
	}
	public void setMarqu�(ArrayList<Taquin> marqu�) {
		this.marqu� = marqu�;
	}
	/**
	 * Constructeur vide pour initialis� la structure
	 */
	public Marqu�Position(){
		marqu� = new ArrayList<Taquin>();
	}
	public void afficher(){
		
	}
	/**
	 * ajoute une position du jeu dans la structure
	 * @param aAjouter position � ajouter dans la structure
	 */
	public void ajouterPosition (Taquin aAjouter){
		marqu�.add(aAjouter);
	}
	public void ajouterPosition(Maillon enTraitement){
		Taquin position = enTraitement.getPosition();
		this.ajouterPosition(position);
	}
	/**
	 * test si la position se trouve d�j� dans l'ensemble marqu�
	 * @param enTraitement position � tester
	 */
	public boolean appartient (Maillon enTraitement){
		return marqu�.contains(enTraitement.getPosition());
	}
	/**
	 * retourne le nombre de position stock�es dans la structure de donn�e
	 */
	public int taille (){
		return marqu�.size();
	}
}
