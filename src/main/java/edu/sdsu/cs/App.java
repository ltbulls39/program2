package edu.sdsu.cs;

import edu.sdsu.cs.datastructures.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Program 2
 * CS 310
 *
 * Implementation of a weighted directed graph.
 * Created an interface for vertices, and an
 * interface for edges
 *
 * @author Nicholas Hernandez
 *      - cssc0256
 * @author Bernard Gonzales
 *      - cssc
 */

public class App {
    private static List<IVertex<String>> vertexList;
    private static IGraph<String, Integer> graph;
    private static Map<String, IVertex<String>> vertexMap;

    public static void main(String[] args) {
        vertexMap = new HashMap<>();
        vertexList = new LinkedList<>();
        graph = new WDGraph<>();

        FileReader fileReader;
        BufferedReader cities;
        BufferedReader edges;

        switch (args.length) {
            case 0:
                fileReader = reader("defaultCities.csv");
                cities = new BufferedReader(fileReader);
                fileReader = reader("defaultEdges.csv");
                edges = new BufferedReader(fileReader);
                addVertices(cities);
                addEdges(edges);
                break;
            case 2:
                fileReader = reader(args[0]);
                cities = new BufferedReader(fileReader);
                fileReader = reader(args[1]);
                edges = new BufferedReader(fileReader);
                addVertices(cities);
                addEdges(edges);
                break;
            default:
                System.out.println("Incorrect number of arguments. 0 or 2 expected. Try again next time.");
                System.exit(-1);
                break;
        }

        System.out.println("Would you like to enter your own city names to find a path between?     y/n");
        Scanner scan = new Scanner(System.in);
        String response = scan.nextLine();
        performTests(response, scan);

    }

    private static void performTests(String response, Scanner scan) {
        switch (response) {
            case "y":
                printVertices();
                System.out.println("Enter the name of the starting city: ");
                IVertex<String> start = null;
                while (start == null) {
                    response = scan.nextLine().toLowerCase();
                    response = changeFormat(response);
                    start = getVertex(response);
                    if (start == null) System.out.println("Not a valid city, please enter another one");
                }
                System.out.println("Enter the name of the ending city: ");
                IVertex<String> end = null;
                while (end == null) {
                    response = scan.nextLine().toLowerCase();
                    response = changeFormat(response);
                    end = getVertex(response);
                    if (end == null) System.out.println("Not a valid city, please enter another one");
                }

                int distance = graph.minimumDistance(start, end);

                switch (distance) {
                    case -2:
                        System.out.println("You entered the same city as a start and an end.");
                        System.exit(0);
                        break;
                    case -1:
                        System.out.println("Invalid start or ending vertex.");
                        System.exit(-1);
                        break;
                    case 0:
                        System.out.println("There is no path between the two vertices");
                        System.exit(-1);
                        break;
                    default:
                        break;
                }

                System.out.println("The distance from " + start.getName() + " to " + end.getName() + " is: " +
                        distance);
                System.out.println();
                System.out.println("Shortest path from " + start.getName() + " to " + end.getName() + ": ");

                for (IEdge<Integer> edge : graph.shortestPath(start, end)) {
                    System.out.println(edge);
                }
                break;
            case "n":
                start = vertexList.get(0);
                end = vertexList.get(vertexList.size() - 1);

                distance = graph.minimumDistance(start, end);
                switch (distance) {
                    case -2:
                        System.out.println("You entered the same city as a start and an end.");
                        System.exit(0);
                        break;
                    case -1:
                        System.out.println("Invalid start or ending vertex.");
                        System.exit(-1);
                        break;
                    case 0:
                        System.out.println("There is no path between the two vertices");
                        System.exit(-1);
                        break;
                    default:
                        break;
                }

                System.out.println("The distance from " + start.getName() + " to " + end.getName() + " is: " +
                        distance);
                System.out.println();
                System.out.println("Shortest path from " + start.getName() + " to " + end.getName() + ": ");

                for (IEdge<Integer> edge : graph.shortestPath(start, end)) {
                    System.out.println(edge);
                }
                break;
            default:
                break;
        }
    }

    private static void printVertices() {
        System.out.println();
        for (IVertex<String> vertex: graph.vertices()) {
            System.out.println(vertex);
        }
        System.out.println();
    }

    private static void addVertices(BufferedReader reader) {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                IVertex<String> vertex = new GraphVertex<>(changeFormat(arr[0].trim()));
                vertexList.add(vertex);
                vertexMap.put(vertex.getName(), vertex);
                graph.addVertex(vertex);
            }
            reader.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void addEdges(BufferedReader reader) {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                IVertex<String> start = vertexMap.get(changeFormat(arr[0].trim()));
                IVertex<String> end = vertexMap.get(changeFormat(arr[1].trim()));
                Integer cost = Integer.parseInt(arr[2].trim());
                IEdge<Integer> edge = new WeightedEdge<>(start, end, cost);
                graph.addEdge(edge);
            }

            reader.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static IVertex<String> getVertex(String name) {
        if (vertexMap.containsKey(name)) {
            return vertexMap.get(name);
        }

        return null;
    }

    private static String changeFormat(String name) {
        String[] arr = name.split(" ");
        for (int i = 0; i < arr.length; i++) {
            char[] characters = arr[i].toCharArray();
            characters[0] = Character.toUpperCase(characters[0]);
            arr[i] = new String(characters);
        }

        return String.join(" ", arr);
    }

    private static FileReader reader(String line) {
        try {
            return new FileReader(line);
        }   catch (IOException e) {
            System.out.println(line + " is not a correct file name. Please run again.");
            System.exit(-1);
        }
        return null;
    }
}