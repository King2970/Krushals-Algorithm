package util;
public class Vertex {
    public String name;

    public Vertex(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        Vertex another = (Vertex) o;
        return this.name.equals(another.name);
    }

    @Override
    public String toString() {
        return name;
    }
}