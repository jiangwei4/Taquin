public class MarquéIncomplet implements Marqué {
	private boolean []marqué; // tableau de boolean représentant les position une position est indicé comme suit dans le tableau
	public boolean[] getMarqué() {
		return marqué;
	}
	public void setMarqué(boolean[] marqué) {
		this.marqué = marqué;
	}
	// position.hashCode()%taille du tableau , une case est à vrai si on a stocké la position
	/**
	 * Constructeur de l'ensemble marqué incomplet
	 * @param taille représente le nombre maximum de position qu'on peux stocker dans la structure
	 */
	public MarquéIncomplet(int taille){
		marqué = new boolean [taille];
		for (int i=0 ; i<taille ; i++){
			marqué[i]=false;
		}
	}
	/**
	 * ajoute une position à la structure
	 * @param aAjouter position ajouté à la strucuture
	 */
	public void ajouterPosition(Taquin aAjouter){
		int index = aAjouter.hashCode()%marqué.length;
		marqué[index]=true;
	}
	/**
	 * test si la position se trouve déjà dans la structure
	 * @param position qu'on doit tester
	 */
	public boolean appartient (Maillon enTraitement){
		Taquin position = enTraitement.getPosition();
		int index = position.hashCode()%marqué.length;
		return marqué[index];
	}
	public void ajouterPosition (Maillon enTraitement){
		Taquin position = enTraitement.getPosition();
		this.ajouterPosition(position);
	}
	/**
	 * retourn le nombre de position stockées dans la structure de donnée
	 */
	public int taille (){
		int cpt = 0;
		for (int i=0 ;i<marqué.length;i++){
			if (marqué[i]==true){
				cpt++;
			}
		}
		return cpt;
	}
}
