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
public class FuertementeConexo {
    public Conexo grafoConexo;
    public boolean esFuertementeConexo;
    
    public FuertementeConexo(DiGrafo unDiGrafo){
        esFuertementeConexo=true;
        for (int i = 0; i < unDiGrafo.cantidadVertices() && esFuertementeConexo; i++) {
            DFS dfs=new DFS(unDiGrafo,1);
          esFuertementeConexo=dfs.hayCaminoATodos();
        }
    }
    
    public boolean esFuertementeConexo(){
        return this.esFuertementeConexo;
    }
    
   
}
