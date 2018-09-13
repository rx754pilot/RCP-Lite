package org.rcplite.platform.ui;

import org.rcplite.platform.services.LogService;
import org.rcplite.platform.windows.ComponentPosition;
import org.rcplite.platform.windows.ViewComponent;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@ViewComponent.Configuration(
        position = ComponentPosition.DOCUMENT
)
public class HtmlViewerComponent extends ViewComponent {

    public HtmlViewerComponent(){
        setTitle("HTML Viewer");
        setLayout(new BorderLayout());
        // create jeditorpane
        JEditorPane jEditorPane = new JEditorPane();

        // make it read-only
        jEditorPane.setEditable(false);

        // create a scrollpane; modify its attributes as desired
        JScrollPane scrollPane = new JScrollPane(jEditorPane);

        // add an html editor kit
        HTMLEditorKit kit = new HTMLEditorKit();
        jEditorPane.setEditorKit(kit);

        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
        styleSheet.addRule("h1 {color: blue;}");
        styleSheet.addRule("h2 {color: #ff0000;}");
        styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");

        String htmlString = "<html>\n"
                + "<body>\n"
                + "<h1>Welcome!</h1>\n"
                + "<h2>This is an H2 header</h2>\n"
                + "<p>This is some sample text</p>\n"
                + "<p><a href=\"http://devdaily.com/blog/\">devdaily blog</a></p>\n"
                + "</body>\n";

        // create a document, set it on the jeditorpane, then add the html
        Document doc = kit.createDefaultDocument();
        jEditorPane.setDocument(doc);
        jEditorPane.setText(htmlString);
        add(scrollPane, BorderLayout.CENTER);

        JButton btn = new JButton("demo");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogService.getInstance().log("Testing the microphone");
            }
        });
        add(btn, BorderLayout.NORTH);
    }

}
