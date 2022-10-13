import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> strs = new ArrayList<>();

    public String handleRequest(URI url) {
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")){
                    strs.add(parameters[1]);
                    return String.format("Added " + parameters[1]);
                }
            }
            else if (url.getPath().contains("/search")){
                String[] parameters = url.getQuery().split("=");
                int count = 0;
                String temp = parameters[1];
                String ans = "";
                for (String s : strs){
                    if (s.contains(temp)){
                        ans = ans + s + "\n";
                        }
                    }
                return String.format(ans);
                }
            return "404 Not Found!";
            }


    class SearchEngine {
        public static void main(String[] args) throws IOException {
            if(args.length == 0){
                System.out.println("Missing port number! Try any number between 1024 to 49151");
                return;
                }

            int port = Integer.parseInt(args[0]);

            Server.start(port, new Handler());
        }
    }

}