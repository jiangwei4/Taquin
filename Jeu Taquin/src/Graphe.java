import java.util.ArrayList;
import java.util.Iterator;


public class Graphe {
	private Marqu� marqu�; // test si une position a d�j� �t� explor�
	private Atrait� atrait�; // structure qui g�re les maillon en attente de traitement
	private long tempsExecution ; // temps d'�x�cution pour le parcourt 
	private int nbPositionsParcourus ; // nbDePositionsParcouru lors du parcourt
	private int nbCoups ; // nbCoups jou� pour arriv� a la solution 
	private Taquin initial; // position depuis laquel on a lanc� le jeu de taquin
	/**
	 * Constructeur vide du graphe 
	 */
	public Graphe (){
		marqu� = null;
		atrait� = null;
		tempsExecution = 0;
		nbPositionsParcourus = 0;
		nbCoups = 0;
		initial = null;
	}
	/**
	 * Constructeur de graphe (initialisation + parcourt)
	 * @param commande un tableau repr�sentant le choix des structures de donn�es choisis par l'utilisateur en tapant sa commande
	 * @param d�lai dur�e au bout de laquelle on stoppe le parcourt du graphe 
	 * @param initial position initial du parcourt (celle donn�e par le fichier pass� en param�tre par l'utilisateur
	 * @throws Exception sortie d'erreur
	 */
	public Graphe (String[] commande , int d�lai , Taquin initial) throws Exception {
		for (int i=0 ; i<commande.length;i++){
			switch (commande[i]){
			case ("progressif"):
				marqu� = new Marqu�Progressif();
				if (i+1<commande.length){	
					i++;
					atrait� = new LifoProf(commande[i]);
				}else{
					atrait� = new LifoProf("");
				}	
				break;
			case("bit"):
				int taille ;
				try{
					taille = Integer.parseInt(commande[i+1]);
					i++;
				}catch (Exception e){
					taille = 4000;
				}
				marqu� = new Marqu�Incomplet(taille);
				break;
			case("file"):
				atrait� = new Fifo();
				break;
			case ("pile"):
				atrait� = new LifoSimple();
				break;
			case ("manhattan"):
				atrait� = new tas(new ComparableManhattan());
				break;
			case ("pmanhattan"):
				atrait� = new tas (new ComparableManhattanProfondeur());
				break;
			case ("choix"):
				atrait� = new tas (new ComparableChoix());
				break;
			case ("prof"):
				if (i+1<commande.length){	
					i++;
					atrait� = new LifoProf(commande[i]);
				}else{
					atrait� = new LifoProf("");
				}
				break;
			default :
				throw new Exception ("La commande que vous avez tap� n'existe pas");
			}
		}
		if (marqu�==null){
			marqu� = new Marqu�Position();
		}
		this.initial = initial;
		if (!initial.solvable()){
			throw new NonSolvableException ("Le jeu de taquin n'est pas solvable");
		}
		long start= System.currentTimeMillis();
		marqu�.ajouterPosition(initial);
		atrait�.ajouterMaillon(initial);
		boolean solution = false;
		gestionDeDelai gdd = new gestionDeDelai ();
		long temps ;
		//System.out.println(System.currentTimeMillis()-start);
		//gdd.run(d�lai);
		//System.out.println(System.currentTimeMillis()-start);
		while ((!atrait�.estVide())&&(!solution)&&(gdd.isAlive())){
			temps= System.currentTimeMillis()-start;
			 if(temps>=d�lai){ 
                 throw new DelaiExecutionException("D�lai d'�x�cution d�pass�");// stoppe l'�x�cution du programme si le temps  d'�x�cution devient sup�rieur au d�lai impos�
			 }
			 solution = atrait�.positionSuivante().getPosition().testVictoire();
			if (!solution){
				ArrayList<Maillon> successeurs = atrait�.traiterMaillon();
				Iterator<Maillon> it = successeurs.iterator();
				while ((it.hasNext())&&(!solution)){
					Maillon successeurEnTraitement = it.next();
					if (!marqu�.appartient(successeurEnTraitement)){
						marqu�.ajouterPosition(successeurEnTraitement);
						atrait�.ajouterMaillon(successeurEnTraitement);
					}
				}
			}
			if ((atrait�.estVide())&&(!solution)&&(marqu� instanceof Marqu�Progressif)){
				Marqu�Progressif mp = (Marqu�Progressif) marqu�;
				mp.incr�menterBorne();
				marqu� = mp;
				atrait�.ajouterMaillon(initial);
				nbPositionsParcourus = 0 ;
			}
		}
		System.out.println(System.currentTimeMillis()-start);
		/*if (gdd.isAlive()){
			tempsExecution = System.currentTimeMillis()-start;
			gdd.interrupt();
		}else{
			throw new DelaiExecutionException("D�lai d'�x�cution d�pass�");
		}*/
		if (!solution){
			throw new NonSolvableException ("L'algorithme n'a pas abouti � une solution");
		}else{
			nbCoups = atrait�.positionSuivante().getProfondeur();
			nbPositionsParcourus = marqu�.taille();
			tempsExecution = System.currentTimeMillis()-start;
		}
	}
	public Marqu� getMarqu�() {
		return marqu�;
	}
	public void setMarqu�(Marqu� marqu�) {
		this.marqu� = marqu�;
	}
	public Atrait� getAtrait�() {
		return atrait�;
	}
	public void setAtrait�(Atrait� atrait�) {
		this.atrait� = atrait�;
	}
	public long getTempsExecution() {
		return tempsExecution;
	}
	public void setTempsExecution(long tempsExecution) {
		this.tempsExecution = tempsExecution;
	}
	public int getNbPositionsParcourus() {
		return nbPositionsParcourus;
	}
	public void setNbPositionsParcourus(int nbPositionsParcourus) {
		this.nbPositionsParcourus = nbPositionsParcourus;
	}
	public int getNbCoups() {
		return nbCoups;
	}
	public void setNbCoups(int nbCoups) {
		this.nbCoups = nbCoups;
	}
	public Taquin getInitial() {
		return initial;
	}
	public void setInitial(Taquin initial) {
		this.initial = initial;
	}
	/**
	 * r�cup�re la solution sous forme d'ArrayList
	 * @throws Exception
	 */
	public ArrayList<Taquin> r�cupererSolution () throws Exception{
		Maillon finale = atrait�.positionSuivante ();
		if (finale instanceof MaillonSimple){
			MaillonSimple finaleSimple =(MaillonSimple) finale;
			return finaleSimple.solution();
		}else{
			if(finale instanceof MaillonProf){
				MaillonProf finaleProf = (MaillonProf) finale;
				return finaleProf.solution(initial);
			}else{
				throw new Exception ("erreur type maillon");
			}
		}
		
			
	}
	/**
	 * affiche la solution 
	 * @throws Exception sortie d'erreur
	 */
	public void afficherSolution () throws Exception{
		ArrayList<Taquin> r�solution = this.r�cupererSolution();
		for (int i=0;i<r�solution.size() ; i++){
			System.out.println(r�solution.get(i));
		}
	}	
	
}
