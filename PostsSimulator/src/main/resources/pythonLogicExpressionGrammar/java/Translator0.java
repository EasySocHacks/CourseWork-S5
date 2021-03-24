import grammar.objects.nonterminals.NonTerminal;
import grammar.objects.attributes.Attribute;
import utils.Table;
import java.util.Map;
import java.util.List;

public class Translator0 { 
   public static void run(List<Map<String, Attribute>> args) { 
       args.get(0).get("table").setValue(args.get(1).get("table").getValue()); 
   }
}