package com.cardpay.pccredit.bank.id;

import org.hibernate.id.UUIDHexGenerator;


/**
 * Description of IDGenerator
 * 
 * @author Vincent
 * @created on Dec 27, 2013
 * 
 * @version $Id: IDGenerator.java 200 2013-12-30 12:02:36Z vincent $
 */
public class IDGenerator {
	static UUIDHexGenerator GUID = new UUIDHexGenerator();

	public synchronized static String generateID() {
		return GUID.generate(null, null).toString();
	}

	
	
}
