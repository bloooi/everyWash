package ga.washmose.mose.seller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import ga.washmose.mose.ItemData;
import ga.washmose.mose.OrderData;
import ga.washmose.mose.OrderInfo;
import ga.washmose.mose.R;

/**
 * Created by leejaebeom on 2016. 10. 10..
 */

public class ManageProcessFragment extends Fragment {

    int page;
    String title;
    OrderData data;
    ManageProcessAdapter adapter;
    ArrayList<SellerManageItem> items = new ArrayList<>();
    public static ManageProcessFragment newInstance(int page, String title) {
        ManageProcessFragment fragment = new ManageProcessFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new OrderData();
        page = getArguments().getInt("page", 0);
        title = getArguments().getString("title", "세탁진행중");
        items.add(new SellerManageItem("10/03", "3일 남음", 1, "분당구 수내동 10-1 트라펠리스 910호"));
        items.add(new SellerManageItem("10/05", "5일 남음", 4, "분당구 정자동 아이파크 A동 1240호"));
        adapter = new ManageProcessAdapter(items, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage, container, false);
        ListView listView = (ListView) view.findViewById(R.id.manage_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data.code = 1111;
                data.progress = 2;
                Calendar collection = Calendar.getInstance();
                collection.set(Calendar.YEAR, 2016);
                collection.set(Calendar.MONTH, 11);
                collection.set(Calendar.DAY_OF_MONTH, 11);
                data.collectionDate = collection;
                data.completeDate = collection;
                data.address = items.get(position).address;

                ArrayList<ItemData> items = new ArrayList<ItemData>();
                items.add(new ItemData("Url","티셔츠", 3, 2000, "세탁 진행중.."));
                items.add(new ItemData("Url","남성 속옷 하의", 8, 1000, "세탁 안함"));
                data.items = items;
                Intent intent = new Intent(getContext(), SellerOrderManageActivity.class);
                intent.putExtra("orderData", data);
                startActivity(intent);
            }
        });
        return view;
    }

    public class ManageProcessAdapter extends BaseAdapter {

        ArrayList<SellerManageItem> items;
        Context context;
        public ManageProcessAdapter(ArrayList<SellerManageItem> items, Context context) {
            this.items = items;
            this.context = context;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_manage_process, parent, false);
                holder = new ViewHolder();
                holder.date = (TextView) convertView.findViewById(R.id.manage_process_date);
                holder.code= (TextView)convertView.findViewById(R.id.manage_process_code);
                holder.address = (TextView)convertView.findViewById(R.id.manage_process_address);
                holder.remainDate = (TextView)convertView.findViewById(R.id.manage_process_remain_date);

                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.date.setText(items.get(position).date);
            holder.code.setText(String.valueOf(items.get(position).code));
            holder.address.setText(items.get(position).address);
            holder.remainDate.setText(items.get(position).remainDate);

            return convertView;
        }

        class ViewHolder{
            TextView date, code, address, remainDate;
        }
    }
}
