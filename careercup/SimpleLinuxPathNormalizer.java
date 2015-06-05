import java.util.*;
class SimpleLinuxPathNormalizer{
  public static void main(String[]args){
    String path = normalizePath("../..////..../../abc/def/ghij//../mno");
    System.out.println(path);
  }
  private static String normalizePath(String path){
    if (path == null) return null;
    String[] arr = path.split("/");
    Deque<String> ans = new LinkedList<String>();
    for(int i = 0; i < arr.length; ++i){
      if (arr[i].startsWith("..")){
        if (ans.isEmpty() || ans.getLast().startsWith("..")){
          ans.addLast(arr[i]);
        } else {
          ans.removeLast();
        }
      } else if (!arr[i].equals("")){
        ans.addLast(arr[i]);
      }
    }
    return join(ans);
  }
  private static String join(Deque<String> slist){
    if (slist == null || slist.isEmpty()) return "";
    StringBuilder sb = new StringBuilder();
    Iterator<String> it = slist.iterator();
    sb.append(it.next());
    while(it.hasNext()){
      sb.append("/");
      sb.append(it.next());
    }
    return sb.toString();
  }
}
