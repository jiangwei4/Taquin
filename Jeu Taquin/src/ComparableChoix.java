import java.util.Comparator;
public class ComparableChoix implements Comparator<Maillon> {
	/**
	 * m�thode de comparaison de deux maillon par leur distance de Manhattan pond�r�e + leur profondeur
	 * pour l'utilisation de la file � priorit�
	 */
	public int compare (Maillon taquin1 , Maillon taquin2){
		if ((taquin1.getPosition().distanceDeManhattanPond�r�e()+taquin1.getProfondeur()) < (taquin2.getPosition().distanceDeManhattanPond�r�e()+taquin1.getProfondeur())){
			return -1;
		}else{
			if ((taquin1.getPosition().distanceDeManhattanPond�r�e()+taquin1.getProfondeur()) > (taquin2.getPosition().distanceDeManhattanPond�r�e()+taquin1.getProfondeur())){
				return 1;
			}else{
				return 0;
			}
		}
	}
}
