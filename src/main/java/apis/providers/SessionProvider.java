package apis.providers;

public interface SessionProvider {
    void session(String key, String value);
    String session(String key);
    void clear();
}
