import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WordLookup {
    private String path;
    private ArrayList<Pair> invList = new ArrayList<>();

    WordLookup(String path){
        this.path = path;
    }

    void findFiles() throws IOException {
        Files.walk(Paths.get(path)).filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".txt") || p.toString().endsWith(".pdf")).forEach(this::readFiles);
    }

    private void readFiles(Path p){
        HashMap<String,Integer> hm = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(p);
            for (String s:lines) {
                for (String str: s.replace("\n","").replace("\r","") //replace newlines
                        .split("[ .:,;\n]")) { // remove . : ; , and spaces
                    if(!str.equals("")){
                        hm.merge(str,1, Integer::sum);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        invList.add(new Pair(p,hm));
        for (Map.Entry<String,Integer> elm:hm.entrySet()) {
            System.out.println("word: " + elm.getKey());
            System.out.println("occurrences: " + elm.getValue());
        }
    }

}
