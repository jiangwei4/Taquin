import java.util.*;
import java.io.*;
public class Taquin {
	int largeur ;
	int hauteur ;
	int[][] grille ;
	int largeurCaseVide ;
	int hauteurCaseVide ;
	public static Scanner in = new Scanner (System.in);
	public Taquin (){
	
	}
	public Taquin (String fichier) throws IOException {
		File file = new File (fichier);
		Scanner sc = new Scanner (file);
		boolean caseVidePlace = false;
		ArrayList<Integer> caseDejaPlace = new ArrayList<Integer>();
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
		grille = new int [hauteur][largeur];
		for (int i=0 ; i<hauteur ; i++){
			for (int j=0 ; j<largeur ; j++){
				if (sc.hasNextInt()){
					grille[i][j] = sc.nextInt();
					if (grille[i][j]==0){
						hauteurCaseVide = i;
						largeurCaseVide = j;
						caseVidePlace = true;
					}else{
						if((grille[i][j]<0)||(grille[i][j]>= (hauteur*largeur))){
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
				}else{
					sc.close();
					throw new IOException ("Informations manquantes dans le fichier");
				}
			}
		}
		if (!caseVidePlace){
			sc.close();
			throw new IOException ("Pas de case vide dans le jeu");
		}
		sc.close();
	}
	public String toString (){
		String s ="";
		for (int i=0 ; i<hauteur ;i++){
			for (int j=0 ; j<largeur ; j++){
				s+= "| "+grille[i][j];
			}
			s+= " | \n";
		}
		return s;
	}
	public boolean testVictoire (){
		boolean test = true;
		for (int i=0 ; i<hauteur ; i++){
			for (int j=0 ; j <largeur ; j++){
				if(grille[i][j] != ((largeur*i)+j)){
					test = false;
				}
			}
		}
		return test;
	}
	public String choixDeplacement (){
		ArrayList<String> choixPossibles = new ArrayList<String>();
		if ((largeurCaseVide-1>=0)&&(grille[hauteurCaseVide][largeurCaseVide-1]!=-1)){ // le -1 servira pour les grille non rectangulaire
			System.out.println("Gauche");
			choixPossibles.add("Gauche");
		}
		if ((largeurCaseVide+1<largeur)&&(grille[hauteurCaseVide][largeurCaseVide+1]!=-1)){ // le -1 servira pour les grille non rectangulaire
			System.out.println("Droite");
			choixPossibles.add("Droite");
		}
		if ((hauteurCaseVide-1>=0)&&(grille[hauteurCaseVide-1][largeurCaseVide]!=-1)){ // le -1 servira pour les grille non rectangulaire
			System.out.println("Haut");
			choixPossibles.add("Haut");
		}
		if ((hauteurCaseVide+1<hauteur)&&(grille[hauteurCaseVide+1][largeurCaseVide]!=-1)){ // le -1 servira pour les grille non rectangulaire
			System.out.println("Bas");
			choixPossibles.add("Bas");
		}
		String choix = "";
		do{
			choix = in.nextLine();
			if (!choixPossibles.contains(choix)){
				System.out.println("Erreur de saisie vérifiez la syntaxe "
						+ "de la case choisie (attention aux majuscules)");
			}
		}while (!choixPossibles.contains(choix));
		return choix;
	}
	public void permutation (String choixCase){
		switch (choixCase){
		case ("Gauche"):
			grille[hauteurCaseVide][largeurCaseVide] = grille[hauteurCaseVide][largeurCaseVide-1];
			grille[hauteurCaseVide][largeurCaseVide-1]=0;
			largeurCaseVide --;
			break;
		case ("Droite"):
			grille[hauteurCaseVide][largeurCaseVide] = grille[hauteurCaseVide][largeurCaseVide+1];
			grille[hauteurCaseVide][largeurCaseVide+1]=0;
			largeurCaseVide ++;
			break;
		case ("Haut"):
			grille[hauteurCaseVide][largeurCaseVide] = grille[hauteurCaseVide-1][largeurCaseVide];
			grille[hauteurCaseVide-1][largeurCaseVide]=0;
			hauteurCaseVide --;
			break;
		case ("Bas"):
			grille[hauteurCaseVide][largeurCaseVide] = grille[hauteurCaseVide+1][largeurCaseVide];
			grille[hauteurCaseVide+1][largeurCaseVide]=0;
			hauteurCaseVide ++;
			break;
		}
	}
	public Taquin clone(){
		Taquin clone = new Taquin ();
		clone.hauteur = hauteur;
		clone.largeur = largeur;
		clone.grille = new int[hauteur][largeur];
		clone.hauteurCaseVide = hauteurCaseVide;
		clone.largeurCaseVide = largeurCaseVide;
		for (int i=0 ; i<hauteur ; i++){
			for (int j=0 ; j<largeur ; j++){
				clone.grille[i][j]= grille[i][j];
			}
		}
		return clone;
	}
	public boolean solvable(){
		Taquin test = this.clone();
		return (pariteCaseVide() == test.pariteNombreDeplacement());
	}
	public boolean pariteCaseVide (){
		int positionZero=(hauteurCaseVide*largeur)+(largeurCaseVide);
		return ((positionZero%2)==0);
	}
	public boolean pariteNombreDeplacement (){
		int nbDeplacement=0;
		for (int i=0;i<hauteur;i++){
			for(int j=0;j<largeur;j++){
				if(grille[i][j]!=(i*largeur)+j){
					nbDeplacement++;
					for (int x=0;x<hauteur;x++){
						for(int y=0;y<largeur;y++){
							if(grille[x][y]==(i*largeur)+j){
								grille[x][y]=grille[i][j];
								grille[i][j]=(i*largeur)+j;		
							}
						}
					}
				}
			}
		}
		return ((nbDeplacement%2)==0);
	}
}
