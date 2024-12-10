import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class Interpreter {
    private  Stack<Scope> scopes = new Stack<>();
    private Cache<String, Integer> cache = new Cache<>(10);

    private static Interpreter instance = null;

    private Interpreter(){
        scopes.push(new Scope());
    }

    public static Interpreter getInstance(){
        if(instance == null){
            instance = new Interpreter();
        }
        return instance;
    }

    public void processPrint(String ident){
        if(cache.containsKey(ident)){
            System.out.println(cache.get(ident));
            return;
        }
        for (int i = scopes.size() - 1; i >= 0; i--) {
            Scope currentScope = scopes.get(i);
            if (currentScope.containsSymbol(ident)) {
                int value = currentScope.getSymbolValue(ident);
                cache.put(ident, value);
                System.out.println(value);
                return;
            }
        }
        System.err.println("null");
    }
    public void processScope(){
        scopes.push(new Scope());
    }
    public void processClosingScope(){
        Scope closedScope = scopes.pop();
        Iterator<Map.Entry<String, Integer>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (closedScope.containsSymbol(entry.getKey())) {
                iterator.remove();
            }
        }
    }
    public void processAssignmentIdent(String ident, String valOf){
        if(cache.containsKey(valOf)){
            int value = cache.get(valOf);
            scopes.peek().setSymbolValue(ident, value);
            cache.put(ident, value);
            return;
        }
        for (int i = scopes.size() - 1; i >= 0; i--) {
            Scope currentScope = scopes.get(i);
            if (currentScope.containsSymbol(valOf)) {
                int value = currentScope.getSymbolValue(valOf);
                currentScope.setSymbolValue(ident, value);
                cache.put(ident, value);
                return;
            }
        }
    }
    public void processAssignmentLiteral(String ident, Integer val){
        scopes.peek().setSymbolValue(ident, val);
        cache.put(ident, val);
    }
}
