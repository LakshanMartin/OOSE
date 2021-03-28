import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents the main user interface for the Image Viewer application.
 */
public class MainWindow
{
    private Album album;
    private Stage stage;
    
    private BorderPane mainBox = new BorderPane();
    private ImageView imageWidget = new ImageView();
    private Label captionWidget = new Label();
    private ListIterator<ImageRecord> iter;
    
    public MainWindow(Album album, Stage stage)
    {
        this.album = album;
        this.stage = stage;
    }
    
    public File chooseAlbumFile()
    {
        FileChooser dialog = new FileChooser();
        dialog.setTitle("Select Album File");
        dialog.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        
        File currentDir = new File(System.getProperty("user.dir"));
        File resourcesDir = new File(currentDir, "resources");        
        if(resourcesDir.isDirectory())
        {
            dialog.setInitialDirectory(resourcesDir);
        }
        else
        {
            dialog.setInitialDirectory(currentDir);
        }
        return dialog.showOpenDialog(stage);
    }
    
    /**
     * Builds the main UI, based on an Album and a JavaFX 'Stage' (which is basically a 
     * pre-existing window).
     */
    public void show()
    {
        Platform.setImplicitExit(true);
        stage.setTitle("JavaFX Image Viewer");
        
        // *** Fix this code so that it loads the initial (first) image. ***
        List<ImageRecord> collection = album.getAlbum();
        iter = collection.listIterator();
        ImageRecord firstRecord = iter.next();
        
        String url = new File(firstRecord.getFilename()).toURI().toString();
        imageWidget.setImage(new Image(url));
        
        // Add 'mainBox' to the window. This is a container for holding the other bits: the toolbar, 
        // scroller (containing the image), and caption.
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        
        Button prevBtn = new Button("Previous");
        Button nextBtn = new Button("Next");
        ToolBar toolBar = new ToolBar(prevBtn, nextBtn);
        mainBox.setTop(toolBar);
        
        // Set up nextBtnHandler() to be called when nextBtn is clicked, and similarly for prevBtn.
        // (This uses Java 8's 'method reference' feature.)
        prevBtn.setOnAction(this::prevBtnHandler);
        nextBtn.setOnAction(this::nextBtnHandler);
        
        ScrollPane scroller = new ScrollPane();
        scroller.setContent(imageWidget);
        mainBox.setCenter(scroller);
        
        // *** Fix this code so that it displays the caption for the first image. ***
        captionWidget.setText(firstRecord.getCaption());
        mainBox.setBottom(captionWidget);
        
        stage.sizeToScene();
        stage.show();
    }
    
    /**
    * Retrieves the album.
    */
    public Album getAlbum()
    {
        return album;
    }

    /**
     * Called to handle "previous" button clicks.
     */
    private void prevBtnHandler(ActionEvent event)
    {
        // *** Fix this code so that it actually displays the previous image & caption. ***

        if(iter.hasPrevious())
        {
            iter.previous();

            if(iter.hasPrevious())
            {
                ImageRecord prev = iter.previous();

                String url = new File(prev.getFilename()).toURI().toString();
                imageWidget.setImage(new Image(url));
                captionWidget.setText(prev.getCaption());
            }
            
            iter.next();
        }
    }

    /**
     * Called to handle "next" button clicks.
     */
    private void nextBtnHandler(ActionEvent event)
    {
        // *** Fix this code so that it actually displays the next image & caption. ***
    
        if(iter.hasNext())
        {
            ImageRecord next = iter.next();

            String url = new File(next.getFilename()).toURI().toString();
            imageWidget.setImage(new Image(url));
            captionWidget.setText(next.getCaption());
        }        
    }
}
