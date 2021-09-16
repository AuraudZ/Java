import org.graalvm.compiler.asm.aarch64.AArch64MacroAssembler.AddressGenerationPlan.WorkPlan;

public class StartWord {

    // Given a string and a second "word" string, we'll say that the word matches the string if it
    // appears at the front of the string, except its first char does not need to match exactly. On
    // a match, return the front of the string, or otherwise return the empty string. So, so with
    // the string "hippo" the word "hi" returns "hi" and "xip" returns "hip". The word will be at
    // least length 1.
    public String startWord(String str, String word) {
        if (str.length() == 0) {
            return "";
        }
        if (str.length() < word.length()) {
            return "";
        }
        if (word.length() == 0) {
            return str.substring(0, 1);
        } else if (str.substring(1, word.length()).equals(word.substring(1, word.length())))
            return str.substring(0, word.length());
        else
            return "";
    }
}


