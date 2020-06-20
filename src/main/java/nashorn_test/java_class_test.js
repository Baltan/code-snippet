/**
 * Java 的类型可以简单的通过 Java.extend 进行扩展
 */
var Runnable = Java.type('java.lang.Runnable');
var Printer = Java.extend(Runnable, {
    run: function () {
        print('printed from a separate thread');
    }
});

var Thread = Java.type('java.lang.Thread');
new Thread(new Printer()).start();

new Thread(function () {
    print('printed from another thread');
}).start();