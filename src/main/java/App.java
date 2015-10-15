import java.util.HashMap;
import spark.ModelAndView;
import java.util.ArrayList;
import static spark.Spark.*;
import spark.template.velocity.VelocityTemplateEngine;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/words", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("words", Word.all());
    model.put("template", "templates/words.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("words/new", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/word-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/words", (request, response) ->{
    HashMap<String, Object> model = new HashMap<String, Object>();
    String inputtedWord = request.queryParams("inputtedWord");
    Word newWord = new Word(inputtedWord);
    model.put("word", newWord);
    model.put("words", Word.all());
    model.put("template", "templates/words.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/words/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    Word word = Word.find(Integer.parseInt(request.params(":id")));
    model.put("word", word);

    model.put("template", "templates/word.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("words/:id/definitions/new", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Word word = Word.find(Integer.parseInt(request.params(":id")));
    ArrayList<Definition> definitions = word.getDefinitions();
    model.put("word", word);
    model.put("definitions", definitions);
    model.put("template", "templates/word-definitions-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/definitions", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    Word word = Word.find(Integer.parseInt(request.queryParams("wordId")));
    ArrayList<Definition> definitions = word.getDefinitions();

    if (definitions == null) {
      definitions = new ArrayList<Definition>();
      request.session().attribute("definitions", definitions);
    }

    String description = request.queryParams("description");
    Definition newDefinition = new Definition(description);

    definitions.add(newDefinition);

    model.put("definitions", definitions);
    model.put("word", word);
    model.put("template", "templates/word.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

 }
}
