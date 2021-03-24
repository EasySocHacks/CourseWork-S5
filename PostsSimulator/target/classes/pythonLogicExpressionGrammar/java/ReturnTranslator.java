import grammar.objects.nonterminals.NonTerminal;
import grammar.objects.attributes.Attribute;
import utils.Table;
import java.util.Map;
import java.util.List;

public class ReturnTranslator { 
   public static void run(List<Map<String, Attribute>> args) { 
       for (String attributeName : args.get(0).keySet())
   args.get(1).get(attributeName).setValue(args.get(0).get(attributeName).getValue()); 
   }
}