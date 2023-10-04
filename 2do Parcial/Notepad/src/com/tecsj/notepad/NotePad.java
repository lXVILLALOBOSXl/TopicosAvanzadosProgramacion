package com.tecsj.notepad;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.tecsj.util.GUI.convertJMenuItemToJButton;
import static com.tecsj.util.GUI.createMenuItem;

public class NotePad extends JFrame implements ActionListener, WindowListener{

    JTextArea jta = new JTextArea();
    File fnameContainer;

    public NotePad(){
        Font fnt = new Font("Arial",Font.PLAIN,15);
        Container frame = getContentPane();
        JMenuBar jmb = new JMenuBar();
        JToolBar jtb = new JToolBar();
        JMenu jmfile = new JMenu("File");
        JMenu jmedit = new JMenu("Edit");
        JMenu jmformat = new JMenu("Format");
        JMenu jmhelp = new JMenu("Help");

        frame.setLayout(new BorderLayout());
        JScrollPane sbrText = new JScrollPane(jta);
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sbrText.setVisible(true);

        jta.setFont(fnt);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);

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
        frame.add(new JScrollPane(jta), BorderLayout.CENTER);

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
            jta.setText("");
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
        } else if (e.getActionCommand().equals("Save")) {
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
        }else if (e.getActionCommand().equals("Exit")){
            Exiting();
        }else if (e.getActionCommand().equals("Copy")){
            jta.copy();
        }else if (e.getActionCommand().equals("Paste")){
            jta.paste();
        }else if (e.getActionCommand().equals("About Notepad")){
            JOptionPane.showMessageDialog(this,"Created by : Luis Villalobos","Notepad",JOptionPane.INFORMATION_MESSAGE);
        }else if (e.getActionCommand().equals("Cut")){
            jta.cut();
        }

    }

    public void OpenFile(String fname) throws IOException{
        BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
        String l;
        jta.setText("");
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        while ((l = d.readLine()) != null){
            jta.setText(jta.getText() + l + "\r\n");
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        d.close();
    }

    public void SaveFile(String fname) throws IOException{
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataOutputStream o = new DataOutputStream(new FileOutputStream(fname));
        o.writeBytes(jta.getText());
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
