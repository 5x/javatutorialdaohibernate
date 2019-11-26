package servlets;

import uglySht.UglySht;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.descriptor.TaglibDescriptor;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(name = "EnvironmentInfoServlet", urlPatterns = {"/info/"})
public class EnvironmentInfoServlet extends HttpServlet {
    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        this.servletConfig = config;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = new PrintWriter(response.getWriter());
        response.setContentType("text/html");

        String pageTitle = "EnvironmentInfo";
        out.print(UglySht.getHeadTemplatePart(pageTitle));
        out.print("<pre>");

        ServletConfig config = this.servletConfig;

        Enumeration<String> e = config.getInitParameterNames();
        String message;
        String parameterName;
        String parameterValue;

        while (e.hasMoreElements()) {
            parameterName = e.nextElement();
            parameterValue = config.getInitParameter(parameterName);
            message = String.format("InitParameter[%s]: %s", parameterName, parameterValue);
            out.println(message);
        }

        String servletName = config.getServletName();
        String servletNameRepr = String.format("Servlet name: %s", servletName);
        out.println(servletNameRepr);

        ServletContext context = config.getServletContext();

        String orderedLibs = ServletContext.ORDERED_LIBS;
        String orderedLibsRepr = String.format(
                "[Ordered libs] The name of the ServletContext attribute whose value contains the list of names " +
                        "of JAR files in WEB-INF/lib ordered by their web fragment names: %s",
                orderedLibs);
        out.println(orderedLibsRepr);

        String tempDir = ServletContext.TEMPDIR;
        String tempDirRepr = String.format(
                "[TempDir] The name of the ServletContext attribute which stores the private temporary directory " +
                        "(of type java.io.File) provided by the servlet container for the ServletContext: %s",
                tempDir);
        out.println(tempDirRepr);


        Enumeration<String> attributeNames = context.getAttributeNames();

        String attributeKey;
        Object attributeValue;

        while (e.hasMoreElements()) {
            attributeKey = attributeNames.nextElement();
            attributeValue = context.getAttribute(attributeKey);
            message = String.format("InitParameter[%s]: %s", attributeKey, attributeValue);
            out.println(message);
        }

        ClassLoader classLoader = context.getClassLoader();
        String classLoaderRepr = String.format(
                "[ClassLoader] Gets the class loader of the web application represented by this ServletContext: %s",
                classLoader);
        out.println(classLoaderRepr);

        String contextPath = context.getContextPath();
        String contextPathRepr = String.format("[ContextPath] Returns the context path of the web application: %s",
                contextPath);
        out.println(contextPathRepr);

        int effectiveMajorVersion = context.getEffectiveMajorVersion();
        String effectiveMajorVersionRepr = String.format(
                "Gets the major version of the Servlet specification that the application represented by this " +
                        "ServletContext is based on: %s",
                effectiveMajorVersion);
        out.println(effectiveMajorVersionRepr);

        int effectiveMinorVersion = context.getEffectiveMinorVersion();
        String effectiveMinorVersionRepr = String.format(
                "Gets the minor version of the Servlet specification that the application represented by this " +
                        "ServletContext is based on: %s",
                effectiveMinorVersion);
        out.println(effectiveMinorVersionRepr);

        Map<String, ? extends FilterRegistration> filterRegistrations = context.getFilterRegistrations();
        for (Map.Entry<String, ? extends FilterRegistration> entry : filterRegistrations.entrySet()) {
            message = String.format("filterRegistrations[%s]: %s", entry.getKey(), entry.getValue());
            out.println(message);
        }

        JspConfigDescriptor jspConfigDescriptor = context.getJspConfigDescriptor();
        if (jspConfigDescriptor != null) {
            Collection<JspPropertyGroupDescriptor> jspPropertyGroups = jspConfigDescriptor.getJspPropertyGroups();
            String jspPropertyGroupsRepr = Arrays.toString(jspPropertyGroups.toArray());
            out.println(jspPropertyGroupsRepr);

            Collection<TaglibDescriptor> tagLibs = jspConfigDescriptor.getTaglibs();
            String tagLibsRepr = Arrays.toString(tagLibs.toArray());
            out.println(tagLibsRepr);
        }

        int majorVersion = context.getMajorVersion();
        String majorVersionRepr = String.format(
                "Returns the major version of the Servlet API that this servlet container supports.: %s",
                majorVersion);
        out.println(majorVersionRepr);

        int minorVersion = context.getMajorVersion();
        String minorVersionRepr = String.format(
                "Returns the minor version of the Servlet API that this servlet container supports.: %s",
                minorVersion);
        out.println(minorVersionRepr);


        String serverInfo = context.getServerInfo();
        String serverInfoRepr = String.format(
                "Returns the name and version of the servlet container on which the servlet is running.: %s",
                serverInfo);
        out.println(serverInfoRepr);

        String servletContextName = context.getServletContextName();
        String servletContextNameRepr = String.format(
                "Returns the name and version of the servlet container on which the servlet is running.: %s",
                servletContextName);
        out.println(servletContextNameRepr);


        out.print("</pre>");
        out.print(UglySht.getFooterTemplatePart());
    }
}
