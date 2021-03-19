/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosClaveValor;

/**
 *
 * @author liz fernanda reina quispert
 */
public class Conexo {
    private DFS dfsGrafo;
    public Conexo(Grafo unGrsfo){
        dfsGrafo=new DFS(unGrsfo,0);
    }
    
    public boolean esConexo(){
        return dfsGrafo.hayCaminoATodos();
    }
    
    
}
