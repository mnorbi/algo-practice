class Main{
	public static void main(String args[]){
		new Solver().start();
	}
	static class Solver{
		void start(){
			java.util.Scanner sc = new java.util.Scanner(System.in);
			boolean isBackQuote = true;
			while(sc.hasNextLine()){
				String nextLine = sc.nextLine();
				for(int i = 0; i < nextLine.length(); i++){
					char c = nextLine.charAt(i);
					if (c == '\"'){
						c = quoteStyle(isBackQuote);
						System.out.print(c);
						isBackQuote = !isBackQuote;
					}
					System.out.print(c);
				}
				System.out.println();
			}
		}
		char quoteStyle(boolean isBackQuote){
			if (isBackQuote){
				return '`';
			} else {
				return '\'';
			}
		}
	}
}
