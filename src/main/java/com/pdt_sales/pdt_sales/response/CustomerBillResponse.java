package com.pdt_sales.pdt_sales.response;

import com.pdt_sales.pdt_sales.entity.Product;
import java.util.List;

public class CustomerBillResponse {
    
    private double totalBill;
    private List<Product> orderedProductKey;
    public double getTotalBill() {
        return totalBill;
    }
    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }
    public List<Product> getOrderedProductKey() {
        return orderedProductKey;
    }
    public void setOrderedProductKey(List<Product> orderedProductKey) {
        this.orderedProductKey = orderedProductKey;
    }
    public void setOrderedProductKeys(List<Long> orderedProductKeys) {
    }

    
}
