package me.jcala.xmarket.mvp.trade;

import android.content.Context;

import com.orhanobut.logger.Logger;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;

public class TradeDetailPresenterImpl implements TradeDetailPresenter,TradeDetailModel.onDetailListener {
    private Context context;
    private TradeDetailView view;
    private TradeDetailModel model;

    public TradeDetailPresenterImpl(Context context, TradeDetailView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeDetailModelImpl();
    }

    @Override
    public void onComplete(Result<Trade> result) {
        if (result==null){
            return;
        }

        switch (result.getCode()) {
            case 100:
                view.whenSuccess(result.getData());
                break;
            case 99:
                view.whenFail(result.getMsg());
                break;
            default:
        }
    }


    @Override
    public void loadData(String tradeId) {
        model.executeDetailReq(this,tradeId);
    }

}
