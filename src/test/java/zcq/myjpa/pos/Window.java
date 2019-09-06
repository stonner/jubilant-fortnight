package zcq.myjpa.pos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Window {

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
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
		SerialPost.initJComboBox(comboBox);
		
		
		
		JLabel lblip = new JLabel("IP");
		
		JLabel label = new JLabel("POST");
		
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
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SerialPost.logArea = logArea;
				//获取串口号
				final String post = comboBox.getSelectedItem().toString();
				//判断是否为空
				if(null == post || "".equals(post.trim())){
					SerialPost.append("请输入串口号！");
					return;
				}
				
				//获取ip
				String ip = ipText.getText();
				//判断是否为空
				if(null == ip || "".equals(ip.trim())){
					SerialPost.append("请输入IP！");
					return;
				}
				SerialPost.url = "http://" + ip;
				
				//获取端口
				String postTxt = postText.getText();
				//判断是否为空
				if(null == postTxt || "".equals(postTxt.trim())){
					SerialPost.append("请输入POST！");
					return;
				}
				SerialPost.url += ":" + postTxt;
				
				//获取项目名
				String name = nameText.getText();
				//判断是否为空
				if(null == name || "".equals(name.trim())){
					SerialPost.append("请输入NAME！");
					return;
				}
				SerialPost.url += "/" + name;
				
				//获取终端号
				String num = numText.getText();
				if(null == num || "".equals(num.trim())){
					SerialPost.append("请输入终端号！");
					return;
				}
				SerialPost.num = num;
				
				//获取html
				String html = htmlText.getText();
				if(null == html || "".equals(html.trim())){
					SerialPost.append("请输入html地址！");
					return;
				}
				SerialPost.html = html;
				
				//进行读取串口
				Runnable th = new Runnable() {
					@Override
					public void run() {
						try {
						SerialPost.read(post);
						} catch (Exception e1) {
							SerialPost.append("获取串口号失败！" + e1.getLocalizedMessage());
						}
					}
				};
				Thread thread = new Thread(th);
				thread.start();
			}
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
											.addPreferredGap(ComponentPlacement.RELATED)
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
