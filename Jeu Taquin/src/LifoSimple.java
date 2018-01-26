
public class LifoSimple extends Lifo {
	public LifoSimple (){
		super();
	}

	/**
	 * ajoute une position du jeu de taquin à sommet de la pile
	 */
	public void ajouterMaillon (Taquin taquin){
		MaillonSimple aEmpiler = new MaillonSimple(taquin);
		if (this.estVide()){
			setSommet(aEmpiler);
		}else{
			aEmpiler.setSuivant(getSommet());
			setSommet(aEmpiler);
		}
			
	}
	
	
	
}
