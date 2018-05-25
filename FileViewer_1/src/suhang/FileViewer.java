package suhang;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FileViewer extends JFrame implements TreeSelectionListener, ActionListener{

	private static final String FileViewer = null;
	JTree tree;
	DefaultTreeModel treeModel;
	
	
////////////////////////////////// ���丮 ���� ����
	File dir = new File("c:\\");
	
	File[] files = dir.listFiles();
	FileFilter fileFilter = new FileFilter() {
		public boolean accept(File file) {
			return file.isDirectory();
		}
	};
	
	File dir1 = new File("d:\\");
	
	File[] files1 = dir1.listFiles();
	FileFilter fileFilter1 = new FileFilter() {
		public boolean accept(File file1) {
			return file1.isDirectory();
		}
	};
//////////////////////////////// 
	protected boolean state;
	

	public FileViewer() {
		super("���� Ž����");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("����ǻ��");
		DefaultMutableTreeNode branch = new DefaultMutableTreeNode("��ũ����̺�(c:)");
		DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("��ũ����̺�(d:)");
		
		treeModel = new DefaultTreeModel(root);
		
		
		tree = new JTree(treeModel);
		
		JPanel p = new JPanel();
		JLabel L = new JLabel("���� �Ŵ���");
		JTextArea ta = new JTextArea(20,15);
		JScrollPane sc = new JScrollPane(ta);
		JComboBox<String> C = new JComboBox<String>();
		C.addActionListener(this);
		C.addItem("�ѱ���");
		C.addItem("����");
		C.add(p);
		
        C.addActionListener(new ActionListener() {
            
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(C.getSelectedItem() == "�ѱ���") {
					L.setText("���� �Ŵ���");
				}
				else
					L.setText("File Manager");
			}     
        });
		
		
		sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		ta.setLineWrap(true);       //������ �����ٷ� ���� ����.
		
		JTextField t = new JTextField("ȸ�� 2�г� 1�� 5�� ������ �Դϴ�.");
		
		root.add(branch);
		root.add(branch1);
		
		
		sc.setViewportView(tree);
		sc.setPreferredSize(new Dimension(200,-1));
		sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(p,BorderLayout.SOUTH);
		add(sc, BorderLayout.WEST);
		add(t, BorderLayout.NORTH);
		p.add(L,BorderLayout.SOUTH);
		p.add(C,BorderLayout.SOUTH);
		
		setSize(500,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
//////////////////////////////////////////////c����̺� ���� �������
		

		files = dir.listFiles(fileFilter);
		if(files.length == 0) {
			System.out.println("Either dir does not exist or iss not a dirctory");
		} else {
			for(int i = 0; i< files.length; i++) {
				File filename = files[i];
				if (filename.toString().contains("&") ||
						filename.toString().contains("Recovery") || filename.toString().contains

("System")||
						filename.toString().contains("Temp") || filename.toString().contains("PerLogs"))
						continue;
				else
			    System.out.println(filename.toString());
				
				DefaultMutableTreeNode leaf1 = new DefaultMutableTreeNode(filename.getName());
				
				treeModel = new DefaultTreeModel(branch);
			    treeModel.insertNodeInto(leaf1, branch, 0);
				
				branch.add(leaf1);
			}
		}
		
///////////////////////////////////////////////
		
///////////////////////////////////////////////d����̺� ���� �������		 
		files = dir1.listFiles(fileFilter1);
		if(files1.length == 0) {
			System.out.println("Either dir does not exist or iss not a dirctory");
		} else {
			for(int i = 0; i< files1.length; i++) {
				File filename1 = files1[i];
				if (filename1.toString().contains("&") ||
						filename1.toString().contains("Recovery") || filename1.toString().contains

("System")||
						filename1.toString().contains("Temp") || filename1.toString().contains("PerLogs"))
						continue;
				else
			    System.out.println(filename1.toString());
				
				DefaultMutableTreeNode leaf2 = new DefaultMutableTreeNode(filename1.getName());
				
				treeModel = new DefaultTreeModel(branch1);
			    treeModel.insertNodeInto(leaf2, branch1, 0);
				
				branch1.add(leaf2);
			}
		}

		
///////////////////////////////////////////

		
	}

	public static void main(String[] args) {
		new FileViewer();
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

/*	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getPath();
		int count = path.getPathCount();
		File f = new File((String) path.getPathComponent(1)); 
		for(int z = 2; z< count; z++ ){
			String s = (String)path.getPathComponent(z);
		    f= new File(f,s);
		}
		System.out.println(f);
		
	}
}*/