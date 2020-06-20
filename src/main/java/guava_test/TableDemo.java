package guava_test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Set;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/12 16:59
 */
public class TableDemo {
    public static void main(String[] args) {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("zhangsan", "Chinese", 90);
        table.put("zhangsan", "Maths", 100);
        table.put("zhangsan", "English", 80);
        table.put("lisi", "Chinese", 85);
        table.put("lisi", "Maths", 95);
        table.put("lisi", "English", 90);

        System.out.println(table);

        System.out.println("*****************************");

        Set<Table.Cell<String, String, Integer>> set = table.cellSet();
        for (Table.Cell<String, String, Integer> cell : set) {
            System.out.println(cell);
            System.out.println(cell.getRowKey() + "的" + cell.getColumnKey() + "成绩为：" + cell.getValue());
        }

        System.out.println("*****************************");

        Set<String> rowSet = table.rowKeySet();
        for (String str : rowSet) {
            System.out.println(str);
        }

        System.out.println("*****************************");

        System.out.println(table.row("zhangsan"));
        System.out.println(table.column("Chinese"));
    }
}
