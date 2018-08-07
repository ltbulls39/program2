package edu.sdsu.cs;

import edu.sdsu.cs.datastructures.*;

public class App {
    public static void main(String[] args) {
        WDGraph<String, Integer> wdGraph = new WDGraph<>();

        IVertex<String> sanDiego = new GraphVertex<>("San Diego");
        IVertex<String> eastLake = new GraphVertex<>("Eastlake");
        IVertex<String> chulaVista = new GraphVertex<>("Chula Vista");
        IVertex<String> bonita = new GraphVertex<>("Bonita");
        IVertex<String> laMesa = new GraphVertex<>("La Mesa");
        IVertex<String> lemonGrove = new GraphVertex<>("Lemon Grove");


        IEdge<Integer> c2E = new WeightedEdge<>(chulaVista, eastLake, 6);
        IEdge<Integer> c2B = new WeightedEdge<>(chulaVista, bonita, 2);
        IEdge<Integer> e2S = new WeightedEdge<>(eastLake, sanDiego, 17);
        IEdge<Integer> b2LG = new WeightedEdge<>(bonita, lemonGrove, 9);
        IEdge<Integer> b2LM = new WeightedEdge<>(bonita, laMesa, 5);


        wdGraph.addVertex(sanDiego);
        wdGraph.addVertex(eastLake);
        wdGraph.addVertex(chulaVista);
        wdGraph.addVertex(bonita);
        wdGraph.addVertex(laMesa);
        wdGraph.addVertex(lemonGrove);


        wdGraph.addEdge(c2B);
        wdGraph.addEdge(c2E);
        wdGraph.addEdge(e2S);
        wdGraph.addEdge(b2LG);
        wdGraph.addEdge(b2LM);


        System.out.println(wdGraph);
        System.out.println();
        System.out.println();



        IEdge<Integer> s2E = new WeightedEdge<>(sanDiego, eastLake, 17);
        wdGraph.addEdge(s2E);

        System.out.println(wdGraph);
    }
}