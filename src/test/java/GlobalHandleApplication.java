import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalHandleApplication {

	public static void main(String[] args) {

//		final String regex = "[^\\[]+(?=])";
//		final String regex = "[^\\[\\s]+(?=])";
		final String regex = "(?<=\\[(?:class )?)[^\\]]+";
		final String string = "request [class java.lang.String] parameter [name] must not be empty or null \n";

		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(string);

		while (matcher.find()) {
			System.out.println("Full match: " + matcher.group(0));
			for (int i = 1; i <= matcher.groupCount(); i++) {
				System.out.println("Group " + i + ": " + matcher.group(i));
			}
		}
	}
}