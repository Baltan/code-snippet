/**
 * 一次性导入多个Java包时,可以使用JavaImporter并结合with，在with块范围内引用
 *
 * @type {JavaImporter}
 */
var imports = new JavaImporter(java.io, java.lang);
with (imports) {
    var file = new File(__FILE__);
    System.out.println(file.getAbsolutePath());
    System.out.println(file.name);
}