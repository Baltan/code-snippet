package enum_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2017/12/28 14:46
 */
enum Size implements Color {
    EXTRA_SMALL("XS"), SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private String size;

    Size(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public void setColor() {

    }
}
