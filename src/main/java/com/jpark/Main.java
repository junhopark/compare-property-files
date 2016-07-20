package com.jpark;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;

/**
 * This class launches the web application in an embedded Jetty container.
 * This is the entry point to your application. The Java command that is used for
 * launching should fire this main method.
 */
public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception {
    String webPort = System.getenv("PORT");
    if (StringUtils.isBlank(webPort)) {
      webPort = "8080";
    }
    Server server = new Server(NumberUtils.toInt(webPort));
    String warDir = "target/compare-property-files-1.0-SNAPSHOT";

    WebAppContext context = new WebAppContext();
    context.setResourceBase(warDir);
    context.setDescriptor(warDir + "WEB-INF/web.xml");
    context.setConfigurations(new Configuration[]{
        new AnnotationConfiguration(),
        new WebXmlConfiguration(),
        new WebInfConfiguration(),
        new TagLibConfiguration(),
        new PlusConfiguration(),
        new MetaInfConfiguration(),
        new FragmentConfiguration(),
        new EnvConfiguration()});

    // Specify the context path that you want this webapp to show up as
    context.setContextPath("/");
    context.setParentLoaderPriority(true);
    server.setHandler(context);
    server.start();
    server.join();
  }
}
