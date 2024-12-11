import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\n\nEnter relative path to the file: <dir>/<filename>.txt \n");
        System.out.println("--->    ");
        String relFilePath = scanner.nextLine();

        FileReader fr = null;
        try {
            fr = new FileReader(relFilePath);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(1);
        }
        Lexer lexer = new Lexer(fr);
        parser parser = new parser(lexer);
        try {
            parser.parse();
        } catch (Exception e) {
            System.err.println("Parsing error: " + e.getMessage());
            System.exit(1);
        }
    }
}
