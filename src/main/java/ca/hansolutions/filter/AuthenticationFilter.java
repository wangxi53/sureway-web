package ca.hansolutions.filter;

import ca.hansolutions.model.Admin;
import ca.hansolutions.model.Constants;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoleiwang on 2016-11-12.
 */
public class AuthenticationFilter implements Filter {
    static Map<String, Admin> adminMap;

    public static void addAuthenticatedUser(String email, Admin admin){
        adminMap.put(email, admin);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query(Admin.KIND);
        PreparedQuery preparedQuery = datastoreService.prepare(query);

        adminMap = new HashMap<>();
        adminMap.put("tsuxia@gmail.com", new Admin("Nate", "Qu", "tsuxia@gmail.com"));
        adminMap.put("hanson.hao.wu@gmail.com",new Admin("Hao", "Wu", "hanson.hao.wu@gmail.com"));
        adminMap.put("xlei.wangxi53@gmail.com",new Admin("Alex", "Wang", "xlei.wangxi53@gmail.com"));

        for(Entity entity : preparedQuery.asIterable()){

            String firstName = entity.getProperty(Constants.FIRST_NAME).toString();
            String lastName = entity.getProperty(Constants.LAST_NAME).toString();
            String email = entity.getProperty(Constants.EMAIL).toString();
            adminMap.put(email, new Admin(firstName, lastName, email));
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest)servletRequest;
        UserService userService = UserServiceFactory.getUserService();

        String path = request.getRequestURI();
        if(path.startsWith("/_ah/log") || path.startsWith("/_ah/admin")){

            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if(request.getUserPrincipal() != null){

            String email = request.getUserPrincipal().getName().toLowerCase();
            boolean authenticated = authenticate(email);
            if(authenticated){
                String logoutUrl = userService.createLogoutURL(request.getRequestURI());
                Admin admin = adminMap.get(email);
                servletRequest.setAttribute("logoutUrl", logoutUrl);
                servletRequest.setAttribute("adminFirstName", admin.getFirstName());
                servletRequest.setAttribute("email", admin.getEmail());
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                String errorMessage = "Sorry, [" + email + "] is not an authenticated user of Water Q";
                String logoutUrl = userService.createLogoutURL(request.getRequestURI());
                servletRequest.setAttribute("logout", logoutUrl);
                servletRequest.setAttribute("errorMessage", errorMessage);
                servletRequest.getRequestDispatcher("/WEB-INF/views/loginPage.jsp").forward(servletRequest, servletResponse);
            }
        }
        else {
            String loginUrl = userService.createLoginURL(request.getRequestURI());
            servletRequest.setAttribute("loginUrl", loginUrl);
            servletRequest.getRequestDispatcher("/WEB-INF/views/loginPage.jsp").forward(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }

    private boolean authenticate(String email) {

        return adminMap.containsKey(email);
    }
}
