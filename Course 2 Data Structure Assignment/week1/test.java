import java.util.*;
import java.io.*;
/**
 * Write a description of test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test {
    int[] parent;
    
    int computeHeight2(int i){
	if (parent[i] == -1){
	    return 1;   
	}
	return 1 + computeHeight2(parent[i]);
    }
        
    
    int computeHeight() {
           int maxHeight = 0;
        // Node[] nodes = new Node[parent.length];
        // for (int i = 0 ; i < parent.length ; i++){
            // nodes[i] = new Node();
        // }
        // int rootNode = -1;
        // for (int i = 0; i < parent.length ; i++){
            // if(parent[i] != -1){
                // nodes[i].setParent(nodes[parent[i]]);
                // nodes[parent[i]].addChild(nodes[i]);
            // }
            // else{
                // i = rootNode;
            // }
        // }
        
        for (int i = 0 ; i < parent.length ; i++){
            maxHeight = Math.max(maxHeight,computeHeight2(i));
        }
    
        return maxHeight;
    }
    
    int computeHeightTree() {
            //int maxHeight = 0;
            Node[] nodes = new Node[parent.length];
            for (int i = 0 ; i < parent.length ; i++){
                nodes[i] = new Node();
            }
            int rootNode = -1;
            for (int i = 0; i < parent.length ; i++){
                if(parent[i] != -1){
                    //nodes[i].setParent(nodes[parent[i]]);
                    nodes[parent[i]].addChild(nodes[i]);
                }
                else{
                    rootNode = i;
                }
            }
            
            

            return getHeight(nodes[rootNode]);
    	}
    	
    	//helper method
    	
    	public int getHeight(Node node){
    	    int height = 0;
    	    if (node.getChild().isEmpty()){
    	        return 1;   
    	    }
    	    for (Node c : node.getChild()){
    	        height = Math.max(height, getHeight(c));
    	    }
    	    return 1 + height;
    	}
    	
    	private class Node{
	    //private int value;
	    private ArrayList<Node> children;
	    //private Node parent;
	    
	    public Node(){
	        //value = 0;
	        children = new ArrayList<Node>();
	        //parent = null;
	    }
	    
	    // public Node(int val){
	        // value = val;
	        // children = new ArrayList<Node>();
	    // }
	    
	    public ArrayList<Node> getChild(){
	        return children;   
	    }
	    
	    public void addChild(Node node){
	        children.add(node);
	    }
	    
	    // public void setParent(Node node){
	        // parent = node;
	    // }
	    
	    // public int getValue(Node node){
	        // return value;
	    // }
	    
	    // public Node getParent(){
	        // return parent;
	    // }
	    
	}

public void main() {
    parent = new int[]{-1,0,4,0,3};
    System.out.println(computeHeight());
    System.out.println(computeHeightTree());
    
}
}


