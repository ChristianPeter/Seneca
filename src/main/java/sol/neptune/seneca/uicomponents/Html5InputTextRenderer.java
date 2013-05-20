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

    private static final Attribute[] INPUT_ATTRIBUTES =
            AttributeManager.getAttributes(AttributeManager.Key.INPUTTEXT);

    @Override
    protected void getEndTextToRender(FacesContext context, UIComponent component, String currentValue) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("input", component);
        writeIdAttributeIfNecessary(context, writer, component);
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("name", (component.getClientId(context)),
                "clientId");

        String styleClass = (String) component.getAttributes().get("styleClass");

        // Render styleClass, if any.
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, "styleClass");
        }

        String placeholder = ((Html5InputText) component).getPlaceholder();
        if (placeholder != null) {
            writer.writeAttribute("placeholder", placeholder, "placeholder");
        }


        // only output the autocomplete attribute if the value
        // is 'off' since its lack of presence will be interpreted
        // as 'on' by the browser
        if ("off".equals(component.getAttributes().get("autocomplete"))) {
            writer.writeAttribute("autocomplete",
                    "off",
                    "autocomplete");
        }

        // render default text specified
        if (currentValue != null) {
            writer.writeAttribute("value", currentValue, "value");
        }
        if (null != styleClass) {
            writer.writeAttribute("class", styleClass, "styleClass");
        }

        // style is rendered as a passthru attribute
        RenderKitUtils.renderPassThruAttributes(context,
                writer,
                component,
                INPUT_ATTRIBUTES,
                getNonOnChangeBehaviors(component));
        RenderKitUtils.renderXHTMLStyleBooleanAttributes(writer, component);

        RenderKitUtils.renderOnchange(context, component, false);


        writer.endElement("input");
    }
}
