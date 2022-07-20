/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.ui;

import annotations.Injectable;
import barcoder.ui.common.Styles;
import barcoder.ui.extensions.ComponentResizer;
import barcoder.ui.factory.ControlPanelFactory;
import barcoder.utilities.DrawingUtils;
import barcoder.utilities.ImageUtils;
import core.Barcode;
import core.BarcodeContainerInterface;
import core.BarcodeMeasurementsHelperInterface;
import core.MasterSlaveLink;
import core.ui.DrawableBarcode;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Manel
 */
@Injectable(ResolveWithNewInstance = true)
public class BarcodeContainer extends JFrame implements BarcodeContainerInterface {

    private final List<DrawableBarcode> barcodes;
    private final BarcodeContainerPainter painter;
    private final ControlPanelInterface controlPanel;
    private final ImageUtils imageUtils;
    private final BarcodeMeasurementsHelperInterface barcodeMeasurementsHelper;
    private final Styles styles;
    private MasterSlaveLink master;

    private DrawableBarcode highlightedBarcode;
    private final int controlPanelHeight = 100;
    private final Dimension initialContainerSize = new Dimension(400, 600);
    private boolean isHighlightingEnabled = true;
    private boolean isDeleteEnabled = false;
    private static final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);
    private static final Cursor CURSOR_DELETE = new Cursor(Cursor.HAND_CURSOR);
    private final DrawingUtils drawingUtils;

    public BarcodeContainer(Styles styles, ControlPanelFactory controlPanelFactory, BarcodeMeasurementsHelperInterface barcodeMeasurementsHelper, ImageUtils imageUtils, DrawingUtils drawingUtils) {
        this.barcodes = new ArrayList();
        this.styles = styles;
        this.barcodeMeasurementsHelper = barcodeMeasurementsHelper;
        this.imageUtils = imageUtils;

        painter = new BarcodeContainerPainter();
        new ComponentResizer().registerComponent(this);

        setSize(initialContainerSize);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        painter.setLocation(0, 0);
        setPainterSize();

        controlPanel = controlPanelFactory.getNewInstance();
        controlPanel.bindToContainer(this);
        controlPanel.setHighlightToggle(isHighlightingEnabled);

        add(painter);
        add((Component) controlPanel);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                setPainterSize();
                autoAlignBarcodes();
                positionAndResizeControlPanel();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                for (DrawableBarcode drawableBarcode : getBarcodes()) {
                    boolean isCurrentlyHovered = drawableBarcode.isPointHoveringBarcode(me.getPoint());

                    if (isCurrentlyHovered) {
                        deleteBarcode(drawableBarcode);
                        break;
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                handleMouseMotion(me);
            }

            @Override
            public void mouseDragged(MouseEvent me) {
                handleMouseMotion(me);
            }

            private void handleMouseMotion(MouseEvent me) {
                for (DrawableBarcode drawableBarcode : getBarcodes()) {
                    boolean isCurrentlyHovered = drawableBarcode.isPointHoveringBarcode(me.getPoint());
                    boolean isInsidePainter = me.getY() > 0 && me.getY() < painter.getHeight();

                    if (isCurrentlyHovered && !drawableBarcode.wasHovered() && isInsidePainter) {
                        handleMouseEntered(drawableBarcode);
                    } else if (!isCurrentlyHovered && drawableBarcode.wasHovered()) {
                        handleMouseExited(drawableBarcode);
                    }
                }
            }

            private void handleMouseExited(DrawableBarcode drawableBarcode) {
                drawableBarcode.setIsHovered(false);
                highlightedBarcode = null;
                repaint();
                if (isDeleteEnabled) {
                    painter.setCursor(CURSOR_DEFAULT);
                }
            }

            private void handleMouseEntered(DrawableBarcode drawableBarcode) {
                drawableBarcode.setIsHovered(true);
                if (isHighlightingEnabled) {
                    highlightBarcode(drawableBarcode);
                }
                if (isDeleteEnabled) {
                    painter.setCursor(CURSOR_DELETE);
                }
            }
        });

        positionAndResizeControlPanel();
        this.drawingUtils = drawingUtils;
    }

    @Override
    public List<DrawableBarcode> getBarcodes() {
        return barcodes;
    }

    @Override
    public void addBarcode(DrawableBarcode barcode) {
        barcodes.add(barcode);
        autoAlignBarcodes();
    }

    @Override
    public void addBarcode(Barcode barcode) {
        addBarcode(new DrawableBarcode(barcode));
    }

    @Override
    public void setDeleteEnabled(boolean isEnabled) {
        isDeleteEnabled = isEnabled;
        highlightedBarcode = null;
        painter.setCursor(CURSOR_DEFAULT);
        for (DrawableBarcode drawableBarcode : getBarcodes()) {
            drawableBarcode.setIsHovered(false);
        }
        repaint();

    }

    @Override
    public void setHighlightEnabled(boolean isEnabled) {
        isHighlightingEnabled = isEnabled;
        repaint();
    }

    @Override
    public void deleteBarcode(DrawableBarcode barcode) {
        barcodes.remove(barcode);
        autoAlignBarcodes();
        repaint();
    }

    @Override
    public void registerMaster(MasterSlaveLink master) {
        this.master = master;
    }

    @Override
    public void deleteContainer() {
        master.deleteContainer(this);
    }

    private void highlightBarcode(DrawableBarcode barcode) {
        highlightedBarcode = barcode;
        repaint();
    }

    private void autoAlignBarcodes() {
        double nx = AutoAlignParameters.startX, ny = AutoAlignParameters.staryY;

        for (int i = 0; i < barcodes.size(); i++) {
            DrawableBarcode drawableBarcode = barcodes.get(i);
            drawableBarcode.setLocation(nx, ny);

            nx += drawableBarcode.getImageRepresentation().getIconWidth() + AutoAlignParameters.spacingX;
            if (i < barcodes.size() - 1) {
                DrawableBarcode nextBarcode = barcodes.get(i + 1);
                if (nx + nextBarcode.getImageRepresentation().getIconWidth() + AutoAlignParameters.spacingX >= painter.getWidth()) {
                    ny += drawableBarcode.getImageRepresentation().getIconHeight() + AutoAlignParameters.spacingY;
                    nx = AutoAlignParameters.startX;
                }
            }
        }

        repaint();
    }

    private class BarcodeContainerPainter extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(styles.background);
            g2d.fillRect(0, 0, painter.getWidth(), painter.getHeight());

            for (DrawableBarcode drawableBarcode : barcodes) {
                if (!isHighlightingEnabled || highlightedBarcode == null || highlightedBarcode == drawableBarcode) {
                    Image image = drawableBarcode.getImageRepresentation().getImage();
                    image = imageUtils.getCroppedImage(image, drawableBarcode.getImageRepresentation().getIconWidth(), barcodeMeasurementsHelper.getActualBarcodeHeight());
                    g2d.drawImage(
                            image,
                            (int) drawableBarcode.getLocation().getX(),
                            (int) drawableBarcode.getLocation().getY(),
                            this);
                }

                Rectangle barcodeTextBox = new Rectangle((int) (drawableBarcode.getLocation().getX()), (int) (drawableBarcode.getLocation().getY() + barcodeMeasurementsHelper.getActualBarcodeHeight()), (int) drawableBarcode.getImageRepresentation().getIconWidth(), (int) drawableBarcode.getImageRepresentation().getIconHeight() - barcodeMeasurementsHelper.getActualBarcodeHeight());
                g2d.setColor(styles.barcodeBackground);
                g2d.fillRect((int) barcodeTextBox.getX(), (int) barcodeTextBox.getY(), (int) barcodeTextBox.getWidth(), (int) barcodeTextBox.getHeight());
                drawingUtils.drawCenteredString(g, drawableBarcode.getTextRepresentation(), barcodeTextBox, styles.textFont);
            }
        }
    }

    private void setPainterSize() {
        painter.setSize(getWidth(), getHeight() - controlPanelHeight);
    }

    private static class AutoAlignParameters {

        static int startX = 20, staryY = 20, spacingX = 20, spacingY = 20;
    }

    private void positionAndResizeControlPanel() {
        controlPanel.setLocation(0, getHeight() - controlPanelHeight);
        controlPanel.setSize(getWidth(), controlPanelHeight);
    }
}
