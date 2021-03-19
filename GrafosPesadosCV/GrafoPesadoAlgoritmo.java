/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosPesadosCV;

import GrafosClaveValor.DFS;
import GrafosClaveValor.Excepciones.ExcepcionAristaYaExiste;
import GrafosClaveValor.Excepciones.ExcepcionNroVerticesInvalido;
import GrafosClaveValor.Grafo;
import GrafosClaveValor.UtilsRecorridos;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author brandon
 */
public class GrafoPesadoAlgoritmo<T extends Comparable<T>> {
      public GrafoPesado<T> unGrafo;
     public UtilsRecorridos utils;
     
     public GrafoPesadoAlgoritmo(GrafoPesado<T> Grafo){
        this.unGrafo=Grafo;
        utils=new UtilsRecorridos(unGrafo.cantidadVertices());
        utils.desmarcarTodo();
     }
  
    //hay ciclo  
    public boolean hayCicloGrafoPesado() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste{
        GrafoPesado<T> grafoAuxilar= new GrafoPesado(unGrafo.cantidadVertices());
        UtilsRecorridos marcados=new UtilsRecorridos(unGrafo.cantidadVertices());
        marcados.desmarcarTodo();
        int verticeAProcesar=0;
       while(dfsI(grafoAuxilar,verticeAProcesar,marcados)== false){
           if(marcados.estanTodosMarcados()){
               return false;
           }else{
               verticeAProcesar=marcados.posicionNoMarcada();
           }
           
        }
        return true;
    } 
    
     public boolean dfsI(GrafoPesado<T> grafoAuxiliar,int verticeAProcesar,UtilsRecorridos marcados) throws ExcepcionAristaYaExiste{
    List<AdyacenteConPeso> listaDeAdyacente=unGrafo.ListaDeAdyacencias.get(verticeAProcesar);
     List<AdyacenteConPeso> listaVertices=listaDeAdyacente;
   // boolean esCiclo=false;
    int n=0;
    while(n <= listaVertices.size()){
      marcados.marcarVertice(verticeAProcesar);    
       for (int i = 0; i < listaDeAdyacente.size(); i++) {
                if(marcados.estaMarcado(listaDeAdyacente.get(i).getIndiceVertice())==true){
                  if(!grafoAuxiliar.existeAdyacencia(verticeAProcesar,listaDeAdyacente.get(i).getIndiceVertice())){
                    return true;
                  }
                }else{
                  if(unGrafo.existeAdyacencia(verticeAProcesar,listaDeAdyacente.get(i).getIndiceVertice())){
                     grafoAuxiliar.insertarArista(verticeAProcesar,listaDeAdyacente.get(i).getIndiceVertice(),
                             listaDeAdyacente.get(i).getCosto());
                     marcados.marcarVertice(listaDeAdyacente.get(i).getIndiceVertice());
                   }
                }
           }
      if(n!=listaVertices.size()){
        listaDeAdyacente=unGrafo.ListaDeAdyacencias.get(listaVertices.get(n).getIndiceVertice());
        verticeAProcesar=listaVertices.get(n).getIndiceVertice();
      }
     n=n+1;
    }  
       return false;
    }

    //algoritmo Kruskal
   private void ordenador(List<Integer> vertice,List<AdyacenteConPeso> aristas){
       for (int i = 0; i < unGrafo.ListaDeAdyacencias.size(); i++) {
           List<AdyacenteConPeso> adyacentes=unGrafo.ListaDeAdyacencias.get(i);
           for (int j = 0; j < adyacentes.size(); j++) {
              vertice.add(i);
               aristas.add(adyacentes.get(j));
           }
       }
       
       for (int i = 0; i < aristas.size()-1; i++) {
           for (int j = 0; j < aristas.size(); j++) {
               AdyacenteConPeso ayuda=new AdyacenteConPeso(aristas.get(i).getIndiceVertice(),aristas.get(i).getCosto());
               aristas.set(i,aristas.get(j));
               aristas.set(j,ayuda);
               Integer otroVertice=vertice.get(i);
               vertice.set(i,vertice.get(j));
               vertice.set(j,otroVertice);
               
           }
       }
   }
   
   
  public GrafoPesado<T> kruskal() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste{
      GrafoPesado<T> grafoAux=new GrafoPesado(unGrafo.ListaDeAdyacencias.size());
      
      List<Integer> vertices=new ArrayList<>();
      List<AdyacenteConPeso> aristasEnOrden=new ArrayList<>();
      
      ordenador(vertices, aristasEnOrden);
      for (int i = 0; i < aristasEnOrden.size(); i++) {
          grafoAux.insertarArista(vertices.get(i),aristasEnOrden.get(i).getIndiceVertice(),
                  aristasEnOrden.get(i).getCosto());
          if(hayCicloGrafoPesado()){
              grafoAux.eliminarArista(vertices.get(i),aristasEnOrden.get(i).getIndiceVertice());
          }
          
      }
     return grafoAux;
  }
    
}
