import java.util.ArrayList;

public class Definition {
  private static ArrayList<Definition> instances = new ArrayList<Definition>();

    private String mDefinition;
    private int mId;

  public Definition(String definition) {
    mDefinition = definition;
    instances.add(this);
    mId = instances.size();
  }

  public String getDefinition() {
    String wordDefinition = mDefinition;
    return wordDefinition;
  }

  public int getId() {
    return mId;
  }

  public static ArrayList<Definition> all() {
    return instances;
  }

  public static Definition find(int id) {
    try {
      return instances.get(id - 1);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  public static void clear() {
    instances.clear();
  }
}
