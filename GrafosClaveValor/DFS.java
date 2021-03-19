/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosClaveValor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author liz fernanda reina quispert
 */
public class DFS {

    public List<Integer> recorrido;
    private Grafo grafo;
    private UtilsRecorridos controlMarcados;
 
   public DFS(Grafo unGrafo,int posVerticePartida){
       this.grafo=unGrafo;
       grafo.validarVertice(posVerticePartida);
       recorrido =new ArrayList<>();
       controlMarcados=new UtilsRecorridos(grafo.cantidadVertices());
       controlMarcados.desmarcarTodo();
       continuarDFS(posVerticePartida);
   } 
   
   public boolean hayCaminoA(int posVertice){
       grafo.validarVertice(posVertice);
       return controlMarcados.estaMarcado(posVertice);
   }
   
     public int posicionDeCaminoNoMarcado(){
           return controlMarcados.posicionNoMarcada();
   }
    
   public void continuarDFS(int posVertice){
       controlMarcados.marcarVertice(posVertice);
       recorrido.add(posVertice);
       Iterable<Integer> adyacenteEnTurno=grafo.adyacentesDelVertice(posVertice);
       for (Integer posVerticeAdyacente : adyacenteEnTurno) {
           if(!controlMarcados.estaMarcado(posVerticeAdyacente)){
               continuarDFS(posVerticeAdyacente);
           }
       }
   }
   
   public Iterable<Integer> elRecorrido(){
       return recorrido;
   }
   
   public boolean hayCaminoATodos(){
       return controlMarcados.estanTodosMarcados();
   }
   
      
}
