
public class PlayFairCipherSession {
    
    private String _alphabet;
    private String _key;
    private String keyString = "";

    PlayFairCipherSession()
    {
        this._alphabet="ABCDEFGHIKLMNOPQRSTUVWXYZ";
    }

    PlayFairCipherSession(String key)
    {
        this._alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        this._key = key;
    }

    
   public String[][] _ketString(String key)
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

    private String[][] _prepareMessage(String message){
        String[][] result;
        for (int k = 0; k < message.length()-1; k++) {
            for (int i = 0; i < result.length; i++) {
                    result[i][0] = "" + message.charAt(k);
                    result[i][1] = (message.charAt(k)== message.charAt(k+1))? "" : "te"; 
            }
        }

        return result;
    } 



}