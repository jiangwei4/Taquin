
public interface Marqu� {
	public void ajouterPosition (Taquin taquin); // ajouter une position atteinte du jeu de taquin � la structure
	public boolean appartient (Maillon trait�); // test si la position taquin a d�j� �t� atteinte (= se trouve d�j� dans notre structure de donn�e)
	public void ajouterPosition (Maillon trait�);
	public int taille (); // retourne le nombre de position stock� dans la structure
}
