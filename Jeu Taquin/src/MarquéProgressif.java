
public class MarquéProgressif implements Marqué {
	private int borne ;
	private int nbPositionParcouru ;
	public MarquéProgressif (){
		borne = 0;
		nbPositionParcouru = 0;
	}
	public void ajouterPosition (Taquin taquin){
		borne = taquin.distanceDeManhattan();
		nbPositionParcouru = 1;
	}
	public int taille (){
		return nbPositionParcouru;
	}
	public void ajouterPosition(Maillon t){
		nbPositionParcouru ++;
	}
	public boolean appartient (Maillon traité){
		if (traité.getPosition().distanceDeManhattan()+traité.getProfondeur()>borne){
			return true;
		}else{
			MaillonProf enTraitement = (MaillonProf) traité; 
			Taquin test = enTraitement.getPosition().clone();
			for (int i=enTraitement.getChemin().length()-1 ; i>=0 ; i--){
				test.permutationInverse(enTraitement.getChemin().charAt(i));
				if (enTraitement.getPosition().equals(test)){
					return true;
				}
			}
			return false;
		}
	}
	public void incrémenterBorne (){
		borne++;
	}
}
