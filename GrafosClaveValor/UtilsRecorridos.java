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
 * @author brandon
 */
public class UtilsRecorridos {
        public List<Boolean> marcados;
        private int nroVertices;
        
  public UtilsRecorridos(int nroVertices){
            this.nroVertices=nroVertices;
             marcados=new ArrayList<>();
    }
     public void desmarcarTodo(){
       marcados=new ArrayList<>();
       for (int i = 0; i < this.nroVertices; i++) {
           marcados.add(Boolean.FALSE);
       }
   }
     
        public void marcarVertice(int posVertice){
       marcados.set(posVertice, Boolean.TRUE);
   }
        
   public boolean estaMarcado(int posVertice){
       return marcados.get(posVertice);
   }
   
   public boolean estanTodosMarcados(){
       for (int i = 0; i < this.nroVertices; i++) {
            if(!estaMarcado(i)){
               return false;
           }
       }
       return true;
   }
   
   public int posicionNoMarcada(){
        for (int i = 0; i < this.nroVertices; i++) {
           if(!estaMarcado(i)){
               return i;
           }
       }
       return -1; 
   }
   
}
