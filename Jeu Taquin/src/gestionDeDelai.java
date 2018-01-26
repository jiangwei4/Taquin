import java.util.*;
public class gestionDeDelai extends Thread {
	public void run(int delai){
		long start = System.currentTimeMillis();
	    while( System.currentTimeMillis() < ( start + (delai))) {
	      
	    }
	    this.interrupt();
	}
}
