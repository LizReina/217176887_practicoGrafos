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
public class Grafo {
    protected List<List<Integer>> ListaDeAdyacencias; 
    
    public Grafo(){
        this.ListaDeAdyacencias=new ArrayList<>();
    }
    
    public Grafo(int nroVerticesInicial)throws ExcepcionNroVerticesInvalido{
       if(nroVerticesInicial < 0){
           throw new ExcepcionNroVerticesInvalido();
       } 
       
       this.ListaDeAdyacencias=new ArrayList<>();
        for (int i = 0; i < nroVerticesInicial; i++) {
            this.ListaDeAdyacencias.add(new  ArrayList<>());
        }
       
    }
    
    public void insertarVertice(){
        this.ListaDeAdyacencias.add(new ArrayList<>());
    }
    
    public int cantidadAristas(){
        int cantAristas=0;
        int cantLazos=0;
        for (int i = 0; i < this.ListaDeAdyacencias.size(); i++) {
            List<Integer> adyacenciasDelVertice= this.ListaDeAdyacencias.get(i);
            for (Integer posAdyacente : adyacenciasDelVertice) {
                if(i==posAdyacente){
                    cantLazos++;
                }else{
                    cantAristas++;
                }
            }
        }
        cantAristas=(cantAristas/2)+cantLazos;
        return cantAristas;
    }
    
    public int cantidadVertices(){
        return this.ListaDeAdyacencias.size();
    }
    
    public void validarVertice(int posicionDeVertice){
     if(posicionDeVertice < 0  || posicionDeVertice>= cantidadVertices()){
         throw new IllegalArgumentException("El vertice" + posicionDeVertice + "No pertenece al grafo");
     }
     
    }
    
    public void insertarArista(int posVerticeOrigen,int posVerticeDestino)throws ExcepcionAristaYaExiste{
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if(existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        
        List<Integer> adyacenciasDelOrigen=this.ListaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(posVerticeDestino);
        
        if(posVerticeOrigen!=posVerticeDestino){
           List<Integer> adyacenciasDelDestino=this.ListaDeAdyacencias.get(posVerticeDestino);
           adyacenciasDelDestino.add(posVerticeOrigen);
        }
    }
    
    public boolean existeAdyacencia(int posVerticeOrigen,int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
      List<Integer> adyacenciasDelOrigen=this.ListaDeAdyacencias.get(posVerticeOrigen);
      return adyacenciasDelOrigen.contains(posVerticeDestino);
        
    }
    
    public void eliminarVertice(int posVerticeAEliminar){
        validarVertice(posVerticeAEliminar);
        ListaDeAdyacencias.remove(posVerticeAEliminar);
        
        for(List<Integer> adyacentesDeVertice:this.ListaDeAdyacencias) {
           int posicionDeVerticeEnAdy=adyacentesDeVertice.indexOf(posVerticeAEliminar);
           if(posicionDeVerticeEnAdy>=0){
                adyacentesDeVertice.remove(posicionDeVerticeEnAdy);
           }
           
            for (int i = 0; i < adyacentesDeVertice.size(); i++) {
                int posicionAdyacente=adyacentesDeVertice.get(i);
                if(posicionAdyacente>posVerticeAEliminar){
                    adyacentesDeVertice.set(i, posicionAdyacente-1);
                }
            }
        }
}
    
    
  public int gradoVertice(int posVertice){
     validarVertice(posVertice);
     List<Integer> adyacenciasDelVertice=this.ListaDeAdyacencias.get(posVertice);
      return adyacenciasDelVertice.size(); 
  }
  
  public Iterable<Integer> adyacentesDelVertice(int posVertice){
      validarVertice(posVertice);
      List<Integer> adyacenciasDelVertice=this.ListaDeAdyacencias.get(posVertice);
      Iterable<Integer> it=adyacenciasDelVertice;
      return it;
  }
  
    public void eliminarArista(int posVerticeOrigen,int posVerticeDestino) throws ExcepcionAristaYaExiste{
          if(!existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
         throw new ExcepcionAristaYaExiste();
     }
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
     
      List<Integer> posOrigenARemover=this.ListaDeAdyacencias.get(posVerticeOrigen);
       posOrigenARemover.remove(Integer.valueOf(posVerticeDestino));
      List<Integer> posDestinoARemover=this.ListaDeAdyacencias.get(posVerticeDestino);
       posDestinoARemover.remove(Integer.valueOf(posVerticeOrigen));

  }
    
      
    public String mostrarGrafo(){
        StringBuilder cadena = new StringBuilder();
 
        for (int i=0; i<ListaDeAdyacencias.size(); i++) {
            cadena.append( "["+i+"] ->"  +ListaDeAdyacencias.get(i)+"\n");
        }
        
        return cadena.toString();
   }
 
}
