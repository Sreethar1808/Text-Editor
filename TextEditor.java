import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    //create a jframe
    JFrame frame;
    //set the menubar
    JMenuBar menubar;
//create the file menu
    JMenu file, edit;
//create the file menu item
    JMenuItem newFile, openFile, saveFile;

    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;
    TextEditor(){

        //Intialize the Jframe
        frame = new JFrame();
        textArea = new JTextArea();
        menubar= new JMenuBar();

        //Intialize the menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //add the menu into the menubar
        menubar.add(file);
        menubar.add(edit);

        //Intialize the menu bar item
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save file");

        //Add ActionListner to file menu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        close = new JMenuItem("close");

        //Add ActionListener to the edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menubar to the Jframe
        frame.setJMenuBar(menubar);

        // set textarea to the frame
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea,BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);
        // set frame width height & layout
        frame.setBounds(0,0,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
            if(actionEvent.getSource()==newFile)
            {
                TextEditor newTextEditor = new TextEditor();
            }

            if(actionEvent.getSource()==openFile)
            {
               JFileChooser fileChooser = new JFileChooser("C:");
               int chooseOption = fileChooser.showOpenDialog(null);
               if(chooseOption==JFileChooser.APPROVE_OPTION)
               {
                   File file = fileChooser.getSelectedFile();

                   String filePath = file.getPath();
                   try{
                       FileReader fileReader = new FileReader(filePath);
                       BufferedReader bufferedReader = new BufferedReader(fileReader);
                       String intermediate ="", output ="";
                       while((intermediate=bufferedReader.readLine())!=null)
                       {
                           output+=intermediate+"\n";
                       }

                       textArea.setText(output);
                   }
                   catch(FileNotFoundException fileNotFoundException)
                   {
                        fileNotFoundException.printStackTrace();
                   }
                   catch (IOException ioException)
                   {
                       ioException.printStackTrace();
                   }
               }

            }


            if(actionEvent.getSource()==saveFile)
            {
                JFileChooser fileChooser = new JFileChooser("C:");
                int chooseoption = fileChooser.showSaveDialog(null);

                if(chooseoption==JFileChooser.APPROVE_OPTION)
                {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                    try{
                        FileWriter fileWriter = new FileWriter(file);

                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    }
                    catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                }
            }

            if(actionEvent.getSource()==cut)
            {
                textArea.cut();
            }
        if(actionEvent.getSource()==copy)
        {
            textArea.copy();
        }
        if(actionEvent.getSource()==paste)
        {
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll)
        {
           textArea.selectAll();
        }
        if(actionEvent.getSource()==close)
        {
           System.exit(0);
        }
    }
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
    }
}