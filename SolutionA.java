import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class Road {
    public String city1;
    public String city2;

    public Road(String city1, String city2) {
        this.city1 = city1;
        this.city2 = city2;
    }

}

class RoadMap {
    Map<String, Set<Road>> roadMap = new HashMap<String, Set<Road>>();

    //This function helps to get all the cities in the graph
    public Set<String> getAllCities() {
        return this.roadMap.keySet();
    }

    //This function will read the input 
    public void readLine(String line) {
        String[] csv = line.split(",");
        String city1 = csv[0];
        String city2 = csv[1];
        addRoad(city1, city2);
    }

    private void addCity(String city) {
        this.roadMap.put(city, new HashSet<Road>());
    }

    //This function will add both outgoing and incoming roads between two cities
    private void addRoad(String city1, String city2) {
        Road road1 = new Road(city1, city2);
        Road road2 = new Road(city2, city1);
        if (!this.roadMap.containsKey(city1)) {
            addCity(city1);
        }
        if (!this.roadMap.containsKey(city2)) {
            addCity(city2);
        }
        this.roadMap.get(city1).add(road1);
        this.roadMap.get(city2).add(road2);
    }

    //This function will return all the outgoing roads from a city
    public Set<Road> getAllOutgoingRoads(String node) {
        return this.roadMap.get(node);
    }

}

public class GraphAssignment {
    static RoadMap roadMap = new RoadMap();

    public static void readMap(Scanner scanner) {
        while (true) {
            String mapLine = scanner.nextLine();
            if (mapLine.equals("")) {
                break;
            }
            roadMap.readLine(mapLine);
        }

    }


    public static void findAnyRouteToCity(String source, String destination) {
        
       
        Map<String, Boolean> visitedNode = new HashMap<String, Boolean>(); //Syntax to create a Map which tells whether the node is visited or not.
        
       
        Map<String, String> routeMap = new HashMap<String, String>(); //Syntax to create a Map which would contain the route bw two cities.

      
        Queue<String> queue = new LinkedList<String>(); //Syntax to create a queue for each node tranversal
       
       
        for (String city : roadMap.getAllCities()) // Initializing all the cities value to false before tranversing.
        {
            visitedNode.put(city, false);
        }

        visitedNode.put(source, true);//Our source city would be initialized to True.
       
        queue.add(source);
        while (!queue.isEmpty()) {
            
        String currentCity = queue.remove();
      
        for (Road road : roadMap.getAllOutgoingRoads(currentCity)) { //Traversing through all nodes connected with currentCity.
                if (!visitedNode.get(road.city2)) {

                 //Add the city2 of current node to queue if it is False.
                    queue.add(road.city2);
                 
                //Update the false value of the city2 to True.
                    visitedNode.put(road.city2, true);

                //Put currentCity and city2 to the route(HashMap).
                    routeMap.put(road.city2, currentCity);
                }
            }
        }

        if (!visitedNode.get(destination)) { //Program Syntax to check whether destination city is available in route HashMap or not.
            System.out.println("Not Reachable");
            } 
            else {
            String currentCity = destination;
            String route = destination;

           while (!currentCity.equals(source)) { //Syntax to print the path from source to destination city.
                currentCity = routeMap.get(currentCity);
                route = currentCity + " -> " + route;
            }
            System.out.println(route);
        }
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Map");
        readMap(scanner);
        System.out.println("Enter the source ");
        String source = scanner.nextLine();
        System.out.println("Enter the destination ");
        String destination = scanner.nextLine();
        System.out.println("The route from "+source+" to "+destination+" is");

        findAnyRouteToCity(source, destination);
    }

}
