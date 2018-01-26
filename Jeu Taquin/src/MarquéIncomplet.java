public class Marqu�Incomplet implements Marqu� {
	private boolean []marqu�; // tableau de boolean repr�sentant les position une position est indic� comme suit dans le tableau
	public boolean[] getMarqu�() {
		return marqu�;
	}
	public void setMarqu�(boolean[] marqu�) {
		this.marqu� = marqu�;
	}
	// position.hashCode()%taille du tableau , une case est � vrai si on a stock� la position
	/**
	 * Constructeur de l'ensemble marqu� incomplet
	 * @param taille repr�sente le nombre maximum de position qu'on peux stocker dans la structure
	 */
	public Marqu�Incomplet(int taille){
		marqu� = new boolean [taille];
		for (int i=0 ; i<taille ; i++){
			marqu�[i]=false;
		}
	}
	/**
	 * ajoute une position � la structure
	 * @param aAjouter position ajout� � la strucuture
	 */
	public void ajouterPosition(Taquin aAjouter){
		int index = aAjouter.hashCode()%marqu�.length;
		marqu�[index]=true;
	}
	/**
	 * test si la position se trouve d�j� dans la structure
	 * @param position qu'on doit tester
	 */
	public boolean appartient (Maillon enTraitement){
		Taquin position = enTraitement.getPosition();
		int index = position.hashCode()%marqu�.length;
		return marqu�[index];
	}
	public void ajouterPosition (Maillon enTraitement){
		Taquin position = enTraitement.getPosition();
		this.ajouterPosition(position);
	}
	/**
	 * retourn le nombre de position stock�es dans la structure de donn�e
	 */
	public int taille (){
		int cpt = 0;
		for (int i=0 ;i<marqu�.length;i++){
			if (marqu�[i]==true){
				cpt++;
			}
		}
		return cpt;
	}
}
