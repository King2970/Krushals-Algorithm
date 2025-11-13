package util;

import java.io.File;

import java.util.*;

public class question2
{
    private static final int VERTEX_COUNT =20;
    private static final String INPUT_PATH = System.getProperty("user.dir")+"\\input\\" ;

    public static void main(String[] args) throws Exception
    {
        double [][] euclideanDistance = new double[VERTEX_COUNT][VERTEX_COUNT];
        double [][] coordinates = new double[VERTEX_COUNT][2];
        Scanner fileReader = new Scanner(new File(INPUT_PATH + "islands locations-1.csv"));
        fileReader.nextLine();

        //Reads the given CSV file and gets the locations
        while (fileReader.hasNextLine())
        {
            String[] tokens = fileReader.nextLine().split(",");
            int islandID = Integer.parseInt(tokens[0]);
            double lat = Double.parseDouble(tokens[1]);
            double lon = Double.parseDouble(tokens[2]);
            coordinates[islandID][0] =lat;
            coordinates[islandID][1] =lon;
        }

        //gets the distance of every possible edge
        for(int from = 0; from<VERTEX_COUNT-1; from++)
        {
            for(int to = 0; to<VERTEX_COUNT-1; to++)
            {
               euclideanDistance[from][to]= getDirectDistance(coordinates[from][0],coordinates[from][1],coordinates[to][0],coordinates[to][1]);
            }
        }

        //Makes a list of all possible bridges
        List<edge> allPossibleBridges = new ArrayList<>();
        for(int from = 0; from<VERTEX_COUNT-1; from++)
        {
            for(int to =from+1; to<VERTEX_COUNT-1; to++)
            {
                allPossibleBridges.add(new edge(from,to,euclideanDistance[from][to]));
            }
        }
        //Sorts the list
        Collections.sort(allPossibleBridges);

        DisjointSet<Integer> connectedBridges = new DisjointSet<>();
        System.out.println("Here are the min. spanning tree edges:");

        int counter =0;
        double totalLength=0;
        //Main logic loop, It goes through each connection until 19 unions are made.
        for (edge edge: allPossibleBridges)
        {
            //checks to make sure the added bridge won't cause a loop
            if(connectedBridges.find(edge.from) != connectedBridges.find(edge.to))
            {
                System.out.println(edge);
                System.out.println("unionizing: "+edge.from + " and "+ edge.to );
                connectedBridges.union(edge.from, edge.to);
                counter++;
                //Counts the total length
                totalLength = edge.weight + totalLength;
            }

            if(counter == VERTEX_COUNT-1)
            {
                break;
            }
        }
        //Prints out total length
        System.out.println("The length of all the bridges is: " + totalLength);
    }

    public static double getDirectDistance(double lat1, double lon1, double lat2, double lon2) {
        //finds the direct distance (in meters) on earth surface between
        //point 1 at (lat1Â° N, lon1Â° W) and point 2 at (lat2Â° N, lon2Â° W)
        double psi1 = lat1 * Math.PI/180;
        double psi2 = lat2 * Math.PI/180;
        double deltaPsi = psi2 - psi1;
        double deltaLambda = (lon2-lon1) * Math.PI/180;
        double a = Math.sin(deltaPsi/2) * Math.sin(deltaPsi/2) +
                Math.cos(psi1) * Math.cos(psi2) *
                        Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
        return  2 * 6371e3 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    }
}
