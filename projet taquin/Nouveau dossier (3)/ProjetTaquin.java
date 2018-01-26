package taquin;


import java.io.*;
public class ProjetTaquin {
	public static boolean Solvable (String fichier) throws IOException {
		Taquin taquin = new Taquin (fichier);
		return taquin.solvable();
	}
	
	public static void SolvableExp (String fichier) throws IOException {
		Taquin taquin = new Taquin (fichier);
		 taquin.pariteNombreDeplacementExp();
	}
	public static void Jouer (String fichier) throws IOException , NonSolvableException {
		Taquin taquin = new Taquin (fichier);
		if (!taquin.solvable()){
			throw new NonSolvableException ("Ce jeu de taquin n'est pas solvable");
		}
		System.out.println (taquin);
		int compteurDeCoup = 0 ;
		while (!taquin.testVictoire()){
			System.out.println("Quel case voulez vous déplacer sur la case vide ?");
			String choixCase = taquin.choixDeplacement();
			taquin.permutation(choixCase);
			compteurDeCoup++;
			System.out.println (taquin);
		}
		System.out.println("Bravo vous avez gagné !");
		System.out.println("Nombre de coup : "+compteurDeCoup);
	}
	public static void main (String [] args){
		switch (args[0]){
		case ("-name"):
			System.out.println("Ferre Donovan");
			System.out.println("Fournier Paul");
			System.out.println("(Label Louis ?)");
			break;
		case ("-h"):
			System.out.println("-h : affiche une aide interactive");
			System.out.println("-name : affiche les noms des concepteurs du programme");
			System.out.println("-sol 'fichier.taq' -j : retourne true si le jeu de taquin "
					+ "des données du fichier 'fichier.taq' est solvable false sinon");
			System.out.println("-joue 'fichier.taq' : permet de jouer au jeu de taquin "
					+ "avec les données du fichier 'fichier.taq'");
			
			break;
		case ("-sol"):
			try {
				System.out.println(Solvable(args[1]));
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
		case ("-exp"):
			try {
				
				SolvableExp(args[1]);
				System.out.println(Solvable(args[1]));
			}catch (Exception e){
				System.out.println(e);
			}
			break;
			
		}
	}
}

