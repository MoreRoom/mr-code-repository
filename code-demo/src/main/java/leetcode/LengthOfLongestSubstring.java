package leetcode;

import java.util.*;

/**
 * @ClassName LengthOfLongestSubstring
 * @Description TODO
 * @Author MoreRoom
 * @Since 2018/10/24
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring l = new LengthOfLongestSubstring();
        System.out.println(l.lengthOfLongestSubstring("呵呵呵呵呵"));
//        System.out.println(l.best("呵呵呵呵呵"));
    }

    public int best(String s) {
        int strLength = s.length();
        int result = 0;
        int[] index = new int[128];
        for (int i = 0, j = 0; j < strLength; j++) {
            i = Math.max(i, index[s.charAt(j)]);
            result = Math.max(result, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return result;
    }

    public int lengthOfLongestSubstring(String s) {
        int moveIndex = 0;
        int maxLength = 0;
        if (s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        final int sLength = s.length();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < sLength; i++) {
            char c;
            if (map.containsKey((c = charArray[i]))) {
                int currentIndex = map.get(c);
                if (moveIndex <= currentIndex) {
                    int length = i - moveIndex;
                    maxLength = maxLength < length ? length : maxLength;
                    moveIndex = currentIndex + 1;
                }
            }
            map.put(charArray[i], i);
        }
        // last
        int lastLength = sLength - moveIndex;
        maxLength = maxLength < lastLength ? lastLength : maxLength;
        return maxLength;
    }

    // 11%
    public int lengthOfLongestSubstring_11(String s) {
        String maxString = "";
        int startIndex = 0;
        int endIndex = 0;
        int moveIndex = 0;
        final int sLength = s.length();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < sLength; i++) {
            endIndex = i;
            if (map.containsKey(charArray[i])) {
                if (maxString.length() < endIndex - startIndex) {
                    maxString = s.substring(startIndex, endIndex);
                }
                moveIndex = map.get(charArray[i]) + 1;
                map.clear();
                startIndex = moveIndex;
                while (moveIndex != endIndex) {
                    map.put(charArray[moveIndex], moveIndex);
                    moveIndex++;
                }
            }
            map.put(charArray[endIndex], endIndex);
        }
        if (maxString.length() < map.size()) {
            return map.size();
        }
        return maxString.length();
    }


}
