/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosClaveValor;

import GrafosClaveValor.Excepciones.ExcepcionAristaYaExiste;
import GrafosClaveValor.Excepciones.ExcepcionNroVerticesInvalido;
import java.util.List;

/**
 *
 * @author brandon
 * @param <T>
 */
public class MatrizCamino extends DiGrafo{
     int tamañoMatriz;
    int[][] matrizAdyacencia;

    public MatrizCamino() {
          super();
          
    }
     public MatrizCamino(int tamaño) throws ExcepcionNroVerticesInvalido {
           super(tamaño);
          tamañoMatriz=tamaño;
         this.matrizAdyacencia = new int[tamañoMatriz][tamañoMatriz];   
    }
     
     public void setGrafoMatriz(int i, int j, int valor) {
		this.matrizAdyacencia[i][j] = valor;
	}
    
    /**
     * creamos la matriz simetrica con la cantidad de vertices 
     */

    @Override
    public void insertarArista(int verticeOrigen,int verticeDestino) throws ExcepcionAristaYaExiste {
        super.insertarArista(verticeOrigen, verticeDestino); //To change body of generated methods, choose Tools | Templates.
         for (int i = 0; i < ListaDeAdyacencias.size(); i++) {
            List<Integer> adyacencia=ListaDeAdyacencias.get(i);
            for (int j = 0; j < adyacencia.size(); j++) {
                matrizAdyacencia[i][adyacencia.get(j)]=1;
            }
        }
    }

   
    public void insertarMatrizAdyacencia(){
        //inicializarMatrizGrafo();
      
    }
    
 
   
   public void algoritmoWarshall() throws ExcepcionNroVerticesInvalido{
       MatrizCamino matrizAux=new MatrizCamino(tamañoMatriz);
      
       for (int k = 0; k < tamañoMatriz; k++) {
         for (int i = 0; i < tamañoMatriz; i++) {
           for (int j = 0; j < tamañoMatriz; j++) {
                  if(matrizAdyacencia[i][j]==0){
                      if(matrizAdyacencia[i][k]==1 && matrizAdyacencia[k][j]==1){
                         matrizAux.setGrafoMatriz(i, j, 1);
                      }
                  }else{
                      matrizAux.setGrafoMatriz(i, j,1);
                  }                  
           }
         }  
         
           for (int m = 0; m < tamañoMatriz; m++) {
               for (int n = 0;n < tamañoMatriz; n++) {
                   if(matrizAux.matrizAdyacencia[m][n]==1){
                        matrizAdyacencia[m][n]=1;
                   }
               }
           }
       }
       
   }
    
    
 public String mostrarMatriz(){
        StringBuilder cadena = new StringBuilder();
   //     insertarMatrizAdyacencia();
        for (int i=0; i<ListaDeAdyacencias.size(); i++) {
            cadena.append("["+i+"] -> |");
            for (int j = 0; j < ListaDeAdyacencias.size(); j++) {
                 cadena.append( matrizAdyacencia[i][j]+",");
            }
            cadena.append("| \n");
        }
        
        return cadena.toString();
   }
}
