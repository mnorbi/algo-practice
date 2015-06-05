import java.util.*;

public class Srm218Div2Lev3{
	public static void main(String[] args){
		PermissionTree pt = new PermissionTree();

		print(pt.findHome(
			new String[] {"0 Admin", "0 Joe,Phil", "0 Joe"},
			new String[] {"Admin", "Joe", "Phil"}
		));

                print(pt.findHome(
			new String[] {"0 Admin"},
			new String[] {"Peter", "Paul", "Mary"}
		));

		print(pt.findHome(
			new String[] {"0 Admin", "2 John", "0 Peter,John", "0 Tim", "1 John"},
			new String[] {"John"}
		));

		print(pt.findHome(
			new String[] {"0 Admin", "0 Jeff", "1 Mark,Tim", "1 Tim,Jeff", "0 Dan", "4 Ed", "4 Tom", "4 Kyle,Ed", "0 Ben", "8 Rich", "8 Sam", "8 Tim"},
			new String[] {"Jeff", "Ed", "Tim", "Steve"}	
		));

		print(pt.findHome(
			new String[] {"0 Admin", "0 Bob,Joe,Bob", "0 Joe"},
			new String[] {"Joe", "Bob"}
		));
	}
	private static void print(int[] arr){
		for(int i : arr){
			System.out.print(i+" ");
		}
		System.out.println("\n-----------");
	}
}

class PermissionTree{
	public int[] findHome(String[] folders, String[] users){
		State state = new State(folders, users);
		return state.findHome();
	}
	private static class State{
		Map<String, Set<Integer>> foldersOfUser = new LinkedHashMap<>();
		Map<Integer, Set<Integer>> children = new HashMap<>();
		Map<Integer, Set<String>> usersOfFolder = new HashMap<>();
		int[] parentArr;

		State(String[] folders, String[] users){
			initFoldersOfUserMap(users);
			initChildrenMap(folders.length);
			initUsersOfFolder(folders.length);
			parentArr = new int[folders.length];

			parseFolderStructure(folders);
		}

		private int[] findHome(){
			int[] ret = new int[foldersOfUser.size()];
			int i = 0;
			for(String user : foldersOfUser.keySet()){
				ret[i++] = findHome(user);
			}
			return ret;
		}

		private boolean hasPermission(String user, Integer folderId){
			return foldersOfUser.get(user).contains(folderId);
		}
		
		private int findHome(String user){
			Set<Integer> folders = foldersOfUser.get(user);
			if (folders.size() == 0) return -1;
			Iterator<Integer> it = folders.iterator();
			Deque<Integer> path = pathToRoot(it.next());
			while(it.hasNext() && path.size() > 1){
				path = longestCommonPath(path, pathToRoot(it.next()));
			}
			return path.getLast();
		}

		private Deque<Integer> longestCommonPath(Deque<Integer> path1, Deque<Integer> path2){
			Deque<Integer> ret = new LinkedList<Integer>();
			for(Iterator<Integer> it1 = path1.iterator(), it2 = path2.iterator(); it1.hasNext() && it2.hasNext();){
				Integer i1 = it1.next();
				Integer i2 = it2.next();
				if (i1 != i2) break;
				ret.addLast(i1);
			}
			return ret;
		}

		private Deque<Integer> pathToRoot(Integer folderId){
			Deque<Integer> ret = new LinkedList<>();
			ret.addFirst(folderId);
			while(folderId != parentArr[folderId]){
				folderId = parentArr[folderId];
				ret.addFirst(folderId);
			}
			return ret;
		}	

		private void print(Set<Integer> set){
			for(Integer i : set){
				System.out.print(i +" ");
			}
			System.out.println();
		}

		private void parseFolderStructure(String[] folders){
			int i = 0;
			for(String folderDescriptor : folders){
				String[] tokens = folderDescriptor.split(" ");
				Integer parent = parseParent(tokens[0]);
				String[] users = parseUsers(tokens[1]);
				addChildren(parent, i);
				connectFolderWithUsers(i, users);
				++i;
			}
		}

		private void connectFolderWithUsers(Integer i, String[] users){
			for(String user : users){
				if (foldersOfUser.containsKey(user)){
					foldersOfUser.get(user).add(i);
				}
				usersOfFolder.get(i).add(user);
			}
		}

		private void addChildren(Integer parentId, Integer childId){
			if (childId == parentId) return;
			children.get(parentId).add(childId);
			parentArr[childId] = parentId;
		}

		private String[] parseUsers(String token){
			return token.split(",");
		}

		private Integer parseParent(String token){
			return Integer.parseInt(token);
		}

		private void initFoldersOfUserMap(String[] users){
			for(String user : users){
				foldersOfUser.put(user, new HashSet<Integer>());
			}
		}

		private void initChildrenMap(int n){
			for(int i = 0; i < n; i++){
				children.put(i, new HashSet<Integer>());
			}
		}

		private void initUsersOfFolder(int n){
			for(int i = 0; i < n; i++){
				usersOfFolder.put(i, new HashSet<String>());
			}
		}
	}
}
