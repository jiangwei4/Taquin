import java.util.*;
import java.io.*;
public class Taquin {
	private int largeur ; // Largeur de la grille de taquin
	private int hauteur ; // Hauteur de la grille de taquin
	private Case[][] grille ; // grille du jeu de taquin contenant les cases (�quivalent a la position du jeu de taquin)
	private Case vide; // La case vide qui peux �tre d�plac�
	private int nbCases; // nombre de cases jouables dans le jeu (ce param�tre nous servira a effectuer certains controles)
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
	 * Construit une position du jeu de taquin en partant de la position initial et effectuant les d�placements de la case vide
	 * donn�e par Chemin
	 * @param initial position d'initialisation du jeu de taquin (donn�e par le fichier)
	 * @param Chemin s�quence de caract�re repr�sentant les d�placements de la case depuis la position initial pour atteindre la position
	 * cr��
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
	 * @param fichier	la cha�ne de caract�re fichier correspond au nom du fichier rentr� en 
	 * param�tre par l'utilisateur depuis lequel sera cr�� le jeu de taquin
	 * @throws IOException utile pour �viter les probl�mes li� a la lecture du fichier
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
							throw new IOException ("Une des cases a une valeur incoh�rente");
						}else{
							if(caseDejaPlace.contains(grille[i][j])){
								sc.close();
								throw new IOException ("Une case a �t� plac� deux fois");
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
	 * Constructeur de jeu de taquin remplit al�atoirement
	 * @param hauteur hauteur du jeu de taquin a cr�er
	 * @param largeur largeur du jeu de taquin a cr�er
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
                    listeNombres.add(i); //cr�� la liste de nombres qui composera le jeu de taquin dans une arrayList
            }

            int compteurPosition=1;//cette variable permettra de renseigner au constructeur Case la position associ�e � la valeur courante
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

    }while(!this.solvable()); // g�n�re une nouvelle grille tant qu'elle n'est pas solvable
}


	/**
	 * L'appel de cette m�thode sur un jeu de taquin sert a tester sa solvabilit�
	 * @return vrai si le jeu est solvable / faux sinon
	 */
	public boolean solvable(){
		Taquin test = this.clone();
		boolean solSimple = (pariteCaseVide() == test.pariteNombreDeplacement());
		if ((!this.rectangle)&&(solSimple)){
			/*Graphe test = new Graphe ()
			 * on appliquera ici notre meilleur algorithme pour voir si il est possible d'aboutir �
			 * une solution
			 */
			return true ; // en attendant
		}else{
			return solSimple ;
		}
	}
	/**
	 * test la parit� de la position de la case vide (utilis� pour tester la solvabilit�)
	 * @return vrai si la case est sur une position pair / faux sinon
	 */
	public boolean pariteCaseVide (){
		return ((this.distanceDeManhattanCase(vide)%2)==0);
	}
	/**
	 * test la parit� du nombre de d�placements n�cessaire pour atteindre la position de solution
	 * (utilis� pour tester la solvabilit�)
	 * @return vrai si le nombre de d�placements / faux sinon
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
	 * cette m�thode permet de retrouver une case du jeu de taquin gr�ce a sa valeur
	 * dans le jeu
	 * @param position : valeur dans le jeu de la case � trouver (pour plus de pr�cision sur 
	 * la position d'une case voir la class Case)
	 * @return la case li� a la position donn� en param�tre
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
	 * cette m�thode permet de retrouver une case du jeu de taquin gr�ce � sa position
	 * dans le jeu
	 * @param valeur : position dans le jeu de la case a trouver
	 * @return la case li� a la valeur donn� en param�tre
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
	 * m�thode toString classique pour l'affichage
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
	 * @return les coups jouables sous la forme d'une liste de mouvement (en cha�ne de caract�re)
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
	 *  forme de cha�ne de caract�re 
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
				System.out.println("Erreur de saisie v�rifiez la syntaxe "
						+ "de la case choisie (attention aux majuscules)");
			}
		}while (!choixPossibles.containsKey(choix));
		return choix;
	}
	/**
	 * change la position d'une case avec la case vide
	 * @param arriv� : la case a �chang� avec la case vide
	 */
	public void permutation (Case arriv�){
		this.vide.setValeur(arriv�.getValeur());
		arriv�.setValeur(0) ;
		this.vide = arriv�;
	}
	/**
	 * �change la position de deux cases
	 * @param d�part premiere case a �changer
	 * @param arriv� deuxi�me case a �changer
	 */
	public void permutation (Case d�part,Case arriv�){
		if (d�part==vide){
			permutation(arriv�);
		}else{
			if (arriv� == vide){
				permutation(d�part);
			}else{
				int valeur = d�part.getValeur();
				d�part.setValeur(arriv�.getValeur())  ;
				arriv�.setValeur(valeur);
			}
		}
		
	}
	/**
	 * d�place la case vide dans une direction
	 * @param choixCase la direction dans laquelle va �tre d�plac� la case vide
	 */
	public void permutation (String choixCase){
		HashMap<String,Case> possibles = choixPossibles();
		if (possibles.containsKey(choixCase)){
			this.permutation(possibles.get(choixCase));
		}
	}
	/**
	 * effectue une permutation sur le jeu de taquin relativement au caract�re entr� en param�tre
	 * @param choixCase premi�re lettre de la direction dans laquel on se dirige
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
	 * On a red�fini un HashCode pour pouvoir utilis� le contains de l'ArrayList ayant
	 * en cl� des positions du jeu de taquin , d'une position � une autre dans notre utilisation
	 * de la class Taquin la seule chose qui doit chang� c'est le contenu de la grille et non
	 * sa forme , on a donc trouv� un moyen de test� l'�galit� du contenu de deux tableaux .
	 * 
	 * Pour se faire on s'est inspir� de la m�thode utilis� pour �crire les nombres en base 2,
	 * 8 ou 16 .
	 * 
	 * Par exemple pour un jeu de Taquin 2x2 de cette forme :
	 * contenu : |2|1|  position : |1|2|  nbCases = 4 
	 *           |3|0|             |3|0|
	 *           
	 * le hashCode nous donne : valeurCase1 + valeurCase2 + valeurCase3 + valeurCase0
	 * = (2*4^1) + (1*4^2) + (3*4^3) + (0*4^0)
	 * 
	 * On r�cup�re ensuite le reste de la division du resultat obtenue par 2^32 car un int 
	 * est cod� sur 32 bits et on peux avoir plus de position de jeu de taquin que 2^32
	 * (c'est le cas dans le cas d'un jeu de taquin 4x4 par exemple)
	 * 
	 * De cette sorte deux instances de taquin
	 * ayant le m�me contenu on �galement le m�me hashCode et on limite au maximum la duplication
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
	 * m�thode equals bas� sur le contenu du jeu de Taquin
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
	 * ressort une copie de l'instance de jeu de taquin qui appel cette m�thode
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
	 * @return distance de Manhattan de la position du jeu de taquin qui appel la m�thode
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
	 * Calcul la distanceDeManhattanPond�r�e d'une position du jeu de taquin (pour plus de d�tail se r�f�r� au rapport de projet)
	 * @return distance de manhattan pond�r�e de la position du jeu de taquin instanci�
	 */
	public int distanceDeManhattanPond�r�e(){
		int distanceDeManhattanPond�r�e = 0;
		Case positionZ�ro = retrouverCaseALaPosition(0);
		for (int i = 0 ; i < hauteur ; i++){
			for (int j=0 ; j<largeur ; j++){
				if ((grille[i][j]!=null)&&(grille[i][j].getPosition()!=grille[i][j].getValeur())&&(grille[i][j].getValeur()!=0)){
					distanceDeManhattanPond�r�e += (this.distanceDeManhattanCase(grille[i][j])*this.distanceEntreCase(grille[i][j], positionZ�ro));
				}
			}
		}
		return distanceDeManhattanPond�r�e;
	}
	/**
	 * retourne la distance en terme de nb de case a parcourir s�parant les deux cases pass�s en param�tres
	 * @param d�part case de d�part 
	 * @param arriv�e case d'arriv�e
	 * @return distance entre les deux cases
	 */
	public int distanceEntreCase (Case d�part , Case arriv�e){
		return Math.abs(d�part.getHauteur()-arriv�e.getHauteur())+Math.abs(d�part.getLargeur()-arriv�e.getLargeur());
	}
	public void permutationInverse (char d�placement){
		switch(d�placement){
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