import java.util.Set;
class Main{
	public static void main(String[] args){
		new Solver().solve();
	}
	static class Solver{
		public void solve(){
			java.util.Scanner sc = new java.util.Scanner(System.in);
			int lines = sc.nextInt();sc.nextLine();
			java.util.Map<String, Set<String>> map = new java.util.TreeMap<String, Set<String>>();
			while(lines-- > 0){
				String line = sc.nextLine();
				String city = getCity(line);
				if (!map.containsKey(city)){
					map.put(city, new java.util.HashSet<String>());
				}
				String lover = getLover(line);
				map.get(city).add(lover);
			}
			for(java.util.Map.Entry<String, Set<String>> entry : map.entrySet()){
				System.out.println(String.format("%s %s", entry.getKey(), entry.getValue().size()));
			}
		}
		String getCity(String line){
			return line.substring(0, line.indexOf(' '));
		}
		String getLover(String line){
			return line.substring(line.indexOf(' ')+1);
		}
	}
}
