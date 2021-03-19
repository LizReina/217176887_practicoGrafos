/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosClaveValor;

import GrafosClaveValor.Excepciones.ExcepcionAristaYaExiste;
import GrafosClaveValor.Excepciones.ExcepcionNroVerticesInvalido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liz fernanda reina quispert
 */
public class  GrafoNoPesadoE{
  
     public Grafo unGrafo;
     public DFS recorrido;
     public UtilsRecorridos utils;
     
     public GrafoNoPesadoE(Grafo Grafo){
        this.unGrafo=Grafo;
        utils=new UtilsRecorridos(unGrafo.cantidadVertices());
        utils.desmarcarTodo();
      }
    //islas de un grafo
    /*PASOS PARA CANTIDAD ISLAS
    1:tenemos grafo G
    2:Definimos un contador de islas (cantIslas) con 0
    3:Elegimos un vertice no marcado para realizar recorrido(VerticeDeProceso)
    4:realizar un recorrido DFS de grafo G iniciando con verticeDeProceso
    5:sumamos 1 a cantIslas
    6:si el recorrido DFS del grafo G estan todos marcados 
      6.2 retornamos cantidad islas 
    7si el recorrido DFS del graffo no esta marcado 
      7.1 repetir desde el paso 3
    */

    public int cantidadIslas(){
        int cantIslas=1;
       recorrido=new DFS(unGrafo,0);
       while(!recorrido.hayCaminoATodos()){
           int posicionNoMarcado=recorrido.posicionDeCaminoNoMarcado();
           if(posicionNoMarcado != -1){
             recorrido.continuarDFS(posicionNoMarcado);
             cantIslas++;
           }
       }
        return cantIslas;
    }
    
   // Determinaar si hay ciclo en un grafo
    
   ///* Hallar si hay ciclo en un grafo no dirigido
/*1.-crear vertice auxiliar(no dirigido) inicializado solo con vertices del grafo original
2.-inicializar mi estructura auxiliar con todos los vertices no marcados
3.-escoger un vertice no marcado como vertice inicio para dfs 
4.-realizar un recorrido dfs tomando el vertice seleccionado Pandole parametro el vertice auxiliar, el grafo
auxiliar y la estructura de marcados
5.- si al terminar la llamada del paso 4 retorna falso,verifico si todos los vertices quedaron marcados
quiere decir que no hay ciclo y directamente retorno falso
6.-si al terminar la llamada del paso 4 retorna falso, verifico no todos los vertices  quedaron marcados, escogemos
un vertice no marcado como vertice inicio y continuamos en el paso 4
7 si al terminar la llamada del paso 4 retorna verdadero, entonces hemos encontrado el ciclo y retornamos verdadero

(**)
dfs(recibe ek grafo auxiliar, el vertice a procesar y la estructura de marcados)
  1. marca el vertice recibido
  2. selecciona la lista de adyacentes del vertice recibido
  3 por cada vertice adyacente recibico, hecemos:
    3.1. preguntamos si el vertice adyacente esta marcado,añadimos a la arista entre el vertices
        recibido y el vertice adyacente al grafo auxiliar y luego llama al dfs recursivo(el grafo auxiliar,
        el vertice adyacente y la estructura de marcados)
    3.2.preguntamos si el vertice adyacente esta marcado,preguntamos si no existe la arista entre el vertice recibido
       y el vertice adyacente en el grafo auxiliar.si esto se cumplo, hay ciclo y retornamos verdadero 

*/
 
    public boolean hayCicloGrafo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste{
        Grafo grafoAuxilar= new Grafo(unGrafo.cantidadVertices());
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
    public boolean dfsI(Grafo grafoAuxiliar,int verticeAProcesar,UtilsRecorridos marcados) throws ExcepcionAristaYaExiste{
    List<Integer> listaDeAdyacente=(List<Integer>)unGrafo.adyacentesDelVertice(verticeAProcesar);
     List<Integer> listaVertices=listaDeAdyacente;
   // boolean esCiclo=false;
    int n=0;
     marcados.marcarVertice(verticeAProcesar);  
    while(n <= listaVertices.size()){
       for (int i = 0; i < listaDeAdyacente.size(); i++) {
                if(marcados.estaMarcado(listaDeAdyacente.get(i))==true){
                  if(!grafoAuxiliar.existeAdyacencia(verticeAProcesar,listaDeAdyacente.get(i))){
                    return true;
                  }
                }else{
                  if(unGrafo.existeAdyacencia(verticeAProcesar,listaDeAdyacente.get(i))){
                     grafoAuxiliar.insertarArista(verticeAProcesar,listaDeAdyacente.get(i));
                     marcados.marcarVertice(listaDeAdyacente.get(i));
                   }
                }
           }
      if(n!=listaVertices.size()){
        listaDeAdyacente=unGrafo.ListaDeAdyacencias.get(listaVertices.get(n));
        verticeAProcesar=listaVertices.get(n);
      }
     n=n+1;
    }  
       return false;
    }


  //4.	Para un grafo no dirigido implementar un algoritmo para encontrar 
 //que en que vértices del grafo hay ciclos 
      
  public List<Integer> verticesConCiclo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste{
        Grafo grafoAuxilar= new Grafo(unGrafo.cantidadVertices());
        UtilsRecorridos marcados=new UtilsRecorridos(unGrafo.cantidadVertices());
        marcados.desmarcarTodo();
        int verticeAProcesar=0;
        List<Integer> listaDeVertices=new ArrayList<>();
        while(verticeAProcesar < unGrafo.cantidadAristas()-1){
            if(dfsI(grafoAuxilar,verticeAProcesar,marcados)){
             listaDeVertices.add(verticeAProcesar);
          }
           grafoAuxilar= new Grafo(unGrafo.cantidadVertices());
           marcados.desmarcarTodo();   
           verticeAProcesar++;
      }
        
        
    return listaDeVertices;
}
  
}