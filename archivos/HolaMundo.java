 /*
 * Primer programa en java
 *
 *
 */

class HolaMundo{

	public static void main(String[] args){
		System.out.println("Hola Mundo en java");
		if(args.length >0 ){
			System.out.println("Total de Argumentos en linea de comandos: " + args.length);
			for(int i=0; i<args.length; i++){
				if(args[i] != "" || args[i] != null){
					System.out.println("Argumento " + (i+1) +": " + args[i]);
				}
			}
			
		}else{
			System.out.println("No existen argumentos.");
		}
		
	}

}