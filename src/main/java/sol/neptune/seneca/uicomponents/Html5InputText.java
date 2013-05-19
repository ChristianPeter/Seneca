/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.uicomponents;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

/**
 *
 * @author murdoc
 */
@FacesComponent(value = "Html5InputText")
public class Html5InputText extends HtmlInputText {

    @Override
    public String getRendererType() {
        return "javax.faces.Text5";
    }
    private String placeholder;

    public String getPlaceholder() {
        if (this.placeholder != null) {
            return this.placeholder;
        }
        ValueExpression ve = getValueExpression("placeholder");
        if (ve != null) {
            String value = (String) ve.getValue(FacesContext.getCurrentInstance().getELContext());
            return value;

        }
        return null;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public Object saveState(FacesContext context) {
        Object[] states = new Object[2];
        states[0] = super.saveState(context);
        states[1] = placeholder;
        return states;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object[] states = (Object[]) state;
        super.restoreState(context, states[0]); //To change body of generated methods, choose Tools | Templates.
        placeholder = (String) states[1];
    }
}
