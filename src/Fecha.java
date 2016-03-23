 	 /**
	 *
	 *Clase Fecha
	 *
	 *Esta clase sirve para poder gestionar las fechas del programa
	 *saber cuando un libro debe ser devuelto, comparar fechas
	 *
	 *@author Fernando Diaz
	 *@author Taoufik Aadia
     *@version 2.1
	 *
	 */
    public class Fecha{
    
      /**dia de la fecha*/
      private int dia;
   
     /**mes de la fecha*/
      private int mes;
   
     /**año de la fecha*/
      private int año;
   
     //constructor
   
     /**
     *Se crea un objeto Fecha.
	 *Si se intenta crear una fecha incorrecta (con un dia o mes negativo, por ejemplo) se
	 *lanza una Exception que se coge en el mismo metodo
	 *
     *@param dia dia de la fecha
     *@param mes mes de la fecha
     *@param año año de la fecha
     */
       public Fecha(int dia, int mes, int año){
         
      	    try{

            //Se comprueba que el mes no sea menor que 1
            if(mes<1) throw new Exception("se ha introducido un mes incorrecto: mes<1");  

			//Se comprueba que el mes no sea mayor que 12
            else if(mes>12) throw new Exception("se ha introducido un mes incorrecto: mes>12");

			//si es correcto se da el valor al atributo
            else this.mes=mes;
            
			
			//se comprueba que el dia no sea menor que 1		 
            if(dia<1) throw new Exception("se ha introducido un dia incorrecto: dia<1");

			//se comprueba que el dia no sea mayor que los dias del mes
            else if(dia>calcularDiasMes(mes)) throw new Exception("se ha introducido un dia mayor que el numero de dias del mes");
            
			//si el dia es correcto se da el valor al atributo
			else  this.dia=dia;
         
            this.año=año;
			}

			catch(Exception e){
				System.out.println("Error en una fecha : "+e.getMessage());
			}
             
      }
   	   
     /**
     *Devuelve el dia de esta fecha
     *@return dia de la fecha
     */
       public int getDia(){
         return dia;
      }
      
     /**
     *Devuelve el mes de esta fecha
     *@return mes de la fecha
     */	
       public int getMes(){
         return mes;
      }
      
     /**
     *Devuelve el año de esta fecha
     *@return año de la fecha
     */	
       public int getAño(){
         return año;
      }
   
     /**
     *Compara esta fecha con la que se le pasa por parametro, y devuelve un booleano 
    *
     *@param fecha objeto de la clase Fecha
     *@return true si la fecha que llama al metodo es anterior a la que se le pasa por parametro, false en caso contrario
     */
       public boolean esAnterior(Fecha fecha){
      
         //booleano que devolvera el metodo, por defecto false
         boolean anterior=false;
      
         //primero se comparan los años
         if(this.año<fecha.año)
            anterior=true;
         
         
         else if(this.año==fecha.año)
         
          //Si los años son iguales, se compara el mes
            if(this.mes<fecha.mes)
               anterior=true;
            
            else if(this.mes==fecha.mes)
            
            //Si los meses son iguales se compara el dia
               if(this.dia<fecha.dia)
                  anterior=true;
         
      	/*En el caso de que no se halla entrado en ninguna rama de las anteriores, "anterior"
      	 *sigue valiendo false, ya que la fecha que llama al metodo no es anterior a la que se 
      	 *pasa en el argumento*/
         
       //Se devuelve anterior
         return anterior;
      }
   
      /**
   	  *Este metodo le aumenta a la fecha el numero de dias que se le pasa por 
   	  *parámetro y devuelve la nueva fecha
      *
   	  *@param aumento numero de dias que hay que aumentar la fecha
   	  *@return objeto de la clase Fecha 
   	  *@see #calcularDiasMes(int mes)
   	  */   
       public Fecha aumentarFecha(int aumento){
       
         /*variable diasMes que contiene el numero de dias que tiene el mes 
         *para calcular los dias que tiene el mes se utiliza el metodo de 
         *esta clase calcularDiasMes(int mes)*/
         int diasMes=calcularDiasMes(this.mes);
         
      	
        //al dia actual se le suma el aumento y se le resta 1, ya que el dia en que se 
        //hace el prestamo ya cuenta
         int nuevoDia=this.dia+aumento-1;
         int nuevoMes=this.mes;
         int nuevoAño=this.año;
      
         //hay que comprobar que la fecha sea correcta
         if(nuevoDia>diasMes){
            
         /*Si se pasa de los dias del mes se calculan los dias que faltan para que termine 
         *el mes y se le restan al aumento, y luego se resta 1 por lo que ya se ha dicho*/
            nuevoDia=aumento-(diasMes-this.dia)-1;
          //Se incrementa el mes 
            nuevoMes=nuevoMes+1;
         
         //Se comprueba que el mes no se pase de 12
            if(nuevoMes>12){
            
            //Si es superior a 12, se le asigna el mes 1 y se incrementa el año
               nuevoMes=1;
               nuevoAño=nuevoAño+1;
            }
         
         }
      
         //Se devuelve un nuevo objeto de la clase Fecha utilizando su constructor
         return new Fecha(nuevoDia, nuevoMes, nuevoAño);
      
      }
   	
   	
   	/**
   	*Este metodo cálcula el numero de dias que tiene el mes que se le pasa por parámetro
   	*y lo devuelve
   	*
   	*@param mes mes del que se quiere saber el numero de dias que tiene
   	*@return numero de dias del mes (30, 31, 28 ó 29)
   	*@see #esBisiesto(int año)
   	*/
       private int calcularDiasMes(int mes){
       //El metodo es private ya que solo se va a utilizar en esta clase
       
       //variable que se devolvera, se inicializa a 0 
         int diasMes = 0;
      	
       /*array de tamaño 12 que contiene en cada posicion los dias que tienen los meses de
       *un añor normal*/
         int[] meses={31,28,31,30,31,30,31,31,30,31,30,31};
      	
       //Si "mes" está entre 1 y 12 ambos incluidos, se asigna a diasMes la posicion "mes-1" del
       //array ya que este empieza en la posicion 0
         if(mes>0&&mes<=meses.length)
            diasMes=meses[mes-1];
      	
      /*ahora se comprueba, en el caso de que el mes sea febrero, si el año es bisiesto,
      *y si lo es se incrementa en uno el numero de dias del mes (28 => 29)*/
         if(mes==2)
            if(esBisiesto(this.año))
               diasMes++;
      	      	
      	//se devuelve diasMes			
         return diasMes;
      	
      }
   
   	 /**
   	 *Comprueba si un año es bisiesto, mediante la fórmula:
   	 *si un año es multiplo de 4 y no lo es de 100 es bisiesto, o también es
   	 *bisiesto si es múltiplo de 400 
   	 *
   	 *@param año año que se quiere saber si es bisiesto
   	 *@return true si es bisiesto, false en caso contrario
   	 */
       private boolean esBisiesto( int año ) {
        //El metodo es private ya que solo se va a utilizar en esta clase
         
      	//esBisiesto recibe el valor lógico de la formula antes dicha
         boolean esBisiesto=( ((año % 4) == 0) && ((año % 100) != 0) ) || ((año % 400) == 0);
         
      	//se devuelve esBisiesto
         return esBisiesto;
      }
      
   }