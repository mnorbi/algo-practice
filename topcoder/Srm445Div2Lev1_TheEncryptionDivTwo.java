/**

Problem Statement
    
John is obsessed with security. He is writing a letter to his friend Brus and he wants nobody else to be able to read it. He uses a simple substitution cipher to encode his message. Each letter in the message is replaced with its corresponding letter in a substitution alphabet. A substitution alphabet is a permutation of all the letters in the original alphabet. In this problem, the alphabet will consist of only lowercase letters ('a'-'z').
For example, if John's message is "hello" and his cipher maps 'h' to 'q', 'e' to 'w', 'l' to 'e' and 'o' to 'r', the encoded message will be "qweer". If the cipher maps 'h' to 'a', 'e' to 'b', 'l' to 'c' and 'o' to 'd', then the encoded message will be "abccd".
Given the original message, determine the cipher that will produce the encoded string that comes earliest alphabetically. Return this encoded string. In the example above, the second cipher produces the alphabetically earliest encoded string ("abccd").
Definition
    
Class:
TheEncryptionDivTwo
Method:
encrypt
Parameters:
String
Returns:
String
Method signature:
String encrypt(String message)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
If A and B are two Strings of the same length, then A comes earlier alphabetically than B if it contains a smaller character at the first position where the Strings differ.
Constraints
-
message will contain between 1 and 50 characters, inclusive.
-
message will contain only lowercase letters ('a'-'z').
Examples
0)

    
"hello"
Returns: "abccd"
The example from the statement.
1)

    
"abcd"
Returns: "abcd"
Here the message is encoded to itself.
2)

    
"topcoder"
Returns: "abcdbefg"

3)

    
"encryption"
Returns: "abcdefghib"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class TheEncryptionDivTwo{
	public String encrypt(String message){
		char[] cipher = new char['z'+1];
		char[] ret = new char[message.length()];
		char nextCipherChar = 'a';
		for(int i = 0; i < ret.length; i++){
			char currentChar = message.charAt(i);
			char encodedChar = cipher[currentChar];
			if (encodedChar == 0){
				cipher[currentChar] = nextCipherChar++;
				encodedChar = cipher[currentChar];
			}
			ret[i] = encodedChar;
		}
		return new String(ret);
	}
}
