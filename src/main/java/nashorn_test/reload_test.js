/**
 * 方法和函数可以使用点符号或方括号来进行调用
 *
 * 在使用重载的参数来调用方法时可以传递可选参数来确定具体调用了哪个方法，如 println(double)
 */
var System = Java.type('java.lang.System');
System.out.println(10);
System.out["println"](11.0);
System.out["println(double)"](12);