/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosPesadosCV;

/**
 *
 * @author liz fernanda reina quispert
 */
public class AdyacenteConPeso {
  private int indiceVertice;
  private double costo;

    public AdyacenteConPeso(int vertice) {
        this.indiceVertice=vertice;
    }

    public AdyacenteConPeso(int vertice, double costo) {
        this.indiceVertice = vertice;
        this.costo = costo;
    }

    public void setIndiceVertice(int indiceVertice) {
        this.indiceVertice = indiceVertice;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getIndiceVertice() {
        return indiceVertice;
    }

    public double getCosto() {
        return costo;
    }


    public int compareTo(AdyacenteConPeso vert) {
        Integer esteVertice=this.indiceVertice;
        Integer elOtroVertice=vert.indiceVertice;
        return esteVertice.compareTo(elOtroVertice);
    }

    @Override
    public boolean equals(Object otro) {
        if(otro==null){
            return false;
        }
        if(getClass()!=otro.getClass()){
            return false;
        }
        AdyacenteConPeso other=(AdyacenteConPeso)otro;
        return this.indiceVertice==other.indiceVertice;
    }

    @Override
    public int hashCode() {
        int hash=3;
        hash=67*hash+this.indiceVertice;
        return hash;
    }
     
}
