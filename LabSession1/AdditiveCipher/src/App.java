
public class App {
    public static void main(String[] args) throws Exception {
      
       AdditiveCipher worker = new AdditiveCipher();
       System.out.println(worker.encrypt("test"));
    }
}
