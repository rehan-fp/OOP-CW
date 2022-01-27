import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GUI_Table {

    private ArrayList<String> place = new ArrayList<>(19);//I assume that there are maximus 20 players in one race
    private int total;
    private int position;


    public void setPlace(ArrayList<String> place) {
        this.place = new ArrayList<>();
    }
    public ArrayList<String> getPlace() {
        return place;
    }



    GUI_Table() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        DefaultTableModel model2 = new DefaultTableModel();
        DefaultTableModel model3 = new DefaultTableModel();
        DefaultTableModel model4 = new DefaultTableModel();
        JTable table3 = new JTable();


        //main frame
        JFrame frame = new JFrame();
        frame.setSize(1600, 2000);
        frame.setTitle("F1 CHAMPIONSHIP");
        ImageIcon image2 = new ImageIcon("f1.jpg");
        frame.setIconImage(image2.getImage());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Driver Details

        //Table
        JTable table = new JTable();
        //Label
        JLabel label = new JLabel();
        label.setText("Driver Details");
        label.setForeground(Color.red);
        label.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 30));
        JPanel panel = new JPanel();
        panel.add(label);
        panel.setBounds(10, 25, 500, 475);
        panel.add(new JScrollPane(table));
        Object[] columns = {"Name", "Location", "Team", "Racers", "Points", "1st Place"};
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // The index of the column that need to be class changed
                if (columnIndex == 4 || columnIndex == 5) return Integer.class;
                return super.getColumnClass(columnIndex);
            }
        };
        table.setModel(model);
        model.setColumnIdentifiers(columns);
        table.setFont(new Font("Arial", Font.BOLD, 15));
        table.setRowHeight(30);
        table.setBackground(Color.white);
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.BLACK);
        frame.add(panel);

        //reading the data of the Formula1ChampionshipManager
        String filePath = "F1Table_GUI.txt";
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] tableLines = br.lines().toArray();
            for (int i = 0; i < tableLines.length; i++) {
                table.setAutoCreateRowSorter(true);
                DefaultRowSorter sorter = ((DefaultRowSorter) table.getRowSorter());
                ArrayList list = new ArrayList<>();
                list.add(new RowSorter.SortKey(4, SortOrder.DESCENDING));
                sorter.setSortKeys(list);
                sorter.sort();
                String line = tableLines[i].toString();
                String[] dataRow = line.split(",");
                model.addRow(dataRow);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        //Button
        JButton asc = new JButton();
        panel.add(asc);
        asc.setFocusable(false);
        asc.setBounds(30, 600, 150, 25);
        asc.setText("Ascending");
        asc.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
        asc.setBackground(Color.BLUE);
        asc.setForeground(Color.WHITE);
        frame.getContentPane().add(asc);
        asc.setLocation(275, 520);
        //ActionListener for the Row sorter
        asc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setAutoCreateRowSorter(true);
                DefaultRowSorter sorter = ((DefaultRowSorter) table.getRowSorter());
                ArrayList list = new ArrayList<>();
                list.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
                sorter.setSortKeys(list);
                sorter.sort();
            }
        });

        //Button
        JButton point = new JButton();
        frame.setLayout(new FlowLayout());
        table.setDefaultRenderer(table.getColumnClass(5), renderer);
        point.setFocusable(false);
        point.setBounds(50, 350, 110, 25);
        point.setText("Places");
        point.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
        point.setBackground(Color.BLUE);
        point.setForeground(Color.WHITE);
        frame.getContentPane().add(point);
        point.setLocation(100, 520);
        //ActionListener for the Row sorter
        point.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setAutoCreateRowSorter(true);
                DefaultRowSorter sorter = ((DefaultRowSorter) table.getRowSorter());
                ArrayList list = new ArrayList<>();
                list.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
                sorter.setSortKeys(list);
                sorter.sort();
            }
        });

        //Race Details

        //Table
        JTable table2 = new JTable();
        //Label
        JLabel label2 = new JLabel();
        label2.setText("Race Details");
        label2.setForeground(Color.red);
        label2.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 30));
        JPanel panel2 = new JPanel();
        panel2.add(label2);
        panel2.setBounds(500, 25, 500, 475);
        panel2.add(label2);
        frame.add(panel2);
        panel2.add(new JScrollPane(table2));
        Object[] columns2 = {"Position","Name"};
        table2.setModel(model2);
        model2.setColumnIdentifiers(columns2);
        table2.setFont(new Font("Arial", Font.BOLD, 15));
        table2.setRowHeight(30);
        table2.setBackground(Color.white);
        table2.setForeground(Color.BLACK);
        table2.setGridColor(Color.BLACK);
        //adding the element of the table 1 column 0 to a arraylist
        for (int i = 0; i < table.getRowCount(); i++) {
            String name = (String) model.getValueAt(i, 0);
            place.add(name);
            //adding elements to the table2
            model2.addRow(new Object[]{i+1});
        }

        //Button
        JButton addrace = new JButton();
        frame.setLayout(new FlowLayout());
        table.setDefaultRenderer(table.getColumnClass(5), renderer);
        addrace.setFocusable(false);
        addrace.setBounds(3000, 350, 150, 25);
        addrace.setText("Add Race");
        addrace.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
        addrace.setBackground(Color.BLUE);
        addrace.setForeground(Color.WHITE);
        frame.getContentPane().add(addrace);
        addrace.setLocation(750, 525);
        addrace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addrace) {
                    //Shuffling the arraylist to generate random index
                    Collections.shuffle(place);
                    //Shuffled names saving to a file
                    try {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        FileWriter myWriter = new FileWriter("RaceGUI.txt");
                        myWriter.write(dtf.format(now) + ",");
                        for (int i = 0; i < place.size(); i++) {
                            myWriter.write(place.get(i) + ",");
                        }
                        myWriter.close();
                    } catch (IOException f) {
                        f.printStackTrace();
                    }

                    //Updating the driver detail table according the random generated places
                    for (int i = 0; i < place.size(); i++) {
                        model2.setValueAt(place.get(i), i, 1);

                        if (model2.getValueAt(i, 0).equals(1)) {

                            total = 25 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                            position = 1 + Integer.parseInt(table.getValueAt(i, 5).toString());
                            model.setValueAt(position, i, 5);


                        } else if (model2.getValueAt(i, 0).equals(2)) {

                            total = 18 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);


                        } else if (model2.getValueAt(i, 0).equals(3)) {

                            total = 15 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);


                        } else if (model2.getValueAt(i, 0).equals(4)) {

                            total = 12 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(5)) {

                            total = 10 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(6)) {

                            total = 8 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(7)) {

                            total = 6 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(8)) {

                            total = 4 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(9)) {

                            total = 2 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(10)) {

                            total = 1 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        }

                        int racers = 1 + Integer.parseInt(table.getValueAt(i, 3).toString());
                        model.setValueAt(racers, i, 3);

                    }
                    //reading the random race detail of the F1 championship Manager  to table 3
                    String filePath2 = "RaceGUI.txt";
                    File file2 = new File(filePath2);
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file2));
                        Object[] tableLines = br.lines().toArray();
                        for (int i = 0; i < tableLines.length; i++) {
                            table3.setAutoCreateRowSorter(true);
                            String line = tableLines[i].toString();
                            String[] dataRow3 = line.split(",");
                            model3.addRow(dataRow3);
                        }

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();

                    }


                }
            }

        });
        //Button
        JButton Prob = new JButton();
        frame.setLayout(new FlowLayout());
        table.setDefaultRenderer(table.getColumnClass(5), renderer);
        Prob.setFocusable(false);
        Prob.setBounds(3000, 350, 150, 25);
        Prob.setText("Prob");
        Prob.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
        Prob.setBackground(Color.BLUE);
        Prob.setForeground(Color.WHITE);
        frame.getContentPane().add(Prob);
        Prob.setLocation(575, 525);
        Prob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==Prob){
                    //adding the driver names to a new arraylist
                    ArrayList<String> start = new ArrayList<>(place);
                    //shuffling them
                    Collections.shuffle(start);
                    //adding shuffle list to new array
                    ArrayList<String> prob = new ArrayList<>();

                    if(start.size()<2) {
                        // 1st position player has 0.4 probability
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        prob.add(start.get(0));

                    }else if(start.size()<3) {
                        // 1st position player has 0.4 probability
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        // 2nd position player has 0.3 probability
                        prob.add(start.get(1));
                        prob.add(start.get(1));
                        prob.add(start.get(1));

                    }else if(start.size()<4) {
                        // 1st position player has 0.4 probability
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        // 2nd position player has 0.3 probability
                        prob.add(start.get(1));
                        prob.add(start.get(1));
                        prob.add(start.get(1));
                        // 3rd position player has 0.1 probability
                        prob.add(start.get(2));

                    }else if(start.size()<5) {
                        // 1st position player has 0.4 probability
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        // 2nd position player has 0.3 probability
                        prob.add(start.get(1));
                        prob.add(start.get(1));
                        prob.add(start.get(1));
                        // 3rd position player has 0.1 probability
                        prob.add(start.get(2));
                        // 4th position player has 0.1 probability
                        prob.add(start.get(3));
                    }else if(start.size()>5 && start.size()<10 ){
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        prob.add(start.get(0));
                        // 2nd position player has 0.3 probability
                        prob.add(start.get(1));
                        prob.add(start.get(1));
                        prob.add(start.get(1));
                        // 3rd position player has 0.1 probability
                        prob.add(start.get(2));
                        // 4th position player has 0.1 probability
                        prob.add(start.get(3));
                        //5th - 9th has 0.02 probability
                        Random ran = new Random();
                        int index = ran.nextInt(5);
                        // Adding a random player who is in 5th to 9th position
                        prob.add(start.get(index+4));
                    }

                    // Shuffling the probability array to get the first position
                    Set<String> set = new LinkedHashSet<>();

                    // Setting the first place to position probability
                    set.add(prob.get(0));

                    //again shuffle for get the other positions
                    Collections.shuffle(start);

                    set.addAll(start);

                    //all the places adding to a new arraylist
                    ArrayList<String> last = new ArrayList<String>(set);


                    //writing the details of the new array into a txt file
                    try {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        FileWriter myWriter = new FileWriter("Probability_GUI.txt");
                        myWriter.write(dtf.format(now)+",");
                        for (int i = 0; i < set.size() ; i++) {
                            myWriter.write(last.get(i)+ ",");
                        }
                        myWriter.close();
                    } catch (IOException f) {

                        f.printStackTrace();
                    }

                    //reading the file and adding the detail to summary table
                    String filePath2 = "Probability_GUI.txt";
                    File file2 = new File(filePath2);
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file2));
                        Object[] tableLines = br.lines().toArray();
                        for (int i = 0; i < tableLines.length; i++) {
                            table3.setAutoCreateRowSorter(true);
                            String line = tableLines[i].toString();
                            String[] dataRow3 = line.split(",");
                            model3.addRow(dataRow3);
                        }

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();

                    }
                    //updating the driver details table
                    for (int i = 0; i < last.size(); i++) {
                        model2.setValueAt(last.get(i), i, 1);

                        if (model2.getValueAt(i, 0).equals(1)) {

                            total = 25 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                            position = 1 + Integer.parseInt(table.getValueAt(i, 5).toString());
                            model.setValueAt(position, i, 5);


                        } else if (model2.getValueAt(i, 0).equals(2)) {

                            total = 18 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);


                        } else if (model2.getValueAt(i, 0).equals(3)) {

                            total = 15 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);


                        } else if (model2.getValueAt(i, 0).equals(4)) {

                            total = 12 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(5)) {

                            total = 10 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(6)) {

                            total = 8 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(7)) {

                            total = 6 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(8)) {

                            total = 4 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(9)) {

                            total = 2 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        } else if (model2.getValueAt(i, 0).equals(10)) {

                            total = 1 + Integer.parseInt(table.getValueAt(i, 4).toString());
                            model.setValueAt(total, i, 4);

                        }

                        int racers = 1 + Integer.parseInt(table.getValueAt(i, 3).toString());
                        model.setValueAt(racers, i, 3);


                    }



                }
            }
        });



        // Race Summary

        //Label
        JLabel label3 = new JLabel();
        label3.setText("Summary");
        label3.setForeground(Color.red);
        label3.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 30));
        table3.setModel(model3);
        JPanel panel3 = new JPanel();
        panel3.add(label3);
        panel3.setBounds(1000, 25, 500, 1000);
        panel3.add(label3);
        frame.add(panel3);
        panel3.add(new JScrollPane(table3));
        table3.setFont(new Font("Arial", Font.BOLD, 15));
        table3.setRowHeight(30);
        table3.setSize(1500,500);
        table3.setBackground(Color.white);
        table3.setForeground(Color.BLACK);
        table3.setGridColor(Color.BLACK);
        table3.setModel(model3);
        JButton summary = new JButton();
        frame.setLayout(new FlowLayout());
        summary.setFocusable(false);
        table.setDefaultRenderer(table.getColumnClass(5), renderer);
        summary.setBounds(50, 1000, 150, 25);
        summary.setText("Sort");
        summary.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
        summary.setBackground(Color.BLUE);
        summary.setForeground(Color.WHITE);
        frame.getContentPane().add(summary);
        summary.setLocation(1200, 525);
        //ActionListener for the Row sorter
        summary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == summary) {
                    table3.setAutoCreateRowSorter(true);
                    DefaultRowSorter sorter = ((DefaultRowSorter) table3.getRowSorter());
                    ArrayList list = new ArrayList<>();
                    list.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
                    sorter.setSortKeys(list);
                    sorter.sort();

                }
            }

        });

        //adding the table heading for the table 3
        model3.addColumn("Date");
        for(int i=0;i<table2.getRowCount();i++){
            model3.addColumn((i+1)+" Place");
        }
        //reading the file
        String filePath2 = "RaceSummary.txt";
        File file2 = new File(filePath2);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file2));
            Object[] tableLines = br.lines().toArray();
            for (int i = 0; i < tableLines.length; i++) {
                table3.setAutoCreateRowSorter(true);
                String line = tableLines[i].toString();
                String[] dataRow2 = line.split(",");
                model3.addRow(dataRow2);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        }


        //Search Bar

        //Text
        JTextField txt=new JTextField(20);
        //Table
        JTable table4=new JTable();
        JPanel panel4 = new JPanel();
        panel4.setBounds(500, 600, 500, 1000);
        frame.add(panel4);
        panel4.add(new JScrollPane(table4));
        Object[] columns4 = {"Date","Name","Place"};
        table4.setModel(model4);
        model4.setColumnIdentifiers(columns4);
        table4.setFont(new Font("Arial", Font.BOLD, 15));
        table4.setRowHeight(30);
        table4.setBackground(Color.white);
        table4.setForeground(Color.BLACK);
        table4.setGridColor(Color.BLACK);
        frame.getContentPane().add(txt);
        txt.setBounds(500, 350, 250, 25);
        txt.setLocation(250, 800);
        JButton search = new JButton();
        frame.setLayout(new FlowLayout());
        table.setDefaultRenderer(table.getColumnClass(5), renderer);
        search.setFocusable(false);
        search.setBounds(3000, 350, 150, 25);
        search.setText("Search");
        search.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
        search.setBackground(Color.RED);
        search.setForeground(Color.WHITE);
        frame.getContentPane().add(search);
        search.setLocation(300, 850);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txt.getText();
                for (int i = 0; i < table3.getRowCount(); i++) {
                    ArrayList list = new ArrayList();

                    for(int j=0;j<table3.getColumnCount()-1;j++){
                        list.add(table3.getValueAt(i, j+1));
                    }
                    if (list.contains(name)) {
                        model4.addRow(new Object[]{model3.getValueAt(i, 0), name,list.indexOf(name)+1});

                    }
                }
            }

        });
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI_Table();



    }


}




