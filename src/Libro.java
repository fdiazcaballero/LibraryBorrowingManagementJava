  	 /**
	 *
	 *Clase Libro
	 *
	 *Esta clase contiene los atributos y metodos necesarios para representar
	 *un objeto Libro en memoria
	 *
	 *@see Fecha
	 *@see Usuario
	 *@author Fernando Diaz 
	 *@author Taoufik Aadia
     *@version 2.1
	 *
	 */
    public class Libro{
    
    //atributos
    
    /**
    *tipo del libro: fondo, sala, especial, creacion 
    */
      private String tipo;
   	
   	/**
   	*titulo del libro 
   	*/
      private String titulo;
   	
   	/** 
   	*codigo del libro
   	*/
      private int isbn;
   	
   	/**
   	*Si el libro no esta prestado apunta a null, si esta prestado apunta a la
   	*fecha limite de devolución
   	*/
      private Fecha fechaDevolucion;
   	
   	/**
   	*Si el libro no está prestado apunta a null, si está prestado apunta al 
   	*usuario que lo tiene
   	*/
      private Usuario propietario;
      
   
   //constructor
   
   /**
   *Crea un objeto Libro
   *
   *@param tipo tipo del libro
   *@param titulo titulo del libro
   *@param isbn codigo del libro
   */
       public Libro(String tipo, String titulo, int isbn){
         
		 //Se inicializan los atributos
         this.tipo=tipo;
         this.titulo=titulo;
         this.isbn=isbn;
         
      	//Se inicializan a null
         this.fechaDevolucion=null;
         this.propietario=null;
         
      }
   	
   	//metodos
   	
   	/**
   	*Devuelve la fecha de devolucion del libro
   	*@return objeto de la clase Fecha
   	*/
       public Fecha getFechaDevolucion(){
         return this.fechaDevolucion;
      }
     
    /**
   	*Devuelve el isbn del libro
   	*@return entero que contiene el isbn
   	*/  
       public int getISBN(){
         return this.isbn;
      }
      
   	/**
   	*Devuelve el tipo de libro
   	*@return cadena que contiene el libro
   	*/
       public String getTipo(){
         return this.tipo;
      }
      
   	/**
   	*Devuelve el titulo del libro
   	*@return cadena que contiene el titulo
   	*/
       public String getTitulo(){
         return this.titulo;
      }
      
   	/**
   	*Devuelve el Usuario que tiene el libro
   	*@return objeto de la clase Usuario si el libro esta prestado y null si no lo esta
   	*/
       public Usuario getPropietario(){
         return this.propietario;
      }
      
    /**
   	*Establece la fecha de devolucion del libro
   	*@param fechaDevolucion 
   	*/
       public void setFechaDevolucion(Fecha fechaDevolucion){
         this.fechaDevolucion=fechaDevolucion;
      }
   	
   	/**
   	*Establece el usuario que tiene el libro prestado
   	*@param usuario objeto de la clase Usuario
   	*/
       public void setPropietario(Usuario usuario){
         this.propietario=usuario;
      }
   }//Fin de la clase Libro