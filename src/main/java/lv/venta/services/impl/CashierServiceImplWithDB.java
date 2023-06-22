package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Cashier;
import lv.venta.repos.ICashierRepo;
import lv.venta.services.ICashierService;

@Service
public class CashierServiceImplWithDB implements ICashierService{

	@Autowired
	private ICashierRepo cashierRepo;

	@Override
	public ArrayList<Cashier> getAllCashiers() throws Exception {
		return (ArrayList<Cashier>) cashierRepo.findAll();
	}
	}
