import java.util.*;

/**

XXXXXXX      XXXXXXXXXXXX       XXXXXXXXXXX
XX    X    XXX       XXX      XXX        XX
XX    X  XXX       XXX    XXXXX          XX
XX    XXXX      XXXX    XXX       XXX    XX
XX    XX     XXXX    XXXX      XXX  X    XX
XX         XXX     XXX       XXX    X    XX
XX       XX      XXX       XX       X    XX
XX    XXXX     XXX       XX         X    XX
XX    XX     XX      XXXX       XXXXX    XX
XX    XX  XXX       XX         XX        XX
XX    XXXXX      XXX         XXX         XX
XX    XXX      XXX         XXX           XX
XX            XX         XXX        XX   XX
XX        XXXXX      XXXXX        XXX    XX
XX      XXX       XXXX         XXXX X    XX
XX    XXX      XXXX         XXXX    X    XX
XX  XXX     XXXX          XXX       X    XX
XXXX       XX          XXXX         X    XX
XXX       XXXXXXXXXXXXXXX           XXXXXXX

**/
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    int tcc = sc.nextInt();
    double sqrtTwo = Math.sqrt(2);
    for(int i = 1; i <= tcc; ++i){
      int N = sc.nextInt();
      double pathLength = 0.0;
      if (N > 1){
        pathLength = (N-2)*(N-2)*sqrtTwo + 4*(N-1);
      }
      System.out.println(String.format("%.3f",pathLength));
    }
  }
}
