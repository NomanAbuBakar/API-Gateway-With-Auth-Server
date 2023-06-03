package ic.auth.service.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Utils {
    public static boolean ifEmptyList(List<?> list) {
        return null == list || list.isEmpty();
    }

    public static boolean ifEmptyMap(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    public static boolean isEmptyString(String s) {
        return null == s || s.isEmpty() || s.isBlank();
    }

    public static boolean isInvalidLong(Long l) {
        return null == l || l < 1l;
    }

    public static <T> List<T> applyPagination(List<T> sourceList, int pageNo, int pageSize) {
        if (pageSize <= 0 || pageNo <= 0) {
            return sourceList;
        }
        int fromIndex = (pageNo - 1) * pageSize;
        if (sourceList == null || sourceList.size() < fromIndex) {
            return Collections.emptyList();
        }
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    public static String getConfirmationToken() {
        return String.valueOf(UUID.randomUUID());
    }
}