package org.example.essentials.components;

import org.example.essentials.components.info.GogreenPageComponentInfo;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.cms7.essentials.components.CommonComponent;

//@ParametersInfo(type = GogreenPageComponentInfo.class)
public class GogreenPageComponent extends CommonComponent {

    @Override
    public void doBeforeRender(final HstRequest request, final HstResponse response) {
        super.doBeforeRender(request, response);

        setComponentId(request, response);

        final GogreenPageComponentInfo paramInfo = getComponentParametersInfo(request);
        request.setAttribute(REQUEST_ATTR_PARAM_INFO, paramInfo);

        HstRequestContext requestContext = RequestContextProvider.get();
        request.setModel(REQUEST_ATTR_DOCUMENT, requestContext.getContentBean());

        // getLinkedBean("gogreen:image", Imageset.class);
        // request.setModel("imageBean", getHippoBeanForPath(paramInfo.getImage(), HippoDocument.class));
    }

}
