package com.connect.brick.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.connect.brick.model.Estimate;
import com.connect.brick.model.EstimateDoc;
import com.connect.brick.model.EstimateDocItem;
import com.connect.brick.model.EstimateSub;
import com.connect.brick.model.Order;
import com.connect.brick.repository.EstimateRepository;
import com.connect.brick.repository.OrderRepository;

@Service
public class OrderService {

	@Value("${upload.image.locations.path}")
	private String uploadImageLocationPath;
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
    private EstimateRepository estimateRepository;
	
	@Transactional
	public List<Order> getOrderAll() {
		
		return orderRepository.findAllByOrderByOrderDateDesc();
	}
	
	@Transactional
	public Order getOrderByNo(Long no) {
		return orderRepository.findById(no).get();
	}
	
	@Transactional
	public Order _reg_Order(Order order) {
		
		LocalDateTime now = LocalDateTime.now();
		order.setOrderDate(now);
		order.getCustomer().setRegDate(now);
		
		return orderRepository.save(order);
	}
	
	@Transactional
	public Order _mod_Order(Order order) {
		
		Long yet = order.getNo();
		
		order.setEstimates( getOrderByNo(yet).getEstimates() );
		
		return orderRepository.save(order);
	}

	@Transactional
	public Order _reg_order_estimate(Order order, Estimate estimate) {
		
		EstimateDoc estimateDoc = estimate.getEstimateDoc();
		
		for (EstimateSub sub : estimate.getEstimateSubs()) {
			sub.setEstimate(estimate);
		}
		
		int totalEstimateCost = 0;
		
		for (EstimateDocItem item : estimate.getEstimateDoc().getItems()) {
			int cost = item.getItemAmtCost();
			totalEstimateCost += cost;
			item.setEstimateDoc(estimateDoc);
		}
		
		estimateDoc.setTotalEstimateCost(totalEstimateCost);
		
		LocalDateTime now = LocalDateTime.now();
		estimate.setEstimateDate(now);
		estimateDoc.setRegDate(now);
		
		Estimate result = estimateRepository.save(estimate);
		
		List<Estimate> ests = new ArrayList<>();
		if(order.getEstimates()!=null)
			for (Estimate est : order.getEstimates()) {
				ests.add(est);
			}
		
		ests.add(result);
		
		order.setEstimates(ests);
		
		return orderRepository.save(order);
	}
	
	@Transactional
	public Estimate _mod_order_estimate(Estimate estimate) {
		
		Order order = estimate.getOrder();
		EstimateDoc estimateDoc = estimate.getEstimateDoc();
		
		for (EstimateSub sub : estimate.getEstimateSubs()) {
			sub.setEstimate(estimate);
		}
		
		int totalEstimateCost = 0;
		
		for (EstimateDocItem item : estimate.getEstimateDoc().getItems()) {
			int cost = item.getItemAmtCost();
			totalEstimateCost += cost;
			item.setEstimateDoc(estimateDoc);
		}
		
		estimateDoc.setTotalEstimateCost(totalEstimateCost);
		
		LocalDateTime now = LocalDateTime.now();
		estimate.setEstimateDate(now);
		
		List<Estimate> ests = new ArrayList<>();
		if(order.getEstimates()!=null) {
			for (Estimate est : order.getEstimates()) {
				ests.add(est);
			}
		}
		
		order.setEstimates(ests);
		
		return estimateRepository.save(estimate);
	}
	
	@Transactional
	public Order _reg_order_estimate_form(Order order, List<Estimate> ests) {
		LocalDateTime now = LocalDateTime.now();

		order.setOrderDate(now);
		order.getCustomer().setRegDate(now);
		order.setEstimates(ests);

		return orderRepository.save(order);
	}
	
	@Transactional
	public Estimate _mod_estimate(Estimate est,int isElevator,String isDestroy) {
		LocalDateTime now = LocalDateTime.now();
		EstimateDoc estimateDoc = new EstimateDoc();
		estimateDoc.setEstimate(est);
		estimateDoc.setTotalEstimateCost(est.getTotalFinalCost());
		estimateDoc.setRegDate(now);
		estimateDoc.setMemo("");
		List<EstimateDocItem> items = new ArrayList<>();
		EstimateDocItem tileItem = new EstimateDocItem();
		tileItem.setEstimateDoc(estimateDoc);
		tileItem.setItemType("tile");
		tileItem.setItemName(est.getMtContents().getMaterial().getCbName());
		tileItem.setItemAmt(est.getTileQtt());
		tileItem.setItemCost(est.getEstTilePrice());
		tileItem.setItemAmtCost(est.getTotalTileCost());
		items.add(tileItem);
		
		for (EstimateSub esub : est.getEstimateSubs()) {
			String subName = esub.getSubMaterial().getSubName();
			int subBoxAmount = esub.getSubBoxAmount();
			int subBoxCost = esub.getSubBoxCost();
			int subBoxAmountCost = esub.getSubBoxAmountCost();
			EstimateDocItem subItem = new EstimateDocItem();
			subItem.setEstimateDoc(estimateDoc);
			subItem.setItemType("sub");
			subItem.setItemName(subName);
			subItem.setItemAmt(subBoxAmount);
			subItem.setItemCost(subBoxCost);
			subItem.setItemAmtCost(subBoxAmountCost);
			items.add(subItem);
		}
		
		int consCost = est.getConsCost() + est.getManageCost();
		int totalConsAllCost = est.getTotalConsAllCost();
		EstimateDocItem consItem = new EstimateDocItem();
		consItem.setEstimateDoc(estimateDoc);
		consItem.setItemType("cons");
		consItem.setItemName("시공비");
		consItem.setItemAmt(1);
		consItem.setItemCost(consCost);
		consItem.setItemAmtCost(totalConsAllCost);
		items.add(consItem);
		
		int transCost = est.getTransCost();
		int totalTransCost = est.getTotalTransCost();
		EstimateDocItem transItem = new EstimateDocItem();
		transItem.setEstimateDoc(estimateDoc);
		transItem.setItemType("trans");
		transItem.setItemName("운송비");
		transItem.setItemAmt(1);
		transItem.setItemCost(transCost);
		transItem.setItemAmtCost(totalTransCost);
		items.add(transItem);
		
		if(isDestroy.equals("와플에서철거예정")) {
			int totalDestroyCost = est.getTotalDestroyCost();
			EstimateDocItem destroyItem = new EstimateDocItem();
			destroyItem.setEstimateDoc(estimateDoc);
			destroyItem.setItemType("destroy");
			destroyItem.setItemName("철거비");
			destroyItem.setItemAmt(1);
			destroyItem.setItemCost(totalDestroyCost);
			destroyItem.setItemAmtCost(totalDestroyCost);
			items.add(destroyItem);
		}
		
		if(isElevator == 1) {
			EstimateDocItem destroyItem = new EstimateDocItem();
			destroyItem.setEstimateDoc(estimateDoc);
			destroyItem.setItemType("add");
			destroyItem.setItemName("승강기/기타보양비");
			destroyItem.setItemAmt(1);
			destroyItem.setItemCost(100000);
			destroyItem.setItemAmtCost(100000);
			items.add(destroyItem);
		}

		
		estimateDoc.setItems(items);
		est.setEstimateDoc(estimateDoc);
		
		return estimateRepository.save(est);
	}
	
//	@Transactional
//	public Order _send_Email_from_order(Order order) {
//		
//		order.setEmailSendFlag(true);
//		
//		return orderRepository.save(order);
//	}
	
}
