import java.io.*;
import java.util.*;
public class Stack{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader in = new BufferedReader(new FileReader("stack.in"));
      BufferedWriter out = new BufferedWriter(new FileWriter("stack.out"));
    ){
     int n = Integer.valueOf(in.readLine());
     ArrayList<String> stack = new ArrayList<>();
     while(n-->0){
       String[] sarr = in.readLine().split(" ");
       if(sarr[0].equals("+")){
         stack.add(sarr[1]);
       } else {
         String s = stack.get(stack.size()-1);
         stack.remove(stack.size()-1);
         out.write(s);
         out.newLine();
       }
     } 
    }
  }
}
