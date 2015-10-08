import java.util.HashMap;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("definitions", request.session().attribute("definitions"));

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/definitions", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      ArrayList<Definition> definitions = request.session().attribute("definitions");

      if (definitions == null) {
        definitions = new ArrayList<Definition>();
        request.session().attribute("definitions", definitions);
      }

      String definition = request.queryParams("definition");
      Definition newDefinition = new Definition(definition);

      definitions.add(newDefinition);
      request.session().attribute("definitions", definitions);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
