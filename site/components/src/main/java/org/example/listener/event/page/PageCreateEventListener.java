package org.example.listener.event.page;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.pagecomposer.jaxrs.api.ChannelEventListenerRegistry;
import org.hippoecm.hst.pagecomposer.jaxrs.api.PageCreateEvent;
import org.onehippo.cms7.services.eventbus.Subscribe;

public class PageCreateEventListener {

    public void init() {
        ChannelEventListenerRegistry.get().register(this);
    }

    public void destroy() {
        ChannelEventListenerRegistry.get().unregister(this);
    }

    @Subscribe
    public void onPageEvent(PageCreateEvent event) throws RepositoryException {
        System.out.println("In PageUpdateEvent");
//      if (event.getException() != null) {
            /* Node updatedNode = event.getPageActionContext().getUpdatedPageNode();
            Node siteMapItemNode = event.getPageActionContext().getUpdatedSiteMapItemNode();
            System.out.println("name -> " + updatedNode.getName());
            System.out.println("path -> " + updatedNode.getPath());
            System.out.println("map name -> " + siteMapItemNode.getName());
            System.out.println("map path -> " + siteMapItemNode.getPath()); */
        System.out.println("event.getPageActionContext() -> " + event.getPageActionContext());
        System.out.println("mount -> " + event.getPageActionContext().getEditingMount());
        System.out.println("page node -> " + event.getPageActionContext().getNewPageNode());
        System.out.println("item node -> " + event.getPageActionContext().getNewSiteMapItemNode());
        Node siteMapItemNode = event.getPageActionContext().getNewSiteMapItemNode();

        if (siteMapItemNode !=  null) {

            if (siteMapItemNode.hasProperty("hst:relativecontentpath") && StringUtils.isNotEmpty(siteMapItemNode.getProperty("hst:relativecontentpath").getString())) {
                System.out.println("primary document exists");
            } else {
                System.out.println("primary document doesn't exists");
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