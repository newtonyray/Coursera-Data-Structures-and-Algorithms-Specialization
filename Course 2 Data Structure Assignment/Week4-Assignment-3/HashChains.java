import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> elems;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;
    private ArrayList[] table; 
    private int idx;

    public static void main(String[] args) throws IOException {
        
        
        
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query) {
        
        switch (query.type) {
            case "add":
                idx = hashFunc(query.s);
                if(!table[idx].contains(query.s)){
                    table[idx].add(0,query.s);} 
                break;
            case "del":
                idx = hashFunc(query.s);
                if (table[idx].contains(query.s)){
                    table[idx].remove(query.s);}
                break;
            case "find":
                idx = hashFunc(query.s);
                System.out.println(table[idx].contains(query.s)? "yes" : "no");
                break;
            case "check":
                if (table[query.ind].size() == 0 ){
                    System.out.print("\n");
                }
                else{
                    String toPrint = "";
                    for(int i = 0; i<table[query.ind].size();i++){
                        toPrint = toPrint + table[query.ind].get(i) + " ";
                    }
                    System.out.println(toPrint);
                }
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        table = new ArrayList[bucketCount];
        for(int i = 0 ; i < bucketCount;i++){
            table[i] = new ArrayList<Query>();
        }
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
