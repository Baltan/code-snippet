package optional;

import java.util.Optional;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/28 23:06
 */
public class Test1 {
    public static void main(String[] args) {
        Optional<Goddess> goddess = Optional.ofNullable(new Goddess("波多野老师"));
        Optional<Goddess> nullGoddess = Optional.ofNullable(null);
        Optional<Man> man = Optional.ofNullable(new Man(goddess));
        Optional<Man> noGoddessMan = Optional.ofNullable(new Man(nullGoddess));
        System.out.println(getGoddessName(man));
        System.out.println(getGoddessName(noGoddessMan));
        System.out.println(getGoddessName(Optional.ofNullable(null)));
    }

    public static String getGoddessName(Optional<Man> man) {
        return man.orElse(new Man())
                .getGoddess()
                .orElse(new Goddess("苍老师"))
                .getName();
    }
}

class Goddess {
    private String name;

    public Goddess(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Man {
    private Optional<Goddess> goddess = Optional.empty();

    public Man() {
    }

    public Man(Optional<Goddess> goddess) {
        this.goddess = goddess;
    }

    public Optional<Goddess> getGoddess() {
        return goddess;
    }
}
