import java.util.*;
/**
Given an bunch of airline tickets with [from, to], for example [MUC, LHR], [CDG, MUC], [SFO, SJC], [LHR, SFO], please reconstruct the itinerary in order, 
for example: [ CDG, MUC, LHR, SFO, SJC ]. 
//tickets can be represented as nodes
**/
class AirplaneItinerary_CareerCup_5079989210841088{
	public static void main(String[]args){
		print(itinerary(
			new String[][]{
				{"MUC", "LHR"}, {"CDG", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}
			}));
	}
	
	static void print(String[] path){
		for(String s : path){
			System.out.print(s);
			System.out.print(" ");
		}
		System.out.println();
	}
	
	static String[] itinerary(String[][] tickets){
	  Map<String, String> map = new HashMap<>();
	  for(int i = 0; i < tickets.length; ++i){
	    map.put(tickets[i][0], tickets[i][1]);
	  }
	  String[] ret = new String[tickets.length+1];
	  int i = 0;
	  ret[i] = findStart(map);
	  while(map.containsKey(ret[i])){
	    String next = map.get(ret[i]);
	    ret[++i] = next;
	  }
	  return ret;
	}
	
	static String findStart(Map<String, String> map){
	  Set<String> s1 = new HashSet<>(map.keySet());
	  s1.removeAll(map.values());
	  return s1.iterator().next();
	}
}