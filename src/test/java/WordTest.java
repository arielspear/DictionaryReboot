import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class WordTest {

  @Test
  public void getWord_returnsWord_true() {
    Word testWord = new Word("dance");
    assertEquals("dance", testWord.getWord());
  }

  @Test
  public void getId_returnsWordId() {
    Word testWord = new Word("dance");
    assertTrue(Word.all().size() == testWord.getId());
  }

  @Test
  public void getDefinitions_initiallyReturnsEmptyArrayList() {
    Word testWord = new Word("dance");
    assertTrue(testWord.getDefinitions() instanceof ArrayList);
  }

  @Test
  public void all_returnsAllInstancesOfDefinition_true() {
    Word firstWord = new Word("dance");
    Word secondWord = new Word("dog");
    assertTrue(Word.all().contains(firstWord));
    assertTrue(Word.all().contains(secondWord));
  }

  @Test
  public void clear_removesAllWordInstancesFromMemory() {
    Word testWord = new Word("dance");
    Word.clear();
    assertEquals(Word.all().size(), 0);
  }

  @Test
  public void find_returnsWordWithSameId() {
    Word testWord = new Word("dance");
    assertEquals(Word.find(testWord.getId()), testWord);
  }

  @Test
  public void addDefinition_addsDefinitionToList() {
    Word testWord = new Word("dog");
    Definition testDefinition = new Definition("adult puppy");
    testWord.addDefinition(testDefinition);
    assertTrue(testWord.getDefinitions().contains(testDefinition));
  }
}
