public class ADFGVXCipher {
    //members
    private String[][] table;
    private String message;
    private String keyword;
    private String[] tmp;
    public static void main(String[] args) {
        ADFGVXCipher encryptor = new ADFGVXCipher("computer", "eva", "lisa");
        String enc = encryptor.encrypt();
        ADFGVXCipher decryptor = new ADFGVXCipher(enc, "eva","lisa");
        decryptor.decrypt();
    }


    public ADFGVXCipher(String message, String key, String keyword)
    {   
        tmp = new String[6];
        tmp[0] = "A"; tmp[1] = "D"; tmp[2] = "F"; tmp[3] = "G"; tmp[4] = "V"; tmp[5] = "X";
        this.keyword = keyword.toLowerCase();
        this.message = message.toUpperCase();
        this.table = _square(key);
      
    }

    //generate table from given key 
    private String[][] _square(String key)
    {
        String[][] table = new String[6][6]; 
        
        // empty cipher table
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] ="";
            }
        }
        //key to generate cipher table, 
        String keyString = "";
        for (int i = 0; i < key.length(); i++)
        {
            keyString +=key.toUpperCase().charAt(i);
        }
        keyString += "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        //init matrix with empty char
        for(int i =0; i < 5; i++) for(int j = 0; j < 5; j++)  table[i][j] = "";        
        for(int k = 0; k < keyString.length(); k++)
        {  
            boolean repeat = false;  
            boolean used = false;  
            for(int i = 0; i < 6; i++)
            {  
                for(int j = 0; j < 6; j++)
                {  
                    if(table[i][j].equals("" + keyString.charAt(k)))
                    {  
                        repeat = true;
                    }  
                    else if(table[i][j].equals("") && !repeat && !used) 
                    {  
                    table[i][j] = "" + keyString.charAt(k);  
                    used = true; 
                    }
                }
            }
        }        
        System.out.println("used " + key +" to generate the following ADFGVX table: \n");
        System.out.println( "    A | D | F | G | V | X |");
        System.out.println(" --------------------------");
        for (int i = 0; i < table.length; i++) 
        {
            System.out.print(tmp[i] +" | ");
        for (int j = 0; j < table.length; j++)
            {
            System.out.print(table[i][j] + " | ");
            }
        System.out.println("\n --------------------------");
        }
        return table;  
    }

        //map keyword to numerical values
        private int[] _mapKeyword()
        {
            int[] res = new int [keyword.length()];

            int[] positions = new int[keyword.length()];
            for (int i = 0; i < positions.length; i++)
            {
                positions[i] = (int) keyword.charAt(i);
            }

            for (int i = 0; i < res.length; i++) {
                res[i] = 0; 
                for (int j = 0; j < res.length; j++) 
                {
                    if (positions[i] > positions[j] ) res[i]++;
                }
            }
            String tmporary = "";
            for (int i = 0; i < res.length; i++) {
                tmporary += res[i] + " ";
            }
            System.out.println(" keyword is: " + keyword+ ", in numeric values: "+ tmporary);
            return res;
        }

    //encode characters using table
    private String _encode()
    {
        String res = "";
        for (int k = 0; k < message.length(); k++) 
        {
            char ch =  message.charAt(k);
            for (int i = 0; i < 6; i++) 
            {
                for (int j = 0; j < 6; j++) 
                {   
                    if ( (int) ch  == (int) table[i][j].charAt(0) ) res += tmp[i] + tmp[j] +"";
                }
            }
        }
        System.out.print("message: \t ");
        for (int i = 0; i < message.length(); i++)
        {
            System.out.print(message.charAt(i) + "\t"); 
        }
        System.out.println();
        System.out.print("transitive text: ");
        for (int i = 0; i < res.length()/2; i++) 
        {   
           System.out.print( "" + res.charAt(i * 2) +res.charAt(i * 2 + 1)  + "\t");
        }
        System.out.println("\n");
      
        return res; 
    }

    //encrypt message
    public String encrypt()
    {
        
        String res = "";
        String encodedMessage = _encode();
        
        int  columns = keyword.length();
        int  rows = encodedMessage.length() / columns;

        String[][] temp = new String[rows][columns];
        //initialising with empty chars
        for (int i = 0; i < rows; i++) 
        for (int j = 0; j < columns; j++)
            temp[i][j] = "😁"; 

        //mapping keyword to numeric values 
        int[] pos = _mapKeyword();
        //-----enryption stage-----
        try 
        {
            //filling columns with order according to keyword
            for (int i = 0; i < pos.length; i++) 
            {
                //using the currentColumn and the pos = (x + y * width) formula assures that output columns are in correct order( automatically rearranaged) 
                int currentColumn = pos[i];
                for (int j = 0; j < rows; j++)
                {   
                    temp[j][currentColumn] = encodedMessage.charAt(j + currentColumn * rows) + "";  
                }
            }
        }
        catch (Exception e) {
            System.err.println("error filling columns, check your key and keyword values. \n details: " + e);
            return "";
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                res += temp[i][j];
            }
        }
        res = res.toUpperCase();
        System.out.println("Encrypted message is: \t ");
        for (int i = 0; i < res.length()/2; i++)
        {
            System.out.print("" + res.charAt( i*2 ) + res.charAt(i * 2 + 1)+ "  ");
        }
        System.out.println();
        return res;
    }

    //decrypt message
    public String decrypt()
    {
        String res = "";
        String msg = message.toUpperCase();
        int  columns = keyword.length();
        int  rows = msg.length() / columns;

        String[][] temp = new String[rows][columns];
        //initialising with empty chars
        for (int i = 0; i < rows; i++) 
        for (int j = 0; j < columns; j++)
            temp[i][j] = "😵"; 
        
        int[] pos = _mapKeyword();
        //filling columns with order according to keyword
        for (int i = 0; i < pos.length; i++) 
        {
            //using the currentColumn and the pos = (x + y * width) formula assures that output columns are in correct order( automatically rearranaged) 
            int currentColumn = pos[i];
            for (int j = 0; j < rows; j++)
            {   
                temp[j][currentColumn] = msg.charAt(j + currentColumn * rows) + "";  
            }
        }
        String transitiveText = "";
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                transitiveText += temp[i][j];
            }
        }
        System.out.print("transitive text: ");
        for (int i = 0; i < transitiveText.length()/2; i++) 
        {   
           System.out.print( "" + transitiveText.charAt(i * 2) +transitiveText.charAt(i * 2 + 1)  + "\t");
        }
        System.out.println("\n");   
        int row = 0, col = 0;
        for (int i = 0; i < transitiveText.length()/2; i++)
        {
            String ch1 = transitiveText.charAt(i * 2)+"";
            String ch2 = transitiveText.charAt(i * 2 + 1)+"";
            for (int j = 0; j < tmp.length; j++)
            {    
                if( (int) ch1.charAt(0) == (int) tmp[j].charAt(0)) row = j;
                if( (int) ch2.charAt(0) == (int) tmp[j].charAt(0)) col = j;
            }
            res += table[row][col];
        }


        res = res.toLowerCase();
        System.out.println("Decrypted message is: \t ");
        for (int i = 0; i < res.length(); i++)
        {
            System.out.print(""+res.charAt( i ));
        }

        return res;
    }
}
