import java.util.*;
public class Question9_11{
	public static void main(String[]args){
		System.out.println(countWaysToParens("1^0|0|1", 0));
	}
	private static int countWaysToParens(String expr, int expected){
		return countWaysToParens(expr, expected, 0, expr.length()-1);
	}
	private static int countWaysToParens(String expr, int expected, int from, int to){
		if (from == to){
			if (toInt(expr.charAt(from)) == expected) {
				return 1;
			}
			return 0;
		}
		int ret = 0;
		for(int i = from+1; i <= to; i+=2){
			switch (expr.charAt(i)){
				case '^':
					ret += xorCountWaysToParens(expr, expected, from, i-1, i+1, to);
					break;
				case '|':
					ret += orCountWaysToParens(expr, expected, from, i-1, i+1, to);
					break;
				case '&':
					ret += andCountWaysToParens(expr, expected, from, i-1, i+1, to);
					break;
				default:
					throw new IllegalArgumentException(String.format("Invalid character[%s] found at position[%d] for expression[%s]", expr.charAt(i), i, expr));
			}
		}
		return ret;
	}
	private static int xorCountWaysToParens(String expr, int expected, int from1, int to1, int from2, int to2){
		return countWaysToParens(expr, 0, from1, to1)*countWaysToParens(expr, expected, from2, to2)+
			countWaysToParens(expr, 1, from1, to1)*countWaysToParens(expr, expected^1, from2, to2);
	}
	private static int orCountWaysToParens(String expr, int expected, int from1, int to1, int from2, int to2){
		int firstFalseCount = countWaysToParens(expr, 0, from1, to1);
		int secondFalseCount = countWaysToParens(expr, 0, from2, to2);
		if (expected == 0){
			return firstFalseCount*secondFalseCount;
		}
		int firstTrueCount = countWaysToParens(expr, 1, from1, to1);
		int secondTrueCount = countWaysToParens(expr, 1, from2, to2);
		return firstFalseCount*secondTrueCount+firstTrueCount*secondFalseCount+firstTrueCount*secondTrueCount;
	}
	private static int andCountWaysToParens(String expr, int expected, int from1, int to1, int from2, int to2){
                int firstTrueCount = countWaysToParens(expr, 1, from1, to1);
                int secondTrueCount = countWaysToParens(expr, 1, from2, to2);
                if (expected == 1){
                        return firstTrueCount*secondTrueCount;
                }
                int firstFalseCount = countWaysToParens(expr, 0, from1, to1);
                int secondFalseCount = countWaysToParens(expr, 0, from2, to2);
                return firstFalseCount*secondTrueCount+firstTrueCount*secondFalseCount+firstFalseCount*secondFalseCount;
        }
	private static int toInt(char c){
		return c-'0';
	}
}
