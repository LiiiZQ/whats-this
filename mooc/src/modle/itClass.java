package modle;

public class itClass {

	public String id="";
	public String tid="";
	public String sid="";
	public String tname="";
	public String cname="";
	public String homework="";

	public String des="";
	public itClass(String id,String tname,String cname,String des,String homework)
	{
		this.id=id;
		this.tname=tname;
		this.cname=cname;
		this.homework=homework;
		this.des=des;
		
	}
	public itClass(String id,String tid,String tname,String cname,String des,String homework)
	{
		this.id=id;
		this.tid=tid;
		this.tname=tname;
		this.cname=cname;
		this.homework=homework;
		this.des=des;
		
	}
}
