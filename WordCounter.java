package Solution;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter '1' to input text manually, or '2' to provide a file path:");
        int choice = scanner.nextInt();

        String text = "";
        if (choice == 1) {
            scanner.nextLine(); // Consume the newline character after reading the integer input
            System.out.println("Enter the text:");
            text = scanner.nextLine();
        } else if (choice == 2) {
            scanner.nextLine(); // Consume the newline character after reading the integer input
            System.out.println("Enter the file path:");
            String filePath = scanner.nextLine();
            text = readFile(filePath);
            if (text == null) {
                System.err.println("Error reading the file. Exiting the program.");
                System.exit(1);
            }
        } else {
            System.err.println("Invalid choice. Exiting the program.");
            System.exit(1);
        }

        Map<String, Integer> wordCountMap = countWords(text);

        System.out.println("Word Count:");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Total Words: " + getTotalWordCount(wordCountMap));
        System.out.println("Total Unique Words: " + wordCountMap.size());
    }

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return content.toString();
    }

    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Split the text into words using spaces and punctuation as delimiters
        String[] words = text.split("\\s+|\\p{Punct}");

        // Count the occurrences of each word
        for (String word : words) {
            word = word.toLowerCase();
            int count = wordCountMap.getOrDefault(word, 0);
            wordCountMap.put(word, count + 1);
        }

        // Ignore common words or stop words (add more if needed)
        wordCountMap.remove("");
        wordCountMap.remove("the");
        wordCountMap.remove("and");
        wordCountMap.remove("a");
        wordCountMap.remove("is");
        wordCountMap.remove("for");
        wordCountMap.remove("to");
        wordCountMap.remove("of");

        return wordCountMap;
    }

    public static int getTotalWordCount(Map<String, Integer> wordCountMap) {
        int totalWords = 0;
        for (int count : wordCountMap.values()) {
            totalWords += count;
        }
        return totalWords;
    }
}
