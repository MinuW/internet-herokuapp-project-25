package internet.herokuapp.selenium.util;

public class Links {
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void clickLinks(String linkName) {
        Name = linkName;
    }
}
