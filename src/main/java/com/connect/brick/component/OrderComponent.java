package com.connect.brick.component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.connect.brick.model.DpMaterial;

public class OrderComponent {
	
	public static List<DpMaterial> orderBySalesPriceAsc(List<DpMaterial> results) {

		Collections.sort(results, new Comparator<DpMaterial>() {

			public int compare(DpMaterial o1, DpMaterial o2) {
				
				// compare two instance of `Score` and return `int` as result.\
				Integer orderByPrice1 = 0;
				if(o1.getMaterial().getMtSales().getSalesPrice()!=null && o1.getMaterial().getMtSales().getSalesPrice()!=0)
					orderByPrice1 = o1.getMaterial().getMtSales().getSalesPrice();
				else
					orderByPrice1 = o1.getMaterial().getMtSales().getConsumerPrice();
				
				Integer orderByPrice2 = 0;
				if(o2.getMaterial().getMtSales().getSalesPrice()!=null && o2.getMaterial().getMtSales().getSalesPrice()!=0)
					orderByPrice2 = o2.getMaterial().getMtSales().getSalesPrice();
				else
					orderByPrice2 = o2.getMaterial().getMtSales().getConsumerPrice();
				
				return orderByPrice1.compareTo(orderByPrice2);
			}
		});
		
		return results;
	}
	
	public static List<DpMaterial> orderBySalesPriceDesc(List<DpMaterial> results) {

		Collections.sort(results, new Comparator<DpMaterial>() {

			public int compare(DpMaterial o1, DpMaterial o2) {
				
				// compare two instance of `Score` and return `int` as result.\
				Integer orderByPrice1 = 0;
				if(o1.getMaterial().getMtSales().getSalesPrice()!=null && o1.getMaterial().getMtSales().getSalesPrice()!=0)
					orderByPrice1 = o1.getMaterial().getMtSales().getSalesPrice();
				else
					orderByPrice1 = o1.getMaterial().getMtSales().getConsumerPrice();
				
				Integer orderByPrice2 = 0;
				if(o2.getMaterial().getMtSales().getSalesPrice()!=null && o2.getMaterial().getMtSales().getSalesPrice()!=0)
					orderByPrice2 = o2.getMaterial().getMtSales().getSalesPrice();
				else
					orderByPrice2 = o2.getMaterial().getMtSales().getConsumerPrice();
				
				return orderByPrice2.compareTo(orderByPrice1);
			}
		});
		
		return results;
	}
	
}
