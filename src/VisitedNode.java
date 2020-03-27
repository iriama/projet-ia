public class VisitedNode {
    private int hash;
    private int level;

    public VisitedNode(int hash) {
        this.hash = hash;
    }

    public VisitedNode(int hash, int level) {
        this.hash = hash;
        this.level = level;
    }

    public int getHash() {
        return hash;
    }

    public int getLevel() {
        return level;
    }
}