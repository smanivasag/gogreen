<#include "../include/imports.ftl">

<div class="news-overview">
    <#if pageable?? && pageable?has_content>
        <#list pageable.items as item>
            <@hst.link var="newsPageLink" hippobean=item />
            <div class="blog-post has-edit-button">
                <@hst.manageContent hippobean=item />
                <div class="blog-post">
                    <div class="blog-post-type">
                        <i class="icon-news"> </i>
                    </div>
                    <div class="blog-span">
                        <#if item.image?? && item.image.large??>
                            <@hst.link var="imgSrc" hippobean=item.image.large />
                            <div class="blog-post-featured-img">
                                <a href="${newsPageLink}"><img src="${imgSrc}" alt="${item.title?html}" /></a>
                            </div>
                        </#if>
                        <h2>
                            <a href="${newsPageLink}">${item.title?html}</a>
                        </h2>
                        <div class="blog-post-body">
                            <p>${item.introduction}</p>
                        </div>
                        <div class="blog-post-details">
                            <div class="blog-post-details-item blog-post-details-item-left icon-calendar">
                                <#if item.date?? && item.date.time??>
                                    <p><@fmt.formatDate value=item.date.time type="both" dateStyle="medium" timeStyle="short"/></p>
                                </#if>
                            </div>
                            <div class="blog-post-details-item blog-post-details-item-right">
                                <a href="${newsPageLink}">Read more<i class="fa fa-chevron-right"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>

        <#if cparam.showPagination>
            <#include "../include/pagination.ftl">
        </#if>
    <#elseif editMode>
        <div>
            <img src="<@hst.link path='/images/essentials/catalog-component-icons/news-list.png'/>"> Click to edit News List
            <div class="has-new-content-button">
                <@hst.manageContent templateQuery="new-news-document" rootPath="news" defaultPath="${currentYear}/${currentMonth}"/>
            </div>
        </div>
    </#if>
</div>


<div class="col-md-3 col-sm-3">
    <div class="hst-container">
        <div class="hst-container-item">
            <div class="sidebar-block">
                <h3 class="h3-sidebar-title sidebar-title">Category</h3>
                <div class="sidebar-content tags">
                    <a href="news-facet.html">Solar&nbsp;(23)</a> <a href="news-facet.html">Global Warming&nbsp;(13)</a> <a href="news-facet.html">Energy&nbsp;(9)</a> <a href="news-facet.html">Office&nbsp;(4)</a> <a href="news-facet.html">Home&nbsp;(3)</a> <a href="news-facet.html">School&nbsp;(3)</a> <a href="news-facet.html">Family&nbsp;(2)</a> <a href="news-facet.html">Recycling&nbsp;(2)</a> <a href="news-facet.html">Animal &amp; Garden&nbsp;(1)</a> <a href="news-facet.html">Gadgets&nbsp;(1)</a>
                </div>
            </div>
            <div class="sidebar-block">
                <h3 class="h3-sidebar-title sidebar-title">Archive</h3>
                <div class="sidebar-content tags">
                    <a href="news-facet.html">2015&nbsp;(3)</a>
                    <a href="news-facet.html">2014&nbsp;(1)</a>
                </div>
            </div>
        </div>
    </div>
</div>

<#-- @ftlvariable name="item" type="org.example.beans.NewsDocument" -->
<#-- @ftlvariable name="pageable" type="org.onehippo.cms7.essentials.components.paging.Pageable" -->
<#--  <#if pageable?? && pageable.items?has_content>
<div>
  <#list pageable.items as item>
    <@hst.link var="link" hippobean=item />
    <article class="has-edit-button">
      <@hst.manageContent hippobean=item/>
      <h3><a href="${link}">${item.title?html}</a></h3>
      <#if item.date?? && item.date.time??>
        <p><@fmt.formatDate value=item.date.time type="both" dateStyle="medium" timeStyle="short"/></p>
      </#if>
      <p>${item.location?html}</p>
      <p>${item.introduction?html}</p>
    </article>
  </#list>
  <div class="has-new-content-button">
    <@hst.manageContent documentTemplateQuery="new-news-document" folderTemplateQuery="new-news-folder" rootPath="news" defaultPath="${currentYear}/${currentMonth}"/>
  </div>
  <#if cparam.showPagination>
    <#include "../include/pagination.ftl">
  </#if>
</div>  -->
<#-- @ftlvariable name="editMode" type="java.lang.Boolean"-->
<#--  <#elseif editMode>
<div>
  <img src="<@hst.link path='/images/essentials/catalog-component-icons/news-list.svg'/>"> Click to edit News List
  <div class="has-new-content-button">
    <@hst.manageContent documentTemplateQuery="new-news-document" folderTemplateQuery="new-news-folder" rootPath="news" defaultPath="${currentYear}/${currentMonth}"/>
  </div>
</div>
</#if>  -->