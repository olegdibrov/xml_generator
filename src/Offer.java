import java.util.ArrayList;

public class Offer {
     int id;
     int price;
     final static String CURRENCY_ID = "UAH";
     int categoryId;
     String vendor;
     String name;
     String description;
     ArrayList<Param> params = new ArrayList<>();

    public Offer(int id, int price, int categoryId, String vendor, String name, String description, ArrayList<String> param,
                 ArrayList<String> valueOfParam){
        this.id = id;
        this.price = price;
        this.categoryId = categoryId;
        this.vendor = vendor;
        this.name = name;
        this.description = description;
        for (int i = 0; i < param.size(); i++) {
            params.add(new Param(param.get(i), valueOfParam.get(i)));
        }

    }
}
