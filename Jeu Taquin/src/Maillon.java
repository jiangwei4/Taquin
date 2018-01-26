import java.util.*;
public abstract class Maillon {
	private Maillon suivant; // le maillon a traité logiquement une fois que celui ci est traité
	private int profondeur; // nombre de coup pour atteindre la position du jeu
	private Taquin position; // position du jeu de Taquin représenté par le maillon
	public abstract ArrayList<Maillon> successeurs(); // retourne les successeurs du maillon sous la forme d'une liste de maillon
	public Maillon getSuivant() {
		return suivant;
	}
	public void setSuivant(Maillon suivant) {
		this.suivant = suivant;
	}
	public int getProfondeur() {
		return profondeur;
	}
	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}
	public Taquin getPosition() {
		return position;
	}
	public void setPosition(Taquin position) {
		this.position = position;
	}
}
