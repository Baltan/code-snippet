package system;

import lombok.SneakyThrows;

/**
 * Description:
 *
 * @author baltan
 * @date 2026/2/12 15:07
 */
public class Test9 {
    @SneakyThrows
    public static void main(String[] args) {
        speak("damn it");
    }

    @SneakyThrows
    public static void speak(String content) {
        Runtime.getRuntime().exec(new String[]{"say", content});
    }
}
