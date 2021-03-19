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
 * @author brandon
 */
public class BFS {
    public List<Boolean> marcados;
    private List<Integer> recorrido;
    private Grafo grafo;
    
   public BFS(Grafo unGrafo,int posVerticePartida){
       this.grafo=unGrafo;
       grafo.validarVertice(posVerticePartida);
       desmarcarTodo();
       ejecutarBFS(posVerticePartida);
   }
   
   private void desmarcarTodo(){
       marcados=new ArrayList<>();
       recorrido =new ArrayList<>();
       for (int i = 0; i < grafo.cantidadVertices(); i++) {
           marcados.add(Boolean.FALSE);
       }
   }
   
   private void marcarVertice(int posVertice){
       marcados.set(posVertice, Boolean.TRUE);
   }
   
   public boolean hayCaminoA(int posVertice){
       grafo.validarVertice(posVertice);
       return estaMarcado(posVertice);
   }
   
    public boolean estaMarcado(int posVertice){
       return marcados.get(posVertice);
   }
    
   public void ejecutarBFS(int posVertice){
       Queue<Integer> cola=new LinkedList<>();
       cola.offer(posVertice);
       do{
          int posVerticeEnTurno=cola.poll();
          recorrido.add(posVerticeEnTurno);
          Iterable<Integer> adyacentesEnTurno=grafo.adyacentesDelVertice(posVerticeEnTurno);
           for (Integer posVerticeAdyacente :adyacentesEnTurno ) {
               if(!estaMarcado(posVerticeAdyacente)){
                   cola.add(posVerticeAdyacente);
                   marcarVertice(posVerticeAdyacente);
               }
           }
       }while(!cola.isEmpty());
   }
   
   public Iterable<Integer> elRecorrido(){
       return recorrido;
   }
}
