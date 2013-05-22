/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.i18n;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 *
 * @author murdoc
 */
@Named
@SessionScoped
public class LocaleManager
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Locale locale;

    public Locale getLocale() {
        
        System.out.println("locale1: " + locale);
        if (locale == null) {
            locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        }
        System.out.println("locale2: " + locale);
        if (locale == null) {
            locale = Locale.GERMAN;
        }
        
        
        System.out.println("locale3: " + locale);
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLanguage() {
        if (locale == null) {
            return Locale.GERMAN.getLanguage();
        }
        return locale.getDisplayLanguage(locale);
    }

    public void setLanguage(String language) {
        System.out.println(language);
        Locale newLocale = getSupportedLocales().get(language);
        System.out.println(newLocale);
        setLocale(newLocale);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(newLocale);
    }
    private final static Map<String, Locale> supportedLocales;

    static {
        supportedLocales = new HashMap<String, Locale>();
        Locale de = Locale.GERMAN;
        Locale en = Locale.ENGLISH;

        supportedLocales.put(de.getDisplayLanguage(de), de);
        supportedLocales.put(en.getDisplayLanguage(en), en);

    }

    public Map<String, Locale> getSupportedLocales() {
        return LocaleManager.supportedLocales;
    }
   
    
    public Set<String> getLocalesAsString(){
        return getSupportedLocales().keySet();
    }
}
