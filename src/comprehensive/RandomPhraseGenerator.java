package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Prints a random phrase for a number of times using rules from the given file
 *
 * @author Tanner Holladay
 * @version April 16, 2020
 */
public class RandomPhraseGenerator {

    // The non-terminal definitions
    private static HashMap<String, ArrayList<String>> definitions = new HashMap<>();
    private static Random rnd = new Random();

    /**
     * Prints a random phrase for a number of times using rules from the given file
     *
     * @param args arg[0] The path and name of the file | arg[1] The number of phrases to be printed
     */
    public static void main(String[] args) {

        File file = new File(args[0]);

        try (Scanner scanner = new Scanner(file)) {
            // The current non-terminal that is being added to definitions
            String current = "";

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!current.isEmpty()) {
                    // If the definition ends then current is blank again
                    if (line.equals("}")) {
                        current = "";
                    } else {
                        definitions.get(current).add(line);
                    }
                } else if (line.equals("{")) {
                    // If the definition starts, then set the current non-terminal to the next line
                    current = scanner.nextLine();
                    definitions.put(current, new ArrayList<>());
                }
            }

            for (int i = 0; i < Integer.parseInt(args[1]); i++) {
                // Prints the random phrase for the given amount of times
                randomLine("<start>");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }

    /**
     * Picks a random line from the given non-Terminal
     *
     * @param nonTerm The non-Terminal to get a random line from
     * @return Returns a line that replaces all non-Terminals with another random phrase
     */
    private static String randomLine(String nonTerm) {
        int randomNum = rnd.nextInt(definitions.get(nonTerm).size());
        String line = definitions.get(nonTerm).get(randomNum);

        // Searches the definition for non-terminals and replaces them with a random one from their definition
        int openIndex = line.indexOf('<');
        while (openIndex >= 0) {
            // The closing character of the non-terminal
            int closingIndex = line.indexOf('>', openIndex) + 1;

            String current = line.substring(openIndex, closingIndex);

            // The line replaced with the non-terminal definition
            line = line.substring(0, openIndex) + randomLine(current) + line.substring(closingIndex);

            // The opening character of the non-terminal
            openIndex = line.indexOf('<', openIndex + 1);
        }

        return line;
    }
}
