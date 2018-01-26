import java.util.*;
public class MarquéPosition implements Marqué {
	private ArrayList<Taquin> marqué; // liste des positions traités du jeu de taquin
	public ArrayList<Taquin> getMarqué() {
		return marqué;
	}
	public void setMarqué(ArrayList<Taquin> marqué) {
		this.marqué = marqué;
	}
	/**
	 * Constructeur vide pour initialisé la structure
	 */
	public MarquéPosition(){
		marqué = new ArrayList<Taquin>();
	}
	public void afficher(){
		
	}
	/**
	 * ajoute une position du jeu dans la structure
	 * @param aAjouter position à ajouter dans la structure
	 */
	public void ajouterPosition (Taquin aAjouter){
		marqué.add(aAjouter);
	}
	public void ajouterPosition(Maillon enTraitement){
		Taquin position = enTraitement.getPosition();
		this.ajouterPosition(position);
	}
	/**
	 * test si la position se trouve déjà dans l'ensemble marqué
	 * @param enTraitement position à tester
	 */
	public boolean appartient (Maillon enTraitement){
		return marqué.contains(enTraitement.getPosition());
	}
	/**
	 * retourne le nombre de position stockées dans la structure de donnée
	 */
	public int taille (){
		return marqué.size();
	}
}
