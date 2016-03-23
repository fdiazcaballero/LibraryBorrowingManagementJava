	//con el fin de utilizar los m�todos de lectura de ficheros, importamos las clases necesarias
   import java.io.*;
   
   /**
   *
   *Clase LectorLibros
   *
   *Esta clase se encarga de leer el fichero que contiene la lista de libros ( con el formato requerido) y 
   *almacena en memoria los objetos de la clase Usuario mediante su atributo de la clase ListaUsuario
   *@author Fernando Diaz
   *@author Taoufik Aadia
   *@version 2.1
   *
   *@see Fecha
   *@see Usuario
   *@see ListaUsuarios
   *
   */
    public class LectorUsuarios{
   
   
   //Atributos
   	
   	/**
   	*Objeto de la clase ListaUsuarios, almacena los datos obtenidos del fichero procesado
   	*/  
      private ListaUsuarios usuarios;
   
   
   	//Constructores
   	
   	/**
   	*Crea el objeto apartir de un fichero que se le pasa por par�metro.
	*Crea un objeto de la clase ListaUsuarios para inicializar el atributo usuarios
	*donde se van a guardar todos los usuarios de la biblioteca
   	*Este m�todo trata ciertas excepciones que pueden lanzar los m�todos de lectura
   	*
   	*@param users flujo a leer que contiene el fichero de usuarios
	*@see ListaUsuarios#ListaUsuarios()
	*@see Libro#Libro
   	*/   
       public LectorUsuarios(BufferedReader users){
      
        try{
         
         	//Creamos el atributo de ListaUsuarios, anteriormente declarado
            this.usuarios=new ListaUsuarios();
         	
         	//declaramos una variable local de tipo String, cadena que almacena cada linea leida por el m�todo
            String linea=users.readLine();
            
         	//se eliminan las l�neas en blanco que puede contener el fichero al principio.			
            while(linea!=null&&linea.trim().equals(""))
               linea=users.readLine();
            
         	//leemos mientras no se acabe el fichero y mientras no haya lineas en blanco
            while (linea!=null&&!linea.trim().equals("")){
            
              //Se crea una cadena con el fin de almacenar la primera palabra de cada l�nea, lo correspondiente al tipo de usuario
              //para ello cortamos la l�nea desde el principio hasta el primer espacio, y a que se supone que el formato del fichero 
              //respeta el modelo del enunciado 
               String tipo=linea.substring(0,(linea.indexOf(" "))).trim();
            	
              // Una segunda cadena que almacena, el nombre del usuario, esto es, desde el primer espacio en blanco hasta la
              //primera coma, siempre suponiendo que el formato del fichero sigue un modelo 
               String nombre=linea.substring((linea.indexOf(" ")),(linea.indexOf(","))).trim();
            	
            	// Una cadena que almacena la dedicaci�n del usuario, �ltima palabra de cada l�nea, se corresponde a la cadena que
               // abarca desde la coma hasta el final de l�nea, evidentemente.
               String dedicacion=linea.substring((linea.indexOf(",")+1),(linea.length())).trim();
            
                //antes de a�adir se comprueba que este usuario todavia no existe, puesto que el nombre debe ser
				//unico ya que es el unico modo de identificar a un usuario al leer el fichero transacciones.txt
				if(usuarios.buscarUsuario(nombre)==-1){
               		//Apartir de lo anterior , creamos un Objeto de la clase Usuario y lo a�adimos a la lista de usuarios, atributo de la clase
               		usuarios.add(new Usuario(tipo,nombre,dedicacion));
				}
				//Si el usuario ya existia se muestra en la pantalla un mensaje de informacion
				else{
					System.out.println("ADVERTENCIA: No se puede a�adir mas de una vez a la\nbiblioteca"
										+" al usuario => "+nombre);
				}
													
            		
            	//Una vez creado y agregado el usuario a la lista, pasamos a leer la siguiente linea del fichero e intentamos crear
            	//un nuevo objeto Usuario, as� se repite el proceso hasta que no queden m�s l�enas con informaci�n procesable						
               linea=users.readLine();
               
            	//Saltamos las lineas en blanco que pueda haber en medio de las demas, si es que las hay          
               while(linea!=null&&linea.trim().equals(""))
                  linea=users.readLine();
            
            
            }
         	
         	//Cerramos el flujo de lectura de fichero, pasado por par�metro
            users.close();
         }
			
			//Se coge una posible excepcion que se lance en la lectura
			catch(IOException e){
			System.out.println("Error en el buffer de lectura");
			}
        
      }
      
    //METODOS
   		
    /**
	*Devuelve el Objeto usuarios, de la clase ListaUsuarios, es necesaria la existencia de este m�todo para acceder
	*al atributo de la clase, ya que �ste es private.
	*
	*@return unico atributo de la clase 
	*/
     public ListaUsuarios getListaUsuarios(){
         return this.usuarios;
      }
      
   }//Fin de la clase

