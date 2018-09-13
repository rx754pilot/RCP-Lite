package org.rcplite.platform.ui;

import org.rcplite.platform.modules.Tool;
import org.rcplite.platform.services.ShellService;
import org.rcplite.platform.services.ToolsService;
import org.rcplite.platform.windows.ViewComponent;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

public class ToolBoxComponent extends JPanel {
	 JTree tree;

	 public ToolBoxComponent() {
		 DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tools");
         Iterator<Tool> tools = ToolsService.getInstance().getAll();
         while(tools.hasNext()){
             DefaultMutableTreeNode node = new DefaultMutableTreeNode(tools.next());
             root.add(node);
         }

		 tree = new JTree(root);
         tree.setCellRenderer(new ToolboxCellRenderer());

		 setLayout(new BorderLayout());
		 JScrollPane jsp = new JScrollPane(tree);
		 jsp.setPreferredSize(new Dimension(200, 200));
		 add(jsp, BorderLayout.CENTER);

         MouseListener ml = new MouseAdapter() {
             public void mousePressed(MouseEvent e) {
             int selRow = tree.getRowForLocation(e.getX(), e.getY());
             TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
             if(selRow != -1) {
                 if(e.getClickCount() == 1) {
                 }
                 else if(e.getClickCount() == 2) {
                     DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)selPath.getLastPathComponent();
                     if(dmtn.getUserObject() instanceof Tool ){
                         Tool tool = (Tool)dmtn.getUserObject();
                         for(ViewComponent v: tool.getPerspective().getViews()){
                             ShellService.getInstance().getShell().addViewComponent(v);
                         }
                     }
                 }
             }
             }
         };
         tree.addMouseListener(ml);
	 } 
}
