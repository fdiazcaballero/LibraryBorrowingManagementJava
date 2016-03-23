 	/**
	*
	*Clase ListaUsuarios
    *
	*Esta clase incorpora las funcionalidades de la clase Lista y añade el metodo buscar
	*
    *Hereda de la clase Lista porque el metodo buscar es necesario definirlo para esta clase
	*en particular debido al tipo de objetos que va a contener (Usuario)
	*
	*@author Fernando Diaz 
	*@author Taoufik Aadia
 	*@version 2.1
	*@see Lista
	*
	*/
    public class ListaUsuarios extends Lista{
   
       
   	  /**
   	  *Crea un objeto de esta clase, llamando al constructor con parametros de la super-clase
	  *
	  *@param incremento cantidad que se incrementa cada vez que se llena el array
	  *@see Lista#Lista(int)
   	  */
       public ListaUsuarios(int incremento){
		//se invoca al constructor de la superclase
        super(incremento);
      }
   	
	  /**
   	  *Crea un objeto de esta clase, llamando al constructor sin parametros de la super-clase
	  *
	  *@see Lista#Lista()
   	  */
       public ListaUsuarios(){
         super();
      }

        
   
   	/**
   	*Devuelve la posicion de un usuario en el array dado su nombre
   	*
   	*@param nombre nombre del usuario
   	*@return entero que indica la posicion en el array, si no se encuentra se devuelve -1
   	*/
       public int buscarUsuario(String nombre){
         
         //entero que se va a devolver
         int pos=0;
             
        //Se recorre el array y solo se para si se acaba el array o si el nombre coincide con el que buscamos                  
         while( pos < posActual && ! (((Usuario)array[pos]).getNombre().equals(nombre)))
            pos++;
         
      	
         //Si se ha parado porque se acaba el array es porque no se ha encontrado el usuario, se
      	//devuelve -1
         if(pos>=posActual)
            pos=-1;
      		
         //se devuelve pos
         return pos;
      }
       
      	
     
   }//Fin de la clase ListaUsuarios

