import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class EditDistance{

  public static void main (String[] args) {
    String[] arr = new String[2];
    arr[0] = "Avery";
    arr[1] = "Briggs";
    arr = getUserStrings();
    // maxEditDistance(arr[0], arr[1]); // works but is just a.length() + b.length()
    computeMinEditDistance(arr[0], arr[1]);
  }

  public static void computeMinEditDistance(String a, String b){
    int lenA = a.length();
    int lenB = b.length();
    int x = max(lenA, lenB);
    String s = ((x == lenA)? b : a);
    ArrayList<String> instructions = minEditDistance(a, b);
    System.out.println(instructions);
    // for(int i = 0, j = 0; i < instructions.size(); i++){
    //   else{ //do nothing
    //     if(i < instructions.size()-1){
    //       System.out.print(s.charAt(i) + ", ");
    //     }
    //     else{
    //       System.out.println(s.charAt(i));
    //     }
    //   }
    // }
  }

  public static void maxEditDistance (String a, String b) {
    a = a.toUpperCase();
    b = b.toUpperCase();
    System.out.println("Maximum edit Distance to convert \"" + a + "\" to \"" + b + "\"");
    int n = a.length() + 2;
    int m = b.length() + 2;
    int[][] table = new int[m][n];
    for(int i = 2; i < max(n, m); i++){
      if(i < n){
        table[0][i] = a.charAt(i - 2);
        table[1][i] = i - 1;
      }
      if(i < m){
        table[i][0] = b.charAt(i - 2);
        table[i][1] = i - 1;
      }
    }
    for(int i = 2; i < m; i++){
      for(int j = 2; j < n; j++){
        int x = table[i][j-1];
        int y = table[i-1][j-1];
        int z = table[i-1][j];
        int mini = max(x, max(y, z));
        int u = table[0][j];
        int v = table[i][0];
        // System.out.println("x: " + x + ", y: " + y + ", z: " + z + ", max(x, max(y, z): " + mini);
        table[i][j] = mini + 1;
      }
    }
    show(table);
  }

  public static ArrayList<String> minEditDistance (String a, String b) {
    a = a.toUpperCase();
    b = b.toUpperCase();
    System.out.println("Minimum edit Distance to convert \"" + a + "\" to \"" + b + "\"");
    int n = a.length() + 2;
    int m = b.length() + 2;
    int[][] table = new int[m][n];
    for(int i = 2; i < max(n, m); i++){
      if(i < n){
        table[0][i] = a.charAt(i - 2);
        table[1][i] = i - 1;
      }
      if(i < m){
        table[i][0] = b.charAt(i - 2);
        table[i][1] = i - 1;
      }
    }
    for(int i = 2; i < m; i++){
      for(int j = 2; j < n; j++){
        int x = table[i][j-1];
        int y = table[i-1][j-1];
        int z = table[i-1][j];
        int mini = min(x, min(y, z));
        int u = table[0][j];
        int v = table[i][0];
        if(u == v){
          table[i][j] = table[i-1][j-1];
        }
        else{
          // System.out.println("x: " + x + ", y: " + y + ", z: " + z + ", min(x, min(y, z): " + mini);
          table[i][j] = mini + 1;
        }
      }
    }
    show(table);
    // System.out.println(backtrackInstrictions(table, table.length-1, table[table.length-1].length-1, ""));
    ArrayList<String> instructions = new ArrayList<String>();
    instructions = backtrackInstrictionsA(table, table.length-1, table[table.length-1].length-1, instructions);
    Collections.reverse(instructions);
    return instructions;
  }

  public static int min(int a, int b){
    return ((a <= b)? a : b);
  }

  public static int max(int a, int b){
    return ((a >= b)? a : b);
  }

  public static void show(int[][] arr){
    System.out.print("{");
    for(int i = 0; i < arr.length; i++){
      System.out.print("{");
      if(i > 0)
        System.out.print(" ");
      for(int j = 0; j < arr[i].length; j++){
        if(j < arr[i].length-1){
          if(i == 0 || j == 0)
            System.out.print((char)arr[i][j] + ", ");
          else
            System.out.print(arr[i][j] + ", ");
        }
        else{
          if(i == 0 || j == 0)
            System.out.print((char)arr[i][j]);
          else
            System.out.print(arr[i][j]);
        }
      }
      if(i < arr.length-1)
        System.out.println("},");
      else
        System.out.print("}");
    }
    System.out.println("}");
  }

  public static String backtrackInstrictions(int[][] arr, int i, int j, String line){
    int a = arr[i][j-1];
    int b = arr[i-1][j-1];
    int c = arr[i-1][j];
    int x = min(a, min(b, c));
    int y = arr[i][j];
    int l = line.length();
    // System.out.println("i: " +i + ", j: " + j+" x: " + x + ", a: " + a + ", b: " + b + ", c: " +c);
    if(x == b && arr[i][j] != b){
      line += "Exchange {"+(char)arr[0][j]+" -> "+(char)arr[i][0]+"},\n" ;
      i--;
      j--;
    }
    else if(y == b && x == b){ // checked last
      line += "Do nothing {"+(char)arr[0][j]+","+(char)arr[i][0]+"},\n" ;
      i--;
      j--;
    }
    else if(x == c && (y-1) == c){
      line += "insert {"+(char)arr[i][0]+"},\n" ;
      i--;
    }
    else if(x == a){
      line += "Delete {"+(char)arr[0][j]+"},\n" ;
      j--;
    }
    else{
      System.out.println("ERROR");
      return line;
    }
    // System.out.println(line);
    if(i < 2 || j < 2){
      return line;
    }
    else{
      return backtrackInstrictions(arr, i, j, line);
    }
  }

  public static ArrayList<String> backtrackInstrictionsA(int[][] arr, int i, int j, ArrayList<String> line){
    int a = arr[i][j-1];
    int b = arr[i-1][j-1];
    int c = arr[i-1][j];
    int x = min(a, min(b, c));
    int y = arr[i][j];
    int l = line.size();
    // System.out.println("i: " +i + ", j: " + j+" x: " + x + ", a: " + a + ", b: " + b + ", c: " +c);
    if(x == b && arr[i][j] != b){
      line.add("Exchange {"+(char)arr[0][j]+" -> "+(char)arr[i][0]+"},\n");
      i--;
      j--;
    }
    else if(y == b && x == b){ // checked last
      line.add("Do nothing {"+(char)arr[0][j]+"},\n");
      i--;
      j--;
    }
    else if(x == c && (y-1) == c){
      line.add("insert {"+(char)arr[i][0]+"},\n");
      i--;
    }
    else if(x == a){
      line.add("Delete {"+(char)arr[0][j]+"},\n");
      j--;
    }
    else{
      System.out.println("ERROR");
      return line;
    }
    // System.out.println(line);
    if(i < 2 || j < 2){
      return line;
    }
    else{
      backtrackInstrictionsA(arr, i, j, line);
      return line;
    }
  }

  public static String[] getUserStrings () {
    String[] res = new String[2];
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the first string:");
    String line1 = scan.nextLine();
    System.out.println("Enter the second string:");
    String line2 = scan.nextLine();
    // return res = {line1, line2};
    res[0] = line1;
    res[1] = line2;
    return res;
  }

}
