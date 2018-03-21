import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        int errorIndex = -1;
        for (int position = 0; position < text.length(); ++position){
        char next = text.charAt(position);
       
        if (next == '(' || next == '[' || next == '{') {
            // Process opening bracket, write your code here
            opening_brackets_stack.push(new Bracket(next, position+1));
            //System.out.println(opening_brackets_stack.peek().type);
        }

        else if (next == ')' || next == ']' || next == '}') {
            // Process closing bracket, write your code here
            if (opening_brackets_stack.empty()){
                errorIndex = position+1;
                break;
            }
            else{
                Bracket toCheck = opening_brackets_stack.pop();
                //System.out.println(toCheck.type);
                //System.out.println(next);
                if (!toCheck.Match(next)){
                    errorIndex = position+1;
                    break;
                }
            }
        }
        
        //System.out.println("errorIndex :" + errorIndex);
        } 
        if(!opening_brackets_stack.empty() && errorIndex == -1){
            errorIndex = opening_brackets_stack.peek().position;
        }
    
        System.out.println(errorIndex == -1 ? "Success" : errorIndex);

        // Printing answer, write your code here
    }
}
