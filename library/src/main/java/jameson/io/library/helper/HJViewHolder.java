package jameson.io.library.helper;

import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jameson on 9/18/15.
 */
public class HJViewHolder {

    private HJViewHolder() {
    }

    public static <T extends View> T get(View view, int id) {
        SparseArray viewHolder = (SparseArray)view.getTag();
        if(null == viewHolder) {
            viewHolder = new SparseArray();
            view.setTag(viewHolder);
        }

        View childView = (View)viewHolder.get(id);
        if(null == childView) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }

        return (T)childView;
    }

    public static TextView getTextView(View view, int id) {
        return get(view, id);
    }

    public static ImageView getImageView(View view, int id) {
        return get(view, id);
    }

    public static Button getButton(View view, int id) {
        return get(view, id);
    }

}
