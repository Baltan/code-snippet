var list2 = new java.util.ArrayList();
list2.add("ddd2");
list2.add("aaa2");
list2.add("bbb1");
list2.add("aaa1");
list2.add("bbb3");
list2.add("ccc");
list2.add("bbb2");
list2.add("ddd1");

/**
 * 可以在接受 Lambda 表达式的地方使用函数来替代
 */
list2
    .stream()
    .filter(function (el) {
        return el.startsWith("aaa");
    })
    .sorted()
    .forEach(function (el) {
        print(el);
    });