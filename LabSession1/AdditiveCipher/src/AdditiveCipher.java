

public class AdditiveCipher{
    /**
     * Additive encryption formula:
     * C = m + k mod(alphabet.length)
     * C: cipher text
     * m: message
     * k: key
     * alphabet: the alphabet used to represent messages and cipherTexts
     * !- This is the Naive approach to additiveCipher😄
     */
    int k = 3; // default key value (3) is the one used with Caesar Cipher
    //Modern english alphabet
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    //mod is the length of the alphabet
    private  final int _mod = alphabet.length() - 1;

    //encoding funtion
    private int[] _encode(String message){
        //validating characters
        int tempIndex = 0;
        int[] encode = new int[message.length()];
        int index;
        for (int i = 0; i < message.length(); i++) {
            index = alphabet.indexOf(message.charAt(i));
            if (index > 0){
                encode[tempIndex] = index;
                i++;
            } 
            else { System.out.println(message.charAt(i) + " is not included in alphabet");}
        }
        return encode;
    }

    //decoding function
    private String _decode(int[] encodedMessage){
        String res = "";
        for (int i : encodedMessage) {
            res += alphabet.charAt(i);  
        }
        return res;

    }

    /** 
     * encrypt function is used to encrypt a Message,
     * encrypt uses encode and decode functions
     *(message)-> encrypt(message) -> decode(CipherText) 
    */
    public String encrypt(String message, int key){
        int[] encodedMessage = _encode(message);

        // encyrption formula C = M + k % (alphabet length)
        for (int i : encodedMessage) i = i + k % _mod;

        return _decode(encodedMessage);
    }
    
    
    /** decryption function
     *  decyption formula :
     * c = m - k mod(alphabet.length) (when >0)
     * c = (m - k mod(alphabet.length)) + alphabet.length (when <0)
    */ 
    public String decrypt(String message, int key){
        int[] encodedMessage= _encode(message);
        // decyrption formula C = M - k % (alphabet length)
        for (int i : encodedMessage){
            i = ((i - key % _mod) > 0) ?  i - key % _mod :(i - key % _mod) + _mod; 
        } 
        return _decode(encodedMessage);
    }
}