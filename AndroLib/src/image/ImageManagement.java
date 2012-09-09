package image;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;

public class ImageManagement extends Component {

    private Image image;

    public ImageManagement() {
        image = null;
    }

    public ImageManagement(Image image) {
        this();
        setImage(image);
    }

    public void setImage(Image image) {

        this.image = image;

        if (image != null) {
            MediaTracker tracker;
            try {
                tracker = new MediaTracker(this);
                tracker.addImage(image, 0);
                tracker.waitForID(0);
            } catch (InterruptedException e) {
            }
        }
        repaint();

    }

    public Image getImage() {
        return image;
    }
}