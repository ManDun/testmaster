import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class SimpleTree extends JPanel {
  JTree tree;
  DefaultMutableTreeNode root, node1, node2, node3, node4;
  public SimpleTree() {
    root = new DefaultMutableTreeNode("root", true);
    node1 = new DefaultMutableTreeNode("node 1", true);
    node2 = new DefaultMutableTreeNode("node 2" , true);
    node3 = new DefaultMutableTreeNode("node 3" , true);
    node4 = new DefaultMutableTreeNode("node 4" , true);
    root.add(node1);
    node1.add(node2);
    root.add(node3);
    node3.add(node4);
    setLayout(new BorderLayout());
    UIManager.put("Tree.expandedIcon",  new ImageIcon("treeMinus.gif"));
    UIManager.put("Tree.collapsedIcon", new ImageIcon("treePlus.gif"));
    tree = new JTree(root);
    add(new JScrollPane((JTree)tree),"Center");
    }

  public Dimension getPreferredSize(){
    return new Dimension(200, 120);
    }

  public static void main(String s[]){
    MyJFrame frame = new MyJFrame("SimpleTree");
    }
  }

class WindowCloser extends WindowAdapter {
  public void windowClosing(WindowEvent e) {
    Window win = e.getWindow();
    win.setVisible(false);
    System.exit(0);
    }
  }

class MyJFrame extends JFrame {
  SimpleTree panel;
  MyJFrame(String s) {
    super(s);
    setForeground(Color.black);
    setBackground(Color.lightGray);
    panel = new SimpleTree();
    getContentPane().add(panel,"Center");

    setSize(300,300);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowCloser());
    }
}

