import java.util.*;

/**
You are given 4 integer numbers.
Using each number once and only
once with any operators from +,
-, *, /, (, ), build an expression
that evaluates to 24.
**/
class FindExpressionEqualToValue_CareerCup_5735906000502784{
    public static void main(String[]arg){
        System.out.println(gen(new int[]{5, 7, 13, 169}, 4));
    }

    private static char[] OPERATORS = "+-*/".toCharArray();

    public static String gen(int[] numbers, int target){
        String[] expr = new String[2*numbers.length-1];
        for(int j = 0; j < numbers.length; ++j){
            expr[j] = ""+numbers[j];
        }

        int[] selectedOperatorIds = new int[numbers.length-1];
        selectedOperatorIds[0] = -1;
        int allOperatorComboCount = exp(OPERATORS.length,selectedOperatorIds.length);
        for(int i = 0; i < allOperatorComboCount; ++i){
            genNextOperatorCombos(expr, numbers.length, selectedOperatorIds);
            String ret = find(expr, target, 0, expr.length);
            if (ret != null) return ret;
        }

        return null;
    }

    private static String find(String[] expr, int target, int lo, int hi){
        if (lo == hi){
            String ret = eval(expr, target);
            return ret;
        }
        for(int i = lo; i < hi; ++i){
            swap(expr, lo, i);
            String ret = find(expr, target, lo+1, hi);
            if (ret != null) return ret;
            swap(expr, lo, i);
        }
        return null;
    }

    private static String eval(String[] expr, int target){
        Deque<String> stack = new ArrayDeque<>();
        for(int i = expr.length-1; i >= 0; --i){
            String op = expr[i];
            if (isOperand(op)){
                stack.push(op);
            } else {
                String operator = op;
                if (stack.isEmpty()) return null;
                String a = stack.pop();
                if (stack.isEmpty()) return null;
                String b = stack.pop();
                Integer val = eval(Integer.parseInt(a), operator.charAt(0), Integer.parseInt(b));
                if (val == null) return null;
                stack.push(val.toString());
            }
        }
        if (stack.isEmpty()) return null;
        if (target == Integer.parseInt(stack.pop())){
            return polishToInfix(expr);
        }
        return null;
    }

    private static String polishToInfix(String[] expr){
        Deque<String> stack = new ArrayDeque<>();
        for(int i = expr.length-1; i >= 0; --i){
            String op = expr[i];
            if (isOperand(op)){
                stack.push(op);
            } else {
                stack.push(
                        String.format("%s%s%s%s%s",
                                parenthesesIfNeeded(op, "("),
                                stack.pop(), op, stack.pop(),
                                parenthesesIfNeeded(op, ")")));
            }
        }
        return stack.pop();
    }

    private static String parenthesesIfNeeded(String operator, String p){
        if (operator.equals("+") || operator.equals("-")) return p;
        return "";
    }

    private static Integer eval(int a, char op, int b){
        switch (op){
            case '+': return a+b;
            case '*': return a*b;
            case '-': return a-b;
            case '/':
                if (b == 0) return null;
                else return a/b;
            default: throw new IllegalArgumentException(""+op);
        }
    }

    private static boolean isOperand(String s){
        return s.charAt(s.length()-1) >= '0' && s.charAt(s.length()-1) <= '9';
    }

    private static void swap(String[] ss, int from, int to){
        String tmp = ss[from];
        ss[from] = ss[to];
        ss[to] = tmp;
    }

    private static void genNextOperatorCombos(String[] expr, int shift, int[] selectedOperatorIds){
        stepCursor(selectedOperatorIds, OPERATORS.length);
        for(int j = 0; j < selectedOperatorIds.length; ++j){
            expr[shift+j] = ""+ OPERATORS[selectedOperatorIds[j]];
        }
    }

    private static void stepCursor(int[] cursor, int limit){
        for(int i = 0; i < cursor.length; ++i){
            int prev = cursor[i];
            cursor[i] = (cursor[i]+1)%limit;
            if (prev < cursor[i]) break;
        }
    }

    public static int exp(int base, int exp){
        int ret = 1;
        while(exp > 0){
            if ((exp & 1) > 0) ret *= base;
            base *= base;
            exp >>= 1;
        }
        return ret;
    }
}