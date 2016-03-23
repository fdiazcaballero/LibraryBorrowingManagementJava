 /**
 *
 *Clase Lista
 *
 *Esta clase tiene la misma utilidad (solo los métodos que nos hacen falta en la práctica)
 *que la clase vector de java. Guarda la informacion en un array de Object.
 *Se declara abstracta para que no se puedan crean objetos de ella, ya que no puede
 *definir un metodo buscar porque no se sabe el tipo de objetos que va a contener, metodo que
 *definiran sus clases-hijas
 *
 *@see ListaUsuarios
 *@see ListaLibros 
 */  
    public abstract class Lista{
      
   	/**
   	*entero que almacena la primera posicion que apunta a null de la lista (primera posicion vacia, donde 
	*se meterá el siguiente elemento).
	*Como en un array las posiciones empiezan a contar desde 0 tambien se puede interpretar como el
	*numero de elementos que contiene el array, sustituto del atributo length.
	*
	*Todas las posiciones del array mayores que posActual apuntaran a null
   	*/  
      protected int posActual;
   	
   	/**
	*array de object en el que se guarda la informacion
	*/
      protected Object [] array;
   	
	/**
	*entero que guarda el incremento de tamaño que va a experimentar el array del atributo 
	*cuando este se llene. Asi cada vez que se añada o se elimine un elemento no habra que 
	*volver a copiar todo el array en uno que tenga solo una posicion mas o menos, sino que 
	*siempre habra un "colchon" menor o igual al incremento de posiciones que apuntan a null
	*("posiciones vacias") para poder añadir un elemento, y el proceso anterior se repetirá
	*menos veces, optimizando asi el tiempo y los recursos empleados
	*/
      protected int incremento;
   	
   	/**
   	*Crea un objeto de esta clase, inicializando posActual a 0 y creando un array
	*del tamaño de incremento
	*
	*Gestiona una posible Exception que se lanzaria si el argumento incremento fuera 
	*menor que 1
	*
	*@param incremento incremento del array y tamaño inicial del array
   	*/
       public Lista(int incremento){
		 try{
			 //Se comprueba que incremento no sea menor que 1, si lo es se lanza una Exception
			 if(incremento<1) throw new Exception("no se puede crear un objeto Lista con"+ 
			                                      "un incremento menor que 1");
			 //Se inicializan los atributos
			 else{
				this.incremento=incremento;
				this.posActual=0;
				this.array=new Object[incremento];
			 }
		 }
		 //Se coge una posible Exception que sea lanzada
		 catch(Exception e){
			 System.out.println("ERROR : "+e.getMessage());
		 }
		 
      }
   	
	/**
	*Se crea un objeto de esta clase llamando al constructor con parametros con
	*el valor de 5 para el atributo incremento 
	*
	*@see #Lista(int)
	*/
       public Lista(){
         this(5);
      }
      
   	
   	/**
   	*Este método sirve para añadir un objeto al array.
   	*El array nunca se llena porque se hace aumentar su tamaño dinaminámicamente.
	*
	*Cuando el array se llena el tamaño se aumenta en una cantidad igual al 
	*atributo incremento. Asi no hay que incrementar de 1 en 1 y el programa es 
	*mas eficiente
   	*
   	*@param elem objeto que se quiere añadir
   	*/
       public void add(Object elem){
       
         //si el array esta lleno
         if(posActual>=array.length){
         
         //Se crea un array auxiliar con un tamaño igual al del array del atributo + "incremento" 
            Object[] array2=new Object[array.length+incremento];
         
         /*se hace apuntar todas las posiciones del array auxiliar
		 *a la posicion respectiva del array atributo sobrando al final en el array 
		 *auxiliar un numero de posiciones igual al incremento 
		 *(que son las que se van a añadir)*/
            for (int i=0;i<array.length;i++){
               array2[i]=array[i];
            }
         
         //ahora ya se puede añadir en el array auxliar "elem" en "posActual"
            array2[posActual]=elem;
         
         //se hace que el atributo "libros" apunte al array auxiliar
            array=array2;
         }
         
		 //si el array no esta lleno no hay que hacer mas que insertar en "posActual"
         else
            array[posActual]=elem;
      
      	//finalmente se incrementa la posActual	
         posActual++;
      
      }
   	
   	
   	 /**
   	 *Este metodo sirve para eliminar una posicion del array de objetos
   	 *El tamaño del array tambien se reduce dinamicamente.
	 *
	 *Cuando en el array queden apuntando a null un numero de posiciones
	 *mayor o igual al incremento, el tamaño de este se reducira en un 
	 *numero igual al incremento. Asi no hay que reducir el tamaño de 1
	 *en 1 cada vez que se elimine un elemento y se gana efectividad
   	 *
   	 *@param pos posicion que se quiere eliminar
   	 */	
       public void remove(int pos){
       
      
      	//Se comprueba que la posicion sea correcta	
         if(pos>=0&&pos<posActual){
            
			//Si hay un numero de posiciones apuntando a null mayor o igual que el incremento
            if(array.length-posActual>=incremento){
            
            /*Se crea un array auxiliar de un tamaño (incremento+1) menor que el array atributo.
			*A incremento se le suma 1 porque ademas de sobrar un numero de posiciones igual a 
			*incremento se va a eliminar otra, asi se deja el array totalmente lleno*/
               Object[] array2=new Object[array.length-(incremento+1)];
            
            
               int i=0;
            //se hace apuntar el array auxiliar atodas las posiciones menores que la que se quiere eliminar 
               while(i<pos){
                  array2[i]=array[i];
                  i++;
               }
            
            //cuando se llega a la posicion que se quiere eliminar, se adelanta una posicion en el 
            //array principal para saltarsela
               while(i<posActual-1){
                  array2[i]=array[i+1];
                  i++;
               }
            
            //el atributo libros apunta al array auxiliar
               array=array2;
            }
            
			//Si no apuntan a null un numero de posiciones mayor o igual al incremento
            else{
               
		       //Se hace que a partir de la posicion que se quiere eliminar cada posicion del 
			   //array apunte al objeto que apuntaba la siguiente
               for(int k=pos;k<posActual-1;k++)
                  array[k]=array[k+1];
               
			   //la ultima posicion apunta a null
               array[posActual-1]=null;
            
            }
         	//Se decrementa la posActual
            posActual--;
         }
      }
   
   
    /**
    *
    *método que devuelve el numero de Objetos que contiene el array atributo
    *
    *@return numero de Objetos que contiene el array atributo
    */  
       public int size(){
         return posActual;
      }
      
     
     /**
   	 *Devuelve el Objeto que esta en la posicion que se le pasa
   	 *@param pos posicion del array 
   	 *@return Objeto que esta en la posicion
   	 */
       public Object elementAt(int pos){
      	
      	//variable a devolver por defecto a null       
         Object elem=null;
       
         //si la posicion es correcta
         if(pos>=0&&pos<posActual)
            elem=array[pos];
         
	     //Se devuelve el objeto
         return elem;
      }
   	
   
   }//Fin de la clase Lista