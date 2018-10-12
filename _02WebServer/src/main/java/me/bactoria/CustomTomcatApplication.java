package me.bactoria;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;


// SpringBoot 를 사용하지 않고 톰캣 실행하기
public class CustomTomcatApplication {
    public static void main(String[] args) throws LifecycleException, IOException {

        // spring boot starter에서 딸려온 Tomcat 사용
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);

        String docBase = Files.createTempDirectory("tomcat-basedir").toString();
        Context context = tomcat.addContext("/", docBase); //Context 등록

        HttpServlet servlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                PrintWriter writer = resp.getWriter();
                writer.println("<html>");
                writer.println("<body><h1>Hello Tomcat</h1></body>");
                writer.println("</html>");
            }
        };

        final String HELLO_SERVLET = "helloServlet";

        tomcat.addServlet("/", HELLO_SERVLET, servlet); //Servlet 등록
        context.addServletMappingDecoded("/hello", HELLO_SERVLET); // "/" 요청이 오면 servletName 을 불러들임

        tomcat.start();
        tomcat.getServer().await(); // 서버 대기 (이 코드 없으면 바로 서버 종료됨)

        // http://localhost:8082/hello 접속 시 "Hello Tomcat" 이 뜬다.


/*     스프링 부트에서는 어떻게 톰캣을 실행시킬까? 위에서 구현한 코드들이 AutoConfiguration으로 짜잔~ 하고 만들어진다.


        스프링부트 App을 실행하면 톰캣이 만들어지고 Servlet이 추가가 되고 WebMvc설정도 되고..

        - External Libraries
        - org.springframework.boot:spring-boot-autoconfigure:2.0.3.RELEASE
        - spring-boot-autoconfigure-2.0.3.RELEASE.jar
        - META-INF
        - spring.factories
          에 @EnableAutoConfiguration 을 할 때 실행시키는 configuration들이 있는데,
          여기서 ServletWebServerFactoryAutoConfiguration 을 보자.

        @import 를 보면,  EmbeddedTomcat, Jetty, Undertow 가 추가되어 온다.
        EmbeddedTomcat의 조건을 보면, Tomcat.class가 있어야하고, 웹서블릿이 없을 경우에 실행시킨다.
        이 경우 Bean으로 TomcatServletWebServerFactory.class 를 등록하게 되는데,
        들어가 보면, Tomcat tomcat = new Tomcat(); 으로 톰캣서버를 생성하는 것을 볼 수 있다.
        connector, context 등 많은 설정을 한다. (안에 소스 파보는거 가성비X)

        나머지 것들도 마찬가지다. 3가지 서버를 모두 찾아보는데,
        첫번째는 해당 서버 클래스가 존재하여야 하고, 서버가 만들어지지 않은 상태여야 한다.
*/

        // https://jojoldu.tistory.com/28
        // https://minwan1.github.io/2017/10/08/2017-10-08-Spring-Container,Servlet-Container/

    }
}
