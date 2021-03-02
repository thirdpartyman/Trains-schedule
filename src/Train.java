
import java.io.Serializable;
import java.util.Date;

public class Train implements Serializable{
	
	private static final long serialVersionUID = 6532221275368110462L;
	
	private int pNumber;
	private String pName;
	private Date departureTime;
		
	/////////////////////////////////////////////////

	public Train() 
	{
		super();
		pNumber=-1;
		pName="Undefined";
		departureTime = new Date();
	}
	public Train(int pNumber, String pName, Date arrivalTime) throws Exception
	{
		setName(pName);
		setNumber(pNumber);
		setDepartureTime(arrivalTime);
		/*pNumber=pNum;
		pName=pN;
		pTimeH=pTH;
		pTimeM=pTM;*/
	}
	
	/////////////////////////////////////////////////
	
	public void setName(String pName) throws Exception
	{
		if (pName.length()>0)
		{
			this.pName=pName;
		}
		else
		{
			throw new Exception("\n!!!Недопустимое имя!!!");
		}
		
	}
	public void setNumber(int pNumber) throws Exception
	{
		
		int i=0,k=pNumber;
		for(i = 0; k != 0; i++)
		{
			k = k / 10; 
		}
		if (i<4 && pNumber>0)
		{
			this.pNumber=pNumber;
		}
		else
		{
			throw new Exception("\n!!!Недопустимый номер!!!");
		}
	}

	public void setDepartureTime(Date arrivalTime)
	{
		this.departureTime = arrivalTime;
	}

	/////////////////////////////////////////////////
	
	public String getName()
	{
		return this.pName;
	}
	public int getNumber()
	{
		return this.pNumber;
	}
	public Date getDepartureTime()
	{
		return this.departureTime;
	}

	
	/////////////////////////////////////////////////
	
	
	//public void showTrainData() throws exept
	//{
	//	if (pNumber!=-1 && !pName.isEmpty() && pTimeH!=-1 && pTimeM!=-1)
	//	{
	//		System.out.println("Номер поезда: "+pNumber);
	//		System.out.println("Пункт назначения: "+pName);
	//		if(pTimeH/10==0) 
	//		{
	//			if(pTimeM/10==0) 
	//			{
	//				System.out.println("Время отправления: "+"0"+pTimeH+":"+"0"+pTimeM);
	//			}
	//			else
	//			{
	//				System.out.println("Время отправления: "+"0"+pTimeH+":"+pTimeM);
	//			}
	//		}
	//		else
	//		{
	//			if(pTimeM/10==0) 
	//			{
	//				System.out.println("Время отправления: "+pTimeH+":"+"0"+pTimeM);
	//			}
	//			else
	//			{
	//				System.out.println("Время отправления: "+pTimeH+":"+pTimeM);
	//			}
	//			System.out.println("_______________________");
	//		}
	//	}
	//	else
	//	{
	//		throw new exept(getName(), getNumber(), getTimeH(), getTimeM());
			//System.out.println("Заполнены не все данные!");
	//	}
			
	//}
	

}


