package jdbc_test;

import jdbc_test.entity.ApplyForm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/8 10:10
 */
public class Test2 {
    private static String urlPrefix = "jdbc:mysql://127.0.0.1:3306/";
    private static String databasePrefix = "databasePrefix_";
    private static String usernamePrefix = "usernamePrefix_";
    private static String password = "password";
    private static String applyFormTable = "applyFormTable_%s_apply_form";
    private static String applyFormExtTable = "applyFormExtTable_%s_apply_form_ext";
    private static String cashFormTable = "cashFormTable_%s_apply_form";
    private static String cashFormExtTable = "cashFormExtTable_%s_cash_form_ext";

    public static void main(String[] args) {
        String[] policyCodes = {"330101"};

        for (String policyCode : policyCodes) {
            Connection connection = null;

            try {
                connection = createConnection(policyCode, password);
                List<ApplyForm> applyFormData = getApplyFormData(policyCode, connection);
                System.out.println(applyFormData);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                destroyConnection(connection);
            }
        }
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection createConnection(String policyCode, String password) throws SQLException {
        String url = getDatabaseUrl(policyCode);
        String username = getDatabaseUsername(policyCode);
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 拼接数据库用户名
     *
     * @param policyCode
     * @return
     */
    private static String getDatabaseUsername(String policyCode) {
        return usernamePrefix + policyCode.split("_")[1];
    }

    /**
     * 拼接数据库url
     *
     * @param policyCode
     * @return
     */
    private static String getDatabaseUrl(String policyCode) {
        return urlPrefix + databasePrefix + policyCode;
    }

    /**
     * 获取申请表数据
     *
     * @param policyCode
     * @param connection
     * @throws SQLException
     */
    private static List<ApplyForm> getApplyFormData(String policyCode, Connection connection) throws SQLException {
        List<ApplyForm> data = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from " + String.format(applyFormTable, policyCode) + " where data_flag = 1";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ApplyForm applyForm = new ApplyForm()
                        .setId(resultSet.getLong("id"))
                        .setBatchId(resultSet.getLong("batch_id"))
                        .setPolicyCode(resultSet.getString("policy_code"))
                        .setAreaCode(resultSet.getLong("area_code"))
                        .setFormState(resultSet.getInt("form_state"))
                        .setCompanyId(resultSet.getLong("company_id"))
                        .setContactName(resultSet.getString("contact_name"))
                        .setContactPhone(resultSet.getString("contact_phone"))
                        .setCreditCode(resultSet.getString("credit_code"))
                        .setApplyTime(resultSet.getDate("apply_time"))
                        .setBankAccountType(resultSet.getInt("bank_account_type"))
                        .setPayMoney(resultSet.getInt("pay_money"))
                        .setPayChannel(resultSet.getInt("pay_channel"))
                        .setCreateTime(resultSet.getDate("create_time"))
                        .setUpdateTime(resultSet.getDate("update_time"));
                data.add(applyForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(resultSet)) {
                resultSet.close();
            }

            if (Objects.nonNull(statement)) {
                statement.close();
            }
        }
        return data;
    }

    private static void destroyConnection(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
