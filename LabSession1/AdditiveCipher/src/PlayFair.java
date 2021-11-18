public class PlayFair {
    private String _alphabet;
    private String[][] _key;
    private String keyString = "";
public static void main(String args[]){

}

private PlayFair(String key, String message){
    _key = _keyString(key);


}  

//generate playfair table from given key string
private String[][] _keyString(String key)
{
 String[][] playfairTable = new String[5][5]; 
 //key to encode
 String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
 //init matrix with empty char
 for(int i =0; i < 5; i++)  for(int j = 0; j < 5; j++)  playfairTable[i][j] = "";          
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

private String _encrypt()
{
    String res = "";

    return res;
}

private String _decrypt()
{
    String res = "";

    return res;
}
}
