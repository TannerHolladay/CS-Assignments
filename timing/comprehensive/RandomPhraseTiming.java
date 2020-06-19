package comprehensive;

import codeTimer.CodeTimer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@CodeTimer.Settings(title = "",
        titleY = "",
        titleX = "",
        loops = 100
)
public class RandomPhraseTiming extends CodeTimer {

    private static String[] args;

    public static void main(String[] args) {
        new RandomPhraseTiming();
    }

    @Override
    public void before(int steps) {
        args = new String[2];
        args[0] = "timing.g";
        args[1] = "1";
        generateFile(steps, 5);
    }

    /**
     * Code that is being timed
     *
     * @param steps Current steps
     */
    @Override
    public void timedCode(int steps) {
        RandomPhraseGenerator.main(args);
    }


    private static void generateFile(int nonTerms, int rulesPerNonTerm) {
        try {
            FileWriter newFile = new FileWriter("timing.g");
            StringBuilder builder = new StringBuilder();
            builder.append("{\n<start>");
            for (int rules = 0; rules < rulesPerNonTerm; rules++) {
                builder.append("\n");
                builder.append("a");
                for (int i = 0; i < nonTerms; i++) {
                    builder.append("<" + i + ">");
                }
            }
            builder.append("\n}\n");

            for (int i = 0; i < nonTerms; i++) {
                builder.append("\n{");
                builder.append("\n<" + i + ">");
                builder.append("\n");
                builder.append("\n}");
            }
            newFile.write(builder.toString());
            newFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
