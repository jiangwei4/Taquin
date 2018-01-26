
public interface Marqué {
	public void ajouterPosition (Taquin taquin); // ajouter une position atteinte du jeu de taquin à la structure
	public boolean appartient (Maillon traité); // test si la position taquin a déjà été atteinte (= se trouve déjà dans notre structure de donnée)
	public void ajouterPosition (Maillon traité);
	public int taille (); // retourne le nombre de position stocké dans la structure
}
