import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("workers.txt");
        Worker w = new Worker(
                "Jack", "Potroshitel", 100);
        Worker w2 = new Worker(
                "John", "Walker", 100);
        Worker w3 = new Worker(
                "Jn", "Wer", 100);

        w.addAnWorker(w3, file);
       w.addAnWorker(w2, file);
        w.addAnWorker(w, file);
        w.addAnWorker(w2, file);

       Worker.deleteAnWorker(w, file);
        Worker.deleteAnWorker(w2, file);
        Worker.deleteAnWorker(w3, file);
    }
}
