import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
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

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Definitions");
  }

  @Test
  public void definitionIsCreated() {
      goTo("http://localhost:4567/");
      fill("#definition").with("The color of bananas.");
      submit(".btn");
      assertThat(pageSource()).contains("The definition has been saved.");
  }

  @Test
  public void definitionIsDisplayed() {
    goTo("http://localhost:4567/");
    fill("#definition").with("The color of strawberries.");
    submit(".btn");
    click("a", withText("Go Back"));
    assertThat(pageSource()).contains("The color of strawberries.");
  }

  @Test
  public  void multipleDefinitionsAreDisplayed() {
    goTo("http://localhost:4567/");
    fill("#definition").with("Frozen water falling from the sky.");
    submit(".btn");
    click("a", withText("Go Back"));
    fill("#definition").with("A cold type of weather.");
    submit(".btn");
    click("a", withText("Go Back"));
    assertThat(pageSource()).contains("Frozen water falling from the sky.");
    assertThat(pageSource()).contains("A cold type of weather.");

  }
}
