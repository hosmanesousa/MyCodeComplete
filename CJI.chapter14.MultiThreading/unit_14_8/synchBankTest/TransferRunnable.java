package unit_14_8.synchBankTest;

public class TransferRunnable implements Runnable 
{
	public TransferRunnable(Bank b,int from,double max)
	{
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}
	
	public void run() 
	{
		try {
			while(true)
			{
				int toAccount = (int)(bank.size() * Math.random());
				double amount = maxAmount * Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int)(DELAY * Math.random()));
			}
		} catch (InterruptedException ie) {
			// TODO Auto-generated catch block
			System.out.println("InterruptedException:" + ie.getMessage());
			ie.printStackTrace();
		}
	}

	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
}
