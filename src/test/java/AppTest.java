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
}
