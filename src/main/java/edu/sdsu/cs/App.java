package edu.sdsu.cs;

import edu.sdsu.cs.datastructures.*;

import java.io.BufferedReader;
import java.io.File;
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
        FileReader fileReader = reader("cities.csv");
        BufferedReader cities = new BufferedReader(fileReader);
        fileReader = reader("edges.csv");
        BufferedReader edges = new BufferedReader(fileReader);
        addVertices(cities);
        addEdges(edges);

        System.out.println(graph);
        System.out.println();
        System.out.println();
        IVertex<String> sanDiego = vertexMap.get("San diego");
        IVertex<String> lemonGrove = vertexMap.get("Lemon grove");
        System.out.println(graph.minimumDistance(sanDiego, lemonGrove));
//        File file = createFile();
    }

    private static void addEdges(BufferedReader reader) {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                IVertex<String> start = vertexMap.get(arr[0].trim());
                IVertex<String> end = vertexMap.get(arr[1].trim());
                Integer thing = Integer.parseInt(arr[2].trim());
                IEdge<Integer> edge = new WeightedEdge<>(start, end, thing);
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
                IVertex<String> vertex = new GraphVertex<>(line);
                vertexSet.add(vertex);
                vertexMap.put(vertex.getName(), vertex);
                graph.addVertex(vertex);
            }
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static File createFile() {
        try {
            File file = new File("edges.csv");
            file.createNewFile();
            return file;
        }   catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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