import java.util.Collections;
import java.util.Stack;

public class RegularExpressionPatternMatching {

    public static void main(String[] args) {

        System.out.println(match("aba", "ab"));
        System.out.println(match("aa", "a*"));
        System.out.println(match("ab", ".*"));
        System.out.println(match("ab", "."));
        System.out.println(match("aab", "c*a*b"));
        System.out.println(match("aab", "a*."));
        System.out.println(match("aab", "m*"));
    }

    private static boolean match(String s, String pattern) {
        System.out.print("Matching - "+s+" with "+pattern+" ");
        Stack<String> matchSequence = new Stack<>();
        Character previousChar = null;
        for (Character c : pattern.toCharArray()) {
            if('*' == c) {
                if(previousChar != null) {
                    matchSequence.pop();
                    matchSequence.add(String.valueOf(previousChar) + String.valueOf('*'));
                    previousChar = null;
                }
            } else {
                matchSequence.add(String.valueOf(c));
                previousChar = c;
            }
        }
        Collections.reverse(matchSequence);
        String currSequence = matchSequence.pop();
        boolean failIfFurtherIterations = false;
        for (Character c : s.toCharArray()) {
            if(failIfFurtherIterations) {
                return false;
            }
            char firstCharacter = currSequence.charAt(0);
            if(currSequence.contains("*")) {
                char c1 = firstCharacter;
                if(c1 == c || c1 == '.') {
                    continue;
                } else {
                    if(matchSequence.isEmpty()) {
                        failIfFurtherIterations = true;
                    } else {
                        currSequence = matchSequence.pop();
                    }
                }
            } else {
                if(c == firstCharacter || firstCharacter == '.') {
                    if(matchSequence.isEmpty()) {
                        failIfFurtherIterations = true;
                    } else {
                        currSequence = matchSequence.pop();
                        continue;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;

    }

}
