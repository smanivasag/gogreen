package org.example.listener.event.page;

import java.util.Map.Entry;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.configuration.components.HstComponentConfiguration;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.configuration.sitemap.HstSiteMapItem;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.pagecomposer.jaxrs.api.ChannelEventListenerRegistry;
import org.hippoecm.hst.pagecomposer.jaxrs.api.PageUpdateEvent;
import org.onehippo.cms7.services.eventbus.Subscribe;

public class PageUpdateEventListener {

    public void init() {
        ChannelEventListenerRegistry.get().register(this);
    }

    public void destroy() {
        ChannelEventListenerRegistry.get().unregister(this);
    }

    @Subscribe
    public void onPageEvent(PageUpdateEvent event) throws RepositoryException {
        System.out.println("In PageUpdateEvent");
//      if (event.getException() != null) {
            /* Node updatedNode = event.getPageActionContext().getUpdatedPageNode();
            Node siteMapItemNode = event.getPageActionContext().getUpdatedSiteMapItemNode();
            System.out.println("name -> " + updatedNode.getName());
            System.out.println("path -> " + updatedNode.getPath());
            System.out.println("map name -> " + siteMapItemNode.getName());
            System.out.println("map path -> " + siteMapItemNode.getPath()); */
        System.out.println("event.getPageActionContext() -> " + event.getPageActionContext());
        System.out.println("node -> " + event.getPageActionContext().getUpdatedPageNode());
        System.out.println("map node -> " + event.getPageActionContext().getUpdatedSiteMapItemNode());
        Node siteMapItemNode = event.getPageActionContext().getUpdatedSiteMapItemNode();


        if (siteMapItemNode !=  null) {
            if (siteMapItemNode.hasProperty("hst:relativecontentpath") && StringUtils.isNotEmpty(siteMapItemNode.getProperty("hst:relativecontentpath").getString())) {
                System.out.println("primary document exists");
            } else {
                System.out.println("primary document doesn't exists");

                Mount mount = event.getPageActionContext().getEditingMount();
                /* System.out.println("mount -> " + mount);

                System.out.println("getSiteMapItems -> " + mount.getHstSite().getSiteMap().getSiteMapItems());

                for (HstSiteMapItem item : mount.getHstSite().getSiteMap().getSiteMapItems()) {
                    System.out.println("getRefId -> " + item.getRefId() + ", getValue -> " + item.getValue() + ", getId -> " + item.getId());
                }

                System.out.println("siteMapItemNode.getIdentifier() -> " + siteMapItemNode.getIdentifier());
                System.out.println("siteMapItemNode.getName() -> " + siteMapItemNode.getName()); */
                HstSiteMapItem hstSiteMapItem = mount.getHstSite().getSiteMap().getSiteMapItem(siteMapItemNode.getName());
                System.out.println("hstSiteMapItem.getComponentConfigurationId() -> " + hstSiteMapItem.getComponentConfigurationId());
                HstComponentConfiguration hstComponentConfiguration = mount.getHstSite().getComponentsConfiguration().getComponentConfiguration(hstSiteMapItem.getComponentConfigurationId());
                System.out.println("hstComponentConfiguration -> " + hstComponentConfiguration);
                System.out.println("getReferenceName -> " + hstComponentConfiguration.getReferenceName());
                System.out.println("getComponentType -> " + hstComponentConfiguration.getComponentType().name());
                if (hstComponentConfiguration.getChildren() != null) {
                    for (Entry<String, HstComponentConfiguration> component : hstComponentConfiguration.getChildren().entrySet()) {
                        System.out.println("key -> " + component.getKey() + ", value -> " + component.getValue());
                        HstComponentConfiguration childComponentConfig = component.getValue();

                        if ((hstComponentConfiguration.getId() + "/main").equals(childComponentConfig.getId()) &&
                            HstComponentConfiguration.Type.COMPONENT.equals(childComponentConfig.getComponentType())) {
                            // Now, lookup for container components
                            for (Entry<String, HstComponentConfiguration> grandChildComponent :  childComponentConfig.getChildren().entrySet()) {
                                if (HstComponentConfiguration.Type.CONTAINER_COMPONENT.equals(grandChildComponent.getValue().getComponentType())) {

                                    for (Entry<String, HstComponentConfiguration> containerItemComponent :  grandChildComponent.getValue().getChildren().entrySet()) {
                                        if (HstComponentConfiguration.Type.CONTAINER_ITEM_COMPONENT.equals(containerItemComponent.getValue().getComponentType())) {
                                            System.out.println("Found CONTAINER_ITEM_COMPONENT -> " + containerItemComponent.getValue());

                                            HstComponentConfiguration containerComponentConfig = containerItemComponent.getValue();
                                            final String documentPath = containerComponentConfig.getParameter("document");
                                            System.out.println("documentPath -> " + documentPath);

                                            if (StringUtils.isNotEmpty(documentPath)) {
                                                // final HippoBean rootBean = event.getPageActionContext().getRequestContext().getSiteContentBaseBean();
                                                HippoBean contentBean = null;

                                                try {
                                                    contentBean = (HippoBean) event.getPageActionContext().getRequestContext().getObjectBeanManager().getObject(mount.getContentPath() + "/" + documentPath);
                                                    System.out.println("contentBean -> " + contentBean);

                                                    if (contentBean != null) {
                                                        System.out.println("isHippoFolderBean -> " + contentBean.isHippoFolderBean());
                                                        System.out.println("isHippoDocumentBean -> " + contentBean.isHippoDocumentBean());
                                                        System.out.println("getProperties -> " + contentBean.getProperties());
                                                        System.out.println("Primary type -> " + contentBean.getNode().getPrimaryNodeType());
                                                        System.out.println("Primary type name -> " + contentBean.getNode().getPrimaryNodeType().getName());

                                                        if ("gogreen:contentdocument".equals(contentBean.getNode().getPrimaryNodeType().getName())) {
                                                            System.out.println("Done with setting hst:relativecontentpath to " + documentPath);
                                                            siteMapItemNode.setProperty("hst:relativecontentpath", documentPath);
                                                        }
                                                    }
                                                } catch (IllegalStateException | ObjectBeanManagerException e) {
                                                    System.out.println("Error in getting the bean for the document path -> " + documentPath);
                                                    e.printStackTrace();
                                                }
                                                // System.out.println("root -> " + rootBean);
                                                // HippoBean contentBean = rootBean.getBean(documentPath);
                                            }

                                        }
                                    }

                                }
                            }
                        }


                        /* if (HstComponentConfiguration.Type.CONTAINER_ITEM_COMPONENT.equals(entry.getValue().getComponentType())) {
                            System.out.println("Found CONTAINER_ITEM_COMPONENT");
                            System.out.println("getParameters -> " + entry.getValue().getParameters());
                            System.out.println("document -> " + entry.getValue().getParameter("document"));
                            final String documentPath = entry.getValue().getParameter("document");
                            System.out.println("entry.getValue().getRenderPath() -> " + entry.getValue().getRenderPath());

                            final HstRequestContext context = event.getPageActionContext().getRequestContext();
                            if (StringUtils.isNotEmpty(documentPath)) {
                                final HippoBean root = context.getSiteContentBaseBean();
                                HippoBean contentBean = root.getBean(documentPath);
                                System.out.println("contentBean -> " + contentBean);
                                System.out.println("isHippoFolderBean -> " + contentBean.isHippoFolderBean());
                                System.out.println("isHippoDocumentBean -> " + contentBean.isHippoDocumentBean());
                                System.out.println("getProperties -> " + contentBean.getProperties());
                            }
                        } */
                    }
                }
            }
        }

        /* System.out.println("getResolvedSiteMapItem -> " + event.getPageActionContext().getRequestContext().getResolvedSiteMapItem());
        System.out.println("getResolvedSiteMapItem -> " + event.getPageActionContext().getRequestContext().getResolvedSiteMapItem().getRelativeContentPath());
        System.out.println("getHstComponentConfiguration -> " + event.getPageActionContext().getRequestContext().getResolvedSiteMapItem().getHstComponentConfiguration());
        System.out.println("getHstComponentConfiguration -> " + event.getPageActionContext().getRequestContext().getResolvedSiteMapItem().getHstSiteMapItem()); */
        return;
//      }
    }
}