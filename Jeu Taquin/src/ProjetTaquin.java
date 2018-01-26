import java.io.*;
import java.util.*;
public class ProjetTaquin {
	
	/**
	 * méthode appeler pour trouver une solution au jeu de taquin
	 * @param fichier nom du fichier contenant le jeu de taquin
	 * @param delai delai au bout du quel on stop l'éxécution de la méthode
	 * @param algo structure de la donnée utilisé pour la résolution
	 * @throws Exception 
	 */
	public static void afficherSolutionHTML (Graphe g)throws Exception{
		ArrayList<Taquin>solution = g.récupererSolution();
		File f = new File ("TaquinDeplacement.php");
		f.delete();
		FileWriter fw = new FileWriter(f);
		fw.write("<?php \n");
		fw.write("$hauteur="+g.getInitial().getHauteur()+";\n");
		fw.write("$largeur="+g.getInitial().getLargeur()+";\n");
		fw.write("$array=array(");
		String a ;
		for(int i=0; i<solution.size();i++){
				a = "";
				StringTokenizer position = new StringTokenizer (""+solution.get(i)," |\n");
				while (position.hasMoreTokens()){
					a+="\""+position.nextToken()+"\"";
					if (position.hasMoreTokens()){
						a+=",";
					}
				}
				if (i!=0){
					fw.write(",");
				}
				fw.write(a);
		}	
		
		fw.write(");\n?>");
		fw.close();
		}
	/**
	 * La méthode afficherStatsMoyenneHTML crée un fichier php contenant un tableaux donnant
	 * le compte rendu de l'éxécution des algorithmes en donnant le nombre de taquin résolu par
	 * algorithme , le nombre de positions moyennes parcourue par chaque algorithme , le nombre
	 * de coup moyen pour trouvé la solution de chaque algorithme ainsi que le délai d'éxécution moyenne
	 * @param stats est un tableau contenant toutes les informations nécessaire à la création
	 * du fichier php ce tableau est construit dans le main en réponse à la commande '-aleatoire'
	 * @throws IOException renvoie une erreur relative à la sortie du fichier 'StatsMoyenne.php'
	 */
	public static void afficherStatsMoyenneHTML (String[][] stats)throws IOException{
		File f = new File ("StatsMoyenne.php");
		f.delete();
		FileWriter fw = new FileWriter(f);
		
		int temps=0;
		fw.write("<?php \n $info=array(");
		for(int i=0; i<stats.length;i++){
			temps=(int) Float.parseFloat(stats[i][4]);
			int ms = temps%1000;
			temps = temps/1000;
			int s = temps%60;
			temps = temps/60;
			int m = temps%60;
			int h = temps/60;
			fw.write("\""+stats[i][0]+"\",\""+stats[i][1]+"\", \""+(int)Float.parseFloat(stats[i][2])+"\" , \""+(int)Float.parseFloat(stats[i][3])+"\", \""+h+"\", \""+m+"\", \""+s+"\", \""+ms+"\"");
			if (i != (stats.length-1)){
				fw.write(",");
			}
			
		}
		
		fw.write(");\n?>");
		fw.close();
	}
	/**
	 * affiche la solution 
	 * @param g graphe parcouru
	 * @throws Exception
	 */
	public static void Solution (Graphe g) throws Exception{
		g.afficherSolution();
	}
	/**
	 * récupere les stats du graphe passé en paramètre (le graphe est déjà parcouru)  
	 * @param g graphe parcouru
	 */
	public static String récupérerStats (Graphe g){
		String stats = g.getNbCoups()+" "+g.getNbPositionsParcourus()+" "+g.getTempsExecution()+" ms";
		return stats;
	}
	/**
	 * afficher les stats du graphe parcouru 
	 * @param g le graphe parcouru 
	 */
	public static void afficherStats (Graphe g){
		String stats= récupérerStats(g);
		Scanner sc = new Scanner (stats);
		System.out.println("Nombres de coups pour atteindre la solution : "+sc.next());
		System.out.println("Nombres de positions parcouru lors de la recherche de solution : "+sc.next());
		System.out.println("Temps d'éxécution en ms : "+sc.next());
		sc.close();
		
	}
	/**
	 * remplit le fichier HTML contenant les stats
	 * @param stats fichiers contenant les info a reporter dans le fichier
	 * @throws IOException assure que l'écriture du fichier se fait correctement
	 */
	public static void afficherStatsHTML (String[][] stats)throws IOException{
		File f = new File ("Stats.php");
		f.delete();
		FileWriter fw = new FileWriter(f);
		fw.write("<?php\n$info=array(");
		Scanner sc;
		int temps=0;
		for(int i=0; i<stats.length;i++){
			sc = new Scanner(stats[i][1]);
			fw.write("\""+stats[i][0]+"\",");
			if(stats[i][1].equals("DelaiExecutionException: Délai d'éxécution dépassé")){
				for(int j=0;j<6;j++)
				fw.write("\" Delai d execution depasse\",");
			} else {
				if (stats[i][1].equals("NonSolvableException: L'algorithme n'a pas abouti à une solution")) {
						for(int j=0;j<6;j++)
						fw.write("\"L algorithme n a pas abouti a une solution\",");
			}else{
				fw.write("\""+sc.next()+"\",");
				fw.write("\""+sc.next()+"\",");
				temps=Integer.parseInt(sc.next());
				int ms = temps%1000;
				temps = temps/1000;
				int s = temps%60;
				temps = temps/60;
				int m = temps%60;
				int h = temps/60;
				fw.write("\""+h+"\",");
				temps=temps/1000;
				fw.write("\""+m+"\",");
				temps=temps/60;
				fw.write("\""+s+"\",");
				temps=temps/60;
				fw.write("\""+ms+"\",");
			}
		}
			sc.close();
		}
		fw.write(");\n?>");
		fw.close();
		}
	/**
	 * méthode qui test la solvabilité du jeu de taquin créé a partir du fichier donnée
	 * en paramètre
	 * @param fichier nom du fichier contenant le jeu de taquin
	 * @return vrai si le jeu est solvable / faux sinon
	 * @throws IOException pour s'assurer de la bonne lecture du fichier
	 */
	public static String[] convertirEnCommande (String commandeString){
		StringTokenizer st = new StringTokenizer (commandeString , " ");
		String[] commandeConvertie = new String[st.countTokens()];
		int i=0;
		while (st.hasMoreTokens()){
			commandeConvertie[i]=st.nextToken();
			i++;
		}
		return commandeConvertie;
	}
	/**
	 * méthode permettant de jouer au jeu de taquin contenu dans le fichier via le terminal
	 * @param fichier nom du fichier contenant le jeu de taquin
	 * @throws IOException pour s'assurer de la bonne lecture du fichier
	 * @throws NonSolvableException pour s'assurer que le jeu de taquin proposé admet
	 * une solution
	 */
	public static void Jouer (String fichier) throws Exception  {
		Taquin taquin = new Taquin (fichier);
		System.out.println(taquin);
		int compteurDeCoup = 0 ;
		boolean abandon = false;
		boolean victoire = taquin.testVictoire();
		while ((!victoire)&&(!abandon)){
			System.out.println("Quel case voulez vous déplacer sur la case vide ?");
			String choixCase = taquin.choixDeplacement();
			if (choixCase.equals("Abandonner")){
				abandon = true;
			}else{
				taquin.permutation(choixCase);
				compteurDeCoup++;
				System.out.println (taquin);
				if (taquin.getVide().getPosition() == 0 ){    // testVictoire() étant une méthode assez gourmande on limite son utilisation ainsi
					victoire = taquin.testVictoire();
				}
			}	
		}
		if (taquin.testVictoire()){
			System.out.println("Bravo vous avez gagné !");
			System.out.println("Nombre de coup : "+compteurDeCoup);
		}else{
			System.out.println("Partie terminé vous avez abandonné");
		}
	}
	public static void main (String [] args){
		ArrayList<String> algo = new ArrayList<String> (); // on stock nos algo ici pour les méthodes qui les utilise tous
		algo.add("pile");
		algo.add("file");
		algo.add("manhattan");
		algo.add("pmanhattan");
		algo.add("choix");
		algo.add("bit pile");
		algo.add("bit file");
		algo.add("bit manhattan");
		algo.add("bit pmanhattan");
		algo.add("bit choix");
		algo.add("bit prof pile");
		algo.add("bit prof manhattan");
		algo.add("bit prof choix");
		algo.add("prof pile");
		algo.add("prof manhattan");
		algo.add("prof choix");
		algo.add("progressif");
		algo.add("progressif manhattan");
		algo.add("progressif choix");
		switch (args[0]){
		case ("-name"):
			System.out.println("Ferre Donovan");
			System.out.println("Fournier Paul");
			System.out.println("Label Louis ");
			break;
		case ("-h"):
			System.out.println("\n-h : affiche une aide interactive\n");
	
			System.out.println("-name : affiche les noms des concepteurs du programme\n");
			System.out.println("-sol 'fichier.taq' : vérifie si le jeu de taquin du fichier est solvable\n");
			System.out.println("-joue 'fichier.taq' : permet de jouer au jeu de taquin "
					+ "avec les données du fichier 'fichier.taq'\n");
			System.out.println("-cal 'delai' 'algo' 'fichier.taq' : donne une solution au jeu "
					+ "de taquin donné en paramètre par 'fichier.taq' en utilisant l'algorithme 'algo'"
					+ " l'éxécution du programme s'arrète si on dépasse le délai 'délai'\n");
			System.out.println("-stat 'delai' 'algo' 'fichier.taq' : retourne les stats de l'algorithme 'algo' sur le 'fichier' donnés en paramètre"
					+ "  si le delai est dépassé on arrète l'éxécution de l'algorithme , si on ne donne pas de paramète algo le programme applique tout les "
					+ "algo au fichier et ressort un fichier HTML contenant un compte rendu des éxécutions\n");
			System.out.println("-anime 'delai' 'algo' 'fichier.taq' retourne la solution du fichier passé en paramètre sous la forme d'un fichier php qui permet"
					+ " d'avoir la solution sous la forme d'une animation , si le délai est dépassé on arrète l'éxécution de l'algorithme . L'algorithme "
					+ "utilisé pour résoudre le jeu de taquin est celui passé en paramètre \n");
			System.out.println("-aleatoire 'n' 'largeur' 'hauteur' 'delai' éxécute tout les algorithme sur 'n' jeu de taquin"
					+ " solvables de taille 'largeur' x 'hauteur' généré aléatoirement et retourne un fichier php contenant"
					+ " les moyennes des performances des différents algorithmes sous forme de tableau \n");
			System.out.println("les différents algo sont ceux du sujets ");
			break;
		case ("-sol"):
			try{
				Taquin taquin = new Taquin (args[1]);
				System.out.println(taquin.solvable());
			}catch (IOException ioe){
					System.out.println(ioe);
			}
			break;
		case ("-joue"):
			try {
				Jouer (args[1]);
			}catch (Exception e){
				System.out.println(e);
			}
			break;
		
		case ("-stat"):
		case ("-cal"):
		case ("-anime"):
			try{
				int délai = Integer.parseInt (args[1]);
				String[] commande ; 
				Graphe g = new Graphe ();
				Taquin jeu = new Taquin (args[args.length-1]);
				if (!jeu.solvable()){
					throw new NonSolvableException ("Jeu de taquin non solvable");
				}else{
					if (args.length> 3){
						commande = Arrays.copyOfRange(args, 2, args.length-1);
						g =  new Graphe (commande , délai , jeu);
					}
					switch (args[0]){
					case ("-cal"):
						Solution (g);
						break;
					case ("-anime"):
						afficherSolutionHTML(g);
						break;
					case("-stat"):
						if (args.length>3){
							afficherStats(g);
						}else{
							String[][]tableauDesStats = new String [algo.size()][2];
							for (int i = 0 ; i < algo.size();i++){
								tableauDesStats[i][0]=algo.get(i);
								try{
									g = new Graphe (convertirEnCommande(algo.get(i)),délai,jeu);
									tableauDesStats[i][1]=récupérerStats(g);
								}catch (Exception e){
									tableauDesStats[i][1]=""+e;
								}
							}
							afficherStatsHTML(tableauDesStats);
						}
					}
				}	
			}catch (Exception e){
				System.out.println(e);
			}
			
			break;
		
		case ("-aleatoire"):
			int nbTaquin = Integer.parseInt(args[1]);
			int largeurDesTaquins = Integer.parseInt(args[2]);
			int hauteurDesTaquins = Integer.parseInt(args[3]);
			int delai = Integer.parseInt(args[4]);
			boolean rectangle=true;
			if ((args.length>6)&&(args[6].equals("-plus"))){
				rectangle = false;
			}
			Graphe g ;
			String [][] tableauDesMoyennes = new String [algo.size()][5]; // calculs les moyennes pour chaque algo 
			for (int i = 0 ; i<tableauDesMoyennes.length ; i++){
				tableauDesMoyennes [i][0]=algo.get(i); //nom de l'algorithme appliqué
				tableauDesMoyennes [i][1]=""+0;  // nombre de jeux résolu
				tableauDesMoyennes [i][2]=""+0;  // taille solution
				tableauDesMoyennes [i][3]=""+0;  // nb positions parcourus
				tableauDesMoyennes [i][4]=""+0;  // temps moyen d'éxécution
			}
			for (int i=0 ; i<nbTaquin ; i++ ){
				Taquin jeu = new Taquin (hauteurDesTaquins , largeurDesTaquins , rectangle);
				for (int j=0 ; j<tableauDesMoyennes.length ; j++){
					
					try{
						g = new Graphe (convertirEnCommande(algo.get(j)),delai,jeu);
						tableauDesMoyennes [j][1]= ""+((Integer.parseInt(tableauDesMoyennes[j][1]))+1);
						tableauDesMoyennes [j][2]= ""+((Integer.parseInt(tableauDesMoyennes[j][2]))+g.getNbCoups());
						tableauDesMoyennes [j][3]= ""+((Integer.parseInt(tableauDesMoyennes[j][3]))+g.getNbPositionsParcourus());
						tableauDesMoyennes [j][4]= ""+((Integer.parseInt(tableauDesMoyennes[j][4]))+g.getTempsExecution());
					}catch (Exception e){
						
					} 
				}
			}
			for (int i=0 ; i<tableauDesMoyennes.length ; i++){
				if (!tableauDesMoyennes[i][1].equals("0")){
					tableauDesMoyennes[i][2]= ""+((Float.parseFloat(tableauDesMoyennes[i][2]))/(Integer.parseInt(tableauDesMoyennes[i][1])));
					tableauDesMoyennes[i][3]= ""+((Float.parseFloat(tableauDesMoyennes[i][3]))/(Integer.parseInt(tableauDesMoyennes[i][1])));
					tableauDesMoyennes[i][4]= ""+((Float.parseFloat(tableauDesMoyennes[i][4]))/(Integer.parseInt(tableauDesMoyennes[i][1])));
				}
			}
			try{	
				afficherStatsMoyenneHTML (tableauDesMoyennes);
			}catch(Exception e){
				System.out.println(e);
			}
			break ;
		default:
			System.out.println("Cette commande n'existe pas");
		}
		
	}

}

