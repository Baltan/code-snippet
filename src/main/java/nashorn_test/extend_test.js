/**
 * 重载 SuperRunner
 * 创建一个新的 Runner 实例时的 Nashorn 扩展语法：其重载成员的语法参考 Java 的匿名对象的做法
 */
var SuperRunner = Java.type('nashorn_test.SuperRunner');
var Runner = Java.extend(SuperRunner);

var runner = new Runner()
{
    run: function () {
        /**
         * Java.super调用了重载方法 SuperRunner.run()
         */
        Java.super(runner).run();
        print('on my run');
    }
}
runner.run();