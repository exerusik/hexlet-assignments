package exercise;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// BEGIN
class App{
	public static void swapKeyValue(KeyValueStorage storage) {
	Map<String, String> keyValueStorage = storage.toMap();
		Set<Map.Entry<String, String>> entries = keyValueStorage.entrySet();
		entries.stream()
				.forEach(entry -> storage.unset(entry.getKey()));
		entries.stream()
				.forEach(entry -> storage.set(entry.getValue(), entry.getKey()));

	}
}
// END
