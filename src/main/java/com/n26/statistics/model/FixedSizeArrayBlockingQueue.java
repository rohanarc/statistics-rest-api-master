package com.n26.statistics.model;

import java.util.concurrent.ArrayBlockingQueue;

public  class FixedSizeArrayBlockingQueue<E> extends ArrayBlockingQueue<E> {
	 
		/**
		 * generated serial number
		 */
		private static final long serialVersionUID = -7772085623838075506L;
	 
		// Size of the queue
		private int size;
	 
		// Constructor
		public FixedSizeArrayBlockingQueue(int newSize) {
	 
			// Creates an ArrayBlockingQueue with the given (fixed) capacity and default access policy
			super(newSize);
			this.size = newSize;
		}
	 
		// If queue is full, it will remove oldest/first element from queue like FIFO
		@Override
		synchronized public boolean add(E e) {
	 
			// Check if queue full already?
			if (super.size() == this.size) {
				// remove element from queue if queue is full
				this.remove();
			}
			return super.add(e);
		}
	 
	}