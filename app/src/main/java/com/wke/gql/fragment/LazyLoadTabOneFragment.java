package com.wke.gql.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wke.gql.BaseActivity;
import com.wke.gql.BaseView;
import com.wke.gql.R;
import com.wke.gql.net.RxNetWorkUtil;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.RxCityService;

import java.util.List;

/**
 * Created by gql on 17-11-30.
 */

public class LazyLoadTabOneFragment extends LazyLoadFragment implements BaseView {
    private static final String TAG = "LazyLoadTabOneFragment";
    private RecyclerView rv;
    private LinearLayoutManager layoutManager;
    private List<City> cities;
    private LazyLoadTabOneFragment.TabOneAdapter adapter;

    @Override
    protected int setContentView() {
        return R.layout.fragment_tabone;
    }

    @Override
    protected void lazyLoad() {
        rv = (RecyclerView) getContentView().findViewById(R.id.fragment_tabone_rv);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                String a = " N/A";
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        a = "SCROLL_STATE_DRAGGING";
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        a = "SCROLL_STATE_IDLE";
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        a = "SCROLL_STATE_SETTLING";
                        break;
                    default:
                        break;
                }

//              1.静止 -> 被迫拖拽移动 -> 静止  2.静止 -> 被迫拖拽移动 -> 自己滚动 -> 静止
                /**
                 * //正在滚动
                 public static final int SCROLL_STATE_IDLE = 0;

                 //正在被外部拖拽,一般为用户正在用手指滚动
                 public static final int SCROLL_STATE_DRAGGING = 1;

                 //自动滚动开始
                 public static final int SCROLL_STATE_SETTLING = 2;
                 */
                //滑动状态的改变，DRAGGING -> SETTLING -> IDLE
                Log.i(TAG, "onScrollStateChanged: " + a);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {//
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    int changeCount = lastVisibleItemPosition - firstVisibleItemPosition + 1;
                    adapter.notifyItemRangeChanged(firstVisibleItemPosition, changeCount);
                    Log.i(TAG, firstVisibleItemPosition + "-" + lastVisibleItemPosition + ",.." + changeCount);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                dx : 水平滚动距离  dy : 垂直滚动距离
                boolean isScrollBottom = recyclerView.canScrollVertically(1); //false--意味着不能再往下了,已到底部，true表示还能继续往下滑动
                boolean isScrollTop = recyclerView.canScrollVertically(-1);//false--意味着不能再往上了,已到顶部，true表示还能继续往下滑动
                Log.i(TAG, "onScrolled: " + (isScrollBottom ? "isScrollBottom" : " ") + (isScrollTop ? "isScrollTop" : " "));
            }
        });
        layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        ((BaseActivity) getActivity()).rxNetWorkUtil.detchToView(this).useDefaultLoading(false).callBack(new RxNetWorkUtil.RxCallBack<List<City>>() {
            @Override
            public void onPrepare() {

            }

            @Override
            public void onSuccess(List<City> cities) {
                rv.setAdapter(adapter = new LazyLoadTabOneFragment.TabOneAdapter(cities));
            }

            @Override
            public void onFail(Throwable t) {

            }
        }).doWork(((BaseActivity) getActivity()).rxNetWorkUtil.initRetrofitService(RxCityService.class).getAllCity("china"));
    }

    @Override
    public void showLoading() {
        Toast.makeText(getActivity(), "show loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        Toast.makeText(getActivity(), "dismiss Loading", Toast.LENGTH_SHORT).show();
    }


    class TabOneAdapter extends RecyclerView.Adapter<TabOneViewHolder> {

        private List<City> cities;

        public TabOneAdapter(List<City> cities) {
            this.cities = cities;
        }

        @Override
        public TabOneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i(TAG, "onCreateViewHolder: " + viewType);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
            return new TabOneViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TabOneViewHolder holder, int position) {
            Log.i(TAG, "onBindViewHolder: " + position);
            holder.tv.setText((position + 1) + ":" + cities.get(position).name);
        }

        @Override
        public int getItemCount() {
            return cities.size();
        }
    }

    class TabOneViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public TabOneViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_city_name);
        }
    }
}
