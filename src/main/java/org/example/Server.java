package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static spark.Spark.*;

public class Server {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://ec2-3-93-63-65.compute-1.amazonaws.com:4568/collatzsequence";
    public static void main(String[] args) {
        port(getPort());
        get("/",(req,res)->{
            return response();
        });

        get("/collatzsequence",(req,res)->{
            String secuence = req.queryParams("value");
//            return HttpConnectionExample(GET_URL+"?value="+secuence,secuence);
            return "{\n" +
                    "\n" +
                    " \"operation\": \"collatzsequence\",\n" +
                    "\n" +
                    " \"input\":  "+secuence+",\n" +
                    "\n" +
                    " \"output\":  \""+collatzSequence.createSecuence(Integer.parseInt(secuence)) +"\"\n" +
                    "\n" +
                    "}";
        });
    }

//    private static String HttpConnectionExample(String url,String secuence) throws IOException {
//
//            URL obj = new URL(url);
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("User-Agent", USER_AGENT);
//
//            //The following invocation perform the connection implicitly before getting the code
//            int responseCode = con.getResponseCode();
//            System.out.println("GET Response Code :: " + responseCode);
//
//            if (responseCode == HttpURLConnection.HTTP_OK) { // success
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        con.getInputStream()));
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//
//                // print result
//                System.out.println(response.toString());
//                return "{\n" +
//                        "\n" +
//                        " \"operation\": \"collatzsequence\",\n" +
//                        "\n" +
//                        " \"input\":  "+secuence+",\n" +
//                        "\n" +
//                        " \"output\":  \""+collatzSequence.createSecuence(Integer.parseInt(secuence)) +"\"\n" +
//                        "\n" +
//                        "}";
//            } else {
//                System.out.println("GET request not worked");
//                return "fallo";
//            }
//    }

    private static String response(){
        return "<html>\n" +
                "    <head>\n" +
                "        <title>Form Example</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Form with GET</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/collatzsequence?value=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script> </body>\n" +
                "</html>";
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }
}
