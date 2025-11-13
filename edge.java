package util;

public class edge implements Comparable<edge>
{
    public int from;
    public int to;

   public double weight;

    public int compareTo(edge another)
    {
        if(this.weight < another.weight - 1e-5)
            return -1;
        else if(this.weight >another.weight + 1e-5)
            return 1;
        else
            return 0;
    }

    public edge(int from, int to, double weight)
    {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return from + "---" + to + " weight=" + weight;
    }
}
