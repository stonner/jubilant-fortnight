package zcq.myjpa;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

public class TestSwing {

        private JFrame frame;
        private JTextField ipText;
        private JTextField postText;
        private JTextField nameText;
        private JTextField numText;
        private JTextField htmlText;

        /**
         * Launch the application.
         */
        public static void main(String[] args) {
            EventQueue.invokeLater(() -> {
                try {
                    TestSwing testSwing = new TestSwing();
                    testSwing.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        /**
         * Create the application.
         */
        public TestSwing() {
            initialize();
        }

        /**
         * Initialize the contents of the frame.
         */
        private void initialize() {
            frame = new JFrame();
            frame.setTitle("\u83B7\u53D6\u4E3B\u5BC6\u94A5");
            frame.setBounds(100, 100, 622, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setLocationRelativeTo(null);

            JLabel postlabel = new JLabel("串口号");

            final JTextArea logArea = new JTextArea();
            logArea.setLineWrap(true);
            JScrollPane scroll = new JScrollPane(logArea);
            scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            final JComboBox comboBox = new JComboBox();



            JLabel lblip = new JLabel("IP");

            JLabel label = new JLabel("PORT");

            JLabel label_1 = new JLabel("NAME");

            JLabel label_2 = new JLabel("终端号");

            JLabel lblHtml = new JLabel("html");

            htmlText = new JTextField("E:/unionPay/pinpad.html");
            htmlText.setColumns(10);

            postText = new JTextField("7001");
            postText.setColumns(10);

            ipText = new JTextField("10.150.45.41");
            ipText.setColumns(10);

            nameText = new JTextField("/");
            nameText.setColumns(10);

            numText = new JTextField();
            numText.setColumns(10);

            JButton button = new JButton("获取密钥");
            button.addActionListener(e -> {
                System.out.println(e.getActionCommand());
                System.out.println(e.getWhen());
                System.out.println(e.getModifiers());
                System.out.println(e.paramString());
            });








            GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
            groupLayout.setHorizontalGroup(
                    groupLayout.createParallelGroup(Alignment.LEADING)
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(43)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                            .addComponent(lblHtml)
                                            .addComponent(scroll, 0, 0, Short.MAX_VALUE)
                                            .addGroup(groupLayout.createSequentialGroup()
                                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                            .addComponent(postlabel)
                                                            .addComponent(lblip))
                                                    .addGap(18)
                                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                            .addComponent(htmlText)
                                                            .addGroup(groupLayout.createSequentialGroup()
                                                                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                                                            .addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(ipText, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
                                                                    .addGap(38)
                                                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                            .addComponent(label_2)
                                                                            .addComponent(label))
                                                                    .addGap(28)
                                                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                                            .addComponent(postText, 0, 0, Short.MAX_VALUE)
                                                                            .addComponent(numText, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                                                                    .addGap(42)
                                                                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                            .addGroup(groupLayout.createSequentialGroup()
                                                                                    .addComponent(label_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                            .addComponent(button))
                                                                    .addGap(12)))))
                                    .addContainerGap(103, Short.MAX_VALUE))
            );
            groupLayout.setVerticalGroup(
                    groupLayout.createParallelGroup(Alignment.LEADING)
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(41)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                            .addComponent(postlabel)
                                            .addGroup(groupLayout.createSequentialGroup()
                                                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                            .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label_2)
                                                            .addComponent(numText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(button))
                                                    .addGap(18)
                                                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                            .addComponent(ipText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label)
                                                            .addComponent(postText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label_1)
                                                            .addComponent(lblip))))
                                    .addGap(24)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                            .addComponent(lblHtml)
                                            .addComponent(htmlText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18)
                                    .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(21, Short.MAX_VALUE))
            );
            frame.getContentPane().setLayout(groupLayout);
        }

}
