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
    private String alphabet;
    private int key;
    //mod is the length of the alphabet
    private  final int _mod;

    //default constructor with predefined alphabet and key
    AdditiveCipher(){
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.key = 3; // default key value (3) is the one used with Caesar Cipher
        this._mod = alphabet.length() - 1;
    }
    AdditiveCipher(String alphabet){
        this.alphabet = alphabet;
        this._mod = alphabet.length() - 1;
    }
    AdditiveCipher(String alphabet, int key){
        this.alphabet = alphabet;
        this.key = key;
        this._mod = alphabet.length() - 1;
    }

    private int[] _encode(String message){
        
        //characters validation
        int[] result;
        String validMessage ="";
        for (int i = 0; i < message.length(); i++) {
            if(alphabet.indexOf( message.charAt(i)) >= 0) validMessage += message.charAt(i);
            else { System.out.println("the character (" + message.charAt(i) + ") is not included in alphabet");}
        }
        
        //encoding
        result = new int[validMessage.length()];
        for (int i = 0; i < validMessage.length(); i++) {
            result[i] = alphabet.indexOf( validMessage.charAt(i));
        }
        return result;
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
    public String encrypt(String message){
        int[] encodedMessage = _encode(message);
        int[] res = new int[encodedMessage.length];
        for (int i = 0; i < encodedMessage.length; i++) {
            res[i] = (encodedMessage[i] + key) % _mod;
        }
        return _decode(res);
    }
    /** decryption function
     *  decyption formula :
     * c = m - k mod(alphabet.length) (when >0)
     * c = (m - k mod(alphabet.length)) + alphabet.length (when <0)
    */ 
    public String decrypt(String message){
        int[] encodedMessage= _encode(message);
        // decyrption formula C = M - k % (alphabet length)
        for (int i = 0; i < encodedMessage.length; i++) {
            encodedMessage[i] = ((encodedMessage[i] - key % _mod) >= 0) ?  (encodedMessage[i] - key) % _mod :((encodedMessage[i] - key) % _mod) + _mod;
        }
        return _decode(encodedMessage);
    }
}