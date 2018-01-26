import java.util.Comparator;
public class ComparableChoix implements Comparator<Maillon> {
	/**
	 * méthode de comparaison de deux maillon par leur distance de Manhattan pondérée + leur profondeur
	 * pour l'utilisation de la file à priorité
	 */
	public int compare (Maillon taquin1 , Maillon taquin2){
		if ((taquin1.getPosition().distanceDeManhattanPondérée()+taquin1.getProfondeur()) < (taquin2.getPosition().distanceDeManhattanPondérée()+taquin1.getProfondeur())){
			return -1;
		}else{
			if ((taquin1.getPosition().distanceDeManhattanPondérée()+taquin1.getProfondeur()) > (taquin2.getPosition().distanceDeManhattanPondérée()+taquin1.getProfondeur())){
				return 1;
			}else{
				return 0;
			}
		}
	}
}
