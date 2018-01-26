import java.util.*;
import java.io.*;
public class Taquin {
	private int largeur ; // Largeur de la grille de taquin
	private int hauteur ; // Hauteur de la grille de taquin
	private Case[][] grille ; // grille du jeu de taquin contenant les cases (équivalent a la position du jeu de taquin)
	private Case vide; // La case vide qui peux être déplacé
	private int nbCases; // nombre de cases jouables dans le jeu (ce paramètre nous servira a effectuer certains controles)
	private boolean rectangle;
	public static Scanner in = new Scanner (System.in);
	public int getLargeur() {
		return largeur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public int getHauteur() {
		return hauteur;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	public Case[][] getGrille() {
		return grille;
	}
	public void setGrille(Case[][] grille) {
		this.grille = grille;
	}
	public Case getVide() {
		return vide;
	}
	public void setVide(Case vide) {
		this.vide = vide;
	}
	public int getNbCases() {
		return nbCases;
	}
	public void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}
	/**
	 * Constructeur vide
	 */
	public Taquin (){
		largeur = 0 ;
		hauteur = 0 ;
		grille = null;
		vide = null;
		nbCases = 0;
	}
	/**
	 * Construit une position du jeu de taquin en partant de la position initial et effectuant les déplacements de la case vide
	 * donnée par Chemin
	 * @param initial position d'initialisation du jeu de taquin (donnée par le fichier)
	 * @param Chemin séquence de caractère représentant les déplacements de la case depuis la position initial pour atteindre la position
	 * créé
	 */
	public Taquin (Taquin initial , String Chemin){
		Taquin taquin = initial.clone();
		for (int i=0 ; i<Chemin.length();i++){
			switch(Chemin.charAt(i)){
			case'H':
				taquin.permutation("Haut");
				break;
			case 'B':
				taquin.permutation("Bas");
				break;
			case 'D':
				taquin.permutation("Droite");
				break;
			case 'G':
				taquin.permutation("Gauche");
				break;
			}
		}
		this.largeur = taquin.largeur;
		this.hauteur = taquin.hauteur;
		this.grille = taquin.grille;
		this.vide = taquin.vide;
		this.nbCases = taquin.nbCases;
	}
	/**
	 * Constructeur de jeu de taquin 
	 * @param fichier	la chaîne de caractère fichier correspond au nom du fichier rentré en 
	 * paramètre par l'utilisateur depuis lequel sera créé le jeu de taquin
	 * @throws IOException utile pour éviter les problèmes lié a la lecture du fichier
	 */
	public Taquin (String fichier) throws IOException {
		File file = new File (fichier);
		Scanner sc = new Scanner (file);
		boolean caseVidePlace = false;
		rectangle = true;
		ArrayList<Case> caseDejaPlace = new ArrayList<Case>();
		if (sc.hasNextInt()){
			largeur = sc.nextInt();
		}else{
			sc.close();
			throw new IOException ("Informations manquantes dans le fichier");
		}
		if (sc.hasNextInt()){
			hauteur = sc.nextInt();
		}else{
			sc.close();
			throw new IOException ("Informations manquantes dans le fichier");
		}
		nbCases = 0;
		int[][]stock = new int [hauteur][largeur];
		for (int i=0 ; i<hauteur ; i++){
			for (int j=0 ; j<largeur ; j++){
				if (sc.hasNextInt()){
					stock[i][j] = sc.nextInt();
					if (stock[i][j]>=0){
						nbCases ++;
					}else{
						rectangle = false;
					}
				}else{
					sc.close();
					throw new IOException ("Informations manquantes dans le fichier");
				}
			}
		}
		if (nbCases == 0){
			throw new IOException ("Pas de case jouable");
		}
		sc.close();
		int compteurPosition= 1;
		grille = new Case [hauteur][largeur];
		for (int i=0 ; i<hauteur ; i++){
			for (int j=0 ; j<largeur ; j++){
				if (stock[i][j]>= 0){	
					if (compteurPosition==nbCases){
						compteurPosition = 0;
					}
					grille[i][j] = new Case(stock[i][j],compteurPosition,i,j);
					compteurPosition ++;
					if (grille[i][j].getValeur()==0){
						vide = grille[i][j];
						caseVidePlace = true;
					}else{
						if((grille[i][j].getValeur()<0)||(grille[i][j].getValeur()>= nbCases)){
							sc.close();
							throw new IOException ("Une des cases a une valeur incohérente");
						}else{
							if(caseDejaPlace.contains(grille[i][j])){
								sc.close();
								throw new IOException ("Une case a été placé deux fois");
							}else{
								caseDejaPlace.add(grille[i][j]);
							}
						}
					}
				}	
				
			}
		}
		if (!caseVidePlace){
			throw new IOException ("Pas de case vide dans le jeu");
		}
		
	}
	/**
	 * Constructeur de jeu de taquin remplit aléatoirement
	 * @param hauteur hauteur du jeu de taquin a créer
	 * @param largeur largeur du jeu de taquin a créer
	 */
	public Taquin (int hauteur, int largeur , boolean rectangle){
		ArrayList<Integer> listeNombres=new ArrayList<Integer>();
		this.largeur=largeur;
		this.hauteur=hauteur;
		int nbCourant;
		this.rectangle = rectangle ;
		int nbCasesNonJouable = 0 ;
		if (!rectangle){
			while (nbCasesNonJouable==0){
				nbCasesNonJouable = (int) (Math.random()*(hauteur*largeur));
			}
			nbCases = (largeur*hauteur)-nbCasesNonJouable ;
		}else{
			nbCases=largeur * hauteur;
		}
		grille= new Case[hauteur][largeur];
		do{
            for (int i=0; i<nbCases; i++){
                    listeNombres.add(i); //créé la liste de nombres qui composera le jeu de taquin dans une arrayList
            }

            int compteurPosition=1;//cette variable permettra de renseigner au constructeur Case la position associée à la valeur courante
            for (int i=0;i<hauteur;i++){
                    for (int j=0;j<largeur;j++){
                    	int hasard = (int) (Math.random()*2);
                    	if (((nbCasesNonJouable>0)&&(hasard == 0))||(listeNombres.size()==0)){
                    		nbCasesNonJouable--;
                    		grille[i][j]=null;
                    	}else{
                    		  int indiceAuHasard = (int) (Math.random() * (listeNombres.size()-1)); //choisit un nombre au hasard dans la liste
                    		  nbCourant=listeNombres.remove(indiceAuHasard);
                              grille[i][j]= new Case(nbCourant, compteurPosition, i ,j);
                              if(nbCourant==0){
                                      vide=grille[i][j];
                              }
                              if (compteurPosition == (nbCases-1)){
                              	compteurPosition=0;
                              }else{
                              	compteurPosition ++;
                              }	
                    	}
                          
                    }
            }

    }while(!this.solvable()); // génère une nouvelle grille tant qu'elle n'est pas solvable
}


	/**
	 * L'appel de cette méthode sur un jeu de taquin sert a tester sa solvabilité
	 * @return vrai si le jeu est solvable / faux sinon
	 */
	public boolean solvable(){
		Taquin test = this.clone();
		boolean solSimple = (pariteCaseVide() == test.pariteNombreDeplacement());
		if ((!this.rectangle)&&(solSimple)){
			/*Graphe test = new Graphe ()
			 * on appliquera ici notre meilleur algorithme pour voir si il est possible d'aboutir à
			 * une solution
			 */
			return true ; // en attendant
		}else{
			return solSimple ;
		}
	}
	/**
	 * test la parité de la position de la case vide (utilisé pour tester la solvabilité)
	 * @return vrai si la case est sur une position pair / faux sinon
	 */
	public boolean pariteCaseVide (){
		return ((this.distanceDeManhattanCase(vide)%2)==0);
	}
	/**
	 * test la parité du nombre de déplacements nécessaire pour atteindre la position de solution
	 * (utilisé pour tester la solvabilité)
	 * @return vrai si le nombre de déplacements / faux sinon
	 */
	public boolean pariteNombreDeplacement (){
		int nbDeplacement=0;
		for (int i=0;i<hauteur;i++){
			for(int j=0;j<largeur;j++){
				if((grille[i][j]!=null)&&(grille[i][j].getValeur() != grille[i][j].getPosition() )){
					nbDeplacement++;
					permutation (grille[i][j],retrouverCaseAyantLaValeur(grille[i][j].getPosition()));
				}
			}
		}
		return ((nbDeplacement%2)==0);
	}
	/**
	 * cette méthode permet de retrouver une case du jeu de taquin grâce a sa valeur
	 * dans le jeu
	 * @param position : valeur dans le jeu de la case à trouver (pour plus de précision sur 
	 * la position d'une case voir la class Case)
	 * @return la case lié a la position donné en paramètre
	 */
	public Case retrouverCaseAyantLaValeur(int position){
		Case aTrouver = new Case();
		for (int i=0 ; i<hauteur ; i++){
			for (int j=0 ; j<largeur; j++){
				if((grille[i][j]!=null)&&(grille[i][j].getValeur()==position)){
					aTrouver = grille[i][j];
				}
			}
		}
		return aTrouver;
	}
	/**
	 * cette méthode permet de retrouver une case du jeu de taquin grâce à sa position
	 * dans le jeu
	 * @param valeur : position dans le jeu de la case a trouver
	 * @return la case lié a la valeur donné en paramètre
	 */
	public Case retrouverCaseALaPosition (int valeur){
		Case aTrouver = new Case();
		for (int i=0 ; i<hauteur ; i++){
			for (int j=0 ; j<largeur; j++){
				if((grille[i][j]!=null)&&(grille[i][j].getPosition()==valeur)){
					aTrouver = grille[i][j];
				}
			}
		}
		return aTrouver;
	}
	/**
	 * méthode toString classique pour l'affichage
	 */
	public String toString (){
		String s ="";
		for (int i=0 ; i<hauteur ;i++){
			for (int j=0 ; j<largeur ; j++){
				if (grille[i][j]==null){
					s+= "| X";
				}else{
					s+= "| "+grille[i][j];
				}
			}
			s+= " | \n";
		}
		return s;
	}
	/**
	 * Test si la position du jeu de taquin est une position de victoire
	 * @return vrai si la position est gagnante / faux sinon
	 */
	public boolean testVictoire (){
		for (int i=0 ; i<hauteur ; i++){
			for (int j=0 ; j <largeur ; j++){
				if((grille[i][j]!= null)&&(grille[i][j].getValeur() != grille[i][j].getPosition() )){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * donne les coups jouables depuis une position du jeu de taquin
	 * @return les coups jouables sous la forme d'une liste de mouvement (en chaîne de caractère)
	 */
	public HashMap<String,Case> choixPossibles(){
		HashMap<String,Case> choixPossibles = new HashMap<String,Case>();
		if ((vide.getLargeur()-1>=0)&&(grille[vide.getHauteur()][vide.getLargeur()-1]!=null)){ // le -1 servira pour les grille non rectangulaire
			choixPossibles.put("Gauche",grille[vide.getHauteur()][vide.getLargeur()-1]);
		}
		if ((vide.getLargeur()+1<largeur)&&(grille[vide.getHauteur()][vide.getLargeur()+1]!=null)){ // le -1 servira pour les grille non rectangulaire
			choixPossibles.put("Droite",grille[vide.getHauteur()][vide.getLargeur()+1]);
		}
		if ((vide.getHauteur()-1>=0)&&(grille[vide.getHauteur()-1][vide.getLargeur()]!=null)){ // le -1 servira pour les grille non rectangulaire
			choixPossibles.put("Haut",grille[vide.getHauteur()-1][vide.getLargeur()]);
		}
		if ((vide.getHauteur()+1<hauteur)&&(grille[vide.getHauteur()+1][vide.getLargeur()]!=null)){ // le -1 servira pour les grille non rectangulaire
			choixPossibles.put("Bas",grille[vide.getHauteur()+1][vide.getLargeur()]);
		}
		return choixPossibles;
	}
	/**
	 * permet de s'assurer que l'utilisateur rentre un mouvement possible
	 * @return un mouvement possible ou l'instruction d'abandon sous
	 *  forme de chaîne de caractère 
	 */
	public String choixDeplacement (){
		HashMap<String,Case> choixPossibles = this.choixPossibles();
		Set<String> cle = choixPossibles.keySet();
		Iterator<String> it = cle.iterator();
		while (it.hasNext()){
			String choix = it.next();
			System.out.println(choix);
		}
		System.out.println("Abandonner");
		choixPossibles.put("Abandonner",null);
		String choix = "";
		do{
			choix = in.nextLine();
			if (!choixPossibles.containsKey(choix)){
				System.out.println("Erreur de saisie vérifiez la syntaxe "
						+ "de la case choisie (attention aux majuscules)");
			}
		}while (!choixPossibles.containsKey(choix));
		return choix;
	}
	/**
	 * change la position d'une case avec la case vide
	 * @param arrivé : la case a échangé avec la case vide
	 */
	public void permutation (Case arrivé){
		this.vide.setValeur(arrivé.getValeur());
		arrivé.setValeur(0) ;
		this.vide = arrivé;
	}
	/**
	 * échange la position de deux cases
	 * @param départ premiere case a échanger
	 * @param arrivé deuxième case a échanger
	 */
	public void permutation (Case départ,Case arrivé){
		if (départ==vide){
			permutation(arrivé);
		}else{
			if (arrivé == vide){
				permutation(départ);
			}else{
				int valeur = départ.getValeur();
				départ.setValeur(arrivé.getValeur())  ;
				arrivé.setValeur(valeur);
			}
		}
		
	}
	/**
	 * déplace la case vide dans une direction
	 * @param choixCase la direction dans laquelle va être déplacé la case vide
	 */
	public void permutation (String choixCase){
		HashMap<String,Case> possibles = choixPossibles();
		if (possibles.containsKey(choixCase)){
			this.permutation(possibles.get(choixCase));
		}
	}
	/**
	 * effectue une permutation sur le jeu de taquin relativement au caractère entré en paramètre
	 * @param choixCase première lettre de la direction dans laquel on se dirige
	 */
	public void permutation (char choixCase){
		switch(choixCase){
		case ('D'):
			this.permutation("Droite");
		    break;
		case ('G'):
			this.permutation("Gauche");
			break;
		case ('H'):
			this.permutation("Haut");
			break;
		case ('B'):
			this.permutation("Bas");
			break;
		}
	}
	/**
	 * On a redéfini un HashCode pour pouvoir utilisé le contains de l'ArrayList ayant
	 * en clé des positions du jeu de taquin , d'une position à une autre dans notre utilisation
	 * de la class Taquin la seule chose qui doit changé c'est le contenu de la grille et non
	 * sa forme , on a donc trouvé un moyen de testé l'égalité du contenu de deux tableaux .
	 * 
	 * Pour se faire on s'est inspiré de la méthode utilisé pour écrire les nombres en base 2,
	 * 8 ou 16 .
	 * 
	 * Par exemple pour un jeu de Taquin 2x2 de cette forme :
	 * contenu : |2|1|  position : |1|2|  nbCases = 4 
	 *           |3|0|             |3|0|
	 *           
	 * le hashCode nous donne : valeurCase1 + valeurCase2 + valeurCase3 + valeurCase0
	 * = (2*4^1) + (1*4^2) + (3*4^3) + (0*4^0)
	 * 
	 * On récupère ensuite le reste de la division du resultat obtenue par 2^32 car un int 
	 * est codé sur 32 bits et on peux avoir plus de position de jeu de taquin que 2^32
	 * (c'est le cas dans le cas d'un jeu de taquin 4x4 par exemple)
	 * 
	 * De cette sorte deux instances de taquin
	 * ayant le même contenu on également le même hashCode et on limite au maximum la duplication
	 * de hashcode 
	 */
	@Override
	public int hashCode() {
		long result = 0;
		for (int i=0 ; i<hauteur ; i++){
			for (int j=0 ; j<largeur ; j++){
				result += this.grille[i][j].getValeur()*Math.pow(this.nbCases,this.grille[i][j].getPosition());
			}
		}
		int hashcode = (int) (result%Math.pow(2, 32));
		return hashcode;
	}
	/**
	 * méthode equals basé sur le contenu du jeu de Taquin
	 */
	@Override
	public boolean equals (Object autre){
		if (!(autre instanceof Taquin)){
			return false;
		}else{
			Taquin other = (Taquin) autre;
			if (largeur!=other.largeur){
				return false;
			}
			if (hauteur!=other.hauteur){
				return false;
			}
			if (nbCases!=other.nbCases){
				return false;
			}
			if (!vide.equals(other.vide)){
				return false;
			}
			for (int i=0 ; i<hauteur ; i++){
				for (int j=0 ; j<largeur ; j++){
					if (!grille[i][j].equals(other.grille[i][j])){
						return false;
					}
				}
			}
			return true;
		}
	}
	/**
	 * ressort une copie de l'instance de jeu de taquin qui appel cette méthode
	 */
	public Taquin clone(){
		Taquin clone = new Taquin ();
		clone.hauteur = hauteur;
		clone.largeur = largeur;
		clone.grille = new Case[hauteur][largeur];
		clone.nbCases = nbCases;
		for (int i=0 ; i<hauteur ; i++){
			for (int j=0 ; j<largeur ; j++){
				if (grille[i][j]!=null){
					clone.grille[i][j]= grille[i][j].clone();
					if (clone.grille[i][j].getValeur() == 0){
						clone.vide = clone.grille[i][j];
					}
				}else{
					clone.grille[i][j]=null;
				}
			}
		}
		return clone;
	}

	public int distanceDeManhattanCase (Case aCalculer){
		Case aAtteindre = this.retrouverCaseALaPosition(aCalculer.getValeur());
		return distanceEntreCase (aCalculer,aAtteindre);
	}
	/**
	 * Calcul la distance de manhattan du jeu de taquin qui vaut la somme des distances de
	 * Manhattan de toute les cases du jeu 
	 * @return distance de Manhattan de la position du jeu de taquin qui appel la méthode
	 */
	public int distanceDeManhattan (){
		int distanceDeManhattan = 0;
		for (int i = 0 ; i < hauteur ; i++){
			for (int j=0 ; j<largeur ; j++){
				if ((grille[i][j]!=null)&&(grille[i][j].getPosition()!=grille[i][j].getValeur())){
					distanceDeManhattan += this.distanceDeManhattanCase(grille[i][j]);
				}
			}
		}
		return distanceDeManhattan;
	}
	/**
	 * Calcul la distanceDeManhattanPondérée d'une position du jeu de taquin (pour plus de détail se référé au rapport de projet)
	 * @return distance de manhattan pondérée de la position du jeu de taquin instancié
	 */
	public int distanceDeManhattanPondérée(){
		int distanceDeManhattanPondérée = 0;
		Case positionZéro = retrouverCaseALaPosition(0);
		for (int i = 0 ; i < hauteur ; i++){
			for (int j=0 ; j<largeur ; j++){
				if ((grille[i][j]!=null)&&(grille[i][j].getPosition()!=grille[i][j].getValeur())&&(grille[i][j].getValeur()!=0)){
					distanceDeManhattanPondérée += (this.distanceDeManhattanCase(grille[i][j])*this.distanceEntreCase(grille[i][j], positionZéro));
				}
			}
		}
		return distanceDeManhattanPondérée;
	}
	/**
	 * retourne la distance en terme de nb de case a parcourir séparant les deux cases passés en paramètres
	 * @param départ case de départ 
	 * @param arrivée case d'arrivée
	 * @return distance entre les deux cases
	 */
	public int distanceEntreCase (Case départ , Case arrivée){
		return Math.abs(départ.getHauteur()-arrivée.getHauteur())+Math.abs(départ.getLargeur()-arrivée.getLargeur());
	}
	public void permutationInverse (char déplacement){
		switch(déplacement){
		case ('D'):
			this.permutation("Gauche");
		    break;
		case ('G'):
			this.permutation("Droite");
			break;
		case ('H'):
			this.permutation("Bas");
			break;
		case ('B'):
			this.permutation("Haut");
			break;
		}
	}
}	