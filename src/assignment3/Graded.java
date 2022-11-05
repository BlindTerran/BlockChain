package assignment3;
import java.lang.annotation.*;

/* Please DO NOT modify this class, it is required for
 * the UnitTests
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Graded {
	public String description();
	public int marks();
}
