/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosPesadosCV;

import GrafosClaveValor.Excepciones.ExcepcionAristaYaExiste;
import GrafosClaveValor.Excepciones.ExcepcionNroVerticesInvalido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author brandon
 */
public class GrafoPesado<T extends Comparable<T>>{
     protected List<List<AdyacenteConPeso>> ListaDeAdyacencias; 
    
    public GrafoPesado(){
        this.ListaDeAdyacencias=new ArrayList<>();
    }
    
    public GrafoPesado(int nroVerticesInicial)throws ExcepcionNroVerticesInvalido{
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
            List<AdyacenteConPeso> adyacenciasDelVertice= this.ListaDeAdyacencias.get(i);
            for (AdyacenteConPeso posAdyacente : adyacenciasDelVertice) {
                if(i==posAdyacente.getIndiceVertice()){
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
    
    public void insertarArista(int posVerticeOrigen,int posVerticeDestino, double costo)throws ExcepcionAristaYaExiste{
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if(existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        
        List<AdyacenteConPeso> adyacenciasDelOrigen=this.ListaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(new AdyacenteConPeso(posVerticeDestino,costo));
        
        if(posVerticeOrigen!=posVerticeDestino){
           List<AdyacenteConPeso> adyacenciasDelDestino=this.ListaDeAdyacencias.get(posVerticeDestino);
           adyacenciasDelDestino.add(new AdyacenteConPeso(posVerticeOrigen,costo));
        }
    }
    
    public boolean existeAdyacencia(int posVerticeOrigen,int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
      List<AdyacenteConPeso> adyacenciasDelOrigen=this.ListaDeAdyacencias.get(posVerticeOrigen);
      AdyacenteConPeso destino=new AdyacenteConPeso(posVerticeDestino);
      return adyacenciasDelOrigen.contains(destino);
        
    }
  //GrafoPesado  
    public void eliminarVertice(int posVerticeAEliminar){
        validarVertice(posVerticeAEliminar);
        ListaDeAdyacencias.remove(posVerticeAEliminar);
        
        for(List<AdyacenteConPeso> adyacentesDeVertice:this.ListaDeAdyacencias) {
           AdyacenteConPeso adyacenteConPeso=new AdyacenteConPeso(posVerticeAEliminar);
            int posicionDeVerticeEnAdy=adyacentesDeVertice.indexOf(posVerticeAEliminar);
           if(posicionDeVerticeEnAdy>=0){
                adyacentesDeVertice.remove(posicionDeVerticeEnAdy);
           }
           
            for (int i = 0; i < adyacentesDeVertice.size(); i++) {
                AdyacenteConPeso posicionAdyacente=adyacentesDeVertice.get(i);
                if(posicionAdyacente.getIndiceVertice() > posVerticeAEliminar){
                    posicionAdyacente.setIndiceVertice(posicionAdyacente.getIndiceVertice()-1);
// adyacentesDeVertice.set(i, posicionAdyacente-1);
                }
            }
        }
}
    
    
  public int gradoVertice(int posVertice){
     validarVertice(posVertice);
     List<AdyacenteConPeso> adyacenciasDelVertice=this.ListaDeAdyacencias.get(posVertice);
      return adyacenciasDelVertice.size(); 
  }
  
  public Iterable<Integer> adyacentesDelVertice(int posVertice){
      validarVertice(posVertice);
      List<AdyacenteConPeso> adyacenciasDelVertice=this.ListaDeAdyacencias.get(posVertice);
      List<Integer> adyacentesDelVeertice=new ArrayList<>();
      for (AdyacenteConPeso adyacente : adyacenciasDelVertice) {
        adyacentesDelVeertice.add(adyacente.getIndiceVertice());  
      }
      Iterable<Integer> it=adyacentesDelVeertice;
      return it;
  }
 //GrafoPesado 
    public void eliminarArista(int posVerticeOrigen,int posVerticeDestino) throws ExcepcionAristaYaExiste{
          if(!existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
         throw new ExcepcionAristaYaExiste();
     }
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
     int n=0;
    while(n < 2){  
      List<AdyacenteConPeso> listRemovidaOrigen= new ArrayList<>();
       List<AdyacenteConPeso> posOrigenARemover=this.ListaDeAdyacencias.get(posVerticeOrigen);
        for (int i = 0; i < posOrigenARemover.size(); i++) {
           if(posVerticeDestino != posOrigenARemover.get(i).getIndiceVertice()){
                listRemovidaOrigen.add(new AdyacenteConPeso(posOrigenARemover.get(i).getIndiceVertice(),
                        posOrigenARemover.get(i).getCosto()));
           }
       }
        ListaDeAdyacencias.set(posVerticeOrigen, listRemovidaOrigen); 
        
        int auxPosOrigen=posVerticeOrigen;
        posVerticeOrigen=posVerticeDestino;
        posVerticeDestino=auxPosOrigen;
      n=n+1;
    }

  }
    
      
  public String mostrarGrafoPesado(){
        StringBuilder cadena = new StringBuilder();
 
        for (int i=0; i<ListaDeAdyacencias.size(); i++) {
                   cadena.append("["+i+"] ->");
            List<AdyacenteConPeso> adyacencia= ListaDeAdyacencias.get(i);
            for (int j = 0; j < adyacencia.size(); j++) {
                cadena.append("["+adyacencia.get(j).getIndiceVertice()+"|"+ adyacencia.get(j).getCosto()+"] --");
            }
                     cadena.append("\n");
 
        }
        
        return cadena.toString();
   }
}
