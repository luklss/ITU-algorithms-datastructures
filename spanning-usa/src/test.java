import java.lang.reflect.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lucas on 5/3/2017.
 */
public class test {
    public static void main(String[] args) {
/*        String s = "\"San Diego\"--\"Wilmington, DE\" [2745]";
        Pattern p = Pattern.compile(".*\\[ *(.*) *\\].*");
        Matcher m = p.matcher(s);
        m.find();
        int weight = Integer.parseInt(m.group(1));
        System.out.println(weight);

        String[] split = s.split("--");
        String v1 = split[0];
        String v2 = split[1].substring(0, split[1].lastIndexOf(" "));
        System.out.println(v1 + " " + v2);*/

        /*String s = "ABC[548]";
        Pattern p = Pattern.compile(".*\\[ *(.*) *\\].*");
        Matcher m = p.matcher(s);
        m.find();
        String text = m.group(1);
        System.out.println(text);*/

        String p = "a a ";
        System.out.println(p.trim());
    }
}
