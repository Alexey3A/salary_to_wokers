package salary;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class WordExample {
    public static void main(String[] args) throws URISyntaxException, IOException {

        XWPFDocument document = new XWPFDocument();

        XWPFParagraph paragraph1 = document.createParagraph();
        paragraph1.setAlignment(ParagraphAlignment.CENTER);

        XWPFParagraph paragraph2 = document.createParagraph();

        XWPFParagraph paragraph3 = document.createParagraph();
        paragraph3.setAlignment(ParagraphAlignment.LEFT);

        XWPFParagraph paragraph4 = document.createParagraph();

        XWPFRun run1 = paragraph1.createRun();
        run1.setFontSize(14);
        run1.setFontFamily("Times New Roman");
        run1.setText("О ежемесячной выплате премии");
        run1.setBold(true);
        run1.addBreak();

        XWPFRun run2 = paragraph2.createRun();
        run2.setFontSize(14);
        run2.setFontFamily("Times New Roman");
        run2.addTab();
        run2.setText("В связи с исполнением планов работ, " +
                        "а также в честь наступающего нового года и рождества");
        run2.addBreak();

        XWPFRun run3 = paragraph3.createRun();
        run3.setFontSize(14);
        run3.setFontFamily("Times New Roman");
        run3.setText("ПРИКАЗЫВАЮ:");
        run3.setBold(true);
        run3.addBreak();

        long time = System.currentTimeMillis();
        String timeStr = Long.toString(time);
        String fileName = timeStr + ".docx";
        File dir = new File("C://doci");
        File file = new File(dir, fileName);
        document.write(new FileOutputStream(file));

        document.close();
    }
}
