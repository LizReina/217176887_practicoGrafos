/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuGrafos;

import GrafosClaveValor.DFS;
import GrafosClaveValor.DebilmenteConexo;
import GrafosClaveValor.DiGrafo;
import GrafosClaveValor.Excepciones.ExcepcionAristaYaExiste;
import GrafosClaveValor.Excepciones.ExcepcionNroVerticesInvalido;
import GrafosClaveValor.Grafo;
import GrafosClaveValor.GrafoNoPesadoE;
import GrafosClaveValor.DiGrafoNoPesadoE;
import GrafosClaveValor.MatrizCamino;
import GrafosPesadosCV.DiGrafoPesado;
import GrafosPesadosCV.DiGrafoPesadoAlgoritmo;
import GrafosPesadosCV.GrafoPesado;
import GrafosPesadosCV.GrafoPesadoAlgoritmo;
import java.util.Scanner;



/**
 *
 * @author liz fernanda reina quispert
 */
public class TestGrafo {
      public static void main(String[] args) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste{
    
      
       Scanner entrada= new Scanner(System.in);
        System.out.println("escoja para ver las soluciones -> \n" );
        System.out.println( "G   ->(solucion preguntas: 4,5 ) \n");
        System.out.println( " DG  ->(solucion preguntas: 1,2,3,6,7 ) \n");
        System.out.println( " GP  ->(solucion preguntas: \n)");       
        System.out.println( " DGP ->(solucion preguntas: 10)");
        
        String tipoDeArbol=entrada.next();
        tipoDeArbol=tipoDeArbol.toUpperCase();
     switch(tipoDeArbol){
          
          case("G"):
               Grafo grafo = new Grafo(6);
               GrafoNoPesadoE g = new GrafoNoPesadoE(grafo);
                grafo.insertarArista(0,1);
                grafo.insertarArista(0,2);
                grafo.insertarArista(1,3);
                grafo.insertarArista(1,2);
                grafo.insertarArista(3,4);
                grafo.insertarArista(2,4);
                grafo.insertarArista(3,5);
                System.out.println(grafo.mostrarGrafo());
                System.out.println("4.Para un grafo no dirigido implementar un algoritmo para encontrar que en que vértices del grafo hay ciclos \n"+
                       g.verticesConCiclo());
          
               System.out.println("5.-Para un grafo no dirigido implementar un algoritmo para encontrar el número de islas que hay en el grafo \n"+
                       g.cantidadIslas());
               System.out.println(""+g.hayCicloGrafo());
             
              break;
              
          case("DG"):
               DiGrafo diGrafo = new DiGrafo(9);
               DiGrafoNoPesadoE dg = new DiGrafoNoPesadoE(diGrafo);
               DebilmenteConexo db=new DebilmenteConexo();
                diGrafo.insertarArista(0,1);
                diGrafo.insertarArista(1,2);
                diGrafo.insertarArista(3,1);
                diGrafo.insertarArista(2,3);
                diGrafo.insertarArista(4,5);
                diGrafo.insertarArista(6,7);
                diGrafo.insertarArista(6,8);
               
                 MatrizCamino mc=new MatrizCamino(5);
                mc.insertarArista(0,1);
                mc.insertarArista(1,3);
                mc.insertarArista(3,1);
                mc.insertarArista(1,4);
                mc.insertarArista(2,2);
                mc.insertarArista(2,4);
                mc.insertarArista(4,2);
                
                System.out.println(diGrafo.mostrarDiGrafo());
              //  diGrafo.eliminarArista(2,3);
               //  System.out.println(diGrafo.mostrarDiGrafo());
              System.out.println("1.-Para un grafo dirigido implementar un método o clase que sea capas de retornar los componentes de las islas que existen en dicho digrafo \n"
                                 + "->  "+  dg.componentesIslas());
              System.out.println("2.-Para un grafo dirigido implementar un algoritmo para encontrar si el grafo dirigido tiene ciclos \n"+
                        "->  "+dg.hayCicloDirigido());
             System.out.println("3..-Para un grafo dirigido implementar un algoritmo para encontrar si es débilmente conexo \n"+
                       "->  "+ db.esDebilmenteConexo(diGrafo));
             System.out.println("6.-Para un grafo dirigido implementar un algoritmo para encontrar el número de islas que hay en el grafo  \n"+
                        "->  "+  dg.cantidadIslas());
             System.out.println("7.-Para un grafo dirigido implementar el algoritmo de Wharsall, que luego muestre entre que vértices hay camino  \n");
             System.out.println(mc.mostrarMatriz());
             mc.algoritmoWarshall();
             System.out.println("Warshal caminos");   
             System.out.println(mc.mostrarMatriz());

               break;
              
            case("GP"):
                GrafoPesado grafoP = new GrafoPesado(6);
                grafoP.insertarArista(0,1,50);
                grafoP.insertarArista(0,2,10);
                grafoP.insertarArista(0,4,60);
                grafoP.insertarArista(0,5,100);
                grafoP.insertarArista(1,3,50);
                grafoP.insertarArista(1,4,15);
                grafoP.insertarArista(2,1,5);
                grafoP.insertarArista(3,0,80);
                grafoP.insertarArista(3,5,20);
                grafoP.insertarArista(4,5,20);
                grafoP.insertarArista(5,1,40);
                grafoP.insertarArista(5,2,70);
     
               System.out.println(grafoP.mostrarGrafoPesado());
               GrafoPesadoAlgoritmo<Integer> gp = new GrafoPesadoAlgoritmo(grafoP);
                System.out.println(gp.hayCicloGrafoPesado());
            break;
            
            case("DGP"):
                DiGrafoPesado diGrafoP=new DiGrafoPesado(6);
                DiGrafoPesadoAlgoritmo algotimo=new DiGrafoPesadoAlgoritmo(diGrafoP);
                diGrafoP.insertarArista(0,1,50);
                diGrafoP.insertarArista(0,2,10);
                diGrafoP.insertarArista(0,4,60);
                diGrafoP.insertarArista(0,5,100);
                diGrafoP.insertarArista(1,3,50);
                diGrafoP.insertarArista(1,4,15);
                diGrafoP.insertarArista(2,1,5);
                diGrafoP.insertarArista(3,0,80);
                diGrafoP.insertarArista(3,5,20);
                diGrafoP.insertarArista(4,5,20);
                diGrafoP.insertarArista(5,1,40);
                diGrafoP.insertarArista(5,2,70); 
                System.out.println(diGrafoP.mostrarGrafoPesado());
                
                System.out.println("10.	Para un grafo dirigido pesado implementar el algoritmo de Dijkstra que muestre cual es el camino de costo mínimo entre un vértice a y b y cual el costo \n");
                System.out.println("ingrese los vetices a y b");
                System.out.println(algotimo.algoritmoDijkstra(entrada.nextInt(),entrada.nextInt()));
               
            break;
     }
    
     
      }
}
