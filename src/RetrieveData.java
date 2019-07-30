import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

class RetrieveData {
    private String fileName;
    private ArrayList<Pair> invList = new ArrayList<>();

    RetrieveData(String fileName){
        this.fileName = fileName;
    }

    ArrayList<Pair> getData() throws IOException {
        invList.clear();
        retrieveIndex();
        return invList;
    }

    /**
     * Retrieves the inverted index from a file
     */
    private void retrieveIndex() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null){
            if(line.contains(".txt")){
                addToList(".txt",line);
            }
            if(line.contains(".pdf")){
                addToList(".pdf",line);
            }
        }
        br.close();
    }

    /**
     * Method that adds the path and word occurrences to the list
     */
    private void addToList(String suffix, String line){
        HashMap<String,Integer> hm = new HashMap<>();
        int pathIndex = line.indexOf(suffix) + 4;
        String path = line.substring(0,pathIndex);
        String[] words = line.substring(pathIndex+1).split(" ");
        for (int i = 0; i < words.length; i+=2) {
            hm.put(words[i], Integer.valueOf(words[i+1]));
        }
        invList.add(new Pair(Paths.get(path),hm));
    }
}
