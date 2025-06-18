/**
 * 不需要常规的用 getter 或者 setter 来访问类成员属性，可直接用属性名简单访问 Java Bean 中的属性
 */
var Date = Java.type('java.util.Date');
var date = new Date();
date.year += 1900;
print(date.year);