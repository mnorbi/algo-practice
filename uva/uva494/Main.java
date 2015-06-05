class Main{
	public static void main(String[] args){
		new Solver().start();
	}
	static class Solver{
		void start(){
			java.util.Scanner sc = new java.util.Scanner(System.in);
                        java.util.regex.Pattern wordDelimiterPat = java.util.regex.Pattern.compile("[^a-zA-Z]");
			while(sc.hasNextLine()){
				System.out.println(
					countNonEmpty(
						wordDelimiterPat.split(sc.nextLine())
					)
				);
			}
		}
		int countNonEmpty(String[] arr){
			if (arr == null){
				return 0;
			}
			int count = 0;
			for(String s : arr){
				if (s != null && s.length() != 0){
					count++;
				}
			}
			return count;
		}
	}
}
