import java.util.HashMap;

public class Scope {
    private HashMap<String, Integer> symbols = new HashMap<>();

    public int getSymbolValue(String symbol) {
        return symbols.get(symbol);
    }

    public void setSymbolValue(String symbol, int value) {
        symbols.put(symbol, value);
    }

    public boolean containsSymbol(String symbol) {
        return symbols.containsKey(symbol);
    }
}
