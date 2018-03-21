import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
    }

    public class TreeHeight {
    	int n;
    	int parent[];
    	
    	void read() throws IOException {
    		FastScanner in = new FastScanner();
    		n = in.nextInt();
    		parent = new int[n];
    		for (int i = 0; i < n; i++) {
    			parent[i] = in.nextInt();
    		}
    	}
    	
    	int computeHeight2(int i){
    	    if (parent[i] == -1){
    	        return 1;   
    	    }
    	    return 1 + computeHeight2(parent[i]);
    	}
    
    	int computeHeightFast() {
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
    	
    	
    	
    	
    	int computeHeight() {
            int maxHeight = 0;
            if (n <= 10000 || parent.length <= 10000){
               return computeHeightFast();
            }
            
            

            return computeHeightTree();
    	}
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

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
