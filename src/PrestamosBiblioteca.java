   import java.io.FileNotFoundException;
		
	/**
	*
	*Clase PrestamosBiblioteca
	*
	* Esta clase se encarga de ejecutar el prorma de la biblioteca, se pasan como parámetros del método main
	* los nombres de tres ficheros, que se localizan en el directorio que contiene el package Biblioteca.
	* Los ficheros deben ir en el orden siguiente: libros.txt usuarios.txt transacciones.txt
	*
	*@author Fernando Diaz 
	*@author Taoufik Aadia
    *@version 2.1
    *
	*@see Biblioteca
	*/	
    public class PrestamosBiblioteca{
    
    /**
    *
    *método que ejecuta el programa
    *El formato de ejecucion es el siguiente: java PrestamosBiblioteca libros.txt usuarios.txt transacciones.txt
    *
    *@param args[] array de String que se le pasa por linea de comandos y que contiene en cada posicion el nombre de uno de los tres ficheros en el siguiente orden: libros.txt usuarios.txt transacciones.txt
    */
       public static void main (String args[]){
       
         
         try{
         	//se crea un objeto de la clase Biblioteca con los tres parámetros de la barra de comandos        
            Biblioteca b=new Biblioteca(args[0], args[1], args[2]);
         	//Se procesa el fichero de transaciones, relizando todas las operaciones que ésto conlleva al programa        
            b.procesarTransacciones();
         	//Se crea el fichero de salidaen el directorio que contiene la carpeta biblioteca
            b.mostrar();
         }
         	
         	//Se coge una posible FileNotFoundException
             catch(FileNotFoundException e){
               System.out.println(e.getMessage());
            }
            
         	//Se coge cualquier Exception que se halla lanzado
             catch(Exception e){
               System.out.println("Error en el programa : "+e.getMessage());
            }
         	
	  }
   }//FIN DE LA CLASE
