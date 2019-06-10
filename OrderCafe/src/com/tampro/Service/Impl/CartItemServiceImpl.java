package com.tampro.Service.Impl;

import com.tampro.Dao.CartItemRepository;
import com.tampro.Dao.Impl.CartItemRepositoryImpl;
import com.tampro.Model.CartItem;
import com.tampro.Service.CartItemService;
	
public class CartItemServiceImpl  implements CartItemService{
	
	CartItemRepository cartItemRepo = new CartItemRepositoryImpl();

	@Override
	public void addCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		cartItemRepo.addCartItem(cartItem);
	}	

	
	

}
