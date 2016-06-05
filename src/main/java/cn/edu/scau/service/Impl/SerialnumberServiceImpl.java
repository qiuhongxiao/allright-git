package cn.edu.scau.service.Impl;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.dao.SerialnumberDaoImpl;
import cn.edu.scau.service.SerialnumberService;
import cn.edu.scau.vo.Serialnumber;


/**
 * @author wxj
 *
 */
@Service("serialnumberService")
@Transactional
public class SerialnumberServiceImpl implements SerialnumberService {

	@Resource(name="serialnumberDao")
	private SerialnumberDaoImpl serialnumberDao;
	@Override
	public String getPaymentnumber() {	
		Serialnumber serialnumber = serialnumberDao.findAll().get(0);
		int paymentnumber = serialnumber.getPaymentnumber();
		serialnumber.setPaymentnumber(serialnumber.getPaymentnumber() + 1);
		serialnumberDao.update(serialnumber);
		DecimalFormat df = new DecimalFormat("00000000");
		return df.format(paymentnumber);
	}

	@Override
	public String getSaleordernumber() {
		Serialnumber serialnumber = serialnumberDao.findAll().get(0);
		int saleordernumber = serialnumber.getSaleordernumber();
		serialnumber.setSaleordernumber(serialnumber.getSaleordernumber() + 1);
		serialnumberDao.update(serialnumber);
		DecimalFormat df = new DecimalFormat("00000000");
		return df.format(saleordernumber);
	}

	@Override
	public String getCustomernumber() {
		List<Serialnumber> list=serialnumberDao.findAll();
		if(list.isEmpty()){
			Serialnumber temp=new Serialnumber(0, 0, 0, 0);
			serialnumberDao.save(temp);
		}
		Serialnumber serialnumber = serialnumberDao.findAll().get(0);
		int customernumber = serialnumber.getCustomernumber();
		serialnumber.setCustomernumber(serialnumber.getCustomernumber() + 1);
		serialnumberDao.update(serialnumber);
		DecimalFormat df = new DecimalFormat("00000000");
		return df.format(customernumber);
	}
	@Override
	public String getSalereturnnumber() {
		Serialnumber serialnumber = serialnumberDao.findAll().get(0);
		int salereturnnumber = serialnumber.getSalereturnnumber();
		serialnumber.setSalereturnnumber(serialnumber.getSalereturnnumber() + 1);
		serialnumberDao.update(serialnumber);
		DecimalFormat df = new DecimalFormat("00000000");
		return df.format(salereturnnumber);
	}

}
