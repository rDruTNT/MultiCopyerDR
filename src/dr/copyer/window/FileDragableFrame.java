package dr.copyer.window;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;

public abstract class FileDragableFrame extends JFrame implements DropTargetListener {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	public FileDragableFrame() {
		setDropTarget(new DropTarget(super.getContentPane(),this));
	}
	
	public abstract void onDropped(File f);
	
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		try {
			Transferable tr = dtde.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				System.out.println("Possible flavor: " + flavors[i].getMimeType());
				if (flavors[i].isFlavorJavaFileListType()) {
					dtde.acceptDrop(DnDConstants.ACTION_COPY);
					//		          ta.setText("Successful file list drop.\n\n");

					java.util.List<DataFlavor> list = (List<DataFlavor>) tr.getTransferData(flavors[i]);
					for (int j = 0; j < list.size(); j++) {
						onDropped(new File(""+list.get(j)));
						System.out.println(list.get(j) + "\n");
					}
					dtde.dropComplete(true);
					return;
				}
			}
			System.out.println("Drop failed: " + dtde);
			dtde.rejectDrop();
		} catch (Exception e) {
			e.printStackTrace();
			dtde.rejectDrop();
		}
	}
	  
	
	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		System.out.println("ee");
	}
}
