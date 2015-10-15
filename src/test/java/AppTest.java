import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public ClearRule clearRule = new ClearRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Make a dictionary");
  }

  @Test
  public void wordIsDisplayedTest() {
    goTo("http://localhost:4567/words/new");
    fill("#inputWord").with("dog");
    submit(".btn");
    click("a", withText("View all words"));
    assertThat(pageSource()).contains("dog");
  }

  // @Test
  // public void wordsAreDisplayedTest() {
  //   goTo("http://localhost:4567/words/new");
  //   fill("#inputWord").with("dog");
  //   submit(".btn");
  //   click("a", withText("Add new word"));
  //   fill("#inputWord").with("cat");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("dog");
  //   assertThat(pageSource()).contains("cat");
  // }

  // @Test
  // public void wordDefinitionsIsDisplayed() {
  //   goTo("http://localhost:4567/words/new");
  //   fill("#inputWord").with("dog");
  //   submit(".btn");
  //   click("a", withText("dog"));
  //   click("a", withText("Add a Definition"));
  //   fill("#inputDefinition").with("Humanitie's friend");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("Humanitie's friend");
  // }

  // @Test
  // public void definitionsAreAddedAndDisplayed() {
  //   goTo("http://localhost:4567/words/new");
  //   fill("#inputWord").with("apple");
  //   submit(".btn");
  //   click("a", withText("apple"));
  //   click("a", withText("Add a Definition"));
  //   fill("#inputDefinition").with("A fruit that grows on a tree.");
  //   submit(".btn");
  //   click("a", withText("Add a Definition"));
  //   fill("#inputDefinition").with("My breakfast.");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("A fruit that grows on a tree.");
  //   assertThat(pageSource()).contains("My breakfast.");
  // }

}
