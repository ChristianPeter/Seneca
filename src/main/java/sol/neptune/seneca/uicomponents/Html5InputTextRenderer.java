/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.uicomponents;

import com.sun.faces.renderkit.Attribute;
import com.sun.faces.renderkit.AttributeManager;
import com.sun.faces.renderkit.RenderKitUtils;
import com.sun.faces.renderkit.html_basic.TextRenderer;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

/**
 *
 * @author murdoc
 */
@FacesRenderer(componentFamily = "javax.faces.Input", rendererType = "javax.faces.Text5")
public class Html5InputTextRenderer extends TextRenderer {

    private static final String EMPTY_STRING = "";
    private static final Attribute[] INPUT_ATTRIBUTES =
            AttributeManager.getAttributes(AttributeManager.Key.INPUTTEXT);

    @Override
    protected void getEndTextToRender(FacesContext context, UIComponent component, String currentValue) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("input", component);
        writeIdAttributeIfNecessary(context, writer, component);
        writer.writeAttribute("name", component.getClientId(context), "name");


        // Render styleClass, if any.
        String styleClass = (String) component.getAttributes().get("styleClass");
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, "styleClass");
        }

        String placeholder = ((Html5InputText) component).getPlaceholder();
        if (placeholder != null) {
            writer.writeAttribute("placeholder", placeholder, "placeholder");
        }

        // Render standard HTMLattributes expect of styleClass.
        RenderKitUtils.renderPassThruAttributes(
                context, writer, component, INPUT_ATTRIBUTES, getNonOnChangeBehaviors(component));
        RenderKitUtils.renderXHTMLStyleBooleanAttributes(writer, component);
        RenderKitUtils.renderOnchange(context, component, false);

        writer.writeAttribute("value", ((Html5InputText) component).getValue(), "value");
        writer.endElement("input");
    }
}
