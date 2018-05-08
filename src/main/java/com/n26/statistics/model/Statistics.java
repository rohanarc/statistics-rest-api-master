package com.n26.statistics.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * statistics data pojo
 *
 */
public class Statistics {

	private Double sum;

	private Double avg;

	private Double max;

	private Double min;

	private Long count;

	private static final int SECONDS_STAT = 60000;//ms
	
	public Statistics() {
	}

	/**
	 * can be optimized with using DoubleSummaryStatistics 
	 * @param transactions
	 */
	public Statistics(Collection<Transaction> transactions) {
		final List<Double> amountsLastMinute = transactions.stream().filter(s -> (System.currentTimeMillis() - s.getTimestamp()) < SECONDS_STAT).map(Transaction::getAmount)
				.collect(Collectors.toList());
		final Long count = amountsLastMinute.stream().count();
		this.setCount(count);
		if (count > 0) {
			this.setSum(amountsLastMinute.stream().mapToDouble(Double::doubleValue).sum());
			this.setAvg(amountsLastMinute.stream().mapToDouble(Double::doubleValue).average().getAsDouble());
			this.setMax(amountsLastMinute.stream().max(Double::compareTo).get());
			this.setMin(amountsLastMinute.stream().min(Double::compareTo).get());
		}
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
