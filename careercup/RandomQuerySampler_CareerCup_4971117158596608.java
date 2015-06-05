import java.util.*;
import java.util.concurrent.*;

/**
Given a list of queries and their counts, write a function that returns one of the queries at random such that over time it returns an equal distribution based on the counts provided in the input.
**/
class RandomQuerySampler_CareerCup_4971117158596608 {
  public static void main(String[]args){
  	Map<String, Integer> freqTable = new HashMap<>();
  	freqTable.put("query1",10);
  	freqTable.put("query2",20);
  	freqTable.put("query3",40);
  	Sampler sampler = new Sampler(freqTable);
  	
  	testSampler(sampler);
  }
  private static void testSampler(Sampler sampler){
  	Map<String, Integer> testSampleCounts = new HashMap<>();
  	for(int i = 0; i < 1000; ++i){
		int count = 0;
		String query = sampler.sample();
		if (testSampleCounts.containsKey(query)){
			count = testSampleCounts.get(query);
		}
		testSampleCounts.put(query, count+1);
  	}
  	print(testSampleCounts, 1000);
  }
  private static <S,T> void print(Map<S,T> map, int total){
  	for(Map.Entry<S,T> entry : map.entrySet()){
  		System.out.printf("%s %2.2f%%\n",entry.getKey(),100.0*(Integer)entry.getValue()/total);
  	}
  }
}
class Sampler{
  private final TreeMap<Integer, String> intervalMap;
  private final int end;


  public Sampler(Map<String, Integer> queryFreqTable){
    this.intervalMap = new TreeMap<>();
    Iterator<Map.Entry<String, Integer>> it = queryFreqTable.entrySet().iterator();
    
    int i = 0;
    while(it.hasNext()){
      Map.Entry<String, Integer> next = it.next();
      intervalMap.put(i, next.getKey());
      i += next.getValue();
    }
    end = i;
  }

  public String sample(){
    Random rnd = getRandom();
    int rndVal = rnd.nextInt(end);
    return intervalMap.floorEntry(rndVal).getValue();
  }

  private Random getRandom(){
    return ThreadLocalRandom.current();
  }
}