import grammar.objects.nonterminals.NonTerminal;
import grammar.objects.attributes.Attribute;
import utils.Table;
import java.util.Map;
import java.util.List;

public class Translator44 { 
   public static void run(List<Map<String, Attribute>> args) { 
       args.get(0).get("ans").setValue(
            (boolean)args.get(1).get("ans").getValue() ^
            (boolean)args.get(2).get("ans").getValue()); 
   }
}