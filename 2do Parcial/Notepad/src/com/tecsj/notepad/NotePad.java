package com.tecsj.notepad;

import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

import static com.tecsj.util.GUI.convertJMenuItemToJButton;
import static com.tecsj.util.GUI.createMenuItem;

public class NotePad extends JFrame implements ActionListener, WindowListener{

    JTextPane jtp = new JTextPane();
    StyledDocument styledDocument = new DefaultStyledDocument();
    File fnameContainer;

    public NotePad(){
        jtp.setStyledDocument(styledDocument);
        Font fnt = new Font("Arial",Font.PLAIN,15);
        Container frame = getContentPane();
        JMenuBar jmb = new JMenuBar();
        JToolBar jtb = new JToolBar();
        JMenu jmfile = new JMenu("File");
        JMenu jmedit = new JMenu("Edit");
        JMenu jmformat = new JMenu("Format");
        JMenu jmhelp = new JMenu("Help");

        frame.setLayout(new BorderLayout());
        JScrollPane sbrText = new JScrollPane(jtp);
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sbrText.setVisible(true);

        jtp.setFont(fnt);
        // Enable line wrapping
        //jtp.putClientProperty(JTextPane.W3C_LENGTH_UNITS, true);



        add(sbrText);

        JMenuItem newItem = createMenuItem(jmfile,"New","../icons/new.png",this);
        JMenuItem openItem = createMenuItem(jmfile,"Open","../icons/open.png",this);
        JMenuItem saveItem = createMenuItem(jmfile,"Save","../icons/save.png",this);
        JMenuItem saveAsItem = createMenuItem(jmfile,"Save as ...","../icons/saveAsText@3x.png",this);
        jmfile.addSeparator();
        JMenuItem exitItem = createMenuItem(jmfile,"Exit","../icons/door_out.png",this);


        JMenuItem cutItem = createMenuItem(jmedit,"Cut","../icons/cut.png",this);
        JMenuItem copyItem = createMenuItem(jmedit,"Copy","../icons/copy.png",this);
        JMenuItem pasteItem = createMenuItem(jmedit,"Paste","../icons/paste.png",this);
        jmfile.addSeparator();
        JMenuItem findItem = createMenuItem(jmedit,"Find","../icons/find.png",this);

        JMenuItem fontItem = createMenuItem(jmformat,"Font","../icons/font.png",this);
        JMenuItem backgroundItem = createMenuItem(jmformat,"Background","../icons/folder_palette.png",this);
        JMenuItem adjustItem = createMenuItem(jmformat,"Adjust","../icons/T_WordWrap_Sm_N@3x.png",this);


        JMenuItem aboutItem = createMenuItem(jmhelp,"About","../icons/help_about.png",this);

        jmb.add(jmfile);
        jmb.add(jmedit);
        jmb.add(jmformat);
        jmb.add(jmhelp);

        setJMenuBar(jmb);


        jtb.add(convertJMenuItemToJButton(newItem));
        jtb.add(convertJMenuItemToJButton(openItem));
        jtb.add(convertJMenuItemToJButton(saveItem));
        jtb.addSeparator();

        jtb.add(convertJMenuItemToJButton(cutItem));
        jtb.add(convertJMenuItemToJButton(copyItem));
        jtb.add(convertJMenuItemToJButton(pasteItem));
        jtb.add(convertJMenuItemToJButton(findItem));
        jtb.addSeparator();

        jtb.add(convertJMenuItemToJButton(fontItem));
        jtb.add(convertJMenuItemToJButton(backgroundItem));
        jtb.addSeparator();

        jtb.add(convertJMenuItemToJButton(aboutItem));
        jtb.addSeparator();

        jtb.add(convertJMenuItemToJButton(exitItem));
        jtb.addSeparator();


        // Add components to the frame using layout managers
        frame.add(jtb, BorderLayout.NORTH);
        frame.add(new JScrollPane(jtp), BorderLayout.CENTER);

        setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
        addWindowListener(this);
        setSize(500,500);
        setTitle("Untitled.txt - Notepad");
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        JFileChooser jfc = new JFileChooser();
        if(e.getActionCommand().equals("New")){
            this.setTitle("UntitledNew.txt - Notepad");
            jtp.setText("");
            fnameContainer = null;
        }else if (e.getActionCommand().equals("Open")){
            int ret = jfc.showDialog(null,"Open");
            if(ret == JFileChooser.APPROVE_OPTION){
                try {
                    File fyl = jfc.getSelectedFile();
                    OpenFile(fyl.getAbsolutePath());
                    this.setTitle(fyl.getName() + " - Notepad");
                    fnameContainer = fyl;
                }catch (IOException ers){}
            }
        } else if (e.getActionCommand().equals("Save as ...")) {
            if(fnameContainer != null){
                jfc.setCurrentDirectory(fnameContainer);
                jfc.setSelectedFile(fnameContainer);
            }else {
                jfc.setSelectedFile(new File("Untitled.txt"));
            }


            int ret = jfc.showSaveDialog(null);
            if (ret == JFileChooser.APPROVE_OPTION){
                try {
                    File fyl = jfc.getSelectedFile();
                    SaveFile(fyl.getAbsolutePath());
                    this.setTitle(fyl.getName() + " - Notepad");
                    fnameContainer = fyl;
                }catch (Exception ets){}
            }
        }else if (e.getActionCommand().equals("Save")) {
            if (fnameContainer != null) {
                try {
                    // Check if the file exists, if so, save directly
                    SaveFile(fnameContainer.getAbsolutePath());
                    this.setTitle(fnameContainer.getName() + " - Notepad");
                } catch (Exception ex) {
                    // Handle exceptions (e.g., file not found or unable to save)
                    ex.printStackTrace();
                }
            } else {
                // If fnameContainer is null, this is equivalent to "Save As..."
                // Implement "Save As..." functionality here
                // You can use the existing code for "Save As..." with appropriate modifications
                if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = jfc.getSelectedFile();
                        SaveFile(file.getAbsolutePath());
                        this.setTitle(file.getName() + " - Notepad");
                        fnameContainer = file;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }else if (e.getActionCommand().equals("Exit")){
            Exiting();
        }else if (e.getActionCommand().equals("Copy")){
            jtp.copy();
        }else if (e.getActionCommand().equals("Paste")){
            jtp.paste();
        }else if (e.getActionCommand().equals("Find")) {
            // Create a dialog to input the search text
            String searchText = JOptionPane.showInputDialog("Find:");

            if (searchText != null) {
                String text = jtp.getText();
                int startIndex = text.indexOf(searchText);

                if (startIndex != -1) {
                    // Text was found, select and scroll to the found text
                    jtp.select(startIndex, startIndex + searchText.length());
                    jtp.setCaretPosition(startIndex);
                } else {
                    JOptionPane.showMessageDialog(null, "Text not found.");
                }
            }
        }else if (e.getActionCommand().equals("Font")) {
            // Create an array of available font names
            String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

            // Prompt the user to select a font
            String selectedFont = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose a font:",
                    "Font Selection",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    fontNames,
                    fontNames[0]);

            if (selectedFont != null) {
                // Prompt the user to select a font size
                String fontSizeString = JOptionPane.showInputDialog("Enter font size:");
                int fontSize = Integer.parseInt(fontSizeString);

                // Apply the selected font and size to the text in the JTextArea
                Font newFont = new Font(selectedFont, Font.PLAIN, fontSize);
                jtp.setFont(newFont);
            }
        }else if (e.getActionCommand().equals("Background")) {
            // Show a color chooser dialog
            Color selectedColor = JColorChooser.showDialog(null, "Choose Background Color", jtp.getBackground());

            if (selectedColor != null) {
                // Set the selected color as the background color for the JTextArea
                jtp.setBackground(selectedColor);
            }
        }else if (e.getActionCommand().equals("Adjust")) {
            // Define alignment options
            String[] alignmentOptions = { "Right", "Left", "Center" };

            // Prompt the user to select an alignment
            String selectedAlignment = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose text alignment:",
                    "Text Alignment",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    alignmentOptions,
                    alignmentOptions[0]);

            if (selectedAlignment != null) {
                int alignment = SwingConstants.LEFT; // Default to left alignment

                // Determine the chosen alignment
                if (selectedAlignment.equals("Left")) {
                    alignment = SwingConstants.CENTER;
                } else if (selectedAlignment.equals("Center")) {
                    alignment = SwingConstants.RIGHT;
                }

                // Apply the chosen alignment to the selected text in the JTextArea
                StyledDocument doc = jtp.getStyledDocument();
                int start = jtp.getSelectionStart();
                int end = jtp.getSelectionEnd();

                // Create a SimpleAttributeSet to hold the alignment style
                SimpleAttributeSet alignmentStyle = new SimpleAttributeSet();
                StyleConstants.setAlignment(alignmentStyle, alignment);

                // Apply the alignment style to the selected text
                doc.setParagraphAttributes(start, end - start, alignmentStyle, false);
            }
        }
        else if (e.getActionCommand().equals("About Notepad")){
            JOptionPane.showMessageDialog(this,"Created by : Luis Villalobos","Notepad",JOptionPane.INFORMATION_MESSAGE);
        }else if (e.getActionCommand().equals("Cut")){
            jtp.cut();
        }

    }

    public void OpenFile(String fname) throws IOException{
        BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
        String l;
        jtp.setText("");
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        while ((l = d.readLine()) != null){
            jtp.setText(jtp.getText() + l + "\r\n");
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        d.close();
    }

    public void SaveFile(String fname) throws IOException{
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataOutputStream o = new DataOutputStream(new FileOutputStream(fname));
        o.writeBytes(jtp.getText());
        o.close();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void windowDeactivated(WindowEvent e){}

    public void windowActivated(WindowEvent e){}

    public void windowDeiconified(WindowEvent e){}

    public void windowIconified(WindowEvent e){}

    public void windowClosed(WindowEvent e){}

    public void windowClosing(WindowEvent e){Exiting();}

    public void windowOpened(WindowEvent e){}


    public void Exiting(){
        System.exit(0);
    }






}
