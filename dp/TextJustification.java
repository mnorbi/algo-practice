import java.util.*;
class TextJustification{
    public static void main(String[]args){
        System.out.println(
                justify("1234 123 12 1 1234567 123 12345 12 123 1234 123456", 11));
    }

    public static String justify(String input, int w){
        if (input == null || input.length() == 0) return input;
        String[] arr = input.split(" ");
        int[] penalty = new int[arr.length+1];
        int[] prev = new int[arr.length];
        penalty[0] = 0;//initially forgot to set up the default 0 penalty case
        penalty[1] = penalty(w,arr,0);
        for(int i = 1; i < arr.length; ++i){
            penalty[i+1] = penalty[i]+penalty(w,arr,i);//did not add previous penalty
            prev[i] = i;//did not and later incorrectly initialized, by default each word goes into a new row
            int lineWidth = arr[i].length();
            for(int j = i-1; j >= 0; --j){
                lineWidth += arr[j].length()+1;
                if (lineWidth > w) break;
                int newPenalty = penalty[j] + (w-lineWidth);
                if (newPenalty < penalty[i+1]){
                    penalty[i+1] = newPenalty;
                    prev[i] = j;
                }
            }
        }
        LinkedList<String> ret = new LinkedList<>();
        for(int i = arr.length-1, j = i; i >= 0; i = j){
            for(; j >= prev[i]; --j){
                ret.addFirst(arr[j]);
            }
            ret.addFirst("\n");
        }
        ret.removeFirst();
        return toString(ret);
    }

    private static String toString(List<String> list){
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        String prev = "\n";
        while(it.hasNext()){
            String next = it.next();
            if (!"\n".equals(next) && !"\n".equals(prev)) {
                sb.append(" ");
            }
            sb.append(next);
            prev = next;
        }
        return sb.toString();
    }

    private static int penalty(int w, String[] arr, int i){
        return Math.abs(w-arr[i].length());
    }
}
