package com.ecom.payload;

public class PaymentSuccessResponse {
	
	    private String paymentStatus;
	    private String user_order_id;
	    private boolean captured = false;
	    
		public PaymentSuccessResponse(String paymentStatus, String user_order_id, boolean captured) {
			super();
			this.paymentStatus = paymentStatus;
			this.user_order_id = user_order_id;
			this.captured = captured;
		}

		public PaymentSuccessResponse() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getPaymentStatus() {
			return paymentStatus;
		}

		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}

		public String getUser_order_id() {
			return user_order_id;
		}

		public void setUser_order_id(String user_order_id) {
			this.user_order_id = user_order_id;
		}

		public boolean isCaptured() {
			return captured;
		}

		public void setCaptured(boolean captured) {
			this.captured = captured;
		}
		
		
	    
	    

}
