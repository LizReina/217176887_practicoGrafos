/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosPesadosCV;

import GrafosClaveValor.UtilsRecorridos;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author liz fernanda reina quispert
 */
public class DiGrafoPesadoAlgoritmo<T extends Comparable<T>> {
      public DiGrafoPesado<T> unGrafo;
     public UtilsRecorridos utils;
     
     public DiGrafoPesadoAlgoritmo(DiGrafoPesado<T> Grafo){
        this.unGrafo=Grafo;
        utils=new UtilsRecorridos(unGrafo.cantidadVertices());
        utils.desmarcarTodo();
      } 
     
  protected List<Double> inicializaCosto(){
       List<Double> costo=new ArrayList<>();
       for (int i = 0; i < unGrafo.ListaDeAdyacencias.size(); i++) {
           costo.add(Double.MAX_VALUE);
       }
       return costo;
   }
  
   protected List<Integer> inicializaPredecesores(){
       List<Integer> predecesores=new ArrayList<>();
       for (int i = 0; i < unGrafo.ListaDeAdyacencias.size(); i++) {
           predecesores.add(-1);
       }
       return predecesores;
   }
  
  public void actualizarCosto(List<Double> listCostos,int posicionVertice,double valor){
         listCostos.set(posicionVertice, valor);
    }
    
     public void actualizarPredecesores(List<Integer> predecesores ,int posicion,int valor){
         predecesores.set(posicion, valor);
    }
    
       public int posicionCostoMenor(List<Double> listaCosto){
        int posicionCosto=0;
        double costoMenor=Double.MAX_VALUE;
        for (int i = 0; i < listaCosto.size()-1; i++) {
            if(utils.estaMarcado(i)==false){
                if(costoMenor>listaCosto.get(i)){
                   costoMenor= listaCosto.get(i);
                   posicionCosto=i;
                }
            }
        }

        return posicionCosto;
    }
    
    //algoritmo de Dijkstra
     public Stack algoritmoDijkstra(int posOrigen,int posDestino){
        Stack recorrido=new Stack<>();
        List<Double> costos=inicializaCosto();
        List<Integer> predecesores=inicializaPredecesores();
        int posMenorCosto=posOrigen;
         actualizarCosto(costos,posOrigen,0);
        while(!utils.estaMarcado(posMenorCosto)){
          List<AdyacenteConPeso> adyacencia=unGrafo.ListaDeAdyacencias.get(posMenorCosto);
          utils.marcarVertice(posMenorCosto);
            for (int i = 0; i < adyacencia.size(); i++) {
                if(!utils.estaMarcado(adyacencia.get(i).getIndiceVertice())){
                    double costoA=costos.get(adyacencia.get(i).getIndiceVertice());
                    double costoV=costos.get(posMenorCosto);
                    double costoVA=adyacencia.get(i).getCosto();
                     if(costoA>costoV+costoVA){
                         actualizarCosto(costos,adyacencia.get(i).getIndiceVertice(),costoV+costoVA);
                         actualizarPredecesores(predecesores, adyacencia.get(i).getIndiceVertice(),
                                 posMenorCosto);
                     }     
            }
        }
        posMenorCosto=posicionCostoMenor(costos);
       
     }
      int k=posDestino;
        while(k!=posOrigen){
            recorrido.add(k);
            k=predecesores.get(k);
        }
        recorrido.add(posOrigen);
        return recorrido; 
     }
     
   //14.	Para un grafo dirigido implementar al algoritmo de ordenamiento topológico. 
   //Debe mostrar cual es el orden de los vértices según este algoritmo.
     
   
}
