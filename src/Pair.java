import java.nio.file.Path;
import java.util.HashMap;

public class Pair {
    private Path path;
    private HashMap hm;

    Pair(Path path, HashMap hm){
        this.hm = hm;
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public HashMap getHm() {
        return hm;
    }

    public void setHm(HashMap hm) {
        this.hm = hm;
    }
}
