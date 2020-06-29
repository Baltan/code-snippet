package pack_test.util;

import pack_test.exception.PasswordException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.io.File;
import java.util.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-06-29 18:51
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PackUtil {
    public static void main(String[] args) {
        List<File> chosenFile = new ArrayList<>();
        /**
         * 创建一个指定标题的框架，初始不可见
         */
        JFrame frame = new JFrame("加解密工具");
        frame.setSize(600, 500);
        frame.setLocation(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton fileBtn = new JButton("选择文件");
        fileBtn.setBounds(100, 60, 80, 40);
        panel.add(fileBtn);

        JTextArea filePathArea = new JTextArea();
        filePathArea.setBounds(220, 60, 280, 40);
        filePathArea.setEditable(false);
        panel.add(filePathArea);

        fileBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setBounds(280, 0, 400, 500);
            /**
             * 0-打开 1-取消
             */
            int operation = fileChooser.showOpenDialog(null);

            if (operation == 0) {
                chosenFile.clear();
                filePathArea.setText("");
                File file = fileChooser.getSelectedFile();
                filePathArea.append(file.getAbsolutePath());
                chosenFile.add(file);
            }
        });

        JLabel passwordLabel = new JLabel("加密密码");
        passwordLabel.setBounds(100, 140, 80, 40);
        panel.add(passwordLabel);

        /**
         * 密码输入框
         */
        JPasswordField passwordInput = new JPasswordField();
        passwordInput.setBounds(220, 140, 280, 40);
        passwordInput.setEchoChar('*');
        panel.add(passwordInput);

        JLabel confirmPasswordLabel = new JLabel("确认密码");
        confirmPasswordLabel.setBounds(100, 220, 80, 40);
        panel.add(confirmPasswordLabel);

        /**
         * 密码输入框
         */
        JPasswordField confirmPasswordInput = new JPasswordField();
        confirmPasswordInput.setBounds(220, 220, 280, 40);
        confirmPasswordInput.setEchoChar('*');
        panel.add(confirmPasswordInput);

        JLabel decryptPasswordLabel = new JLabel("解密密码");
        decryptPasswordLabel.setBounds(100, 300, 80, 40);
        panel.add(decryptPasswordLabel);

        /**
         * 密码输入框
         */
        JPasswordField decryptPasswordInput = new JPasswordField();
        decryptPasswordInput.setBounds(220, 300, 280, 40);
        decryptPasswordInput.setEchoChar('*');
        panel.add(decryptPasswordInput);

        JButton encryptBtn = new JButton("加密");
        encryptBtn.setBounds(110, 400, 100, 40);
        panel.add(encryptBtn);

        encryptBtn.addActionListener(event -> {
            char[] password = passwordInput.getPassword();
            char[] confirmPassword = confirmPasswordInput.getPassword();

            if (!Arrays.equals(password, confirmPassword)) {
                JOptionPane.showMessageDialog(null, "两次密码输入不一致，请重新输入！", "提示", JOptionPane.WARNING_MESSAGE);
                passwordInput.setText("");
                confirmPasswordInput.setText("");
                return;
            }

            if (chosenFile.isEmpty()) {
                JOptionPane.showMessageDialog(null, "未选择文件！", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            File file = chosenFile.get(0);
            String source = file.getAbsolutePath();
            String parent = file.getParent();
            String destination = parent + File.separator + UUID.randomUUID() + ".zip";

            try {
                EncodeUtil.encode(source, destination, password);
            } catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "加密失败！", "提示", JOptionPane.WARNING_MESSAGE);
                t.printStackTrace();
                return;
            }
        });

        JButton decryptBtn = new JButton("解密");
        decryptBtn.setBounds(250, 400, 100, 40);
        panel.add(decryptBtn);

        decryptBtn.addActionListener(event -> {
            char[] decryptPassword = decryptPasswordInput.getPassword();

            if (Objects.isNull(decryptPassword) || decryptPassword.length == 0) {
                JOptionPane.showMessageDialog(null, "请输入密码！", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (chosenFile.isEmpty()) {
                JOptionPane.showMessageDialog(null, "未选择文件！", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            File file = chosenFile.get(0);
            String source = file.getAbsolutePath();
            String parent = file.getParent();

            try {
                DecodeUtil.decode(source, parent, decryptPassword);
            } catch (PasswordException e) {
                JOptionPane.showMessageDialog(null, "密码错误！", "提示", JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
                return;
            } catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "解密失败！", "提示", JOptionPane.WARNING_MESSAGE);
                t.printStackTrace();
                return;
            }
        });

        JButton resetBtn = new JButton("重置");
        resetBtn.setBounds(390, 400, 100, 40);
        panel.add(resetBtn);

        resetBtn.addActionListener(event -> {
            passwordInput.setText("");
            confirmPasswordInput.setText("");
            decryptPasswordInput.setText("");
            chosenFile.clear();
            filePathArea.setText("");
        });

        frame.add(panel);
        /**
         * 设置容器布局为流式布局
         */
        frame.setVisible(true);
    }
}
