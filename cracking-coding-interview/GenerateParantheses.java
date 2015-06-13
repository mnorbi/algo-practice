import java.util.*;

/*
((((()))))
(((()())))
((()(())))
(((())()))
((()()()))
(()((())))
(()(()()))
(()()(()))
(((()))())
((()())())
((())()())
(()(())())
(()()()())
()(((())))
()((()()))
()(()(()))
()((())())
()(()()())
()()((()))
()()(()())
()()()(())
(((())))()
((()()))()
(()(()))()
((())())()
(()()())()
((()))()()
(()())()()
(())()()()
()((()))()
()(()())()
()()(())()
()(())()()
()()()()()
*/ 
//                          ((())()())
//	   onion (...) ---> (())()()
//         sfx ...()   ---> (())()
//         sfx ...()   ---> (())
//         onion (...) ---> ()

public class GenerateParantheses{
        public static void main(String[] args){
                Collection<String> pars = any(4);
                print(pars);
        }
        public static void print(Collection<String> coll){
          for(String s: coll){
            System.out.println(s);
          }
        }
        public static Collection<String> any(int len){
          if (len == 0) return Collections.emptyList();
          if (len == 1) return Arrays.asList("()");
 
          Collection<String> ret = new ArrayList<String>();
 
          Collection<String> on = onion(len);//(<not-empty>)
          ret.addAll(on);
          Collection<String> ps = pfxSimple(len);//()(<not-empty>)
          ret.addAll(ps);
          Collection<String> ss = sfxSimple(len);//(<not-empty>)()
          ret.addAll(ss);
          Collection<String> ma = midAny(len);//()<any>() tie-fighter :)
          ret.addAll(ma);
          return ret;
        }
        public static Collection<String> onion(int len){
          if (len <= 1) return Collections.emptyList();
          Collection<String> any = any(len-1);
          Collection<String> ret = new ArrayList<String>();
          for(String s : any){
            ret.add("("+s+")");
          }
          return ret;
        }
        public static Collection<String> pfxSimple(int len){
          if (len <= 1) return Collections.emptyList();
          Collection<String> on = onion(len-1);
          Collection<String> pfx = pfxSimple(len-1); 
 
          Collection<String> ret = new ArrayList<String>();
          for(String s : on){
                    ret.add("()"+s);
          }
          for(String s : pfx){
                    ret.add("()"+s);
          }
          return ret;
        }
        public static Collection<String> sfxSimple(int len){
          if (len <= 1) return Collections.emptyList();
          Collection<String> on = onion(len-1);
	  Collection<String> sfx = sfxSimple(len-1);
 
          Collection<String> ret = new ArrayList<String>();
          for(String s : on){
                    ret.add(s+"()");
          }
          for(String s : sfx){
                    ret.add(s+"()");
          }
          return ret;
        }
        public static Collection<String> midAny(int len){
          if (len <= 1) return Collections.emptyList();
          Collection<String> an = any(len-2);
          if (an.isEmpty()) return Arrays.asList("()()");
          Collection<String> ret = new ArrayList<String>();
 
          for(String s : an){
            ret.add("()"+s+"()");
          }
          return ret;
        }
}
