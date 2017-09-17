class Solution{

  public int characterReplacement(String s, int k) {
    if (k >= s.length()) { return s.length(); }

    int[] windowFrequencies = new int[128];

    int maxWindowFrequency = 0;
    for(int i = 0; i < k; ++i){
      maxWindowFrequency = Math.max(maxWindowFrequency, ++windowFrequencies[s.charAt(i)]);
    }

    //trying to extend initial k long window
    int windowLength = k;
    for(int i = k; i < s.length() && maxWindowFrequency+k > windowLength; ++i){
      ++windowLength;
      maxWindowFrequency = Math.max(maxWindowFrequency, ++windowFrequencies[s.charAt(i)]);
    }

    //k modifications consumed by window, remaining option to look for more repetitions or slide window
    for(int i = windowLength; i < s.length(); ++i){
      int currentFrequency = ++windowFrequencies[s.charAt(i)];
      if (currentFrequency > maxWindowFrequency){
        ++windowLength;
        maxWindowFrequency = currentFrequency;
      } else {
        --windowFrequencies[s.charAt(i-windowLength)];
      }
    }

    return windowLength;
  }

}
