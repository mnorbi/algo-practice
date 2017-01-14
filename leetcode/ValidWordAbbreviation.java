import java.util.Arrays;

class ValidWordAbbreviation {
  public static void main(String[] args) {
    ValidWordAbbreviation solver = new ValidWordAbbreviation();
    System.out.println(
      Arrays.toString(
        new boolean[]{
          solver.isValidWordAbbreviation("word", "word"),
          solver.isValidWordAbbreviation("word", "1ord"),
          solver.isValidWordAbbreviation("word", "w1rd"),
          solver.isValidWordAbbreviation("word", "wo1d"),
          solver.isValidWordAbbreviation("word", "wor1"),
          solver.isValidWordAbbreviation("word", "2rd"),
          solver.isValidWordAbbreviation("word", "w2d"),
          solver.isValidWordAbbreviation("word", "wo2"),
          solver.isValidWordAbbreviation("word", "1o1d"),
          solver.isValidWordAbbreviation("word", "1or1"),
          solver.isValidWordAbbreviation("word", "w1r1"),
          solver.isValidWordAbbreviation("word", "1o2"),
          solver.isValidWordAbbreviation("word", "2r1"),
          solver.isValidWordAbbreviation("word", "3d"),
          solver.isValidWordAbbreviation("word", "w3"),
          solver.isValidWordAbbreviation("word", "4"),
          solver.isValidWordAbbreviation("word", "w1r0d"),
          solver.isValidWordAbbreviation("internationalization", "i12iz4n"),
          solver.isValidWordAbbreviation("apple", "a2e")
        }
      )
    );
  }

  boolean isValidWordAbbreviation(String word, String abbr) {
    if (word == abbr) {
      return true;
    }
    if (word == null || abbr == null) {
      return false;
    }

    int wInd = 0, aInd = 0;
    while (wInd < word.length() && aInd < abbr.length()) {
      char wc = word.charAt(wInd);
      char ac = abbr.charAt(aInd);

      if (wc == ac) {
        ++wInd;
        ++aInd;
        continue;
      } else if (ac > '0' && Character.isDigit(ac)) {
        int len = 0;
        int nAInd = aInd;
        while (nAInd < abbr.length() && Character.isDigit(abbr.charAt(nAInd))) {
          len *= 10;
          len += (abbr.charAt(nAInd)-'0');
          ++nAInd;
        }
        wInd += len;
        aInd = nAInd;
        continue;
      }

      return false;
    }

    return wInd == word.length() && aInd == abbr.length();
  }

}
