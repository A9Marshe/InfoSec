//شغلات ما اشتغلت
/*
        1- بفك التشفير ممكن تتلطع النتيجة 
        helxlo instead of hello
        2- ecrypting(message = jacket, key = jake) --> AKFIGY,
            but when decrypting(message = 'AKFIGY', key = jake) --> iacket

            @TODO should these be addressesd?
*/
public class PlayFair {

    private String[][] _cipherTable;
    private String inputMessage;

    public static void main(String args[]){
        PlayFair encryptor = new PlayFair("jake", "jacket");
        encryptor.encrypt();
        System.err.println("now decrypting");
        PlayFair decrpytor = new PlayFair("jake", encryptor.encrypt());
        decrpytor.decrypt();
    }

private PlayFair(String key, String message){
    System.out.println("Using PlayFair cipher with: ");
    this._cipherTable = _keyString(key);
    System.out.println(" input message is: \n" + message);
    inputMessage = message;
}  

//generate playfair table from given key string
private String[][] _keyString(String key)
{
    String[][] playfairTable = new String[5][5]; 
    //key to generate cipher table, replacing Js by Is
    String keyString = "";
    for (int i = 0; i < key.length(); i++) {
        if (key.toUpperCase().charAt(i) == 'J')
        {
            keyString += "I"; continue;
        } 
        keyString +=key.toUpperCase().charAt(i);
    }
    keyString += "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    //init matrix with empty char
    for(int i =0; i < 5; i++) for(int j = 0; j < 5; j++)  playfairTable[i][j] = "";
            
    for(int k = 0; k < keyString.length(); k++)
        {  
        boolean repeat = false;  
        boolean used = false;  
        for(int i = 0; i < 5; i++)
        {  
            for(int j = 0; j < 5; j++)
            {  
                if(playfairTable[i][j].equals("" + keyString.charAt(k)))
                {  
                    repeat = true;
                }  
                else if(playfairTable[i][j].equals("") && !repeat && !used) 
                {  
                    playfairTable[i][j] = "" + keyString.charAt(k);  
                used = true; 
                }
            }
        }
    }
        System.out.println("used " + key +"to generate the following Cipher table: \n");
        for (int i = 0; i < playfairTable.length; i++) 
        {
        for (int j = 0; j < playfairTable.length; j++)
            {
            System.err.print(playfairTable[i][j] + " | ");
            }
        System.out.println("\n --------------------");
        }
        _cipherTable = playfairTable;
    return playfairTable;  
    }


//encode function
private int[] _encode(String text)
{
    int[] res = new int[text.length()];
    for (int i = 0; i < text.length(); i++) {
       res[i] = (int) text.charAt(i);
    }
    return res;
}
//decode function
private String _decode(int[] text)
{
    String res = "";
    for (int i = 0; i < text.length; i++) {
        res += (char) text[i];
    }
    return res;
}

//validate message: removes illegal characters and asserts that all letters are in lower case
private String _validate(String message)
{
    String res ="", msg = message.toLowerCase();
    for (int i = 0; i < msg.length(); i++) {
        if( (123 > (int) msg.charAt(i)) && ((int) msg.charAt(i) > 96) ) res+= msg.charAt(i);
    }
    System.out.println("valid message is: \n" + res +", now doing preperations");
    return res;
}

// _prepare message: prepares letter pairs, seperates repeated letters, and adds padding when necessary
private String _prepareMessage(String myMessage)
{
    String res = "";
    String message = _validate(myMessage);

    //this isEvenLength boolean is need after the loop concludes
    boolean isEvenLength = (message.length() %2 == 0)? true : false ;
    for (int i = 0; i < message.length()/2; i++) {
        res+= message.charAt(i*2);
        res+= message.charAt(i * 2 + 1) == message.charAt(i * 2 ) ? ("x" +  message.charAt(i*2 + 1) ):  message.charAt(i*2 + 1);

    }
    
    //if the message is not of even length, then we need to add the last character as the previous loop does not read it
    if(!isEvenLength) res+= message.charAt(message.length() - 1);
    
    //if the final message (with added x characters and final character in case of NOT isEvenLength, we need padding)
    boolean needsPadding = (res.length() %2 == 0)? false : true;
    if(needsPadding) res+= "z";
    

    //printing message to be encrypted
    System.out.println("\n final message to work on: ");
    System.out.print(res + "\t :");
    for (int i = 0; i < res.length(); i++) {
        System.out.print(res.charAt(i));
        if( i % 2 == 1) System.out.print(" ");
    }
    System.out.println();
    return res;
}

//get location returns the row [0] and column[1] of a given character within the playfair cihpertable we configured before
private int[] _getLocation(char c)
{  
     int[] res = new int[2];
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            if( _cipherTable[i][j].toLowerCase().charAt(0) == c) 
            {
                res[0] = i;
                res[1] = j;
            }
        }
    }
    return res;
} 

public String encrypt()
{
    String res = "";
    String message = _prepareMessage(inputMessage);
    for (int i = 0; i < message.length() / 2; i++) {
        int[] c1 = _getLocation(message.charAt(i * 2));
        
        int[] c2 = _getLocation(message.charAt(i * 2 + 1 ));
        //set locations for a character set (two consecutive charaters)
        
        int rowc1 = c1[0];
        int colc1 = c1[1];
        int rowc2 = c2[0];
        int colc2 = c2[1];

        //same row situation
        if (rowc1 == rowc2)
        {
            res += _cipherTable[rowc1][(colc1 + 1) % 5];
            res += _cipherTable[rowc1][(colc2 + 1) % 5];
        }

        //same column situation
        else if (colc1 == colc2)
        {
            res += _cipherTable[(rowc1 + 1) % 5][colc1];
            res += _cipherTable[(rowc2 + 1) % 5][colc1];
        }

        // rectangle situation
        else
        {
            res += _cipherTable[rowc1][colc2];
            res += _cipherTable[rowc2][colc1];
        }
    }
    System.out.println("encrpyted message is: " + res.toUpperCase());
    return res.toUpperCase();
}

//decryption functionality
public String decrypt()
{
    String res = "";
    String message = _prepareMessage(inputMessage);
    for (int i = 0; i < message.length() / 2; i++) {
        int[] c1 = _getLocation(message.charAt(i * 2));
        
        int[] c2 = _getLocation(message.charAt(i * 2 + 1 ));
        //set locations for a character set (two consecutive charaters)
        
        int rowc1 = c1[0];
        int colc1 = c1[1];
        int rowc2 = c2[0];
        int colc2 = c2[1];

        //same row situation
        if (rowc1 == rowc2)
        {
            res += _cipherTable[rowc1][(colc1 - 1) % 5];
            res += _cipherTable[rowc1][(colc2 - 1) % 5];
        }

        //same column situation
        else if (colc1 == colc2)
        {
            res += _cipherTable[(rowc1 - 1) % 5][colc1];
            res += _cipherTable[(rowc2 - 1) % 5][colc1];
        }

        // rectangle situation
        else
        {
            res += _cipherTable[rowc1][colc2];
            res += _cipherTable[rowc2][colc1];
        }
    }
    System.out.println("Decrypted message is: " + res.toLowerCase());
    return res.toLowerCase();
}


}

