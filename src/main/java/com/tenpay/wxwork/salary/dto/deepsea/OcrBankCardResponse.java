package com.tenpay.wxwork.salary.dto.deepsea;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OcrBankCardResponse extends DeepseaResponse {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("data: {%s}, %s", data, super.toString());
    }

    public String getCardNumber() {
        List<Data.Item> items = data.getItems();
        for (Data.Item item : items) {
            if (item.getItem().equals("卡号")) {
                return item.getItemstring();
            }
        }

        return "";
    }

    public static class Data {

        private List<Item> items;

        public List<Item> getItems()
        {
            return items;
        }

        public void setItems(List<Item> items)
        {
            this.items = items;
        }

        public static class Item {
            private String item;

            private String itemstring;

            @Override
            public String toString() {
                return String.format("item: %s, itemstring: %s", item, itemstring);
            }

            public String getItemstring()
            {
                return itemstring;
            }

            public void setItemstring(String itemstring)
            {
                this.itemstring = itemstring;
            }

            public String getItem()
            {
                return item;
            }

            public void setItem(String item)
            {
                this.item = item;
            }
        }
    }
}