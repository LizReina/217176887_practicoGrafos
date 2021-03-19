/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosClaveValor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liz fernanda reina quispert
 */
public class DebilmenteConexo {
    public Conexo grafoConexo;
    public boolean esDebilmenteConexo;
 
       public DebilmenteConexo(){
       
      }
    
    public boolean esDebilmenteConexo(DiGrafo unDiGrafo){
        DFS recorrido=new DFS(unDiGrafo,0);
        int posicionDelVerticeInicial=0;
        boolean bandera=true;
     while(bandera==true){
         recorrido.continuarDFS(posicionDelVerticeInicial);
          if(recorrido.hayCaminoATodos()){
            return false;
          }
          
         boolean noMarcado=false;
           for (int i = 0; i < unDiGrafo.cantidadVertices()&& noMarcado==false; i++){
             if(!recorrido.hayCaminoA(i)){
                List<Integer> adyacente=unDiGrafo.ListaDeAdyacencias.get(i);
                    for (int j = 0; j < adyacente.size(); j++) {
                      if(recorrido.hayCaminoA(adyacente.get(j))){
                          posicionDelVerticeInicial=i;
                          noMarcado=true;
                      }
                 }
             }
           }
         if(noMarcado==false){
             return bandera;
         }
         
     }
     return bandera;
    }
}
