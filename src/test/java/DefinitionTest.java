import org.junit.*;
import static org.junit.Assert.*;

public class DefinitionTest {

  @Test
  public void Definition_instantiatesCorrectly_true() {
    Definition myDefinition = new Definition("The color of bananas.");
    assertEquals(true, myDefinition instanceof Definition);
  }

  @Test
  public void Definition_instanciatesWithDefinition_true() {
    Definition myDefinition = new Definition("A breed of dogs.");
    assertEquals("A breed of dogs.", myDefinition.getDefinition());
  }
}
