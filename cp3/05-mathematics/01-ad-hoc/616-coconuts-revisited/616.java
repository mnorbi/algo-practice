/**
See solution formula in: MATHEMATICAL PUZZLES AND DIVERSIONS (Marting Gardner) and https://cms.math.ca/crux/backfile/Crux_v4n07_Aug.pdf
and http://demonstrations.wolfram.com/CoconutsSailorsAndAMonkey/
**/
import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextInt()){
      int coconuts = sc.nextInt();
      if (coconuts < 0) break;
      findFor(coconuts);
    }
  }
  private static void findFor(int coconuts){
    for(int people = 10; people > 1; --people){
      int remaining = coconuts;
      int rounds = people;
      for(;rounds>0;--rounds){
        if ((remaining-1)%people != 0 || remaining <= 0){
          break;
        }
        remaining = ((remaining-1)/people)*(people-1);
      }
      if (rounds == 0 && remaining >= 0 && remaining%people == 0){
        System.out.println(String.format("%d coconuts, %d people and %d monkey", coconuts, people, 1));
        return;
      }
    }
    System.out.println(String.format("%s coconuts, no solution", coconuts));
  }
}
