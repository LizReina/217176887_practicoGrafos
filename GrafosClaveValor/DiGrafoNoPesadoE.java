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
public class DiGrafoNoPesadoE {
     public Grafo unGrafo;
     public DFS recorrido;
     public UtilsRecorridos utils;
     
     public DiGrafoNoPesadoE(Grafo Grafo){
        this.unGrafo=Grafo;
        utils=new UtilsRecorridos(unGrafo.cantidadVertices());
        utils.desmarcarTodo();
      }
    /*islas en DiGrafo
   1:tenemos grafo G
    2:Definimos un contador de islas (cantIslas) con 0
    3:Elegimos un vertice no marcado para realizar recorrido(VerticeDeProceso)
    4:realizamos o continuamos un recorrido DFS de grafo G iniciando con verticeDeProceso
    5:si luego de finalizar el recorrido estan todos marcados 
       5.1:sumamos 1 a cantIslas
       5.2:retorno cantidadISlas
    6:si luego de finalizar el recorrido no estan todos marcados 
       6.1:elegir como nuevo vertice un vertice no marcado con adyacente marcado
       6.2 si no existe un como nuevo vertice con adyacente marcado 
         6.2.1 sumamos 1 a cantIslas
         6.2.2:repetimos desde el paso 3
       6.3: si existe un nuevo verticeDeProceso no marcado con adyacente marcado
        6.3.1:repetimos desde el paso 4
    
    */ 
    //digrafo
    public int cantidadIslas(){
        int cantIslas=1;
        int verticeDeProceso=0;
       recorrido=new DFS(unGrafo,verticeDeProceso);
      while(!recorrido.hayCaminoATodos()){
         verticeDeProceso=recorrido.posicionDeCaminoNoMarcado();
         List<Integer> verticesMarcados=unGrafo.ListaDeAdyacencias.get(verticeDeProceso);
         if(verticesMarcados.size()>0){
           for (int i = 0; i < verticesMarcados.size(); i++) {
               if(!recorrido.hayCaminoA(verticesMarcados.get(i))){
                 recorrido.continuarDFS(verticeDeProceso);
                 cantIslas++;
               }
            }
         }else{
             cantIslas++;
             recorrido.continuarDFS(verticeDeProceso);
         }
      } 
        return cantIslas;
    }
    
    public int posicicionRecorridoF(List<Boolean> recorrido,boolean b){
        for (int j = 0; j < recorrido.size(); j++) {
            if(recorrido.get(j) == b){
                return j;
            }
        }
         return -1;
    }
   // hay ciclo
      public boolean hayCicloDirigido(){
        List<Boolean> recorrido=new ArrayList<>();  
        UtilsRecorridos marcados=new UtilsRecorridos(unGrafo.cantidadVertices());
        marcados.desmarcarTodo();
          for (int i = 0; i < unGrafo.ListaDeAdyacencias.size(); i++) {
              if(dfsRDigrafoI(marcados,i)){
                  return true;
              }
            marcados.desmarcarTodo();
          }
      return false;
   }

    public boolean dfsRDigrafoI(UtilsRecorridos marcados,int posicionEnTurno){
      marcados.marcarVertice(posicionEnTurno);  
        for (int j = posicionEnTurno; j < unGrafo.ListaDeAdyacencias.size(); j++) {
          List<Integer> adyacenciaIndice=unGrafo.ListaDeAdyacencias.get(j);
            for (int i = 0; i < adyacenciaIndice.size(); i++) { 
                if(marcados.estaMarcado(j)){
                    if(!marcados.estaMarcado(adyacenciaIndice.get(i))){
                     marcados.marcarVertice(adyacenciaIndice.get(i));
                   }else{
                      if(posicionEnTurno==adyacenciaIndice.get(i) ){
                     return true;
                    }
                  } 
               }else{
                  marcados.marcarVertice(j);
                  if(!marcados.estaMarcado(adyacenciaIndice.get(i))){
                    marcados.marcarVertice(adyacenciaIndice.get(i));
                   }
                }
          }
        }
        return false;
                
    }             
    
 //1.-1.	Para un grafo dirigido implementar un método o clase que sea capas de retornar 
   // los componentes de las islas que existen en dicho digrafo 
    
    public void ponerLaIsla(List<List<Integer>> cantIslas,DFS recorrido,int verticeDeProceso){
        List<Integer> listaIsla=new ArrayList<>();
          for (int i = verticeDeProceso; i < recorrido.recorrido.size(); i++) {
            listaIsla.add(recorrido.recorrido.get(i));
          }

        cantIslas.add(listaIsla);
    }

 public List<List<Integer>> componentesIslas(){
        List<List<Integer>> cantIslas=new ArrayList<>();
        int verticeDeProceso=0;
       recorrido=new DFS(unGrafo,verticeDeProceso);
       ponerLaIsla(cantIslas,recorrido,verticeDeProceso);
      while(!recorrido.hayCaminoATodos()){
         verticeDeProceso=recorrido.posicionDeCaminoNoMarcado();
         List<Integer> verticesMarcados=unGrafo.ListaDeAdyacencias.get(verticeDeProceso);
         if(verticesMarcados.size()>0){
           for (int i = 0; i < verticesMarcados.size(); i++) {
               if(!recorrido.hayCaminoA(verticesMarcados.get(i))){
                   recorrido.continuarDFS(verticeDeProceso);
                   ponerLaIsla(cantIslas,recorrido,verticeDeProceso);
                 //cantIslas++;
               }
          }
         }else{
            recorrido.continuarDFS(verticeDeProceso);
            ponerLaIsla(cantIslas,recorrido,verticeDeProceso);
         }
      } 
        return cantIslas;
    }
 

 
    
}