package pt.pa.gui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pt.pa.model.Laptop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author amfs
 */
public class LaptopsGui extends BorderPane {

    private static final String DATA_PATH = "src/main/resources/laptop_reviews.json";

    private static final String BANNER_PATH = "src/main/resources/header_banner.png";

    List<Laptop> laptops;

    ListView<Laptop> listViewLaptops;

    private VBox mainContent;


    public LaptopsGui() throws Exception {
        try {
            this.laptops = loadData();
            initializeComponents();
        } catch (FileNotFoundException e) {
            throw new Exception("Unable to initialize Laptops GUI");
        }
    }

    public void initializeComponents() throws FileNotFoundException {
     this.mainContent = new VBox();
     VBox imageBox = new VBox();
     Label label = new Label("Laptop Information");
     this.mainContent.setSpacing(10);

     ImageView bannerrr = loadThumbnailImage();

     this.listViewLaptops = new ListView();
     this.listViewLaptops.getItems().addAll(laptops);
     this.setLeft(listViewLaptops);

     this.mainContent.getChildren().addAll(label);
     imageBox.getChildren().addAll(bannerrr);
     this.setTop(imageBox);
     this.setRight(mainContent);
     //this.mainContent.

    }



    /**
     * Load the data  contain on json file specified on DATA_PATH.
     * @return list of Lapstop contained on the file
     * @throws FileNotFoundException in case of the file not exists
     */
    private List<Laptop> loadData() throws FileNotFoundException {

        Gson gson = new Gson();

        Type arrayListType = new TypeToken<ArrayList<Laptop>>() {
        }.getType();

        return gson.fromJson(new FileReader(DATA_PATH), arrayListType);


    }

    /**
     * create an Image View from the image file specified on BANNER_PATH
     * @return the Image View created from the file specified on BANNER_PATH
     * @throws FileNotFoundException in case the file not exists
     */
    ImageView loadThumbnailImage() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(BANNER_PATH);
        Image image = new Image(inputStream);
        return new ImageView(image);
    }

}