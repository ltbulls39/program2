package edu.sdsu.cs;

import edu.sdsu.cs.datastructures.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App {
    static Set<IVertex<String>> vertexSet;
    static WDGraph<String, Integer> graph;

    static Map<String, IVertex<String >> vertexMap;
    public static void main(String[] args) {
        vertexMap = new HashMap<>();
        vertexSet = new HashSet<>();
        graph = new WDGraph<>();
        /**
         * Cities, then edges will come in
         */
        FileReader fileReader;
        BufferedReader cities;
        BufferedReader edges;

        switch (args.length) {
            case 0:
                fileReader = reader("cities.csv");
                cities = new BufferedReader(fileReader);
                fileReader = reader("edges.csv");
                edges = new BufferedReader(fileReader);
                addVertices(cities);
                addEdges(edges);
                break;
            case 2:
                fileReader = reader(args[0]);
                cities = new BufferedReader(fileReader);
                fileReader = reader(args[1]);
                edges = new BufferedReader(fileReader);
                double time = System.nanoTime();
                addVertices(cities);
                System.out.println("Time taken to addVertices: " + ((System.nanoTime() - time) / 1000000000));
                time = System.nanoTime();
                addEdges(edges);
                System.out.println("Time taken to addEdges: " + ((System.nanoTime() - time) / 1000000000));
                break;
            default:
                System.out.println("You've entered the incorrect number of command line arguments. Try again next time");
                System.exit(-1);
                break;
        }

        IVertex<String> sd = vertexMap.get("Quang Tri");
        IVertex<String> lg = vertexMap.get("An Xuyen");

        double time = System.nanoTime();
        Iterable<IEdge<Integer>> edgeIterator = graph.shortestPath(sd, lg);
        System.out.println("Time taken to compute: " + ((System.nanoTime() - time) / 1000000000));

        System.out.println(graph.minimumDistance(sd, lg));
        System.out.println();
        for (IEdge<Integer> edge : edgeIterator) {
            System.out.println(edge);
        }



    }

    private static void addEdges(BufferedReader reader) {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                IVertex<String> start = vertexMap.get(arr[0].trim());
                IVertex<String> end = vertexMap.get(arr[1].trim());
                Integer cost = Integer.parseInt(arr[2].trim());
                IEdge<Integer> edge = new WeightedEdge<>(start, end, cost);
                graph.addEdge(edge);
            }
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addVertices(BufferedReader reader) {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");

                IVertex<String> vertex = new GraphVertex<>(arr[0].trim());
                vertexSet.add(vertex);
                vertexMap.put(vertex.getName(), vertex);
                graph.addVertex(vertex);
            }
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileReader reader(String line) {
        try {
            FileReader fileReader = new FileReader(line);
            return fileReader;
        }   catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}