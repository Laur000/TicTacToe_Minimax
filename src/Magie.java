public class Magie {
    public int scor ;
    public int depth;
    public int poz;

    public Magie(int scor, int depth) {
        this.scor = scor;
        this.depth = depth;
    }

    public Magie() {
        this.depth = 0;
        this.scor = 0;
        this.poz = 0 ;
    }

    public int getScor() {
        return scor;
    }

    public void setScor(int scor) {
        this.scor = scor;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "Magie{" +
                "scor=" + scor +
                ", depth=" + depth +
                ", poz=" + poz +
                '}';
    }

    public int getPoz() {
        return poz;
    }
}
