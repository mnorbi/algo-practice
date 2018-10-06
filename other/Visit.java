import java.util.*;
class Visit{
	void visit(Api api){
		new Controller(api).visit();
	}
	static class Controller{
		private Set<Tuple> visited;
		private ApiController apiController;
		private int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
	 	Controller(Api api){
			this.apiController = new ApiController(api);
		}
		void visit(){
			visited = new HashSet<>();
		        Tuple root = new Tuple(0,0);
			visited.add(root);
			visit(root);
		}
		void visit(Tuple pos){
			for(int dir = 0; dir < dirs.length; ++dir){
				Tuple nPos = new Tuple(pos.vals[0]+dirs[dir][0], pos.vals[1]+dirs[dir][1]);
				if (!visited.contains(nPos)){
					visited.add(nPos);	
					if (apiController.move(dir)){
						visit(nPos);
						apiController.move(dir+2);
					}
				}
			}
		}
	}
	static class ApiController{
		int dir = 0;
		Api api;
		ApiController(Api api){
			this.api = api;
		}
		boolean move(int nextDir){
			dir = turnTo(nextDir%4);
			return api.step();
		}
		private int turnTo(int nextDir){
			if (nextDir != dir){
				if ((dir+1)%4 == nextDir){
					api.turnRight();
				} else if ((dir+3)%4 == nextDir){
					api.turnLeft();
				} else {
					api.turnRight();
					api.turnRight();
				}
			}
			return nextDir;
		}
	}
	static class Tuple{
		Integer[] vals;
		Tuple(Integer...vals){
			this.vals = vals;
		}
		@Override public boolean equals(Object o){
			if (!(o instanceof Tuple)) { return false; }
			return Arrays.equals(this.vals, ((Tuple)o).vals);
		}
		@Override public int hashCode(){ return Arrays.hashCode(vals); }
	}
	interface Api{
		boolean step();
		void turnLeft();
		void turnRight();
	}
}
