package exercise;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

// BEGIN
class Validator {
    public static List<String> validate(Address address) {
        List<Field> noValid = new ArrayList<>(List.of(address.getClass().getDeclaredFields()));
        return noValid.stream()
                .filter(field -> field.isAnnotationPresent(NotNull.class))
                .filter(field -> {
                  Object value;
                  try{
                      field.setAccessible(true);
                      value = field.get(address);
                      field.setAccessible(false);
                  } catch (IllegalAccessException e) {
                      throw new RuntimeException(e);
                  }
                  return value == null;
                })
                .map(Field -> Field.getName())
                .collect(Collectors.toList());


    }



    public static Map<String, List<String>> advancedValidate(Address address) {
        List<Field> fields = new ArrayList<>(List.of(address.getClass().getDeclaredFields()));
        Map<String, List<String>> map = new HashMap<>();
        fields.stream()
                .filter(field -> field.isAnnotationPresent(NotNull.class) || field.isAnnotationPresent(MinLength.class))
                .forEach(field -> {
                    String name = field.getName();
                    List<String> errors = getErrorMessage(field, address);
                    if (!errors.isEmpty()) {
                        map.put(name, errors);
                    }
                });
        return map;
    }

    public static List<String> getErrorMessage(Field field, Object init) {
        List<String> errors = new ArrayList<>();
        String value;
        try{
            field.setAccessible(true);
            value = (String) field.get(init);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if (field.isAnnotationPresent(NotNull.class) && value == null) {
            errors.add("can not be null");
        } else if (field.isAnnotationPresent(MinLength.class)) {
            int minLength = field.getAnnotation(MinLength.class).minLength();
            if (value.length() < minLength || value == null) {
                errors.add("length less than " + minLength);
            }
        }
        return errors;
    }
}
// END
