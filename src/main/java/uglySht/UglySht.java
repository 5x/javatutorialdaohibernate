package uglySht;

public class UglySht {
    public static String getHeadTemplatePart(String title) {
        return String.format("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <!-- Required meta tags -->\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "\n" +
                "    <!-- Bootstrap CSS -->\n" +
                "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n" +
                "\n" +
                "    <title>%1$s</title>\n" +
                "  </head>\n" +
                "  <body>" +
                "<div class=\"container\">" +
                "<div class=\"row\"><div class=\"col\">" +
                "<ul class=\"nav my-4 justify-content-center\">\n" +
                "  <li class=\"nav-item\">\n" +
                "    <a class=\"nav-link\" href=\"\\cities\\\">Cities</a>\n" +
                "  </li>\n" +
                "  <li class=\"nav-item\">\n" +
                "    <a class=\"nav-link\" href=\"\\info\\\">EnvironmentInfo</a>\n" +
                "  </li>\n" +
                "</ul>\n" +
                "<h1>%1$s</h1>", title);
    }

    public static String getFooterTemplatePart() {
        return "</div></div></div></body></html>";
    }
}
