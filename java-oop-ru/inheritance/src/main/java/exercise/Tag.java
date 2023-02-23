package exercise;

import java.util.Map;

// BEGIN
class Tag{
    private String tag;
    private Map<String, String> attribute;

    public Tag(String tag, Map<String, String> attribute) {
        this.tag = tag;
        this.attribute = attribute;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder( "<" + tag + " ");
        for (Map.Entry<String, String> entry : attribute.entrySet()) {
            stringBuilder.append(entry.getKey() + "=\"" + entry.getValue() + "\" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(">");
        return stringBuilder.toString();
    }

    public String getTag() {
        return tag;
    }

    public Map<String, String> getAttribute() {
        return attribute;
    }
}

/**/
// END
