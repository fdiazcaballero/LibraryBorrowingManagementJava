     /**
	 *
	 *Clase Usuario
	 *
	 *representa un usuario de la biblioteca y contiene las funciones necesarias
	 *para gestionar su prestamo de libros
	 *
	 *@author Fernando Diaz && Taoufik Aadia
 	 *@version 2.1
	 *@see ListaLibros
	 *
	 */
    public class Usuario{
    
    //atributos
    
    /** 
   	*tipo de usuario (profesor, pas ó alumno)
   	*/  
      private String tipoUsuario;
   	
   	/**
   	*nombre del usuario
   	*/
      private String nombre;
   	
   	/**
   	*Objeto de la clase ListaLibros que contiene referencias a los libros que 
   	*el usuario tiene prestados, si no tiene ningun libro prestado apunta a null
   	*/
      private ListaLibros librosPrestados;
   	
   	/**
   	*dependiendo del tipo de usuario que sea la ocupacion será el nombre del departamento,
   	*carrera, o unidad a la que pertenece
   	*/
      private String ocupacion;
      
   	   	
   	/**
   	*array de enteros que va a contener en cada posicion un contador del numero de libros de 
   	*un tipo que el usuario tiene prestado. La posicion 0 apunta al contador de libros de fondo, 
   	*la posicion 1 al de sala, la 2 al de especial, y la 3 al de creacion
   	*/
      private int []numEjemplares;
	//Al ser un array nos evitamos tener que declarar cuatro atributos para cada contador por separado 
         
   	//constructor
   	
   	/**
   	*Crea un objeto Usuario
   	*
   	*@param tipoUsuario tipo del usuario
   	*@param nombre nombre del usuario
   	*@param ocupacion ocupacion del usuario
   	*/
       public Usuario(String tipoUsuario, String nombre, String ocupacion){
       
         //Se inicializan los atributos
         this.tipoUsuario=tipoUsuario;
         this.nombre=nombre;
         this.ocupacion=ocupacion;
         
      	//como aún no se le ha prestado ningun libro "librosPrestados" apunta a null
         this.librosPrestados=null;
      	
      	//numEjemplares será un array de enteros de tamaño 4 que es el numero de tipos de libro que hay
         this.numEjemplares=new int[4];
         
      	//Se hace apuntar todas las posiciones del array numEjemplares a 0, ya que aun no 
      	//tiene prestado ningun ejemplar de ningun tipo
         for(int i=0;i<numEjemplares.length;i++)
            numEjemplares[i]=0;      
      	
      }
   	
   	
   	//metodos

	/**
   	*Devuelve el nombre del usuario
   	*@return cadena que contiene el nombre del usuario
   	*/
       public String getNombre(){
         return this.nombre;
      }
      
   	/**
   	*Devuelve el tipo de usuario
   	*@return cadena que contiene el tipo del usuario
   	*/
       public String getTipoUsuario(){
         return this.tipoUsuario;
      }
   	
   	/**
   	*Devuelve un objeto de la clase ListaLibros que contiene referencias de los libros 
   	*que se encuentran prestados al usuario en este momento
   	*@return atributo librosPrestados
   	*/
       public ListaLibros getLibrosPrestados(){
         return this.librosPrestados;
      }
      
   	/**
   	*Devuelve el numero de libros del tipo "fondo especializado" que el usuario
   	*tiene en este momento
   	*@return posicion 0 del array "numEjemplares" que contiene este contador
   	*/
       public int getNumFondo(){
         return this.numEjemplares[0];
      }
      
   	/**
   	*Devuelve el numero de libros del tipo "libros de sala" que el usuario
   	*tiene en este momento
   	*@return posicion 1 del array "numEjemplares" que contiene este contador
   	*/
       public int getNumSala(){
         return numEjemplares[1];
      }
   	
   	/**
   	*Devuelve el numero de libros del tipo "materiales especiales" que el usuario
   	*tiene en este momento
   	*@return posicion 2 del array "numEjemplares" que contiene este contador
   	*/
       public int getNumEspecial(){
         return numEjemplares[2];
      }
   	
   	/**
   	*Devuelve el numero de libros del tipo "obras de creación" que el usuario
   	*tiene en este momento
   	*@return posicion 3 del array "numEjemplares" que contiene este contador
   	*/
       public int getNumCreacion(){
         return numEjemplares[3];
      }
   	
     /**
   	 *Este metodo sirve para añadir un libro al atributo "librosPrestados" cuando se hace un 
   	 *préstamo al usuario, y para incrementar en 1 el contador correspondiente al tipo de libro
   	 *
   	 *@param libro libro que se presta al usuario y que hay que añadir al atributo librosPrestados
   	 *@see ListaLibros#add(Object libro)
   	 */
       public void añadirLibro(Libro libro){
       
       //array de contiene los tipos de libro que hay
         String []tipoLibros={"fondo","sala","especial","creacion"};
      
         //si librosPrestados apunta a null, se crea un nuevo objeto de la clase ListaLibros
         if(librosPrestados==null)
            librosPrestados=new ListaLibros();
            
      	//se utiliza el metodo add de la clase ListaLibros para añadir "libro" al atributo 
      	//librosPrestados	
         librosPrestados.add(libro);
      	
      	//mediante este bucle while se obtiene la posicion del array tipoLibros que contiene
      	//el tipo de este libro
         int pos=0;
         while(pos<tipoLibros.length&&!libro.getTipo().equals(tipoLibros[pos]))
            pos++;
         
      	/*como numEjemplares contiene en cada posicion el contador del tipo de libro que 
      	*aparece en la misma posicion del array "tipoLibros", y que es la que se acaba de 
      	*obtener en el entero "pos" de acuerdo con el tipo de libro que se quiere añadir
      	*se incrementa esa posicion del array*/
         this.numEjemplares[pos]++;
      
      }
       
   	
      
    /**
   	*Este metodo comprueba si el usuario tiene un número de libros inferior
   	*al máximo numero de libros permitido de acuerdo al tipo de usuario que 
   	*es y al tipo de libro que se le presta
   	*
   	*@param tipoLibro tipo de libro que se le va a prestar
   	*@return true si es apto para el prestamo y no sobrepasa o iguala el numero maximo de libros que tiene permitido, false en caso contrario
   	*/
       public boolean apto(String tipoLibro){
         
      	//variable a devolver, por defecto apunta a true
         boolean b=true;
      	
      	/*
         *array de dos dimensiones, hay una posicion para cada tipo de usuario (profesor, pas y alumno, en este orden)
         *y en cada una de esas posiciones se apunta hacia otro array que contiene el número máximo de 
         *libros de cada tipo que ese usuario puede tener, en este orden: fondo, sala, especial, creacion
         */ 
         int [][]tablaEjemplares={{5,5,2,2},{5,3,2,2},{2,2,2,2}};
      	
      	//array que contiene los tipos de usuario que hay
         String []tiposUsuario={"profesor","pas","alumno"};
      	
      	//array de contiene los tipos de libro que hay
         String []tipoLibros={"fondo","sala","especial","creacion"};
         
      	/*dos marcadores para indicar la posicion del array de dos dimensiones 
      	*"tablaEjemplares" (ya que una dimension corresponde con los tipos de usuario,
      	*y otra con los tipos de libro) que contiene el maximo de libros*/
         int j=0;
         int i=0;
         
      	//en este bucle while se halla la posicion que corresponde al tipo de usuario
         while(j<tiposUsuario.length&&!tipoUsuario.equals(tiposUsuario[j]))
            j++;
         
         //en este bucle while se halla la posicion que corresponde al tipo de libro
         while(i<tipoLibros.length&&!tipoLibro.equals(tipoLibros[i]))
            i++;
         
      	//Si el contador es mayor o igual que el numero maximo de libros (que se encuentra 
      	//en la posicion hallada de "tablaEjemplares"), se hace que b apunte a false (no apto)
         if(this.numEjemplares[i]>=tablaEjemplares[j][i])
            b=false;        
                 
            
         return b;
      }
   	
      
   	 /**
   	 *
   	 *La funcion de este método es comprobar si el usuario tiene algun libro de la biblioteca
   	 *que ya ha sobrepasado la fecha en que debia ser devuelto, ya que si se da esta circunstancia
   	 *el usuario ya no podra volver a sacar ningun libro hasta que no devuelva el/los que tiene
   	 *pasado/s de fecha
   	 *
   	 *@param fechaActual se le pasa la fecha actual para que la compare con la adjudicada a cada libro para ser devuelto
   	 *@return true si el usuario no tiene ningun libro pasado de fecha, false si tiene uno o mas libros pasados de fecha
   	 *
   	 *@see Fecha#esAnterior(Fecha)
   	 * 
   	 */
       public boolean consultarFecha(Fecha fechaActual){
       
         //variable a devolver, por defecto true
         boolean b=true;
         
      	//Se comprueba si tiene libros prestados
         if(this.librosPrestados!=null){
                   
         	//Se recorre el array de libros prestados, cuando b valga false se sale del bucle		 
            for(int i=0;i<librosPrestados.size()&&b;i++){
            
               //Si la fecha de devolucion de algun libro es anterior a la fecha actual (ya 
            	//deberia estar devuelto), se hace apuntar b a false
               if(((Libro)(librosPrestados.elementAt(i))).getFechaDevolucion().esAnterior(fechaActual))
                  b=false;
                  
            }
         }
		
		//Se devuelve b
         return b;
      }
         
   		
   	 /**
	 *La función de este metodo es calcular el máximo numero de dias que un usuario 
	 *puede tener un libro dependiendo del tipo de usuario y de libro
	 *
	 *@param tipoLibro tipo del libro
	 *@return numero maximo de dias que se permite a este usuario tener el libro
	 */
       public int numMaxDias(String tipoLibro){
         
		//entero que se va a devolver
         int numDias=0;
      
		//comparamos con pas que alfabeticamente esta en medio de alumno y profesor, si es
		//alumno es el caso -1, pas caso 0, y profesor caso 1		
         switch(this.tipoUsuario.compareTo("pas")<0?-1:this.tipoUsuario.compareTo("pas")>0?1:0){
 				
			//caso: el usuario es un alumno        
            case(-1):
               if(tipoLibro.equals("creacion")) numDias=15;
				//excepto los de creacion todos los puede tener 7 dias
               else numDias=7;
               break;
            
			//caso: el usuario es un pas
            case(0):
               if(tipoLibro.equals("fondo")) numDias=20;
               else if(tipoLibro.equals("creacion")) numDias=15;
               else numDias=7;
               break;
            
 			//caso: el usuario es un profesor           
            case(1):
               if(tipoLibro.equals("fondo"))numDias=30;
               else if(tipoLibro.equals("creacion")) numDias=15;
               else numDias=7;
               break;

			default:
			  //imposible
			   break;
         
         }
         //se devuelve numDias
         return numDias;
      }
    
       /**
	   *Este metodo sirve para quitar un libro del array de libros prestados cuando el usuario
	   *realiza una devolución, y para decrementar el contador correspondiente a ese tipo de 
	   *libro.
	   *
	   *@param poscicion posicion en el array de libros prestados del libro que se va a devolver
	   *@see Lista#remove(int)
	   */
       public void removeBook(int posicion){
		 
        //Se comprueba que la posicion sea correcta 
		 if(posicion>-1&&posicion<librosPrestados.size()){
			
	    //array que contiene los tipos de libro que hay, en el mismo orden que el array que contiene
		//los contadores de cada tipo
         String []tipoLibros={"fondo","sala","especial","creacion"};
      	
		//se averigua el tipo del libro que se quiere devolver
         String tipo=(((Libro)(librosPrestados.elementAt(posicion)))).getTipo();
         
		//se halla la posicion del contador "pos" que se tiene que decrementar
		 int pos=0;
         while(pos<tipoLibros.length&&!tipo.equals(tipoLibros[pos]))
            pos++;
        
		//Se comprueba por seguridad
		if (pos<tipoLibros.length)
		   //Se decrementa esa posicion      
		   this.numEjemplares[pos]--;
         
		//Se elimina el libro que se ha devuelto del array librosPrestados     
         this.librosPrestados.remove(posicion);
		}
      
      }
		
   }//Fin de la clase Usuario