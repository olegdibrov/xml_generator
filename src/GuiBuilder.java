import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GuiBuilder {
    private JTextArea id;
    private JTextArea price;
    private JComboBox categoryIdList;
    private JTextArea categoryId;
    private JTextArea vendor;
    private JTextArea name;
    private JTextArea description;
    private JFrame frame;

    private ArrayList<JTextArea> nameOfTag;
    private ArrayList<JTextArea> valueOfTag;
    private ArrayList<Offer> offers;


    private static final int NUM_OF_PARAMS = 15;
    private String[] categoryIdStrings = { "Фотоаппараты", "Светофильтры, бленды и крышки для объективов",
    "GPS навигаторы", "Объективы", "Штативы", "Студийные вспышки, осветители и аксессуары", "Отражатели",
    "Радиосинхронизаторы и ДУ", "Рации", "Другое"};
    JLabel result;

    public void buildGui(){
        offers = new ArrayList<>();
        frame = new JFrame("ROZETKA XML");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
       mainPanel.setBackground(Color.lightGray);
       mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel idPanel = new JPanel();
        idPanel.setLayout( new BoxLayout(idPanel, BoxLayout.X_AXIS));
       // idPanel.setBackground(Color.lightGray);
        JPanel pricePanel = new JPanel();
      //  pricePanel.setBackground(Color.lightGray);
        pricePanel.setLayout( new BoxLayout(pricePanel, BoxLayout.X_AXIS));
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout( new BoxLayout(categoryPanel, BoxLayout.X_AXIS));
      //  categoryPanel.setBackground(Color.lightGray);
        JPanel vendorPanel = new JPanel();
        vendorPanel.setLayout( new BoxLayout(vendorPanel, BoxLayout.X_AXIS));
      //  vendorPanel.setBackground(Color.lightGray);
        JPanel namePanel = new JPanel();
        namePanel.setLayout( new BoxLayout(namePanel, BoxLayout.X_AXIS));
      //  namePanel.setBackground(Color.lightGray);
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout( new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
     //   descriptionPanel.setBackground(Color.lightGray);
        JPanel paramPanel = new JPanel();
        paramPanel.setLayout( new BoxLayout(paramPanel, BoxLayout.Y_AXIS));
    //    paramPanel.setBackground(Color.lightGray);

        nameOfTag = new ArrayList<>();
        valueOfTag = new ArrayList<>();
        for(int i = 0; i < NUM_OF_PARAMS; i++){
            JPanel innerPanel = new JPanel();

            JLabel nameOfTagLabel = new JLabel("Имя тэга: ");
            JLabel valueOfTagLabel = new JLabel("Значение тэга: ");
            JSeparator separator = new JSeparator();
            innerPanel.setLayout( new BoxLayout(innerPanel, BoxLayout.X_AXIS));
            JTextArea textAreaName = new JTextArea(2,10);
            textAreaName.setWrapStyleWord(true);
            textAreaName.setLineWrap(true);
            JTextArea textAreaValue = new JTextArea(2,10);
            textAreaValue.setWrapStyleWord(true);
            textAreaValue.setLineWrap(true);
           nameOfTag.add(textAreaName);
           valueOfTag.add(textAreaValue);
          //  innerPanel.setLayout(new BorderLayout(10,10));
           innerPanel.add(nameOfTagLabel);
           innerPanel.add(nameOfTag.get(i));
           innerPanel.add(valueOfTagLabel);
           innerPanel.add(valueOfTag.get(i));

           paramPanel.add(innerPanel);
            paramPanel.add(separator);
        }

        JPanel innerCategoryPanel = new JPanel();
        innerCategoryPanel.setLayout( new BoxLayout(innerCategoryPanel, BoxLayout.Y_AXIS));


        id = new JTextArea(1,20);
        price = new JTextArea(1,20);
        categoryIdList = new JComboBox(categoryIdStrings);
        categoryId = new JTextArea(1,20);
        vendor = new JTextArea(1,20);
        name = new JTextArea(1,20);
        description = new JTextArea(10,30);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        JScrollPane dScroller = new JScrollPane(description);
        dScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        dScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JLabel idLabel = new JLabel("id товара: ");
        JLabel priceLabel = new JLabel("Цена товара: ");
        JLabel categoryIdLabel = new JLabel("Категория(id): ");
        JLabel vendorLabel = new JLabel("Производитель: ");
        JLabel nameLabel = new JLabel("Имя: ");
        JLabel descriptionLabel = new JLabel("Информация о товаре: ");

        idPanel.add(idLabel);
        idPanel.add(id);

        pricePanel.add(priceLabel);
        pricePanel.add(price);

        categoryPanel.add(categoryIdLabel);
        innerCategoryPanel.add(categoryIdList);
        innerCategoryPanel.add(categoryId);
        categoryPanel.add(innerCategoryPanel);

        vendorPanel.add(vendorLabel);
        vendorPanel.add(vendor);

        namePanel.add(nameLabel);
        namePanel.add(name);

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(dScroller);

        JButton saveButton = new JButton("Сохранить товар");
        saveButton.addActionListener( new SaveButtonListener());
        JButton clearButton = new JButton("Очистить поля");
        clearButton.addActionListener(new ClearButtonListener());
        JPanel panelForButtons = new JPanel();
        panelForButtons.setLayout(new BoxLayout(panelForButtons, BoxLayout.X_AXIS));
        panelForButtons.add(clearButton);
        panelForButtons.add(saveButton);
        JPanel panel = new JPanel();
      //  panel.setBackground(Color.darkGray);
        JPanel panel1 = new JPanel();
     //   panel1.setBackground(Color.darkGray);
        JPanel panel2 = new JPanel();
     //   panel2.setBackground(Color.darkGray);
        JPanel panel3 = new JPanel();
      //  panel3.setBackground(Color.darkGray);
        JPanel panel4 = new JPanel();
      //  panel4.setBackground(Color.darkGray);
        //JTextArea textArea = new JTextArea(1,20);
    //    panel.add(textArea);

        result = new JLabel("...");
        mainPanel.add(idPanel);
        mainPanel.add(panel);
        mainPanel.add(pricePanel);
        mainPanel.add(panel1);
        mainPanel.add(categoryPanel);
        mainPanel.add(panel2);
        mainPanel.add(vendorPanel);
        mainPanel.add(panel3);
        mainPanel.add(namePanel);
        mainPanel.add(panel4);
        mainPanel.add(descriptionPanel);
        //mainPanel.add(panel5);
        mainPanel.add(paramPanel);
        mainPanel.add(result);
        mainPanel.add(panelForButtons);


        JScrollPane mScroller = new JScrollPane(mainPanel);
        mScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        frame.getContentPane().add(mScroller);
        frame.setSize(700,935);
        frame.setVisible(true);
    }


    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> param = new ArrayList<>();
            ArrayList<String> valueOfParam = new ArrayList<>();
            JTextArea temp = new JTextArea();
            for (int i = 0; i < NUM_OF_PARAMS; i++){
                if(!nameOfTag.get(i).getText().equals(temp.getText()) && !valueOfTag.get(i).getText().equals(temp.getText())){
                    System.out.println(nameOfTag.get(i).getText());
                    param.add(nameOfTag.get(i).getText());
                    valueOfParam.add(valueOfTag.get(i).getText());
                }
               // else{
               //     param.remove(i);
               //     valueOfParam.remove(i);
              //  };
            }

            int categoryIdFin = 0;
            switch (String.valueOf(categoryIdList.getSelectedItem())){
                case "Фотоаппараты":
                    categoryIdFin = 80001;
                    break;
                case "Светофильтры, бленды и крышки для объективов":
                    categoryIdFin = 80103;
                    break;
                case "GPS навигаторы":
                    categoryIdFin = 80047;
                    break;
                case "Объективы":
                    categoryIdFin = 80060;
                    break;
                case "Штативы":
                    categoryIdFin = 80075;
                    break;
                case "Студийные вспышки, осветители и аксессуары":
                    categoryIdFin = 156839;
                    break;
                case "Отражатели":
                    categoryIdFin = 157234;
                    break;
                case "Радиосинхронизаторы и ДУ":
                    categoryIdFin = 162184;
                    break;
                case "Рации":
                    categoryIdFin = 80005;
                    break;
                case "Другое":
                    categoryIdFin = Integer.parseInt(categoryId.getText());
                }
            try {
                Offer offer = new Offer(Integer.parseInt(id.getText()), Integer.parseInt(price.getText()), categoryIdFin, vendor.getText(), name.getText(), description.getText(), param, valueOfParam);
                offers.add(offer);
                newOffer(offer);
                result.setText("Сохранено!");
            } catch (NumberFormatException ex){
                result.setText("ОШИБКА! НЕ СОХРАНЕНО");
            }

        }
    }


    public void newOffer(Offer offer) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("newStereostore.xml");

            // Вызываем метод для добавления новой книги

            addNewOffer(document, offer);


        } catch (ParserConfigurationException ex) {
            result.setText("ОШИБКА! НЕ СОХРАНЕНО");
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            result.setText("ОШИБКА! НЕ СОХРАНЕНО");
            ex.printStackTrace(System.out);

        } catch (IOException ex) {
            result.setText("ОШИБКА! НЕ СОХРАНЕНО");
            ex.printStackTrace(System.out);

        }
    }

    // Функция добавления новой книги и записи результата в файл
    private static void addNewOffer(Document document, Offer newOffer) throws TransformerFactoryConfigurationError, DOMException {
        // Получаем корневой элемент
        Node root = document.getDocumentElement();

        // Создаем новую offer по элементам
        // <offer>
        Element offer = document.createElement("offer");
        offer.setAttribute("available", "true");
        offer.setAttribute("id", String.valueOf(newOffer.id));
        // <price>
        Element price = document.createElement("price");
        // Устанавливаем значение текста внутри тега
        price.setTextContent(String.valueOf(newOffer.price));
        // <currencyId>
        Element currencyId = document.createElement("currencyId");
        currencyId.setTextContent(newOffer.CURRENCY_ID);
        // <categoryId>
        Element categoryId = document.createElement("categoryId");
        categoryId.setTextContent(String.valueOf(newOffer.categoryId));
        // <vendor>
        Element vendor = document.createElement("vendor");
        vendor.setTextContent(newOffer.vendor);
        // <name>
        Element name = document.createElement("name");
        name.setTextContent(newOffer.name);
        // <description>
        Element description = document.createElement("description");
        description.setTextContent(newOffer.description);

        offer.appendChild(price);
        offer.appendChild(currencyId);
        offer.appendChild(categoryId);
        offer.appendChild(vendor);
        offer.appendChild(name);
        offer.appendChild(description);

        for (int i = 0; i < newOffer.params.size(); i++){
            Element param = document.createElement("param");
            param.setAttribute("name", newOffer.params.get(i).param);
            param.setTextContent(newOffer.params.get(i).valueOfParam);
            offer.appendChild(param);
        }

        // Добавляем внутренние элементы книги в элемент <Book>


        // Добавляем книгу в корневой элемент
        root.appendChild(offer);

        // Записываем XML в файл
        writeDocument(document, root);
    }

    // Функция для сохранения DOM в файл
    private static void writeDocument(Document document, Node root) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("newStereostore.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);

        }
    }

    public void clearFields(){
        id.setText("");
        price.setText("");
        categoryId.setText("");
        vendor.setText("");
        name.setText("");
        description.setText("");
        for (JTextArea temp: nameOfTag) {
            temp.setText("");
        }
        for (JTextArea temp: valueOfTag) {
            temp.setText("");
        }
        result.setText("...");

    }


    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearFields();
        }
    }
}
