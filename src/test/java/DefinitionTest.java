import org.junit.*;
import static org.junit.Assert.*;

public class DefinitionTest {

  @Rule
  public ClearRule clearRule = new ClearRule();

  @Test
  public void description_instantiatesCorrectly_true() {
    Definition myDefinition = new Definition("Color of bananas.");
    assertEquals(true, myDefinition instanceof Definition);
  }

  @Test
  public void description_instantiatesWithDefinition_true() {
    Definition myDefinition = new Definition("Humanitie's best friend.");
    assertEquals("Humanitie's best friend.", myDefinition.getDefinition());
  }

  @Test
  public void all_returnsAllInstancesOfDefinition_true() {
    Definition firstDefinition = new Definition("Color of bananas.");
    Definition secondDefinition = new Definition("Color of daffodils.");
    assertTrue(Definition.all().contains(firstDefinition));
    assertTrue(Definition.all().contains(secondDefinition));
  }

  @Test
  public void newId_descriptionsInstantiateWithAnID_true() {
    Definition myDefinition = new Definition("The color of the sky.");
    assertEquals(Definition.all().size(), myDefinition.getId());
  }

  @Test
  public void find_returnsDefinitionWithSameId_secondDefinition() {
    Definition firstDefinition = new Definition("Color of bananas.");
    Definition secondDefinition = new Definition("Color of daffodils.");
    assertEquals(Definition.find(secondDefinition.getId()), secondDefinition);
  }

  @Test
  public void find_returnsNullWhenNoDefinitionFound_null() {
    assertTrue(Definition.find(9001) == null);
  }

  @Test
  public void clear_emptiesAllDefinitionsFromArrayList() {
    Definition myDefinition = new Definition("When your skin prickles from being cold.");
    Definition.clear();
    assertEquals(Definition.all().size(), 0);
  }
}
