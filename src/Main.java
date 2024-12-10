import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        if(args.length < 1){
            System.err.println("Usage: java Main <file>");
            System.exit(1);
        }
        String fileName = args[0];
        FileReader fr = null;
        try {
            fr = new FileReader("programs/" + fileName);
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