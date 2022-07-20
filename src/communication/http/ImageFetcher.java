/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.http;

import annotations.Injectable;
import core.communication.CommunicationException;
import core.communication.http.ImageFetcherInterface;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Manel
 */
@Injectable
public class ImageFetcher implements ImageFetcherInterface {

    @Override
    public ImageIcon fetchImage(String imageUrl) throws CommunicationException {
        try {
            URL url = new URL(imageUrl);

            return new ImageIcon(ImageIO.read(url));
        } catch (MalformedURLException ex) {
            throw buildException(imageUrl, ex);
        } catch (IOException ex) {
            throw buildException(imageUrl, ex);
        }
    }

    @Override
    public ImageIcon fetchImage(File f) throws CommunicationException {
        try {
            return new ImageIcon(ImageIO.read(f));
        } catch (IOException ex) {
            throw buildException(f.getName(), ex);
        }
    }

    private CommunicationException buildException(String imageUrl, Exception ex) {
        return new CommunicationException(imageUrl, ex);
    }
}
