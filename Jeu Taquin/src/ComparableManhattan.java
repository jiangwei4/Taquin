import java.util.*;
public class ComparableManhattan implements Comparator<Maillon> {
	/**
	 * méthode de comparaison de deux maillon par leur distance de Manhattan
	 * pour l'utilisation de la file à priorité
	 */
	public int compare (Maillon taquin1 , Maillon taquin2){
		if ((taquin1.getPosition().distanceDeManhattan()) < (taquin2.getPosition().distanceDeManhattan())){
			return -1;
		}else{
			if ((taquin1.getPosition().distanceDeManhattan()) > (taquin2.getPosition().distanceDeManhattan())){
				return 1;
			}else{
				return 0;
			}
		}
	}
}
