package util;

import java.util.HashMap;
import java.util.HashSet;

public class question1
{
    public static HashMap<Vertex, HashSet<Vertex>> treeToGraph (BinaryNode<Vertex> root)
    {
        HashMap<Vertex, HashSet<Vertex>> graph= new HashMap<>();

        //Base Case
        if (root == null)
        {
            return graph;
        }
        //Adds the Root to the hashmap
        graph.put(root.element,new HashSet<>());

        //Adds the Left and right subtrees to the graphs
        if(root.left != null)
        {
            //Adds the left element to the root's hashset
            graph.get(root.element).add(root.left.element);

            helper(graph, root.left);
        }
        if(root.right != null)
        {
            //Adds the right element to the root's hashset
            graph.get(root.element).add(root.right.element);

            helper(graph, root.right);
        }
        return graph;
    }

    public static void helper (HashMap<Vertex, HashSet<Vertex>> graph, BinaryNode<Vertex> root)
    {
        //Adds the element to the hashmap
        graph.put(root.element,new HashSet<>());

        //Handles the right and left subtrees.
        if(root.right != null)
        {
            //Adds the right element to the root's hashset
            graph.get(root.element).add(root.right.element);
            helper(graph, root.right);
        }
        if(root.left != null)
        {
            //Adds the left element to the root's hashset
            graph.get(root.element).add(root.left.element);
            helper(graph, root.left);
        }
    }
}
