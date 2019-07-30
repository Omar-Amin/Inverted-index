import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WordLookup {
    private ArrayList<Pair> invList = new ArrayList<>();

    WordLookup(){
    }

    /**
     * With a given path, walk through all directories and find all .txt and .pdf files
     * readFile function is applied for each .txt and .pdf file
     */
    void findFiles(Path path) throws IOException {
        Files.walk(path).filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".txt") || p.toString().endsWith(".pdf")).forEach(this::readFiles);
    }

    /**
     * Reads the content in a file, and counts the words.
     */
    private void readFiles(Path p){
        HashMap<String,Integer> hm = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(p);
            for (String s:lines) {
                for (String str: s.split("[ .:,;\n\r?!]")) { // remove . : ; , and spaces
                    if(!str.equals("")){
                        hm.merge(str,1, Integer::sum);
                    }
                }
            }
            invList.add(new Pair(p,hm));
            storeIndex();
        } catch (IOException e) {
            System.out.println("Error");;
        }
    }

    /**
     * Remove later, used for debugging only
     */
    void debug(){
        for (Pair p:invList) {
            System.out.println(p.getPath());
            for (Map.Entry<String,Integer> elm:p.getHm().entrySet()) {
                System.out.println("word: " + elm.getKey() + " "+ elm.getValue());
            }
        }
    }

    /**
    * Stores the inverted index in a file
    */
    private void storeIndex() throws IOException {
        FileWriter fileWriter = new FileWriter("InvertedIndexData");
        for (Pair p:invList) {
            fileWriter.write(p.getPath().toString() + " ");
            for (Map.Entry<String,Integer> elm:p.getHm().entrySet()) {
                fileWriter.write(elm.getKey() + " " + elm.getValue() + " ");
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}
