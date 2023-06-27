package kr.co.mz.tutorial.http.cache;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private final Map<String, CacheEntry> cacheMap;

    public Cache() {
        cacheMap = new HashMap<>();
    }

    public String get(String url) {
        CacheEntry cacheEntry = cacheMap.get(url);
        if (cacheEntry == null || cacheEntry.isExpired()) {
            return "";
        }
        return cacheEntry.getResponse();
    }//깔끔하게

    public void put(String key, String value) {
        CacheEntry entry = new CacheEntry(value);
        cacheMap.put(key, entry);
    }

    public int size() {
        return cacheMap.size();
    }

    public void clear() {
        cacheMap.clear();
    }
}
