package lombok;

import lombok.*;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-01 16:58
 */
@ToString(exclude = {"id"}, includeFieldNames = false)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "getInstance")
public class Person {
    @NonNull
    @Getter
    @Setter
    private Integer id;

    @NonNull
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String name;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private int age;

    @NonNull
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String gender;

    @NonNull
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String Job;

    /**
     * @param sentence
     * @NonNull 如果参数为null，抛出NPE异常
     */
    public void say(@NonNull String sentence) {
        System.out.println(sentence.trim());
    }

    /**
     * 自动抛受检异常，而无需显式在方法上使用throws语句
     */
    @SneakyThrows
//    @SneakyThrows(InterruptedException.class)
    public void rest() {
        TimeUnit.SECONDS.sleep(1);
    }
}
