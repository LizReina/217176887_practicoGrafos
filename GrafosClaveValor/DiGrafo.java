/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosClaveValor;

import GrafosClaveValor.Excepciones.ExcepcionAristaYaExiste;
import GrafosClaveValor.Excepciones.ExcepcionNroVerticesInvalido;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brandon
 */
public class DiGrafo extends Grafo{

    public DiGrafo() {
    }

    public DiGrafo(int nroVerticesInicial) throws ExcepcionNroVerticesInvalido {
        super(nroVerticesInicial);
    }

       public void insertarArista(int posVerticeOrigen,int posVerticeDestino)throws ExcepcionAristaYaExiste{
        super.validarVertice(posVerticeOrigen);
        super.validarVertice(posVerticeDestino);
        if(super.existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }

        List<Integer> adyacenciasDelOrigen=super.ListaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(posVerticeDestino);
       
    }
       
       
    @Override
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
        
        List<Integer> adyacenciasDelOrigen=this.ListaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.remove(Integer.valueOf(posVerticeDestino));
            
    }

    @Override
    public int gradoVertice(int posVertice) {
        List<Integer> adyacenciaVertice=ListaDeAdyacencias.get(posVertice);
        return adyacenciaVertice.size();
    }
    
    public int gradoDeSalida(int posVertice) {
     return super.gradoVertice(posVertice);
    }
   
    public int gradoDeEntrada(int posVertice){
        super.validarVertice(posVertice);
        int entradasDeVertice=0;
        for (List<Integer> ayacentesDeUnVertice :super.ListaDeAdyacencias) {
            for (Integer posAdyacente : ayacentesDeUnVertice) {
                if(posAdyacente == posVertice){
                    entradasDeVertice++;
                }
            }
        }
      return entradasDeVertice;
    }
    
   // cantidad aristas sobreescribir
    //eliminar Arista
    
 
     public String mostrarDiGrafo(){
        StringBuilder cadena = new StringBuilder();
 
        for (int i=0; i<ListaDeAdyacencias.size(); i++) {
            cadena.append( "["+i+"] ->"  +ListaDeAdyacencias.get(i)+"\n");
        }
        
        return cadena.toString();
   }
}
