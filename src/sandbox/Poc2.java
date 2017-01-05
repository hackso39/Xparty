package sandbox;

public class Poc2 {

	public static void main(String[] args) {
		// a();
		try {
			methodeLevantUneExceptionSiNombrePaire(12);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	//---
	
	public static int a() {
		
		int resultatDeB = b();
		
		return resultatDeB;
	}
	
	public static int b() {
		
		int resultatDeC;
		
		try {
			
			resultatDeC = c();
			
			
		} catch (Exception e) {
			resultatDeC =  -1;
		}
		
		return resultatDeC;
	}
	
	public static int c() throws Exception {
		return methodeLevantUneExceptionSiNombrePaire(2); // soit elle retourne un entier, soit elle "retourne" une exception
	}
	
	//---
	
	
	/**
	 * M�thode qui retourne le nombre pass� en param�tre.
	 * Ce nombre doit �tre impaire. S'il est pair, une exception est lev�e.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int methodeLevantUneExceptionSiNombrePaire(int nombre) throws Exception {
		
		if (nombre % 2 == 0) {
			throw new Exception("Le nombre est invalide car il est pair.");
		}
		
		if (nombre > 10) {
			throw new Exception("Le nombre est invalide car il est sup�rieur � 10.");
		}
		
		return nombre;
	}

}
