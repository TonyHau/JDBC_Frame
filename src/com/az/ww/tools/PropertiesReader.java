package com.az.ww.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 获取属性文件信息
 * @author yuandl
 *
 */
public class PropertiesReader extends Properties
{
  private static final long serialVersionUID = 1L;
  private Properties pro;

  public String get(String key)
  {
    this.pro = new Properties();
    InputStream is = getClass().getResourceAsStream("/cofing/jdbc-mysql.properties");
//    InputStream is = getClass().getResourceAsStream("/cofing/jdbc-oracle.properties");
    try {
      this.pro.load(is);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return this.pro.getProperty(key);
  }
}

