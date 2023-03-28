package associations.records;

public record Cat(String name, String color) {
    public Cat(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Cat(String name) {
        this(name, "");
    }
}
