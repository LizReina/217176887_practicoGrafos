/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosPesadosCV;

import GrafosClaveValor.DiGrafo;
import GrafosClaveValor.Excepciones.ExcepcionAristaYaExiste;
import GrafosClaveValor.Excepciones.ExcepcionNroVerticesInvalido;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brandon
 */
public class DiGrafoPesado<T extends Comparable<T>> extends GrafoPesado<T>  {
      public DiGrafoPesado() {
    }

    public DiGrafoPesado(int nroVerticesInicial) throws ExcepcionNroVerticesInvalido {
        super(nroVerticesInicial);
    }

       public void insertarArista(int posVerticeOrigen,int posVerticeDestino,double costo)throws ExcepcionAristaYaExiste{
        super.validarVertice(posVerticeOrigen);
        super.validarVertice(posVerticeDestino);
        if(super.existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }

        List<AdyacenteConPeso> adyacenciasDelOrigen=super.ListaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(new AdyacenteConPeso(posVerticeDestino,costo));
       
    }
       
  //DiGrafoPesado     
   public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) {
     super.validarVertice(posVerticeOrigen);
     super.validarVertice(posVerticeDestino);
        if(!existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
         try {
             throw new ExcepcionAristaYaExiste();
         } catch (ExcepcionAristaYaExiste ex) {
             Logger.getLogger(DiGrafo.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        
        List<AdyacenteConPeso> listRemovida= new ArrayList<>();
        List<AdyacenteConPeso> adyacenciasDelOrigen=this.ListaDeAdyacencias.get(posVerticeOrigen);
        for (int i = 0; i < adyacenciasDelOrigen.size(); i++) {
           if(posVerticeDestino != adyacenciasDelOrigen.get(i).getIndiceVertice()){
                listRemovida.add(new AdyacenteConPeso(adyacenciasDelOrigen.get(i).getIndiceVertice(),
                        adyacenciasDelOrigen.get(i).getCosto()));
           }
       }
      ListaDeAdyacencias.set(posVerticeOrigen, listRemovida);
    }
//DiGrafoPesado
    @Override
    public int gradoVertice(int posVertice) {
        List<AdyacenteConPeso> adyacenciaVertice=ListaDeAdyacencias.get(posVertice);
        return adyacenciaVertice.size();
    }
    
    public int gradoDeSalida(int posVertice) {
     return super.gradoVertice(posVertice);
    }
   
    public int gradoDeEntrada(int posVertice){
        super.validarVertice(posVertice);
        int entradasDeVertice=0;
        for (int i = 0; i < super.ListaDeAdyacencias.size(); i++) {
            List<AdyacenteConPeso> posAdyacente=ListaDeAdyacencias.get(i);
            for (int j = 0; j < posAdyacente.size(); j++) {
                if(posAdyacente.get(j).getIndiceVertice() == posVertice){
                     entradasDeVertice++;
                } 
            }
        }
      return entradasDeVertice;         
        }

   // cantidad aristas sobreescribir
    //eliminar Arista
    
 
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
