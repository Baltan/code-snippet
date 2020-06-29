package compression_test.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-06-29 22:22
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamUtil {
    public static void close(Closeable c) throws IOException {
        if (Objects.nonNull(c)) {
            c.close();
        }
    }
}
