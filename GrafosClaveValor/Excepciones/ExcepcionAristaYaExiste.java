/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosClaveValor.Excepciones;

/**
 *
 * @author brandon
 */
public class ExcepcionAristaYaExiste extends Exception{

    public ExcepcionAristaYaExiste() {
        super("arista ya existe");
    }

    public ExcepcionAristaYaExiste(String string) {
        super(string);
    }

    
}
