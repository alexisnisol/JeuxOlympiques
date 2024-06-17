package flags;

import java.io.FileReader;
import java.util.NoSuchElementException;

import javax.swing.text.html.ImageView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Exec {
    

    public static void main(String[] args) {

        String countryCode = getCountryCode("Portugal");
        String imageUrl = "https://flagcdn.com/64x48/" + countryCode + ".png";
        System.out.println(imageUrl);
        //Image image = new Image(imageUrl);
        //ImageView imageView = new ImageView(image);
        
    }

    public static String getCountryCode(String countryName) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("flags/codes.json"));
            for (Object key : jsonObject.keySet()) {
                String code = (String) key;
                String name = (String) jsonObject.get(code);
                if (name.equals(countryName)) {
                    return code;
                }
            }
            throw new NoSuchElementException(countryName + " not found (check the spelling and utf-8 encoding)");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
