  //Para permitir la lectura de los ficheros, importamos las clases de Java que nos lo permiten
   import java.io.*;
    
	 /**
	 *
	 *Clase Biblioteca
	 *
	 *Esta clase se encarga de la gestion de una biblioteca. 
	 *Sus atributos contienen en memoria todos los objetos Libro y Usuario que 
	 *existen en la biblioteca. 
	 *Se encarga de procesar el fichero transacciones.txt
	 *y actuar en consecuencia gestionando los prestamos y devoluciones de los 
	 *usuarios que se indican en dicho fichero.
	 *Tambien tiene la capacidad de mostrar en un fichero todos los prestamos que 
	 *hay en este mismo momento
	 *
	 *@author Fernando Diaz
	 *@author Taoufik Aadia
 	 *@version 2.1
	 *@see ListaLibros
	 *@see ListaUsuarios
	 *@see Fecha
     *
	 */
    public class Biblioteca{
    
    /**
    *lista de libros que tiene la biblioteca
   	*/
      private ListaLibros libros;
      
    /**
    *lista de usuarios de la biblioteca
    */  
      private ListaUsuarios usuarios;
      
    /**
    *objeto de la clase BufferedReader que contiene el flujo del fichero de transacciones.txt que lleva 
    *todas las ordenes de gestión de la biblioteca
    */  
      private BufferedReader transacciones;
      
    /**
    *Objeto de la clase fecha, contiene la fecha actual, dia en el que se hace una accion del programa
    */   
      private Fecha fechaActual;
   
   /**
   *
   *Método constructor del objeto de la clase, recibe por parámetro los nombres de los 
   *tres ficheros que se pasan al programa, crea los BufferedReader y los objetos necesarios 
   *de LectorLibros y LectorUsuarios (para leer los ficheros) y mediante estos dos ultimos 
   *obtiene dos objetos de las clases ListaLibros y ListaUsuarios que se guardan en los 
   *atributos de esta clase para almacenar en memoria los libros y usuarios de la bibiblioteca
   *
   *@param libros Cadena con el nombre del fichero que contiene los libros que tiene la biblioteca
   *@param usuarios Cadena con el nombre del fichero que contiene los datos de usuarios de la biblioteca
   *@param transacciones Cadena el nonbre del fichero que contiene todas las acciones que se realizan en la biblioteca
   *@throws FileNotFoundException si no existe alguno de los ficheros libros.txt, usuarios.txt o transacciones.txt en el path que se le pasa
   *
   *@see LectorLibros#LectorLibros(BufferedReader)
   *@see LectorUsuarios#LectorUsuarios(BufferedReader)
   *
   */
       public Biblioteca(String libros, String usuarios, String transacciones)throws FileNotFoundException{
      
      	//Se incializa atributo de la clase Fecha a null	
         this.fechaActual=null;
         
      	//Se crea un fichero con el nombre que se ha pasado al método por parámetro
         File file1=new File(libros);
         
      	//Comprobamos que este fichero existe
         if(!file1.exists()){
            throw new FileNotFoundException("el fichero libros.txt no existe en la ruta especificada");
         }
         
      	//Se crea un segundo fichero para lso usuaarios, con el nombre que se ha pasado como segundo parámetro   
         File file2=new File(usuarios);
         
      	//Comprobamos que existe este fichero
         if(!file2.exists()){
            throw new FileNotFoundException("el fichero usuarios.txt no existe en la ruta especificada");
         }
            
      	//Creamos el fichero de las transacciones con el nombre de fichero que se ha pasado por parámetro		
         File file3=new File(transacciones);
         
      	//Comprobamos que existe este fichero
         if(!file3.exists()){
            throw new FileNotFoundException("el fichero transacciones.txt no existe en la ruta especificada");
         }
      
      
        // Una vez seguros de que existen los ficherosnecesarios, creamos los flujos de lectura
      	//y los atributos de la clase necesarios, para llevar la gestión
        
              
         //Creamos los flujos de lectura, de los tres ficheros    
         BufferedReader books=new BufferedReader(new FileReader(file1));
         BufferedReader users=new BufferedReader(new FileReader(file2));
         this.transacciones=new BufferedReader(new FileReader(file3));
           
         //Se crean las listas de usurarios y libros apartir de la lectura de los anteriores ficheros
         this.libros=new LectorLibros(books).getListaLibros();
         this.usuarios=new LectorUsuarios(users).getListaUsuarios();
                  	
        
      }
                                                                                                                                                                                                                                                                                                                                                                              
         
   		
   	  /**
   	  *
   	  *Método que se encarga de procesar la información que contiene 
      *el fichero de transacciones y actuar a tal efecto utilizando otros metodos de
      *esta clase como prestamo y devolucion para realizar los prestamos y devoluciones
      *de libros en la biblioteca
      *                                
   	  *
   	  *@see #prestamo(Usuario,Libro)
   	  *@see #devolucion(int)
   	  *@see Fecha#Fecha(int,int,int)
   	  */	
       public void procesarTransacciones(){
       
         try{
         
         	//Cadena que almacena las líneas del fichero de transacciones a medida que se van leyendo
            String linea=transacciones.readLine();
            
            //Saltamos todas las líneas en blanco que puede tener el fichero en un principio   
            while(linea!=null&&linea.trim().equals(""))
               linea=transacciones.readLine();
               
         		
            // Solo leemos las lineas que nos vayan a aportar información necesaria
            while(linea!=null&&!linea.trim().equals("")){
               
               //Cadena, que almacena la primera palabra de cada línea, lo correspondiente a la accion que 
            	//debe realizar el programa  
               String accion=linea.substring(0,linea.indexOf(" ")).trim();
               
               //Según el formato del fichero si la palabra es "fecha", creamos un objeto de la clase 
            	//correspondiente, pasando como 
               //prámetros del constructor los datos que se deben incluir después de dicha palabra.
               if(accion.equals("fecha")){
               
                 //Escogemos la cadena que va desde el primer espacio en blanco hasta la primera coma, recortada, y hacemos el 
                 //Casting para convertirla en un entero
                  int dia=(new Integer(linea.substring(linea.indexOf(" "),linea.indexOf(",")).trim())).intValue();
                 //Escogemos la cadena que va desde la primera coma hasta la última, recortada, y hacemos el 
                 //Casting para convertirla en un entero
                  int mes=(new Integer(linea.substring(linea.indexOf(",")+1,linea.lastIndexOf(",")).trim())).intValue();
                 //Escogemos la cadena que va desde la ultima coma hasta el final de la línea, recortada, y hacemos el 
                 //Casting para convertirla en un entero
                  int año=(new Integer(linea.substring(linea.lastIndexOf(",")+1,linea.length()).trim())).intValue();
               	
               	//Apartir de las variables locales anteriores, creamos el atributo fechaActual
                  fechaActual=new Fecha(dia,mes,año);
						
					}
               
               //Según el formato del fichero si la palabra es "prestamo", llamamos al método de 
               //esta clase para prestar un libro a un usuario
               //pasandole por parámetro las variables locales que se crean acontinuación
               else if(accion.equals("prestamo")){
               	
               	//entero que almacena la posición de un usuario en la lista almacenada 
                  int posUs=usuarios.buscarUsuario(linea.substring(linea.indexOf(" "),linea.indexOf(",")).trim());
                  
               	//entero que almacena la posicion del Libro en la lista almacenada
                  int posLi=libros.buscarLibro(new Integer(linea.substring(linea.indexOf(",")+1,linea.length()).trim()).intValue());
                  
               	//Se realiza el prestamo si se ha encotrado el libro y el usuario
                  if(posUs>-1&&posLi>-1)
                  //llamamos al método que se encarga de hacer el préstamo pasándole por parámetro el libro 
                  //a prestar y el usuario que se queda el libro  
                     prestamo(((Usuario)(usuarios.elementAt(posUs))),((Libro)(libros.elementAt(posLi))));
                  
                  //en caso contrario (si no se ha encontrado el libro o el usuario) no se realiza 
				  //el prestamo y se imprime un mensaje de advertencia en la pantalla como informacion
                  else{
                     if(posUs==-1)
                        System.out.println("el usuario : "
                                         +linea.substring(linea.indexOf(" "),linea.indexOf(",")).trim()+ " no esta registrado y no se puede llevar un libro\n");
                     if(posLi==-1)
                        System.out.println("Se ha intentado prestar el libro con isbn => "+linea.substring(linea.indexOf(",")+1,linea.length())
                                         +"\nque no existe en la biblioteca o se encuentra ya prestado\n");
                  }
               
               }
               
               
               //Según el formato del fichero si la palabra es "devolucion", llamamos al método de esta clase para devolver el libro de un usuario
               //pasandole por parámetro las variables locales que se crean acontinuación
               else if(accion.equals("devolucion")){
               
                  //entero que almacena el isbn del libro a devolver
                  int isbnLibro=new Integer(linea.substring(linea.indexOf(" "),linea.length()).trim()).intValue();
                  
                  //llamamos al método de la clase que se encarga de realizar la devolucion del libro  
                  devolucion(isbnLibro);
                  
               }
               //Una vez tratada una línea pasamos a leer la siguiente
               linea=transacciones.readLine();
               
            	//Saltamos todas las líneas en blanco que puede tener el fichero
               while(linea!=null&&linea.trim().equals(""))
                  linea=transacciones.readLine();
            
            }
            
         	//Se cierra le flujo de lectura, una vez no queden mas lineas escritas
            transacciones.close();
            
         }  
         	 //Se coge una posible excepcion que se lance en la lectura           
             catch(IOException e){
               System.out.println("Error en la lectura del fichero transacciones.txt");
            }
         	
         	//Se coge una posible excepcion que se lance si los numeros del fichero destinados al isbn no lo son
             catch(NumberFormatException e){
               System.out.println("Error en el formato de algun codigo isbn en el fichero transacciones.txt");
            }
         	
        
            
      }
   	 
   	 /**
   	 *
   	 *método que se encarga de llevar la gestión de un préstamo.
	 *Comprueba si se dan la condiciones (el usuario no debe tener libros pasados de fecha, ni debe 
	 *haber sobrepasado el limite que tiene asignado para el tipo de libros como el que se quiere 
	 *llevar) para que el prestamo se lleve a cabo, y 
	 *en caso afirmativo lo realiza.
   	 *
   	 *@param usuario Usuario que aspira a llevarse el libro
   	 *@param libro Libro que se presta
   	 *
   	 *@see Usuario#apto
	 *@see Usuario#consultarFecha
	 *@see Usuario#numMaxDias
	 *@see Fecha#aumentarFecha
   	 */	 
       private void prestamo(Usuario usuario,Libro libro){
         
      	//Cadena que almacena el tipo del libro que se va a prestar            
         String tipoLibro=libro.getTipo();
                     
         //Si no existe el usuario o el libro a prestar, no se lleva acabo la acción            
         if(usuario!=null&&tipoLibro!=null){
            
         	//almacenamos el tipo de usuario en una cadena , variable local           
            String tipoUsuario=usuario.getTipoUsuario();
            
         	//Si las condiciones del usuario le permiten llevarse el libro , se prosigue el préstamo
            if(usuario.apto(tipoLibro)&&usuario.consultarFecha(fechaActual)){
                         
               
                  
               	//almacenamos en una variable local, la fecha en la que debe ser devuelto el libro prestado            
               Fecha fechaDev=fechaActual.aumentarFecha(usuario.numMaxDias(tipoLibro));
                  
               	//asignamos la fecha anteriormente calculada al atributo del Libro            
               libro.setFechaDevolucion(fechaDev);
                  
                //asignamos el usuario, omo propietario del libro, esto es una referencia del libro 
				//prestado, al Usuario que lo lleva
               libro.setPropietario(usuario);
                  
               	//Se añade el libro a la lista de libros que tiene prestados el usuario               
               usuario.añadirLibro(libro);
                              
            }
                        
         }
                     
      }
      
   	
   	/**
   	*
   	*método que se encarga de llevar la gestión de devolución del libro
   	*
   	*@param code entero que almacena el número de ISBN del libro que se va a devolver 
   	*@see Usuario#removeBook
   	*/
   	
       private void devolucion(int code){
       
       
         //Se realiza la devolucion si se encuentra el libro
         if(libros.buscarLibro(code)>-1){

         //Como cada libro hace referencia al usuario propietario, almacenamos éste en una variable local
            Usuario u=((Libro)libros.elementAt(libros.buscarLibro(code))).getPropietario();
         
            if(u!=null&&u.getLibrosPrestados()!=null){
            // Después de comprobar que efectivamente el usuario tiene el libro prestado, borramos 
			//éste de su lista de libros prestados
               u.removeBook(u.getLibrosPrestados().buscarLibro(code));
            //hacemos que la fecha de devolucion del libro sea null, esto nos marca si un libro está o no prestado
               ((Libro)libros.elementAt(libros.buscarLibro(code))).setFechaDevolucion(null);
            }
			//Si el libro que se quiere devolver no estaba prestado se imprime un mensaje de advertencia
			else
				System.out.println("Se ha intentado devolver el libro con isbn => "+code+" que no se encontraba prestado\n");
         }
		 
		 //Si el libro no se encuentra se imprime por pantalla un mensaje de advertencia
		 else
			 System.out.println("Se ha intentado devolver el libro con isbn => "+code+" que no existe en la biblioteca\n");
      }
   	
      
   	
   	/**
	*Este metodo se encarga de escribir en un fichero llamado salida.txt en el directorio actual
	*todos los prestamos que la biblioteca tiene realizados en el momento en que se utiliza el metodo, con el
	*siguiente formato: nombre_usuario titulo_libro
	*
	*/
       public void mostrar(){
       
         //codigo supceptible de lanzar excepciones
         try{  
         
            //objeto BufferedWriter que va a escribir en un fichero llamado salida.txt, esté si no
			//existe se creará en el directorio actual
            BufferedWriter salida=new BufferedWriter(new FileWriter(new File("salida.txt")));
         
            //si el atributo usuarios es distinto de null
            if(usuarios!=null){
            
               Usuario u=null;
               Libro l=null;
            
			   //se recorre el array de usuarios
               for(int i=0;i<usuarios.size();i++){
                  u=(Usuario)usuarios.elementAt(i);
                  
				  //Si un usuario tiene libros prestados
                  if(u.getLibrosPrestados()!=null){
                  
                     //Se recorre su array de libros prestados
                     for(int j=0;j<u.getLibrosPrestados().size();j++){
						 //Para cada libro que tiene prestado
                        l=(Libro)u.getLibrosPrestados().elementAt(j);
						//Se recoge en una cadena la informacion necesaria para mostrar
                        String prestamo=u.getNombre()+" "+l.getTitulo();
						//Se escribe en el fichero dicha cadena
                        salida.write(prestamo,0,prestamo.length());
						//Se pasa a nueva linea
                        salida.newLine();
                     }
                  
                  
                  }
               }
            }
			
            salida.flush();
			//Se cierra el flujo
            salida.close();
         }
             
             //Se coge una posible excepcion que se lance en la escritura           
             catch(IOException e){
               System.out.println("Error en el buffer de escritura del fichero salida.txt");
               
            }
      
      }
   	
             
         
   
   } //FIN DE LA CLASE
