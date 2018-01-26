
public class Case {
	private int valeur;  // valeur de la case
	private int position; // position de la case dans le jeu de taquin
	private int hauteur; // coordonn�e en hauteur de la case dans le jeu de taquin
	private int largeur;  // coordonn�e en largeur de la case dans le jeu de taquin
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getHauteur() {
		return hauteur;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	public int getLargeur() {
		return largeur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	/**
	 * Constructeur vide
	 */
	public Case (){
		
	}
	/**
	 * Constructeur de case
	 * @param valeur  voir d�claration de la class
	 * @param position   voir d�claration de la class
	 * @param hauteur  voir d�claration de la class
	 * @param largeur  voir d�claration de la class
	 */
	public Case (int valeur, int position , int hauteur, int largeur ){
		this.valeur = valeur;
		this.position = position;
		this.hauteur = hauteur;
		this.largeur = largeur;
	}
	/**
	 * m�thode toString pour l'affichage
	 */
	public String toString (){
			return ""+this.valeur;
	}
	/**
	 * retourne une copie de la Case qui appelle cette m�thode
	 */
	public Case clone (){
		Case clone = new Case ();
		clone.valeur = valeur;
		clone.position = position;
		clone.largeur = largeur;
		clone.hauteur =hauteur;
		return clone;
	}
	/**
	 * m�thode equals qui test l'�galit� de deux cases
	 */
	public boolean equals(Object autre){
		if (!(autre instanceof Case)){
			return false;
		}else{
			Case other = (Case) autre;
			if (this.valeur!= other.valeur){
				return false;
			}
			if (this.position != other.position){
				return false;
			}
			if (this.hauteur != other.hauteur){
				return false;
			} 
			if (this.largeur != other.largeur){
				return false;
			}
			return true;
		}
		
	}
	
}
