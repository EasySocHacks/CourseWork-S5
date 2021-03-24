package grammar.objects;

import grammar.objects.attributes.Attribute;

import java.util.Map;

public interface GrammarObject {
    String getName();
    boolean equals(Object o);
    int hashCode();
    String toString();
    Map<String, Attribute> getAttributes();
    void setAttributes(Map<String, Attribute> attributes);
}
