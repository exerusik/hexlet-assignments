package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag{
    String tagNext;
    List<Tag> tags;

    public PairedTag(String tag, Map<String, String> attribute, String tagNext, List<Tag> tags) {
        super(tag, attribute);
        this.tagNext = tagNext;
        this.tags = tags;
    }

    /*<тег атрибут1="значение1" атрибут2="значение2">
<тег атрибут1="значение1" атрибут2="значение2">...</тег>*/

    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString() + tagNext);

        for (Tag tag : tags) {
            builder.append("<" + tag.getTag() +" ");
            for (Map.Entry<String, String> entry : tag.getAttribute().entrySet()) {
                builder.append(entry.getKey() + "=\"" + entry.getValue() + "\"");
            }
            builder.append(">");
        }
        //builder.deleteCharAt(builder.length() - 1);
        builder.append("</" + super.getTag() + ">");
        return builder.toString();
    }
}

// END
