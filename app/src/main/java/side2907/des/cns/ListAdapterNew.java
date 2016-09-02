package side2907.des.cns;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Demo on 21.08.2016.
 */
public class ListAdapterNew extends BaseAdapter {

    private List<CardBlank> cards;

    public ListAdapterNew(List<CardBlank> cards) {
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int i) {
        return cards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) view = View.inflate(viewGroup.getContext(), R.layout.layout_items_list, null);

        TextView name = (TextView) view.findViewById(R.id.mainText);
        name.setText(cards.get(i).getName());

        TextView time = (TextView) view.findViewById(R.id.timeText);
        time.setText(cards.get(i).getDate()+" "+cards.get(i).getTime());

        ImageView image = (ImageView) view.findViewById(R.id.img);
        image.setImageResource(cards.get(i).getImage());

        return view;
    }
}
