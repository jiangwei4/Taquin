import java.util.ArrayList;
import java.util.Iterator;


public class Graphe {
	private Marqué marqué; // test si une position a déjà été exploré
	private Atraité atraité; // structure qui gère les maillon en attente de traitement
	private long tempsExecution ; // temps d'éxécution pour le parcourt 
	private int nbPositionsParcourus ; // nbDePositionsParcouru lors du parcourt
	private int nbCoups ; // nbCoups joué pour arrivé a la solution 
	private Taquin initial; // position depuis laquel on a lancé le jeu de taquin
	/**
	 * Constructeur vide du graphe 
	 */
	public Graphe (){
		marqué = null;
		atraité = null;
		tempsExecution = 0;
		nbPositionsParcourus = 0;
		nbCoups = 0;
		initial = null;
	}
	/**
	 * Constructeur de graphe (initialisation + parcourt)
	 * @param commande un tableau représentant le choix des structures de données choisis par l'utilisateur en tapant sa commande
	 * @param délai durée au bout de laquelle on stoppe le parcourt du graphe 
	 * @param initial position initial du parcourt (celle donnée par le fichier passé en paramètre par l'utilisateur
	 * @throws Exception sortie d'erreur
	 */
	public Graphe (String[] commande , int délai , Taquin initial) throws Exception {
		for (int i=0 ; i<commande.length;i++){
			switch (commande[i]){
			case ("progressif"):
				marqué = new MarquéProgressif();
				if (i+1<commande.length){	
					i++;
					atraité = new LifoProf(commande[i]);
				}else{
					atraité = new LifoProf("");
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
				marqué = new MarquéIncomplet(taille);
				break;
			case("file"):
				atraité = new Fifo();
				break;
			case ("pile"):
				atraité = new LifoSimple();
				break;
			case ("manhattan"):
				atraité = new tas(new ComparableManhattan());
				break;
			case ("pmanhattan"):
				atraité = new tas (new ComparableManhattanProfondeur());
				break;
			case ("choix"):
				atraité = new tas (new ComparableChoix());
				break;
			case ("prof"):
				if (i+1<commande.length){	
					i++;
					atraité = new LifoProf(commande[i]);
				}else{
					atraité = new LifoProf("");
				}
				break;
			default :
				throw new Exception ("La commande que vous avez tapé n'existe pas");
			}
		}
		if (marqué==null){
			marqué = new MarquéPosition();
		}
		this.initial = initial;
		if (!initial.solvable()){
			throw new NonSolvableException ("Le jeu de taquin n'est pas solvable");
		}
		long start= System.currentTimeMillis();
		marqué.ajouterPosition(initial);
		atraité.ajouterMaillon(initial);
		boolean solution = false;
		gestionDeDelai gdd = new gestionDeDelai ();
		long temps ;
		//System.out.println(System.currentTimeMillis()-start);
		//gdd.run(délai);
		//System.out.println(System.currentTimeMillis()-start);
		while ((!atraité.estVide())&&(!solution)&&(gdd.isAlive())){
			temps= System.currentTimeMillis()-start;
			 if(temps>=délai){ 
                 throw new DelaiExecutionException("Délai d'éxécution dépassé");// stoppe l'éxécution du programme si le temps  d'éxécution devient supérieur au délai imposé
			 }
			 solution = atraité.positionSuivante().getPosition().testVictoire();
			if (!solution){
				ArrayList<Maillon> successeurs = atraité.traiterMaillon();
				Iterator<Maillon> it = successeurs.iterator();
				while ((it.hasNext())&&(!solution)){
					Maillon successeurEnTraitement = it.next();
					if (!marqué.appartient(successeurEnTraitement)){
						marqué.ajouterPosition(successeurEnTraitement);
						atraité.ajouterMaillon(successeurEnTraitement);
					}
				}
			}
			if ((atraité.estVide())&&(!solution)&&(marqué instanceof MarquéProgressif)){
				MarquéProgressif mp = (MarquéProgressif) marqué;
				mp.incrémenterBorne();
				marqué = mp;
				atraité.ajouterMaillon(initial);
				nbPositionsParcourus = 0 ;
			}
		}
		System.out.println(System.currentTimeMillis()-start);
		/*if (gdd.isAlive()){
			tempsExecution = System.currentTimeMillis()-start;
			gdd.interrupt();
		}else{
			throw new DelaiExecutionException("Délai d'éxécution dépassé");
		}*/
		if (!solution){
			throw new NonSolvableException ("L'algorithme n'a pas abouti à une solution");
		}else{
			nbCoups = atraité.positionSuivante().getProfondeur();
			nbPositionsParcourus = marqué.taille();
			tempsExecution = System.currentTimeMillis()-start;
		}
	}
	public Marqué getMarqué() {
		return marqué;
	}
	public void setMarqué(Marqué marqué) {
		this.marqué = marqué;
	}
	public Atraité getAtraité() {
		return atraité;
	}
	public void setAtraité(Atraité atraité) {
		this.atraité = atraité;
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
	 * récupère la solution sous forme d'ArrayList
	 * @throws Exception
	 */
	public ArrayList<Taquin> récupererSolution () throws Exception{
		Maillon finale = atraité.positionSuivante ();
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
		ArrayList<Taquin> résolution = this.récupererSolution();
		for (int i=0;i<résolution.size() ; i++){
			System.out.println(résolution.get(i));
		}
	}	
	
}
