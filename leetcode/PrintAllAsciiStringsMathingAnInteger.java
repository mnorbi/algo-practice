import java.util.*;
class PrintAllAsciiStringsMatchingAnInteger{
        public static void main(String[] args){
                printCombos(1234);
        }
        public static void printCombos(int i){
                printCombos(i, "");
        }
        private static void printCombos(int i, String str){
                if (i == 0){
                        System.out.println(str);
                        return;
                }
                int rightDigit = rightDigits(i,1);
                printCombos(
                        trimRightDigits(i,1),
                        toChar(rightDigit) + str);
                int rightTwoDigits = rightDigits(i,2);
                if (rightDigit != rightTwoDigits &&
                    inRange(rightTwoDigits)){
                        printCombos(
                                trimRightDigits(i,2),
                                toChar(rightTwoDigits) + str);
                }
        }
        private static int rightDigits(int i, int len){
                return i%((int)Math.pow(10,len));
        }
        private static int trimRightDigits(int i, int len){
                return i/((int)Math.pow(10,len));
        }
        private static char toChar(int i){
                return Character.toChars('a'+i)[0];
        }
        private static boolean inRange(int i){
                return i < 26;
        }
}
