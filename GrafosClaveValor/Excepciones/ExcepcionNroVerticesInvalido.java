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
public class ExcepcionNroVerticesInvalido extends Exception {

    public ExcepcionNroVerticesInvalido() {
        super("numero de vertices invalido");
    }

    public ExcepcionNroVerticesInvalido(String string) {
        super(string);
    }

    
}
