/**
 * 有些包时可以直接使用而不必利用 Java.type 或J avaImporter 引入，如：java.util
 */
var list = new java.util.ArrayList();
list.add("s1");
list.add("s2");
list.add("s3");

var jsArray = Java.from(list);
print(jsArray);
print(Object.prototype.toString.call(jsArray));

var javaArray = Java.to([3, 5, 7, 11], "int[]");
print(javaArray);
print(Object.prototype.toString.call(javaArray));