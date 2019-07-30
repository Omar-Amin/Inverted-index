import java.nio.file.Path;
import java.util.HashMap;

public class Pair {
    private Path path;
    private HashMap<String,Integer> hm;

    Pair(Path path, HashMap<String,Integer> hm){
        this.hm = hm;
        this.path = path;
    }

    Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    HashMap<String,Integer> getHm() {
        return hm;
    }

    public void setHm(HashMap hm) {
        this.hm = hm;
    }
}
