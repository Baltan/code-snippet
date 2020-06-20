var fun1 = function (name) {
    print('Hi there from Javascript, ' + name);
    return "greetings from javascript";
};

var fun2 = function (object) {
    print("JS Class Definition: " + Object.prototype.toString.call(object));
};

var test3 = Java.type('nashorn_test.Test3');
/**
 * 调用Test3.java的fun1()方法
 */
var result = test3.fun1('John Doe');
print('\n');
print(result);
print('\n');

/**
 * 调用Test3.java的fun2()方法
 */
test3.fun2(123);
test3.fun2(49.99);
test3.fun2(true);
test3.fun2("hi there")
test3.fun2(new Number(23));
test3.fun2(new Date());
test3.fun2(new RegExp());
test3.fun2({foo: 'bar'});

/**
 * 调用Test3.java的fun3()方法
 */
test3.fun3({
    foo: 'bar',
    bar: 'foo'
});

function Person(firstName, lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.getFullName = function () {
        return this.firstName + " " + this.lastName;
    }
}

/**
 * 调用Test3.java的fun4()方法
 */
var person1 = new Person("Peter", "Parker");
test3.fun4(person1);