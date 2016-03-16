package hr.bm.context;

import hr.bm.utils.MyApplicationContext;

public class NotBeanClass {

	public void print() {
		System.out.println("Bean moze koristiti obicnu klasu! :-)");
		MyXmlBean myXmlBean = (MyXmlBean) MyApplicationContext.getBean("myXmlBean");
		myXmlBean.print("Print od NotBeanClass.MyXmlBean!!!! :-)");
	}

}
