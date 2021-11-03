public class Vigenere {
    // Ci = (Pi + k[i % k.length]) % 26;

    private String alphabet;
    private String key;
    //mod is the length of the alphabet
    private  final int _mod;

    Vigenere(String key){
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.key = key;
        this._mod = alphabet.length() - 1;
        System.err.println("Created a Vigenere ecnryptor with values: ");
        System.out.println("Alhpabet= " + this.alphabet);
        System.out.println("key= " + this.key);
        System.out.println("alphabet length (mod)= " + this._mod + "\n \n");

    }
    
    Vigenere(String alphabet, String key){
        this.alphabet = alphabet;
        this.key = key;
        this._mod = alphabet.length() - 1;
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

    //viginere encryption function
    public String encrypt(String message){
        // int[] encodedMessage = _encode(message);
        // int[] encodedKey = _encode(key);
        // int[] res = new int[encodedMessage.length];
        // for (int i = 0; i < encodedMessage.length; i++) {
        //     res[i] = (encodedMessage[i] + encodedKey[i % encodedKey.length]) % _mod;
        // }
        int[] encodedMessage = _encode(message);
        int[] encodedKey = _encode(key);
        int[] res = new int [encodedMessage.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = (encodedMessage[i] + encodedKey[i % encodedKey.length]) % _mod;
        }
        return _decode(res);
    }

    /** decryption function
      Pi = (Ci + k[i % k.length]) % 26;
    */ 
    public String decrypt(String message){
        int[] encodedKey = _encode(key);
        int[] encodedMessage= _encode(message);
        for (int i = 0; i < encodedMessage.length; i++) {
            encodedMessage[i] = (  ((encodedMessage[i] - encodedKey[ i % encodedKey.length])   % _mod) >= 0) ?
             (encodedMessage[i] - encodedKey[ i % encodedKey.length] ) % _mod
             : (encodedMessage[i] - encodedKey[ i % encodedKey.length] ) % _mod + _mod;
        }
        return _decode(encodedMessage);
    }
}
