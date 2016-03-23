 //con el fin de utilizar los m�todos de lectura de ficheros, importamos las clases del paquete java.io
   import java.io.*;
   
  /**
  *
  *Clase LectorLibros
  *
  *Esta clase se encarga de leer el fichero que contiene la lista de libros ( con el formato requerido) y 
  *almacena en memoria los objetos de la clase Libro mediante su atributo de la clase ListaLibros
  *
  *@author Fernando Diaz
  *@author Taoufik Aadia
  *@version 2.1
  *
  *@see Fecha
  *@see Libro
  *@see ListaLibros
  *
  */
   public class LectorLibros{
   
   
   	//Atributos
   	
   	/**
   	*Objeto de la clase ListaLibros, almacena los datos obtenidos del fichero procesado
   	*/  
      private ListaLibros libros;
   	
   	//Constructores
   	
   	/**
   	*Crea el objeto apartir de un fichero que se le pasa por par�metro
   	*Este m�todo trata ciertas excepciones que pueden lanzar los m�todos de lectura
	*Crea un objeto de la clase ListaLibros para inicializar el atributo libros donde 
	*se van a guardar todos los libros de la biblioteca
   	*
   	*@param books BufferedReader que contiene el fichero a leer
	*@see ListaLibros#ListaLibros()
	*@see Libro#Libro
   	*/   
       public LectorLibros(BufferedReader books){
         
         try{
         	
         	//creamos el atributo de ListaLibros, anteriormente declarado    
            libros=new ListaLibros();
         	
			//declaramos una variable local de tipo String, cadena que almacena cada linea leida por el m�todo
            String linea=books.readLine();
         
         	//se eliminan  las l�neas en blanco que puede contener el fichero al principio.			
            while(linea!=null&&linea.trim().equals(""))
               linea=books.readLine();
            
         	//leemos mientras no se acabe el fichero y mientras no haya lineas en blanco
            while (linea!=null&&!linea.trim().equals("")){
            
              /*Se crea una cadena con el fin de almacenar la primera palabra de cada l�nea, lo correspondiente al tipo de libro
              *para ello cortamos la l�nea desde el principio hasta el primer espacio, y a que se supone que el formato del fichero 
              *respeta el modelo del enunciado */
               String tipo=linea.substring(0,(linea.indexOf(" "))).trim();
            
              // Una segunda cadena que almacena, el t�tulo del libro, esto es, desde el primer espacio en blanco hasta la
              //primera coma, siempre suponiendo que el formato del fichero sigue un modelo       
               String titulo=linea.substring((linea.indexOf(" ")),(linea.indexOf(","))).trim();
              
              // Un entero que almacena el n�mero de Isbn, ultima palabra de cada l�nea, se corresponde a la cadena que
              // abarca desde la coma hasta el final de l�nea, evidentemente, hay que hacer el Casting necesario para crea dicho entero
               int isbn=Integer.parseInt(linea.substring((linea.indexOf(",")+1),(linea.length())).trim());
					
		      //Se a�ade el libro a la biblioteca si no se ha a�adido anteriormente un ejemplar igual	
				if(libros.buscarLibro(isbn)==-1){
                    //Apartir de lo anterior , creamos un Objeto de la clase Libro y lo a�adimos a la lista de libros, atributo de la clase
               		libros.add(new Libro(tipo,titulo,isbn));
				}
				//Si ya habia un libro igual se muestra en pantalla un mensaje de advertencia
				else{
					System.out.println("ADVERTENCIA: No se puede a�adir mas de una vez a la\nbiblioteca el libro => "
										+titulo+" "+isbn);
				}							
            
               //Una vez creado y agregado el libro a la lista, pasamos a leer la siguiente linea del fichero e intentamos crear
               //un nuevo objeto libro, as� se repite el proceso hasta que nno queden mas l�enas con informaci�n procesable						
               linea=books.readLine();
            	
               //Saltamos las lineas en blanco que pueda haber en medio, si es que las hay          
               while(linea!=null&&linea.trim().equals(""))
                  linea=books.readLine();
            	
            }
         	
			//Cerramos el flujo de lectura de fichero, pasado por par�metro
            books.close();
         }
              
             //se tratan las excepciones que pueda lanzar
             catch(IOException e){
               System.out.println("Error en el buffer de lectura");
            }
         	
             catch(NumberFormatException e){
               System.out.println("Error en el formato (no es un numero) de algun codigo isbn del fichero libros.txt");
            }
      }
   	
   	//METODOS
   		
    /**
   	*Devuelve el Objeto libros, de la clase ListaLibros, es necesaria la existencia de este m�todo para acceder
   	*al atributo de la clase, ya que �ste es private.
   	*
   	*@return objeto ListaLibros unico atributo de la clase 
   	*/
       public ListaLibros getListaLibros(){
         return this.libros;
      }
     
   }//Fin de la clase
