package itemRender;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class ItemRenderer extends BasicComboBoxRenderer {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);
        if (value != null){
            Item item = (Item)value;
            setText( item.getName().toUpperCase() );
        }
        if (index == -1){
            Item item = (Item)value;
            setText( "" + item.getId() );
        }
        return this;
	}

}
