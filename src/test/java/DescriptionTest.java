import org.junit.*;
import static org.junit.Assert.*;

public class DescriptionTest {

  @Test
  public void Description_instantiatesCorrectly_true() {
    Description myDescription = new Description("The color of bananas.");
    assertEquals(true, myDescription instanceof Description);
  }

  @Test
  public void Description_instanciatesWithDescription_true() {
    Description myDescription = new Description("A breed of dogs.");
    assertEquals("A breed of dogs.", myDescription.getDescription());
  }
}
