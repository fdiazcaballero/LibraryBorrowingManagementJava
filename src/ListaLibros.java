    /**
	*
	*Clase ListaLibros
    *
	*Esta clase incorpora las funcionalidades de la clase Lista y añade el metodo buscar
	*
    *Hereda de la clase Lista porque el metodo buscar es necesario definirlo para esta clase
	*en particular debido al tipo de objetos que va a contener (Libro)
	*
	*@author Fernando Diaz 
	*@author Taoufik Aadia
 	*@version 2.1
	*@see Lista
	*
	*/
    public class ListaLibros extends Lista{
   
	  /**
   	  *Crea un objeto de esta clase, llamando al constructor con parametros de la super-clase
	  *
	  *@param incremento cantidad que se incrementa cada vez que se llena el array
	  *@see Lista#Lista(int)
   	  */
       public ListaLibros(int incremento){
        super(incremento);
      }
   	  
	  /**
   	  *Crea un objeto de esta clase, llamando al constructor sin parametros de la super-clase
	  *
	  *@see Lista#Lista()
   	  */
       public ListaLibros(){
         super();
      }

   
	      
		
	  /**
	  *Devuelve la posicion de un libro en el array dado su isbn
	  *
	  *@param isbn codigo numerico del libro
	  *@return entero que indica la posicion del array, si no se encuentra devuelve -1
	  */
       public int buscarLibro(int isbn){
		 
		   //entero que se va a devolver
         int pos=0;
        
         //Se recorre el array y solo se para si se acaba el array o si el isbn coincide con el que buscamos                  
         while(pos<posActual&&((Libro)array[pos]).getISBN()!=isbn)
            pos++;
         
         //Si se ha parado porque se acaba el array es porque no se ha encontrado el libro, se
		 //devuelve -1
         if(pos>=posActual)
            pos=-1;
            
            
         //Se devuelve pos
         return pos;
      }
   	
		     
   }//Fin de la clase ListaLibros
   
   
