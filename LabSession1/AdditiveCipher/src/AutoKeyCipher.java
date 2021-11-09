import java.sql.PreparedStatement;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class AutoKeyCipher {
     // Ci = (Pi + k) % 26;
     private String alphabet;
     private String key = "";
     //mod is the length of the alphabet
     private  final int _mod;

     AutoKeyCipher(){
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this._mod = alphabet.length();
     }
     //characters validation
    private String validate(String message){
        String validMessage ="";
        for (int i = 0; i < message.length(); i++) {

            if(alphabet.indexOf( message.charAt(i)) >= 0) 
                validMessage += message.charAt(i);

            else { 
                System.out.println("the character (" + message.charAt(i) + ") is not included in alphabet");
            }
        }
        return validMessage;
    }
    
    //encoding function
    private int[] _encode(String message){
        String validMessage = validate(message);
        int[] result = new int[validMessage.length()];
        for (int i = 0; i < validMessage.length(); i++) {
            result[i] = alphabet.indexOf( validMessage.charAt(i));
        }
        return result;
    }
    
    //decoding function
    private String _decode(int[] encodedMessage){
        String result = "";
        for (int i = 0; i < encodedMessage.length; i++) {
            result += alphabet.charAt(encodedMessage[i]); 
        }
        return result;
    }

    //preparing encryption key
    private void _prepareKey(String message, String inputKey){
        if ((boolean)(key=="")) key = inputKey + message;
       
    }

    //encryption
    public String encrypt(String message, String inputKey){
        _prepareKey(message, inputKey);
        int[] encodedMessage = _encode(message);
        int[] encodedKey = _encode(key);
        int[] res = new int[encodedMessage.length];
        for (int i = 0; i < encodedMessage.length; i++) {
            res[i] = (encodedMessage[i] + encodedKey[i]) % _mod;
        }
        return _decode(res);
    }

    //decrpytion
    public String decrypt(String message, String inputKey){
        _prepareKey(message, inputKey);
        int[] encodedMessage = _encode(message);
        int[] encodedKey = _encode(key);
        // decyrption formula C = M - k % (alphabet length)
        for (int i = 0; i < encodedMessage.length; i++) {
            encodedMessage[i] = ((encodedMessage[i] - encodedKey[i] % _mod) >= 0) ?  (encodedMessage[i] - encodedKey[i]) % _mod :((encodedMessage[i] - encodedKey[i]) % _mod) + _mod;
        }
        return _decode(encodedMessage);
    }

}
