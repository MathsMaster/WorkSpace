package com.xulp.utils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		calculator();
		
		printArraysBuff("AZAABCEH".getBytes());
		
	}
	
     public static String calculator()
     {
         long [] arrays = new long[90];
         byte [] buff = new byte[90];

         for(int i = 0 ; i < arrays.length;i++)
         {
             if(i == 0 )
             {
                 arrays[i] = 1l;
                 continue;
             }else if(i == 1)
             {
                 arrays[i] = 1l;
                 continue;
             }
             arrays[i] = arrays[i - 1] + arrays[i - 2];
         }
         
         printArraysLong(arrays);
         

         int a = 'A';
         int z = 'Z';
         System.out.println("大写A --> "+ a +" 大写Z --> "+z);

         for(int i = 0 ; i < buff.length;i++)
         {
        	 buff[i] = (byte)(arrays[i] % 26l);
         }
         
         printArraysBuff(buff);
         
         for(int i= 0 ;i < buff.length;i++)
         {
        	 if(buff[i] == 0)
        	 {
        		 buff[i] = 90;
        	 }else{
        		 buff[i] = (byte) (buff[i] + 64);
        	 }
//        	 buff[i] = (byte) (buff[i] + 65);
         }
         
         printArraysBuff(buff);
         
         
         
         System.out.print(new String(buff));


         return null;
     }
     
     static void printArraysLong(long [] arrays)
     {
    	 for(long i : arrays)
    	 {
    		 System.out.print(i+" ");
    	 }
    	 
    	 System.out.println("");
     }
     
     static void printArraysBuff(byte [] arrays)
     {
    	 for(long i : arrays)
    	 {
    		 System.out.print(i+" ");
    	 }
    	 System.out.println("");
     }

}
