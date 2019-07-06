package com.byrfb.examples;

public class Snippet {
	Autos aut=new Autos();
	       aut.setAuto("Auto1");
	       aut.setColor("Negro");
	       aut.setId_auto(12);
	       aut.setPeso(10000);
	       aut.setTama("Grande");
	
	   Field[] fields = aut.getClass().getDeclaredFields();
	   String campos="";
	   String valores="";
	   for (int i = 0; i < fields.length; i++) {
	      try{
	
	           String name = fields[i].getName();
	           String value = fields[i].get(aut).toString();
	           if(i!=0){
	               campos=campos+",";
	               valores=valores+",";
	           }
	           if(fields[i].get(aut) instanceof String){
	               valores=valores+"'"+value+"'";
	           }
	           else{
	               valores=valores+value;
	            }
	           campos=campos+name;
	       } catch (IllegalAccessException ex) {}
	   }
	     String sql="insert into dbo."+aut.getClass().getSimpleName().toLowerCase()+"("+campos+")values("+valores+");";
	     System.out.println(sql);
}

