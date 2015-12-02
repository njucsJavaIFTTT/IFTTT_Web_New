import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.activation.MailcapCommandMap;

class ExpenceCanlendar {
	private Date date;//ʱ��
	private double amount;//���
	private int thisnum;//this������
	private int thatnum;//that������
	private int num;//���Ѽ�¼���
	
	public ExpenceCanlendar(double cost,int thisn,int thatn,int n)
	{
		date= new Date();
		amount = cost;
		thisnum = thisn;
		thatnum = thatn;
		num = n;
	}
	
	public ExpenceCanlendar getRecord()
	{
		return this;
	}
}

public class Account {
	private String username;//�ǳƣ���Ψһ
	private String password;//����
	private String mailAccount;//�����˺ţ�Ψһ
	private double balance;//���
	private int level;
	private Set<ExpenceCanlendar> expenceCanlendar;//���Ѽ�¼
	private int credit;//����
	private double discount;//�ۿ�
	
	public Account() {
		balance = 1000;
		expenceCanlendar = new HashSet<ExpenceCanlendar>();
		level = 1;
		credit = 0;
		discount = 1;
	}
	
	public Account(String user,String pw,String mail){
		username = user;
		password = pw;
		mailAccount = mail;
		balance = 1000;
		level = 1;
		expenceCanlendar = new HashSet<ExpenceCanlendar>();
		credit = 0;
		discount = 1;
	}
		
	/* �޸��ǳ� */
	public void changeUserName(String newName)
	{
		username = newName;
	}
	
	/* �޸����룬����������ԭ���벻��ͬ */
	public boolean changePassword(String pass)
	{
		if(pass.compareTo(password) == 0)
			return false;
		else
			password = pass;
		return true;
	}
	
	/* �޸������˺ţ�����������ԭ���䲻ͬ��һ�㲻�ɵ��� */
	public boolean changeMailAccount(String mail)
	{
		if(mail.compareTo(mailAccount) == 0)
			return false;
		else
			mailAccount = mail;
		return true;
	}
	
	/* ��ֵ */
	public double recharge(double pay){
		balance += pay;
		return balance;
	}
	
	/* ��ʾ��Ҫ�����ڹ���Աǿ���޸ĵȼ�������Ӧ�ظı���ֺ��ۿ� */
	public void changeLevel(int l)
	{
		level = l;
		if(level == 1)
		{
			discount = 1.0;
			if(credit > 1000)
				credit = 0;
		}
		else if(level == 2)
		{
			discount = 0.95;
			if(credit <= 1000 || credit > 2000)
				credit = 1001;
		}
		else if(level == 3)
		{
			discount = 0.9;
			if(credit <= 2000 || credit > 3000)
				credit = 2001;
		}
	}
	
	/* ���ѣ��������Ѽ�¼�ı���֡���Ա�ȼ����ۿ� */
	public boolean Expense(double amount,int thisn,int thatn)
	{
		if(balance < amount * discount)
			return false;
		balance -= amount * discount;
		credit += amount;
		expenceCanlendar.add(new ExpenceCanlendar(amount*discount, thisn, thatn, expenceCanlendar.size()));
		if(credit <= 1000)
		{
			level = 1;
			discount = 1.0;
		}
		else if(credit <= 2000)
		{
			level = 2;
			discount = 0.95;
		}
		else if(credit <= 4000)
		{
			level = 3;
			discount = 0.9;
		}
		return true;
	}
	
	/* ��ȡ�˺���Ϣ */
	public String returnUserName(){ return username;}
	public String returnPassword(){ return password;}
	public String returnMailAccount(){ return mailAccount;}
	public double returnBalance(){ return balance;}
	public int returnLevel(){ return level;}
	public Set<ExpenceCanlendar> returnRecords(){
		Set<ExpenceCanlendar> records = new HashSet<ExpenceCanlendar>();
		records.addAll(expenceCanlendar);
		return records;
	}
}
