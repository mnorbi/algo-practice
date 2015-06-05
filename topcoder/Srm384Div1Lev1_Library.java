import java.util.*;
/**

Problem Statement
    
Mr. Agent has entered a government library where secret documents are stored. Documents are stored in different rooms, and each document has a list of user groups that can access it. In order to access a document, a person must have access to the room in which the document is stored and also be a member of at least one user group with access to the document. Your task is to determine the number of documents Mr. Agent can access.  You are given a String[] userGroups, each element of which is the name of a user group in which Mr. Agent is a member. You are also given a String[] roomRights, each element of which is the name of a room to which Mr. Agent has access. The documents are given in the String[] records. Each element of records is formatted "document_name room user_group" (quotes for clarity), where document_name is the name of a document, room is the name of the room where the document is located, and user_group is the name of a user group that has access to the document. If multiple user groups have access to a document, there will be an entry in records for each group. All documents have distinct names, all rooms have distinct names, and all user groups have distinct names. Return the number of documents Mr. Agent can access.
Definition
    
Class:
Library
Method:
documentAccess
Parameters:
String[], String[], String[]
Returns:
int
Method signature:
int documentAccess(String[] records, String[] userGroups, String[] roomRights)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
records, userGroups and roomRights will each contain between 0 and 50 elements, inclusive.
-
Each element of userGroups and roomRights will contain between 1 and 15 lowercase letters ('a'-'z').
-
Each element of records will be formatted as "document_name room user_group" (quotes for clarity), where document_name, room and user_group each contain between 1 and 15 lowercase letters ('a'-'z').
-
All elements of records with the same document_name will contain the same room.
-
All elements of records will be distinct.
-
All elements of userGroups will be distinct.
-
All elements of roomRights will be distinct.
Examples
0)

    
{"diary computers editor","fairytale gardening editor","comix cars author","comix cars librarian"}
{"employee","editor","author"}
{"history","cars","computers"}
Returns: 2
Mr. Agent only has access to the documents "diary" and "comix".
1)

    
{"diary computers editor","fairytale gardening editor","comix cars author","comix cars librarian"}
{"employee","editor","author","librarian"}
{"history","cars","computers"}
Returns: 2
Three elements of records satisfy the conditions, but two of them are for the same document (comix).
2)

    
{"diary computers editor","fairytale gardening editor","comix cars author","comix cars librarian"}
{"employee","editor","author","librarian"}
{}
Returns: 0
Mr. Agent can not access any rooms.
3)

    
{"a b c","a b d","b b c","b b d","e c d","e c b","e c c","t d e"}
{"c","d","x"}
{"a","b","c"}
Returns: 3

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class Library{
	public int documentAccess(String[] records, String[] userGroups, String[] roomRights){
		Set<String> roomRightSet = new HashSet<String>(Arrays.asList(roomRights));
		Set<String> userGroupSet = new HashSet<String>(Arrays.asList(userGroups));
		int ret = 0;
		for(String record : records){
			String[] split = record.split(" ");
			if (roomRightSet.contains(split[1]) && userGroupSet.contains(split[2])) ret ++;
		}
		return ret;
	}
}
