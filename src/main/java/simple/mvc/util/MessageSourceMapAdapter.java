package simple.mvc.util;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceMapAdapter implements Map<String, String>  {

    @Autowired
    private MessageSource messageSource;

    public String get(Object key) {
      Locale locale = LocaleContextHolder.getLocale();
      String message = messageSource.getMessage(String.valueOf(key), null, locale);
      return StringEscapeUtils.escapeXml(message);
    }
     
    public boolean containsKey(Object key) {
      return true;
    }
     
    public boolean isEmpty() {
      return false;
    }
     
    public int size() {
      throw new UnsupportedOperationException();
    }
     
    public Set<String> keySet() {
      throw new UnsupportedOperationException();
    }
     
    public Collection<String> values() {
      throw new UnsupportedOperationException();
    }
     
    public Set<Entry<String, String>> entrySet() {
      throw new UnsupportedOperationException();
    }
     
    public boolean containsValue(Object value) {
      throw new UnsupportedOperationException();
    }
     
    public String put(String key, String value) {
      throw new UnsupportedOperationException();
    }
     
    public void putAll(Map<? extends String, ? extends String> m) {
      throw new UnsupportedOperationException();
    }
     
    public String remove(Object key) {
      throw new UnsupportedOperationException();
    }
     
    public void clear() {
      throw new UnsupportedOperationException();
    }
}
