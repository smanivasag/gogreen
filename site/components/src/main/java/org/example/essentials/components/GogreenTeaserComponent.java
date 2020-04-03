package org.example.essentials.components;

import org.example.beans.Imageset;
import org.example.essentials.components.info.GogreenTeaserComponentInfo;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.onehippo.cms7.essentials.components.CommonComponent;

@ParametersInfo(type = GogreenTeaserComponentInfo.class)
public class GogreenTeaserComponent extends CommonComponent {

    @Override
    public void doBeforeRender(final HstRequest request, final HstResponse response) {
        super.doBeforeRender(request, response);

        setComponentId(request, response);

        final GogreenTeaserComponentInfo paramInfo = getComponentParametersInfo(request);
        request.setAttribute(REQUEST_ATTR_PARAM_INFO, paramInfo);

        // getLinkedBean("gogreen:image", Imageset.class);
        System.out.println("paramInfo.getImage() -> " + paramInfo.getImage());
        System.out.println("getHippoBeanForPath(paramInfo.getImage(), HippoDocument.class) -> " + getHippoBeanForPath(paramInfo.getImage(), HippoDocument.class));
        request.setAttribute("imageBean", getHippoBeanForPath(paramInfo.getImage(), Imageset.class));
        // request.setModel("imageBean", getHippoBeanForPath(paramInfo.getImage(), HippoDocument.class));
    }

}
