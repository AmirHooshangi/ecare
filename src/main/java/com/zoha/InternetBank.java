package com.zoha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.*;
import java.nio.file.Paths;

/**
 * @author amirhoshangi@gmail.com
 */
@SpringBootApplication
@ComponentScan("com.zoha")
@PropertySource(value = "classpath:testdata.properties", ignoreResourceNotFound=false)
public class InternetBank {
    private static final Logger logger = Logger.getLogger(InternetBank.class);

    public static void main(String[] args) {
      //PrepareElasticSearchDataBase.deletAllIndexes();
      createAndCopyRubyfile();
      DynamicRubyMessageParser.reloadFile();

      SpringApplication.run(new Object[]{InternetBank.class}, args);
      PrepareElasticSearchDataBase.prepareIndexes();
      //System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
      Thread thread = new Thread(new FileSystemChangeHandler(Paths.get(System.getProperty("user.home") + "/ruby")));
      thread.start();
      System.out.println("FileChange listener registered");
      System.out.println("Let's enjoy the internet bank");
    }

  static {
  //TODO: in prodcution should be changed
    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
      new javax.net.ssl.HostnameVerifier(){
        public boolean verify(String hostname,
                              javax.net.ssl.SSLSession sslSession) {
          return true;
        }
      });
  }

  private static void createAndCopyRubyfile() {
    String path = System.getProperty("user.home") + "/ruby";
    File rubyDirectory = new File(path);
    rubyDirectory.mkdir();
      try {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(path+"/ErrorMessage.rb"));
        InputStream inputStream = InternetBank.class.getClassLoader().getResourceAsStream("ErrorMessage.rb");
        IOUtils.copy(inputStream, fileOutputStream);
      } catch (FileNotFoundException e) {
        logger.error(e).log();
      } catch (IOException e) {
        logger.error(e).log();
      }
    }
}

